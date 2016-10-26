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
@WebServlet(name = "UnpaidVacationServlet", urlPatterns = {"/staffing/addUnpaidV", "/staffing/editUnpaidV", "/staffing/deleteUnpaidV"})
public class UnpaidVacationServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    private static int op_mode = INSERT_MODE;

    private Employee employee = new Employee();

    private final SimpleDateFormat vSDF = new SimpleDateFormat("dd/MM/yyyy");
   // private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");

    @EJB
    private EmpUnpaidvacationFacade empUnpaidvacationFacade;
    @EJB
    private TypeunpaidvacationFacade typeUnpaidVacationFacade;
    @EJB
    private EmployeeFacade employeeFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        employee = (Employee) getServletContext().getAttribute("current_employee");
        String userPath = request.getServletPath();

        try {
            switch (userPath) {

                case "/staffing/addUnpaidV": {
                    setOp_mode(INSERT_MODE);
                    flushUnpaidVacation(request, response);
                    break;
                }
                case "/staffing/editUnpaidV": {
                    setOp_mode(UPDATE_MODE);
                    flushUnpaidVacation(request, response);
                    break;
                }
                case "/staffing/deleteUnpaidV": {
                    deleteUnpaidVacation(request, response);
                    break;
                }
            }

        } catch (NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void flushUnpaidVacation(HttpServletRequest request, HttpServletResponse response) throws ParseException, NumberFormatException {

        if (request.getParameter("currentEmp") != null) {
            employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        } else {
            employee = (Employee) getServletContext().getAttribute("employee");
        }

        EmpUnpaidvacation empUnpaidV = new EmpUnpaidvacation();
        if (request.getParameter("id") != null) {
            empUnpaidV = empUnpaidvacationFacade.find(Integer.parseInt(request.getParameter("id")));
        }
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
        String json;
        if (getOp_mode() == INSERT_MODE) {
            empUnpaidvacationFacade.create(empUnpaidV);
            employee.getEmpUnpaidvacationCollection().add(empUnpaidV);
            getServletContext().setAttribute("emp_unpaid_vs", employee.getEmpUnpaidvacationCollection());

            json = unpaidV2json(empUnpaidV);

        } else {
            json = "ok";
            empUnpaidvacationFacade.edit(empUnpaidV);
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

    private String unpaidV2json(EmpUnpaidvacation empUnpaidV) {
        String s = "{";
        s += "\"" + "id" + "\"" + ":" + "\"" + empUnpaidV.getId() + "\"";
        s += ",";
        s += "\"" + "typeunpaidvacationId" + "\"" + ":" + "\"" + empUnpaidV.getTypeunpaidvacationId().getName() + "\"";
        s += ",";
        s += "\"" + "startdate" + "\"" + ":" + "\"" + vSDF.format(empUnpaidV.getStartdate()) + "\"";
        s += ",";
        s += "\"" + "enddate" + "\"" + ":" + "\"" + vSDF.format(empUnpaidV.getEnddate()) + "\"";
        s += ",";
        s += "\"" + "reason" + "\"" + ":" + "\"" + empUnpaidV.getReason() + "\"";
        s += ",";
        s += "\"" + "folding" + "\"" + ":" + "\"" +" <input type='checkbox' editable='false' checked='"+empUnpaidV.getFolding()+"'>"+ "\"";
        s += ",";
        s += "\"" + "doctype" + "\"" + ":" + "\"" + empUnpaidV.getDoctype() + "\"";
        s += ",";
        s += "\"" + "docnumber" + "\"" + ":" + "\"" + empUnpaidV.getDocnumber() + "\"";
        s += ",";
        s += "\"" + "docdate" + "\"" + ":" + "\"" + vSDF.format(empUnpaidV.getDocdate()) + "\"";
        s += "}";

        return s;
    }

    private void deleteUnpaidVacation(HttpServletRequest request, HttpServletResponse response) {
        EmpUnpaidvacation empUnpaidV = empUnpaidvacationFacade.find(Integer.parseInt(request.getParameter("id")));
        empUnpaidvacationFacade.remove(empUnpaidV);
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
