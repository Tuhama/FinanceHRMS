/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;

import entity.EmpHealthleav;
import entity.Employee;
import entity.Typehealthleave;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EmpHealthleavFacade;
import session.EmployeeFacade;
import session.TypehealthleaveFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "HealthLeaveServlet", urlPatterns = {"/staffing/addHealthLeave", "/staffing/editHealthLeave", "/staffing/deleteHealthLeave"})
public class HealthLeaveServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;

    private Employee employee = new Employee();

    //private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat vSDF = new SimpleDateFormat("dd/MM/yyyy");

    @EJB
    private EmpHealthleavFacade empHealthleavFacade;
    @EJB
    private TypehealthleaveFacade typeHelthleaveFacade;
    @EJB
    private EmployeeFacade employeeFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userPath = request.getServletPath();

        try {
            switch (userPath) {

                case "/staffing/addHealthLeave": {
                    setOp_mode(INSERT_MODE);
                    flushHealthLeave(request, response);
                    break;
                }
                case "/staffing/editHealthLeave": {
                    setOp_mode(UPDATE_MODE);
                    flushHealthLeave(request, response);
                    break;
                }
                case "/staffing/deleteHealthLeave": {
                    deleteHealthLeave(request, response);
                    break;
                }
            }

        } catch (NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void flushHealthLeave(HttpServletRequest request, HttpServletResponse response) throws ParseException, NumberFormatException {
        if (request.getParameter("currentEmp") != null) {
            employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        } else {
            employee = (Employee) getServletContext().getAttribute("employee");
        }

        EmpHealthleav empHealthleave = new EmpHealthleav();
        if (request.getParameter("id") != null) {
            empHealthleave = empHealthleavFacade.find(Integer.parseInt(request.getParameter("id")));
        }
        Typehealthleave type_healthleave = typeHelthleaveFacade.find(Short.parseShort(request.getParameter("typehealthleave_id")));
        empHealthleave.setDayscount(Short.parseShort(request.getParameter("dayscount")));
        empHealthleave.setStartdate(vSDF.parse(request.getParameter("startdate")));
        empHealthleave.setEnddate(vSDF.parse(request.getParameter("enddate")));
        empHealthleave.setTypehealthleaveId(type_healthleave);
        //employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empHealthleave.setEmployeeId(employee);
        String json;
        if (getOp_mode() == INSERT_MODE) {
            empHealthleavFacade.create(empHealthleave);
            employee.getEmpHealthleavCollection().add(empHealthleave);
            getServletContext().setAttribute("emp_helthleaves", employee.getEmpHealthleavCollection());

            json = leave2json(empHealthleave);

        } else {
            json = "ok";
            empHealthleavFacade.edit(empHealthleave);
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
     //  {"att":"val",...}
    private String leave2json(EmpHealthleav leave) {

        String s = "{";
        s += "\"" + "id" + "\"" + ":" + "\"" + leave.getId() + "\"";
        s += ",";
        s += "\"" + "typehealthleave_id" + "\"" + ":" + "\"" + leave.getTypehealthleaveId().getName() + "\"";
        s += ",";
        s += "\"" + "startdate" + "\"" + ":" + "\"" + vSDF.format(leave.getStartdate()) + "\"";
        s += ",";
        s += "\"" + "enddate" + "\"" + ":" + "\"" + vSDF.format(leave.getEnddate()) + "\"";
        s += ",";
        s += "\"" + "year" + "\"" + ":" + "\"" + leave.getYear() + "\"";
        s += ",";
        s += "\"" + "dayscount" + "\"" + ":" + "\"" + leave.getDayscount() + "\"";
        s += "}";

        return s;
    }

        private void deleteHealthLeave(HttpServletRequest request, HttpServletResponse response) {
        EmpHealthleav empLeave = empHealthleavFacade.find(Integer.parseInt(request.getParameter("id")));
        empHealthleavFacade.remove(empLeave);
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
