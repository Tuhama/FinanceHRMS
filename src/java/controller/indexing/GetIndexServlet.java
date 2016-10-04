/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.indexing;
import session.*;
import javax.ejb.EJB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "GetIndexServlet", urlPatterns = {"/indexing/mainbranch","/indexing/branch", "/indexing/certificate", "/indexing/position", "/indexing/healthleavetype","/indexing/punishmenttype","/indexing/unpaidvacationtype","/indexing/department", "/indexing/section","/indexing/devision", "/indexing/natianality","/indexing/foreignlanguage","/indexing/category","/indexing/familystatus","/indexing/martialstatus", "/indexing/workstatus"})
public class GetIndexServlet extends HttpServlet {
    @EJB
    private MainbranchFacade mainbranchFacade;
    @EJB
    private BranchFacade branchFacade;
    @EJB
    private CertificateFacade certificateFacade;
    @EJB
    private PositionFacade positionFacade;
    @EJB
    private TypehealthleaveFacade healthleavetypeFacade;
    @EJB
    private TypepunishmentFacade punishmenttypeFacade;
    @EJB
    private TypeunpaidvacationFacade unpaidvacationtypeFacade;
    @EJB
    private DepartmentFacade departmentFacade;
    @EJB
    private SectionFacade sectionFacade;
    @EJB
    private DevisionFacade devisionFacade;
    @EJB
    private NatianalityFacade natianalityFacade;
    @EJB
    private ForeignlanguageFacade foreignlanguageFacade;
    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private FamilystatusFacade familystatusFacade;
    @EJB
    private MartialstatusFacade martialstatusFacade;
    @EJB
    private WorkstatusFacade workstatusFacade;

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
                String userPath = request.getServletPath();

        switch (userPath) {
            case "/indexing/mainbranch":
                getServletContext().setAttribute("mainbranches", mainbranchFacade.findAll());
                userPath = "mainbranch.jsp";
                break;
            case "/indexing/branch":
                getServletContext().setAttribute("mainbranches", mainbranchFacade.findAll());
                getServletContext().setAttribute("branches", branchFacade.findAll());
                userPath = "branch.jsp";
                break;
            case "/indexing/certificate":
                getServletContext().setAttribute("certificates", certificateFacade.findAll());
                userPath = "certificate.jsp";
                break;
            case "/indexing/position":
                getServletContext().setAttribute("positions", positionFacade.findAll());
                userPath = "position.jsp";
                break;
            case "/indexing/healthleavetype":
                getServletContext().setAttribute("healthleave_types", healthleavetypeFacade.findAll());
                userPath = "healthleavetype.jsp";
                break;
            case "/indexing/punishmenttype":
                getServletContext().setAttribute("punishment_types", punishmenttypeFacade.findAll());
                userPath = "punishmenttype.jsp";
                break;
            case "/indexing/unpaidvacationtype":
                getServletContext().setAttribute("unpaid_vacation_types", unpaidvacationtypeFacade.findAll());
                userPath = "unpaidvacationtype.jsp";
                break;
            case "/indexing/department":
                getServletContext().setAttribute("departments", departmentFacade.findAll());
                userPath = "department.jsp";
                break;
            case "/indexing/section":
                getServletContext().setAttribute("departments", departmentFacade.findAll());
                getServletContext().setAttribute("sections", sectionFacade.findAll());
                userPath = "section.jsp";
                break;
            case "/indexing/devision":
                getServletContext().setAttribute("departments", departmentFacade.findAll());
                getServletContext().setAttribute("sections", sectionFacade.findAll());
                getServletContext().setAttribute("devisions", devisionFacade.findAll());
                userPath = "devision.jsp";
                break;
            case "/indexing/natianality":
                getServletContext().setAttribute("natianalities", natianalityFacade.findAll());
                userPath = "natianality.jsp";
                break;
            case "/indexing/foreignlanguage":
                getServletContext().setAttribute("foreignlanguages", foreignlanguageFacade.findAll());
                userPath = "foreignlanguage.jsp";
                break;
            case "/indexing/category":
                getServletContext().setAttribute("categories", categoryFacade.findAll());
                userPath = "category.jsp";
                break;
            case "/indexing/familystatus":
                getServletContext().setAttribute("familystatuss", familystatusFacade.findAll());
                userPath = "familystatus.jsp";
                break;
            case "/indexing/martialstatus":
                getServletContext().setAttribute("martialstatuss", martialstatusFacade.findAll());
                userPath = "martialstatus.jsp";
                break;
            case "/indexing/workstatus":
                getServletContext().setAttribute("workstatuss", workstatusFacade.findAll());
                userPath = "workstatus.jsp";
                break;
        }
        String url = "/indexing/view/" + userPath;

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException | IOException ex) {
        System.out.println("ERROR: ManagmentServlet "+ex.getMessage());
        }
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles index page request (just forword to page)";
    }// </editor-fold>

}
