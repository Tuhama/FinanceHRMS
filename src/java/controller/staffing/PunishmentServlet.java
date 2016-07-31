/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;

import entity.EmpPunishment;
import entity.Employee;
import entity.Typepunishment;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EmpPunishmentFacade;
import session.EmployeeFacade;
import session.TypepunishmentFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "PunishmentServlet", urlPatterns = {"/addPunishment", "/editPunishment", "/deletePunishment"})
public class PunishmentServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;

    private Employee employee = new Employee();

    private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat vSDF2 = new SimpleDateFormat("dd/MM/yyyy");

    @EJB
    private EmpPunishmentFacade empPunishmentFacade;
    @EJB
    private TypepunishmentFacade typePunishmentFacade;
    @EJB
    private EmployeeFacade employeeFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        employee = (Employee) getServletContext().getAttribute("current_employee");
        String userPath = request.getServletPath();

        try {
            switch (userPath) {

                case "/addPunishment": {
                    insertPunishment(request, response);
                    break;
                }
                case "/editPunishment": {
                    //insertPunishment(request);
                    break;
                }
                case "/deletePunishment": {
                    deletePunishment(request, response);
                    break;
                }
            }

            //response.sendRedirect(url);
            //} catch (NumberFormatException | ParseException | IOException ex) {
        } catch (NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void insertPunishment(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ParseException {

        EmpPunishment empPunishment = new EmpPunishment();
        Typepunishment punishment_type = typePunishmentFacade.find(Short.parseShort(request.getParameter("typepunishment_id")));
        empPunishment.setReason(request.getParameter("reason"));
        empPunishment.setDate(vSDF.parse(request.getParameter("date")));
        empPunishment.setDocdate(vSDF.parse(request.getParameter("docdate")));
        empPunishment.setDocnumber(request.getParameter("docnumber"));
        empPunishment.setDoctype(request.getParameter("doctype"));
        empPunishment.setTypepunishmentId(punishment_type);
        employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empPunishment.setEmployeeId(employee);
        empPunishmentFacade.create(empPunishment);
        try {

            String json = punishment2json(empPunishment);
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(json);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
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

    private void deletePunishment(HttpServletRequest request, HttpServletResponse response) {
        EmpPunishment empPunishment = empPunishmentFacade.find(Integer.parseInt(request.getParameter("id")));
        empPunishmentFacade.remove(empPunishment);
    }

    private String punishment2json(EmpPunishment empPunishment) {
 String s = "{";

        s += "\"" + "col1" + "\"" + ":" + "\"" + empPunishment.getTypepunishmentId().getName() + "\"";
                s += ",";
        s += "\"" + "col4" + "\"" + ":" + "\"" + empPunishment.getReason() + "\"";
        s += ",";
        s += "\"" + "col2" + "\"" + ":" + "\"" + vSDF2.format(empPunishment.getDate()) + "\"";
        s += ",";
        s += "\"" + "col3" + "\"" + ":" + "\"" + " " + "\"";
        s += ",";
        s += "\"" + "col5" + "\"" + ":" + "\"" + " " + "\"";
        s += ",";
        s += "\"" + "col6" + "\"" + ":" + "\"" + empPunishment.getDoctype() + "\"";
        s += ",";
        s += "\"" + "col7" + "\"" + ":" + "\"" + empPunishment.getDocnumber() + "\"";
        s += ",";
        s += "\"" + "col8" + "\"" + ":" + "\"" + vSDF2.format(empPunishment.getDocdate()) + "\"";
        s += ",";
        s += "\"" + "row_d" + "\"" + ":" + "\"" + "<input type='button' value='حذف' name='delete_b' onclick='show_delete_dialog_punishment(" + empPunishment.getId() + ")'/>" + "\"";
        s += "}";

        return s;    }

}
