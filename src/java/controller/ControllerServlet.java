/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Mainbranch;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CertificateFacade;
import session.*;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "ControllerServlet", loadOnStartup = 1,urlPatterns = {"/managment", "/staffing", "/reporting"})
public class ControllerServlet extends HttpServlet {

    @EJB
    private MainbranchFacade mainbranchFacade;
    @EJB
    private CertificateFacade certificateFacade;
    @EJB
    private DepartmentFacade departmentFacade;
    @EJB
    private SectionFacade sectionFacade;
    @EJB
    private DevisionFacade devisionFacade;
    @EJB
    private PositionFacade positionFacade;
    @EJB
    private TypehealthleaveFacade typeHelthleaveFacade;
    @EJB
    private TypepunishmentFacade typePunishmentFacade;
    @EJB
    private TypeunpaidvacationFacade typeUnpaidVacationFacade;

    @EJB
    private  CategoryFacade categoryFacade;
    @EJB
    private FamilystatusFacade familystatusFacade;
    @EJB
    private ForeignlanguageFacade foreignlanguageFacade;
    @EJB
    private MartialstatusFacade martialstatusFacade;
    @EJB
    private NatianalityFacade natianalityFacade;
    @EJB
    private WorkstatusFacade workstatusFacade;
    
    
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        super.init(servletConfig);

        short mainbranch_id = Short.parseShort(servletConfig.getServletContext().getInitParameter("mainBranch"));

        Mainbranch mb = mainbranchFacade.find(mainbranch_id);
        getServletContext().setAttribute("branches", mb.getBranchCollection());

        getServletContext().setAttribute("departments", departmentFacade.findAll());

        getServletContext().setAttribute("sections", sectionFacade.findAll());

        getServletContext().setAttribute("devisions", devisionFacade.findAll());

        getServletContext().setAttribute("certificates", certificateFacade.findAll());
        getServletContext().setAttribute("positions", positionFacade.findAll());

        getServletContext().setAttribute("unpaid_vacation_types", typeUnpaidVacationFacade.findAll());

        getServletContext().setAttribute("healthleave_types", typeHelthleaveFacade.findAll());
        getServletContext().setAttribute("punishment_types", typePunishmentFacade.findAll());

        
        getServletContext().setAttribute("genders", "ذكر,أنثى");
        getServletContext().setAttribute("categories", categoryFacade.findAll());
        getServletContext().setAttribute("natianalities", natianalityFacade.findAll());
        getServletContext().setAttribute("foreignlanguages", foreignlanguageFacade.findAll());

        getServletContext().setAttribute("workstatuss", workstatusFacade.findAll());
        getServletContext().setAttribute("familystatuss", familystatusFacade.findAll());

        getServletContext().setAttribute("martialstatuss", martialstatusFacade.findAll());

    }

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
            case "/managment":
                userPath = "managment/index" + ".html";
                break;
            case "/staffing":
                userPath = "staffing/index" + ".html";
                break;
            case "/reporting":
                userPath = "reporting/index" + ".html";
                break;
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/" + userPath;

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
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
        return "Main Window Controller";
    }// </editor-fold>

}
