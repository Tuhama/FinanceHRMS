/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;

import entity.EmpTrainingcourse;
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
import session.EmpTrainingcourseFacade;
import session.EmployeeFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "TrainingCourseServlet", urlPatterns = {"/addTraining", "/editTraining", "/deleteTraining"})
public class TrainingCourseServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;

    private Employee employee = new Employee();

    private final SimpleDateFormat vSDF2 = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");

    @EJB
    private EmpTrainingcourseFacade empTrainingcourseFacade;
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

                case "/addTraining": {
                    setOp_mode(INSERT_MODE);
                    flushTrainingCourse(request, response);
                    break;
                }
                case "/editTraining": {
                    setOp_mode(UPDATE_MODE);
                    flushTrainingCourse(request, response);
                    break;
                }
                case "/deleteTraining": {
                    deleteTrainingCourse(request, response);
                    break;
                }
            }
        } catch (NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void flushTrainingCourse(HttpServletRequest request, HttpServletResponse response) throws ParseException, NumberFormatException {

        if (request.getParameter("currentEmp") != null) {
            employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        } else {
            employee = (Employee) getServletContext().getAttribute("employee");
        }

        EmpTrainingcourse empTrainingcourse = new EmpTrainingcourse();
        if (request.getParameter("id") != null) {
            empTrainingcourse = empTrainingcourseFacade.find(Integer.parseInt(request.getParameter("id")));
        }
        empTrainingcourse.setPlace(request.getParameter("place"));
        empTrainingcourse.setKind(request.getParameter("kind"));
        empTrainingcourse.setDuration(Integer.parseInt(request.getParameter("duration")));
        empTrainingcourse.setStartdate(vSDF.parse(request.getParameter("startdate")));
        empTrainingcourse.setEnddate(vSDF.parse(request.getParameter("enddate")));
        employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empTrainingcourse.setEmployeeId(employee);
        String json;
        if (getOp_mode() == INSERT_MODE) {
            empTrainingcourseFacade.create(empTrainingcourse);
            employee.getEmpTrainingcourseCollection().add(empTrainingcourse);
            getServletContext().setAttribute("emp_trainingcourses", employee.getEmpTrainingcourseCollection());

            json = training2json(empTrainingcourse);

        } else {
            json = "ok";
            empTrainingcourseFacade.edit(empTrainingcourse);
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

    private String training2json(EmpTrainingcourse empTrainingcourse) {
        String s = "{";
        s += "\"" + "id" + "\"" + ":" + "\"" + empTrainingcourse.getId() + "\"";
        s += ",";
        s += "\"" + "kind" + "\"" + ":" + "\"" + empTrainingcourse.getKind() + "\"";
        s += ",";
        s += "\"" + "place" + "\"" + ":" + "\"" + empTrainingcourse.getPlace() + "\"";
        s += ",";
        s += "\"" + "startdate" + "\"" + ":" + "\"" + vSDF2.format(empTrainingcourse.getStartdate()) + "\"";
        s += ",";
        s += "\"" + "enddate" + "\"" + ":" + "\"" + vSDF2.format(empTrainingcourse.getEnddate()) + "\"";
        s += ",";
        s += "\"" + "duration" + "\"" + ":" + "\"" + empTrainingcourse.getDuration() + "\"";
        s += "}";

        return s;
    }

    private void deleteTrainingCourse(HttpServletRequest request, HttpServletResponse response) {

        EmpTrainingcourse empTrainingcourse = empTrainingcourseFacade.find(Integer.parseInt(request.getParameter("id")));
        empTrainingcourseFacade.remove(empTrainingcourse);
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
