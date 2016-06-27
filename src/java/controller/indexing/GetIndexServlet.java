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
@WebServlet(name = "GetIndexServlet", urlPatterns = {"/mainbranch","/branch", "/certificate", "/position", "/healthleavetype","/punishmenttype","/unpaidvacationtype","/department", "/section","/devision", "/natianality","/foreignlanguage","/category","/familystatus","/martialstatus", "/workstatus"})
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
            case "/mainbranch":
                getServletContext().setAttribute("mainbranches", mainbranchFacade.findAll());
                userPath = "mainbranch.jsp";
                break;
            case "/branch":
                getServletContext().setAttribute("mainbranches", mainbranchFacade.findAll());
                getServletContext().setAttribute("branches", branchFacade.findAll());
                userPath = "branch.jsp";
                break;
            case "/certificate":
                getServletContext().setAttribute("certificates", certificateFacade.findAll());
                userPath = "certificate.jsp";
                break;
            case "/position":
                getServletContext().setAttribute("positions", positionFacade.findAll());
                userPath = "position.jsp";
                break;
            case "/healthleavetype":
                getServletContext().setAttribute("healthleave_types", healthleavetypeFacade.findAll());
                userPath = "healthleavetype.jsp";
                break;
            case "/punishmenttype":
                getServletContext().setAttribute("punishment_types", punishmenttypeFacade.findAll());
                userPath = "punishmenttype.jsp";
                break;
            case "/unpaidvacationtype":
                getServletContext().setAttribute("unpaid_vacation_types", unpaidvacationtypeFacade.findAll());
                userPath = "unpaidvacationtype.jsp";
                break;
            case "/department":
                getServletContext().setAttribute("departments", departmentFacade.findAll());
                userPath = "department.jsp";
                break;
            case "/section":
                getServletContext().setAttribute("departments", departmentFacade.findAll());
                getServletContext().setAttribute("sections", sectionFacade.findAll());
                userPath = "section.jsp";
                break;
            case "/devision":
                getServletContext().setAttribute("departments", departmentFacade.findAll());
                getServletContext().setAttribute("sections", sectionFacade.findAll());
                getServletContext().setAttribute("devisions", devisionFacade.findAll());
                userPath = "devision.jsp";
                break;
            case "/natianality":
                getServletContext().setAttribute("natianalities", natianalityFacade.findAll());
                userPath = "natianality.jsp";
                break;
            case "/foreignlanguage":
                getServletContext().setAttribute("foreignlanguages", foreignlanguageFacade.findAll());
                userPath = "foreignlanguage.jsp";
                break;
            case "/category":
                getServletContext().setAttribute("categories", categoryFacade.findAll());
                userPath = "category.jsp";
                break;
            case "/familystatus":
                getServletContext().setAttribute("familystatuss", familystatusFacade.findAll());
                userPath = "familystatus.jsp";
                break;
            case "/martialstatus":
                getServletContext().setAttribute("martialstatuss", martialstatusFacade.findAll());
                userPath = "martialstatus.jsp";
                break;
            case "/workstatus":
                getServletContext().setAttribute("workstatuss", workstatusFacade.findAll());
                userPath = "workstatus.jsp";
                break;
        }
        String url = "/WEB-INF/managment/view/" + userPath;

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
