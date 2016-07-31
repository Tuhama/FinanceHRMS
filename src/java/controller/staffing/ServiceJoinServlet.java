/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;

import entity.EmpServicejoin;
import entity.Employee;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EmpServicejoinFacade;
import session.EmployeeFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "ServiceJoinServlet", urlPatterns = {"/addServiceJoin","/editServiceJoin","/deleteServiceJoin"})
public class ServiceJoinServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;
    
    private Employee employee = new Employee();
    
    private final SimpleDateFormat vSDF2 = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");
        @EJB
    private EmployeeFacade employeeFacade;
        @EJB
    private EmpServicejoinFacade empServicejoinFacade;
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        employee=(Employee)getServletContext().getAttribute("current_employee");
      String userPath = request.getServletPath();

        try {
            switch (userPath) {

                case "/addServiceJoin": {
                    insertServiceJoin(request, response);
                    break;
                }
                case "/editServiceJoin": {
                    //insertServiceJoin(request);
                    break;
                }
                case "/deleteServiceJoin": {
                   deleteServiceJoin(request,response);
                    break;
                }
            }

            //response.sendRedirect(url);
            //} catch (NumberFormatException | ParseException | IOException ex) {
        } catch (NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }
    }

 private void insertServiceJoin(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ParseException {

        EmpServicejoin empServiceJ = new EmpServicejoin();
        empServiceJ.setDaysduration(Short.parseShort(request.getParameter("daysduration")));
        empServiceJ.setMonthsduration(Short.parseShort(request.getParameter("monthsduration")));
        empServiceJ.setPlaceofservice(request.getParameter("placeofservice"));
        empServiceJ.setDocdate(vSDF.parse(request.getParameter("docdate")));
        empServiceJ.setDocnumber(request.getParameter("docnumber"));
        empServiceJ.setDoctype(request.getParameter("doctype"));
        employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empServiceJ.setEmployeeId(employee);
        empServicejoinFacade.create(empServiceJ);
        try {

            String json = service2json(empServiceJ);
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(json);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

    }
    /**
     * @return the op_mode
     */
    public static int getOp_mode() {
        return op_mode;
    }

    /**
     * @param mode the op_mode to set
     */
    public static void setOp_mode(int mode) {
        op_mode = mode;
    }

    private void deleteServiceJoin(HttpServletRequest request, HttpServletResponse response) {

        EmpServicejoin empServiceJ = empServicejoinFacade.find(Integer.parseInt(request.getParameter("id")));
        empServicejoinFacade.remove(empServiceJ);
    }   
    
    
    private String service2json(EmpServicejoin empServiceJ) {
 String s = "{";

        s += "\"" + "col1" + "\"" + ":" + "\"" + empServiceJ.getPlaceofservice() + "\"";
        s += ",";
        s += "\"" + "col2" + "\"" + ":" + "\"" + empServiceJ.getDaysduration()+ "\"";
        s += ",";
        s += "\"" + "col3" + "\"" + ":" + "\"" + empServiceJ.getMonthsduration() + "\"";
        s += ",";
        s += "\"" + "col4" + "\"" + ":" + "\"" + " " + "\"";
        s += ",";
        s += "\"" + "col5" + "\"" + ":" + "\"" + " " + "\"";
        s += ",";
        s += "\"" + "col6" + "\"" + ":" + "\"" + empServiceJ.getDoctype() + "\"";
        s += ",";
        s += "\"" + "col7" + "\"" + ":" + "\"" + empServiceJ.getDocnumber() + "\"";
        s += ",";
        s += "\"" + "col8" + "\"" + ":" + "\"" + vSDF2.format(empServiceJ.getDocdate()) + "\"";
        s += ",";
        s += "\"" + "row_d" + "\"" + ":" + "\"" + "<input type='button' value='حذف' name='delete_b' onclick='show_delete_dialog_service(" + empServiceJ.getId() + ")'/>" + "\"";
        s += "}";

        return s;    }

}
