/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;


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
import session.EmployeeFacade;
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
    private final SimpleDateFormat vSDF2 = new SimpleDateFormat("dd/MM/yyyy");
    
    @EJB
    private EmpEventFacade empEventFacade;
    @EJB
    private PositionFacade positionFacade;
    @EJB
    private EmployeeFacade employeeFacade;

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
                    deleteEvent(request);
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

        employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empEvent.setEmployeeId(employee);

        empEventFacade.create(empEvent);

        //employee.getEmpEventCollection().add(empEvent);
        //getServletContext().setAttribute("emp_events", employee.getEmpEventCollection());
        try {
            //Gson gson = new  GsonBuilder().setExclusionStrategies(new JsonExcludeStrategy()).create();

            // gson.toJsonTree(empEvent).getAsJsonObject().remove("employeeId");
            //String json = gson.toJson(empEvent);
            String json = evet2json(empEvent);
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
/////no space befor or after..it causes problems with json parsing
            response.getWriter().write(json);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    private void deleteEvent(HttpServletRequest request) {
        EmpEvent empEvent = empEventFacade.find(Integer.parseInt(request.getParameter("id")));
        empEventFacade.remove(empEvent);
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

    //  {"att":"val",...}
    private String evet2json(EmpEvent event) {

        /*        Class<?> c = event.getClass();
         Field[] fields = c.getDeclaredFields();
         for (Field field : fields) {
         try {
         if (!field.getName().equals("employeeId") && !field.getName().equals("serialVersionUID")) {
         s += "\"" + field.getName() + "\"" + ":" + "\"" + field.get(event) + "\"";
         s += ",";
         }
        
         } catch (IllegalArgumentException | IllegalAccessException e1) {
         }
         }*/
        String s = "{";
        //   s += "\"" + "id" + "\"" + ":" + "\"" + event.getId() + "\"";
        //  s += ","; 
        /*        s += "\"" + "position" + "\"" + ":" + "\"" + event.getPositionId().getName() + "\"";
         s += ",";
         s += "\"" + "name" + "\"" + ":" + "\"" + event.getName() + "\"";
         s += ",";
         s += "\"" + "startdate" + "\"" + ":" + "\"" + event.getStartdate() + "\"";
         s += ",";
         s += "\"" + "salary" + "\"" + ":" + "\"" + event.getSalary() + "\"";
         s += ",";
         s += "\"" + "category" + "\"" + ":" + "\"" + event.getCategory() + "\"";
         s += ",";
         s += "\"" + "doctype" + "\"" + ":" + "\"" + event.getDoctype() + "\"";
         s += ",";
         s += "\"" + "docnumber" + "\"" + ":" + "\"" + event.getDocnumber() + "\"";
         s += ",";
         s += "\"" + "docdate" + "\"" + ":" + "\"" + event.getDocdate() + "\"";*/
        s += "\"" + "col1" + "\"" + ":" + "\"" + event.getPositionId().getName() + "\"";
        s += ",";
        s += "\"" + "col2" + "\"" + ":" + "\"" + event.getName() + "\"";
        s += ",";
        s += "\"" + "col3" + "\"" + ":" + "\"" + vSDF2.format(event.getStartdate() )+ "\"";
        s += ",";
        s += "\"" + "col4" + "\"" + ":" + "\"" + event.getSalary() + "\"";
        s += ",";
        s += "\"" + "col5" + "\"" + ":" + "\"" + event.getCategory() + "\"";
        s += ",";
        s += "\"" + "col6" + "\"" + ":" + "\"" + event.getDoctype() + "\"";
        s += ",";
        s += "\"" + "col7" + "\"" + ":" + "\"" + event.getDocnumber() + "\"";
        s += ",";
        s += "\"" + "col8" + "\"" + ":" + "\"" + vSDF2.format(event.getDocdate()) + "\"";
        s += ",";
        s += "\"" + "row_d" + "\"" + ":" + "\"" + "<input type='button' value='حذف' name='delete_b' onclick='show_delete_dialog_event(" + event.getId() + ")'/>" + "\"";
        s += "}";

        System.out.println(s);
        return s;
    }

}
