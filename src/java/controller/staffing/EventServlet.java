/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;

import com.google.gson.Gson;
import entity.EmpEvent;
import entity.Employee;
import entity.Position;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EmpEventFacade;
import session.PositionFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "EventServlet", urlPatterns = {"/addEvent", "/editEvent", "/deleteEvent"})
public class EventServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;

    private Employee employee = new Employee();
    private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");
    @EJB
    private EmpEventFacade empEventFacade;
    @EJB
    private PositionFacade positionFacade;

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

                case "/addEvent": {
                    insertEvent(request, response);
                    break;
                }
                case "/editEvent": {
                    //insertEvent(request);
                    break;
                }
                case "/deleteEvent": {
                    // insertEvent(request);
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

    private void insertEvent(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ParseException {

        EmpEvent empEvent = new EmpEvent();
        Position position = positionFacade.find(Short.parseShort(request.getParameter("position_id")));
        empEvent.setCategory(request.getParameter("category"));
        empEvent.setName(request.getParameter("name"));
        empEvent.setSalary(Integer.parseInt(request.getParameter("salary")));
        empEvent.setStartdate(vSDF.parse(request.getParameter("startdate")));
        empEvent.setDocdate(vSDF.parse(request.getParameter("docdate")));
        empEvent.setDocnumber(request.getParameter("docnumber"));
        empEvent.setDoctype(request.getParameter("doctype"));
        empEvent.setPositionId(position);
        empEvent.setEmployeeId(employee);

        //employee.getEmpEventCollection().add(empEvent);
        //getServletContext().setAttribute("emp_events", employee.getEmpEventCollection());
        try {
            empEventFacade.create(empEvent);

            Gson gson = new Gson();
            String json = gson.toJson(empEvent);

            response.setContentType("text/plain");
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

}
