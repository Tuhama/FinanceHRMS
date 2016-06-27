/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.indexing;

import entity.*;
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
@WebServlet(name = "DeleteIndexServlet", urlPatterns = {"/deleteMainBranch", "/deleteBranch", "/deleteCertificate",
    "/deletePosition", "/deleteHealthleavetype", "/deletePunishmenttype", "/deleteUnpaidvacationtype", "/deleteDepartment", "/deleteSection", "/deleteDevision", "/deleteNatianality", "/deleteForeignlanguage", "/deleteCategory", "/deleteFamilystatus", "/deleteMartialstatus", "/deleteWorkstatus"})
public class DeleteIndexServlet extends HttpServlet {

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
////todo check constraints before delete
        String url = "";
        switch (userPath) {
            case "/deleteMainBranch": {
                Mainbranch m_branch = mainbranchFacade.find(Short.parseShort(request.getParameter("id")));
                mainbranchFacade.remove(m_branch);
                url = "mainbranch";
                break;
            }
            case "/deleteBranch": {
                Branch branch = branchFacade.find(Short.parseShort(request.getParameter("id")));
                branchFacade.remove(branch);
                url = "branch";
                break;
            }
            case "/deleteCertificate":
                Certificate certificate = certificateFacade.find(Short.parseShort(request.getParameter("id")));
                certificateFacade.remove(certificate);
                url = "certificate";
                break;
            case "/deletePosition":
                Position position = positionFacade.find(Short.parseShort(request.getParameter("id")));
                positionFacade.remove(position);
                url = "position";
                break;
            case "/deleteHealthleavetype":
                Typehealthleave healthleavetype = healthleavetypeFacade.find(Short.parseShort(request.getParameter("id")));
                healthleavetypeFacade.remove(healthleavetype);
                url = "healthleavetype";
                break;
            case "/deletePunishmenttype":
                Typepunishment punishmenttype = punishmenttypeFacade.find(Short.parseShort(request.getParameter("id")));
                punishmenttypeFacade.remove(punishmenttype);
                url = "punishmenttype";
                break;
            case "/deleteUnpaidvacationtype":
                Typeunpaidvacation unpaidvtype = unpaidvacationtypeFacade.find(Short.parseShort(request.getParameter("id")));
                unpaidvacationtypeFacade.remove(unpaidvtype);
                url = "unpaidvacationtype";
                break;
            case "/deleteDepartment": {
                Department department = departmentFacade.find(Short.parseShort(request.getParameter("id")));
                departmentFacade.remove(department);
                url = "department";
                break;
            }
            case "/deleteSection": {
                Section section = sectionFacade.find(Short.parseShort(request.getParameter("id")));
                sectionFacade.remove(section);
                url = "section";
                break;
            }
            case "/deleteDevision": {
                Devision devision = devisionFacade.find(Short.parseShort(request.getParameter("id")));
                devisionFacade.remove(devision);
                url = "devision";
                break;
            }
            case "/deleteNatianality": {
                Natianality natianality = natianalityFacade.find(Short.parseShort(request.getParameter("id")));
                natianalityFacade.remove(natianality);
                url = "natianality";
                break;
            }
            case "/deleteForeignlanguage": {
                Foreignlanguage foreignlanguage = foreignlanguageFacade.find(Short.parseShort(request.getParameter("id")));
                foreignlanguageFacade.remove(foreignlanguage);
                url = "foreignlanguage";
                break;
            }
            case "/deleteCategory": {
                Category category =categoryFacade.find(Short.parseShort(request.getParameter("id")));
                categoryFacade.remove(category);
                url = "category";
                break;
            }
            case "/deleteFamilystatus": {
                Familystatus familystatus =familystatusFacade.find(Short.parseShort(request.getParameter("id")));
                familystatusFacade.remove(familystatus);
                url = "familystatus";
                break;
            }
            case "/deleteMartialstatus": {
                Martialstatus martialstatus = martialstatusFacade.find(Short.parseShort(request.getParameter("id")));
                martialstatusFacade.remove(martialstatus);
                url = "martialstatus";
                break;
            }
            case "/deleteWorkstatus": {
                Workstatus workstatus = workstatusFacade.find(Short.parseShort(request.getParameter("id")));
                workstatusFacade.remove(workstatus);
                url = "workstatus";
                break;
            }
        }
        try {

            response.sendRedirect(url);

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
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

}
