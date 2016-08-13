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
                    setOp_mode(INSERT_MODE);
                    flushPunishment(request, response);
                    break;
                }
                case "/editPunishment": {
                    setOp_mode(UPDATE_MODE);
                    flushPunishment(request, response);
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

    private void flushPunishment(HttpServletRequest request, HttpServletResponse response) throws ParseException, NumberFormatException {

        if (request.getParameter("currentEmp") != null) {
            employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        } else {
            employee = (Employee) getServletContext().getAttribute("employee");
        }

        EmpPunishment empPunishment = new EmpPunishment();
        if (request.getParameter("id") != null) {
            empPunishment = empPunishmentFacade.find(Integer.parseInt(request.getParameter("id")));
        }
        Typepunishment punishment_type = typePunishmentFacade.find(Short.parseShort(request.getParameter("typepunishment_id")));
        empPunishment.setReason(request.getParameter("reason"));
        empPunishment.setDate(vSDF.parse(request.getParameter("date")));
        empPunishment.setDocdate(vSDF.parse(request.getParameter("docdate")));
        empPunishment.setDocnumber(request.getParameter("docnumber"));
        empPunishment.setDoctype(request.getParameter("doctype"));
        empPunishment.setTypepunishmentId(punishment_type);
        employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empPunishment.setEmployeeId(employee);

        String json;
        if (getOp_mode() == INSERT_MODE) {
            empPunishmentFacade.create(empPunishment);
            employee.getEmpPunishmentCollection().add(empPunishment);
            getServletContext().setAttribute("emp_punishments", employee.getEmpPunishmentCollection());

            json = punishment2json(empPunishment);

        } else {
            json = "ok";
            empPunishmentFacade.edit(empPunishment);
        }

        try {
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            /////no space befor or after..it causes problems with json parsing
            response.getWriter().write(json);

        } catch (IOException ex) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    private String punishment2json(EmpPunishment empPunishment) {
        String s = "{";
        s += "\"" + "id" + "\"" + ":" + "\"" + empPunishment.getId() + "\"";
        s += ",";
        s += "\"" + "typepunishment_id" + "\"" + ":" + "\"" + empPunishment.getTypepunishmentId().getName() + "\"";
        s += ",";
        s += "\"" + "reason" + "\"" + ":" + "\"" + empPunishment.getReason() + "\"";
        s += ",";
        s += "\"" + "date" + "\"" + ":" + "\"" + vSDF2.format(empPunishment.getDate()) + "\"";
        s += ",";
        s += "\"" + "doctype" + "\"" + ":" + "\"" + empPunishment.getDoctype() + "\"";
        s += ",";
        s += "\"" + "docnumber" + "\"" + ":" + "\"" + empPunishment.getDocnumber() + "\"";
        s += ",";
        s += "\"" + "docdate" + "\"" + ":" + "\"" + vSDF2.format(empPunishment.getDocdate()) + "\"";
        s += "}";

        return s;
    }

    private void deletePunishment(HttpServletRequest request, HttpServletResponse response) {
        EmpPunishment empPunishment = empPunishmentFacade.find(Integer.parseInt(request.getParameter("id")));
        empPunishmentFacade.remove(empPunishment);
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

}
