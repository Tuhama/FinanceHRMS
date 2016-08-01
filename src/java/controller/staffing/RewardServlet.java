/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;

import entity.EmpReward;
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
import session.EmpRewardFacade;
import session.EmployeeFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "RewardServlet", urlPatterns = {"/addReward","/editReward","/deleteReward"})
public class RewardServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;
    
    private Employee employee = new Employee();
    
    private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat vSDF2 = new SimpleDateFormat("dd/MM/yyyy");
    
    @EJB
    private EmpRewardFacade empRewardFacade;
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
        employee=(Employee)getServletContext().getAttribute("current_employee");
      String userPath = request.getServletPath();

        try {
            switch (userPath) {

                case "/addReward": {
                    insertReward(request, response);
                    break;
                }
                case "/editReward": {
                    //insertReward(request);
                    break;
                }
                case "/deleteReward": {
                  deleteReward(request,response);
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
    private void insertReward(HttpServletRequest request, HttpServletResponse response) throws ParseException, NumberFormatException {

        EmpReward empReward = new EmpReward();
        empReward.setKind(request.getParameter("kind"));
        empReward.setAmount(Integer.parseInt(request.getParameter("amount")));
        empReward.setDocdate(vSDF.parse(request.getParameter("docdate")));
        empReward.setDocnumber(request.getParameter("docnumber"));
        empReward.setDoctype(request.getParameter("doctype"));
        employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empReward.setEmployeeId(employee);
        empRewardFacade.create(empReward);
        try {

            String json = reward2json(empReward);
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

    private void deleteReward(HttpServletRequest request, HttpServletResponse response) {
        EmpReward empReward = empRewardFacade.find(Integer.parseInt(request.getParameter("id")));
        empRewardFacade.remove(empReward);    }

    private String reward2json(EmpReward empReward) {
 String s = "{";

        s += "\"" + "col1" + "\"" + ":" + "\"" + empReward.getKind() + "\"";
        s += ",";
        s += "\"" + "col2" + "\"" + ":" + "\"" + empReward.getAmount() + "\"";
        s += ",";
        s += "\"" + "col3" + "\"" + ":" + "\"" + " " + "\"";
        s += ",";
        s += "\"" + "col4" + "\"" + ":" + "\"" + " " + "\"";
        s += ",";
        s += "\"" + "col5" + "\"" + ":" + "\"" + " " + "\"";
        s += ",";
        s += "\"" + "col6" + "\"" + ":" + "\"" + empReward.getDoctype() + "\"";
        s += ",";
        s += "\"" + "col7" + "\"" + ":" + "\"" + empReward.getDocnumber() + "\"";
        s += ",";
        s += "\"" + "col8" + "\"" + ":" + "\"" + vSDF2.format(empReward.getDocdate()) + "\"";
        s += ",";
        s += "\"" + "row_d" + "\"" + ":" + "\"" + "<input type='button' value='حذف' name='delete_b' onclick='show_delete_dialog_reward(" + empReward.getId() + ")'/>" + "\"";
        s += "}";

        return s;    }

}
