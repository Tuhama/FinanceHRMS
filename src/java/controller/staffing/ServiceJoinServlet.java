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
@WebServlet(name = "ServiceJoinServlet", urlPatterns = {"/staffing/addServiceJoin", "/staffing/editServiceJoin", "/staffing/deleteServiceJoin"})
public class ServiceJoinServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;

    private Employee employee = new Employee();

    private final SimpleDateFormat vSDF = new SimpleDateFormat("dd/MM/yyyy");
    //private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private EmpServicejoinFacade empServicejoinFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        employee = (Employee) getServletContext().getAttribute("current_employee");
        String userPath = request.getServletPath();

        try {
            switch (userPath) {

                case "/staffing/addServiceJoin": {
                    setOp_mode(INSERT_MODE);
                    flushServiceJoin(request, response);
                    break;
                }
                case "/staffing/editServiceJoin": {
                    setOp_mode(UPDATE_MODE);
                    flushServiceJoin(request, response);
                    break;
                }
                case "/staffing/deleteServiceJoin": {
                    deleteServiceJoin(request, response);
                    break;
                }
            }
        } catch (NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void flushServiceJoin(HttpServletRequest request, HttpServletResponse response) throws ParseException, NumberFormatException {

        if (request.getParameter("currentEmp") != null) {
            employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        } else {
            employee = (Employee) getServletContext().getAttribute("employee");
        }

        EmpServicejoin empServiceJ = new EmpServicejoin();
        if (request.getParameter("id") != null) {
            empServiceJ = empServicejoinFacade.find(Integer.parseInt(request.getParameter("id")));
        }
        empServiceJ.setDaysduration(Short.parseShort(request.getParameter("daysduration")));
        empServiceJ.setMonthsduration(Short.parseShort(request.getParameter("monthsduration")));
        empServiceJ.setPlaceofservice(request.getParameter("placeofservice"));
        empServiceJ.setDocdate(vSDF.parse(request.getParameter("docdate")));
        empServiceJ.setDocnumber(request.getParameter("docnumber"));
        empServiceJ.setDoctype(request.getParameter("doctype"));
        employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empServiceJ.setEmployeeId(employee);
        String json;
        if (getOp_mode() == INSERT_MODE) {
            empServicejoinFacade.create(empServiceJ);
            employee.getEmpServicejoinCollection().add(empServiceJ);
            getServletContext().setAttribute("emp_service_js", employee.getEmpServicejoinCollection());

            json = service2json(empServiceJ);

        } else {
            json = "ok";
            empServicejoinFacade.edit(empServiceJ);
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

    private String service2json(EmpServicejoin empServiceJ) {
        String s = "{";
        s += "\"" + "id" + "\"" + ":" + "\"" + empServiceJ.getId() + "\"";
        s += ",";
        s += "\"" + "placeofservice" + "\"" + ":" + "\"" + empServiceJ.getPlaceofservice() + "\"";
        s += ",";
        s += "\"" + "daysduration" + "\"" + ":" + "\"" + empServiceJ.getDaysduration() + "\"";
        s += ",";
        s += "\"" + "monthsduration" + "\"" + ":" + "\"" + empServiceJ.getMonthsduration() + "\"";
        s += ",";
        s += "\"" + "doctype" + "\"" + ":" + "\"" + empServiceJ.getDoctype() + "\"";
        s += ",";
        s += "\"" + "docnumber" + "\"" + ":" + "\"" + empServiceJ.getDocnumber() + "\"";
        s += ",";
        s += "\"" + "docdate" + "\"" + ":" + "\"" + vSDF.format(empServiceJ.getDocdate()) + "\"";
        s += "}";

        return s;
    }

    private void deleteServiceJoin(HttpServletRequest request, HttpServletResponse response) {

        EmpServicejoin empServiceJ = empServicejoinFacade.find(Integer.parseInt(request.getParameter("id")));
        empServicejoinFacade.remove(empServiceJ);
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
