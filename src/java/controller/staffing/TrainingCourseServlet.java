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

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "TrainingCourseServlet", urlPatterns = {"/addTrainingCourse","/editTrainingCourse","/deleteTrainingCourse"})
public class TrainingCourseServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;
    
    private Employee employee = new Employee();
    private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");
    
    
       @EJB
    private EmpTrainingcourseFacade empTrainingcourseFacade;
       
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

                case "/addTrainingCourse": {
                    insertTrainingCourse(request);
                    break;
                }
                case "/editTrainingCourse": {
                    //insertTrainingCourse(request);
                    break;
                }
                                case "/deleteTrainingCourse": {
                   // insertTrainingCourse(request);
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
    private void insertTrainingCourse(HttpServletRequest request) throws NumberFormatException, ParseException {

        EmpTrainingcourse empTrainingcourse = new EmpTrainingcourse();
        empTrainingcourse.setPlace(request.getParameter("place"));
        empTrainingcourse.setKind(request.getParameter("kind"));
        empTrainingcourse.setDuration(Integer.parseInt(request.getParameter("duration")));
        empTrainingcourse.setStartdate(vSDF.parse(request.getParameter("startdate")));
        empTrainingcourse.setEnddate(vSDF.parse(request.getParameter("enddate")));
        empTrainingcourse.setEmployeeId(employee);
        empTrainingcourseFacade.create(empTrainingcourse);
        employee.getEmpTrainingcourseCollection().add(empTrainingcourse);
        getServletContext().setAttribute("emp_trainingcourses", employee.getEmpTrainingcourseCollection());

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
