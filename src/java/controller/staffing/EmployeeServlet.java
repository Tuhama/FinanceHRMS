/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;

import entity.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.BranchFacade;
import session.CategoryFacade;
import session.CertificateFacade;
import session.DevisionFacade;
import session.EmployeeFacade;
import session.FamilystatusFacade;
import session.ForeignlanguageFacade;
import session.MartialstatusFacade;
import session.NatianalityFacade;
import session.WorkstatusFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "AddEmployeeServlet", urlPatterns = {"/employeeView", "/addEmployee", "/editEmployee", "/deleteEmployee"})
public class EmployeeServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    // public static final int DELETE_MODE = 2;
    public static final int REPORT_MODE = 3;

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;

    private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");

    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private BranchFacade branchFacade;
    @EJB
    private CertificateFacade certificateFacade;
    @EJB
    private DevisionFacade devisionFacade;
    @EJB
    private NatianalityFacade natianalityFacade;
    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private WorkstatusFacade workstatusFacade;
    @EJB
    private FamilystatusFacade familystatusFacade;
    @EJB
    private MartialstatusFacade martialstatusFacade;
    @EJB
    private ForeignlanguageFacade foreignlanguageFacade;

    private Employee employee = new Employee();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        switch (userPath) {
            case "/employeeView":
                getServletContext().setAttribute("employee", null);
                userPath = "newEmployeeT.jsp";
                break;
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/staffing/view/" + userPath;

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userPath = request.getServletPath();
        try {
            switch (userPath) {

                case "/editEmployee": {
                    setOp_mode(UPDATE_MODE);
                    flushEmployee(request, response);
                    break;
                }
                case "/addEmployee":
                    setOp_mode(INSERT_MODE);
                    flushEmployee(request, response);
                    break;

                case "/deleteEmployee":
                    // setOp_mode(DELETE_MODE);
                    deleteEmployee(request, response);
                    break;
            }
            getServletContext().setAttribute("current_employee", employee);
            userPath = "";
            if (!userPath.equals("")) {
                String url = "/WEB-INF/staffing/view/" + userPath;
                request.getRequestDispatcher(url).forward(request, response);
            }

        } catch (NumberFormatException | ParseException ex) {
            Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void flushEmployee(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ParseException, IOException {
       try{
        if (request.getParameter("id") != null) {
            employee = employeeFacade.find(Integer.parseInt(request.getParameter("id")));
        }

        Branch branch = branchFacade.find(Short.parseShort(request.getParameter("branch_id")));
        employee.setBranchId(branch);

        Devision devision = devisionFacade.find(Short.parseShort(request.getParameter("devision_id")));
        employee.setDevisionId(devision);

        Certificate cert = certificateFacade.find(Short.parseShort(request.getParameter("certificate_id")));
        employee.setCertificateId(cert);

        Natianality natianality = natianalityFacade.find(Short.parseShort(request.getParameter("natianality_id")));
        employee.setNatianalityId(natianality);
        Category category = categoryFacade.find(Short.parseShort(request.getParameter("category_id")));
        employee.setCategoryId(category);
        Workstatus workstatus = workstatusFacade.find(Short.parseShort(request.getParameter("workstatus_id")));
        employee.setWorkstatusId(workstatus);
        Familystatus familystatus = familystatusFacade.find(Short.parseShort(request.getParameter("familystatus_id")));
        employee.setFamilystatusId(familystatus);
        Martialstatus martialstatus = martialstatusFacade.find(Short.parseShort(request.getParameter("martialstatus_id")));
        employee.setMartialstatusId(martialstatus);
        Foreignlanguage foreignlanguage = foreignlanguageFacade.find(Short.parseShort(request.getParameter("foreignlanguage_id")));
        employee.setForeignlanguageId(foreignlanguage);

        employee.setGender(request.getParameter("gender"));
        employee.setFirstname(request.getParameter("firstname"));
        employee.setFathername(request.getParameter("fathername"));
        employee.setMothername(request.getParameter("mothername"));
        employee.setLastname(request.getParameter("lastname"));
        employee.setNationalnumber(request.getParameter("nationalnumber"));
        employee.setPlaceOfBirth(request.getParameter("placeOfBirth"));
        employee.setDateOfBirth(vSDF.parse(request.getParameter("dateOfBirth")));
        employee.setRegisteinfo(request.getParameter("registeinfo"));

        employee.setAddress(request.getParameter("address"));
        employee.setHomePhone(request.getParameter("homePhone"));
        employee.setMobilePhone(request.getParameter("mobilePhone"));

        employee.setPersonalnumber(request.getParameter("personalnumber"));
        employee.setSocialsecuritynumber(request.getParameter("socialsecuritynumber"));
        employee.setBasesalary(Integer.parseInt(request.getParameter("basesalary")));

        employee.setFirstworkdate(vSDF.parse(request.getParameter("firstworkdate")));
        employee.setWorkdocdate(vSDF.parse(request.getParameter("workdocdate")));
        employee.setWorkdocnumber(request.getParameter("workdocnumber"));
        if(!"".equals(request.getParameter("modworkdocdate")))
        employee.setModworkdocdate(vSDF.parse(request.getParameter("modworkdocdate")));
        employee.setModworkdocnumber(request.getParameter("modworkdocnumber"));
        employee.setNotes(request.getParameter("notes"));

        if (getOp_mode() == INSERT_MODE) {
            try {
                employeeFacade.create(employee);
                //this will not work with ajax....send just the employee id
                // getServletContext().setAttribute("employee", employee);

                response.setContentType("text/plain");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write(" " + employee.getId().toString() + " ");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            }

        } else {
            employeeFacade.edit(employee);
        }
        } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //todo
    }
}
