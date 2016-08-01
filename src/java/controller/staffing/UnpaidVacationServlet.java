/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;

import entity.EmpUnpaidvacation;
import entity.Employee;
import entity.Typeunpaidvacation;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EmpUnpaidvacationFacade;
import session.EmployeeFacade;
import session.TypeunpaidvacationFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "UnpaidVacationServlet", urlPatterns = {"/addUnpaidV", "/editUnpaidV", "/deleteUnpaidV"})
public class UnpaidVacationServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;

    private Employee employee = new Employee();

    private final SimpleDateFormat vSDF2 = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");

    @EJB
    private EmpUnpaidvacationFacade empUnpaidvacationFacade;
    @EJB
    private TypeunpaidvacationFacade typeUnpaidVacationFacade;
    @EJB
    private EmployeeFacade employeeFacade;

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
        employee = (Employee) getServletContext().getAttribute("current_employee");
        String userPath = request.getServletPath();

        try {
            switch (userPath) {

                case "/addUnpaidV": {
                    insertUnpaidVacation(request, response);
                    break;
                }
                case "/editUnpaidV": {
                    //insertUnpaidVacation(request);
                    break;
                }
                case "/deleteUnpaidV": {
                    deleteUnpaidVacation(request, response);
                    break;
                }
            }

            //response.sendRedirect(url);
            //} catch (NumberFormatException | ParseException | IOException ex) {
        } catch (NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void insertUnpaidVacation(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ParseException {

        EmpUnpaidvacation empUnpaidV = new EmpUnpaidvacation();
        Typeunpaidvacation v_type = typeUnpaidVacationFacade.find(Short.parseShort(request.getParameter("typeunpaidvacation_id")));
        empUnpaidV.setTypeunpaidvacationId(v_type);
        //checkbox value isn't sent when the checkbox is not checked
        if (request.getParameter("folding") != null) {
            empUnpaidV.setFolding(true);
        } else {
            empUnpaidV.setFolding(false);
        }
        empUnpaidV.setReason(request.getParameter("reason"));
        empUnpaidV.setStartdate(vSDF.parse(request.getParameter("startdate")));
        empUnpaidV.setEnddate(vSDF.parse(request.getParameter("enddate")));
        empUnpaidV.setDocdate(vSDF.parse(request.getParameter("docdate")));
        empUnpaidV.setDocnumber(request.getParameter("docnumber"));
        empUnpaidV.setDoctype(request.getParameter("doctype"));
        employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empUnpaidV.setEmployeeId(employee);
        empUnpaidvacationFacade.create(empUnpaidV);
        try {

            String json = unpaidV2json(empUnpaidV);
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

    private void deleteUnpaidVacation(HttpServletRequest request, HttpServletResponse response) {
        EmpUnpaidvacation empUnpaidV = empUnpaidvacationFacade.find(Integer.parseInt(request.getParameter("id")));
        empUnpaidvacationFacade.remove(empUnpaidV);
    }

    private String unpaidV2json(EmpUnpaidvacation empUnpaidV) {
 String s = "{";

        s += "\"" + "col1" + "\"" + ":" + "\"" + empUnpaidV.getTypeunpaidvacationId().getName() + "\"";
        s += ",";
        s += "\"" + "col2" + "\"" + ":" + "\"" + vSDF2.format(empUnpaidV.getStartdate()) + "\"";
        s += ",";
        s += "\"" + "col3" + "\"" + ":" + "\"" + vSDF2.format(empUnpaidV.getEnddate()) + "\"";
        s += ",";
        s += "\"" + "col4" + "\"" + ":" + "\"" + empUnpaidV.getReason() + "\"";
        s += ",";
        s += "\"" + "col5" + "\"" + ":" + "\"" + empUnpaidV.getFolding() + "\"";
        s += ",";
        s += "\"" + "col6" + "\"" + ":" + "\"" + empUnpaidV.getDoctype() + "\"";
        s += ",";
        s += "\"" + "col7" + "\"" + ":" + "\"" + empUnpaidV.getDocnumber() + "\"";
        s += ",";
        s += "\"" + "col8" + "\"" + ":" + "\"" + vSDF2.format(empUnpaidV.getDocdate()) + "\"";
        s += ",";
        s += "\"" + "row_d" + "\"" + ":" + "\"" + "<input type='button' value='حذف' name='delete_b' onclick='show_delete_dialog_UnpaidV(" + empUnpaidV.getId() + ")'/>" + "\"";
        s += "}";

        return s;    }

}
