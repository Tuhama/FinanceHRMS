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
@WebServlet(name = "EditIndexServlet", urlPatterns = {"/indexing/editMainBranch",
    "/indexing/editBranch",
    "/indexing/editCertificate",
    "/indexing/editPosition",
    "/indexing/editHealthleavetype",
    "/indexing/editPunishmenttype",
    "/indexing/editUnpaidvacationtype",
    "/indexing/editDepartment",
    "/indexing/editSection",
    "/indexing/editDevision",
    "/indexing/editNatianality",
    "/indexing/editForeignlanguage",
    "/indexing/editCategory",
    "/indexing/editFamilystatus",
    "/indexing/editMartialstatus",
    "/indexing/editWorkstatus"})
public class EditIndexServlet extends HttpServlet {

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

        String url = "";
        switch (userPath) {
            case "/indexing/editMainBranch": {
                Mainbranch m_branch = mainbranchFacade.find(Short.parseShort(request.getParameter("id")));
                m_branch.setName(request.getParameter("name"));
                mainbranchFacade.edit(m_branch);
                url = "mainbranch";
                break;
            }

            case "/indexing/editBranch": {
                Branch branch = branchFacade.find(Short.parseShort(request.getParameter("id")));
                branch.setName(request.getParameter("name"));
                Mainbranch m_branch = mainbranchFacade.find(Short.parseShort(request.getParameter("mainbranch")));
                branch.setMainbranchId(m_branch);
                branchFacade.edit(branch);
                url = "branch";
                break;
            }

            case "/indexing/editCertificate":
                Certificate certificate = certificateFacade.find(Short.parseShort(request.getParameter("id")));
                certificate.setName(request.getParameter("name"));
                certificateFacade.edit(certificate);
                url = "certificate";
                break;
            case "/indexing/editPosition":
                Position position = positionFacade.find(Short.parseShort(request.getParameter("id")));
                position.setName(request.getParameter("name"));
                positionFacade.edit(position);
                url = "position";
                break;
            case "/indexing/editHealthleavetype":
                Typehealthleave healthleavetype = healthleavetypeFacade.find(Short.parseShort(request.getParameter("id")));
                healthleavetype.setName(request.getParameter("name"));
                healthleavetypeFacade.edit(healthleavetype);
                url = "healthleavetype";
                break;
            case "/indexing/editPunishmenttype":
                Typepunishment punishmenttype = punishmenttypeFacade.find(Short.parseShort(request.getParameter("id")));
                punishmenttype.setName(request.getParameter("name"));
                punishmenttypeFacade.edit(punishmenttype);
                url = "punishmenttype";
                break;
            case "/indexing/editUnpaidvacationtype":
                Typeunpaidvacation unpaidvtype = unpaidvacationtypeFacade.find(Short.parseShort(request.getParameter("id")));
                unpaidvtype.setName(request.getParameter("name"));
                unpaidvacationtypeFacade.edit(unpaidvtype);
                url = "unpaidvacationtype";
                break;
            case "/indexing/editDepartment": {
                Department department = departmentFacade.find(Short.parseShort(request.getParameter("id")));
                department.setName(request.getParameter("name"));
                departmentFacade.edit(department);
                url = "department";
                break;
            }
            case "/indexing/editSection": {
                Section section = sectionFacade.find(Short.parseShort(request.getParameter("id")));
                section.setName(request.getParameter("name"));
                Department department = departmentFacade.find(Short.parseShort(request.getParameter("department")));
                section.setDepartmentId(department);
                sectionFacade.edit(section);
                url = "section";
                break;
            }
            case "/indexing/editDevision": {
                Devision devision = devisionFacade.find(Short.parseShort(request.getParameter("id")));
                devision.setName(request.getParameter("name"));
                Section section = sectionFacade.find(Short.parseShort(request.getParameter("section")));
                devision.setSectionId(section);
                devisionFacade.edit(devision);
                url = "devision";
                break;
            }
            case "/indexing/editNatianality": {
                Natianality natianality = natianalityFacade.find(Short.parseShort(request.getParameter("id")));
                natianality.setName(request.getParameter("name"));
                natianalityFacade.edit(natianality);
                url = "natianality";
                break;
            }
            case "/indexing/editForeignlanguage": {
                Foreignlanguage foreignlanguage = foreignlanguageFacade.find(Short.parseShort(request.getParameter("id")));
                foreignlanguage.setName(request.getParameter("name"));
                foreignlanguageFacade.edit(foreignlanguage);
                url = "foreignlanguage";
                break;
            }
            case "/indexing/editCategory": {
                Category category = categoryFacade.find(Short.parseShort(request.getParameter("id")));
                category.setName(request.getParameter("name"));
                categoryFacade.edit(category);
                url = "category";
                break;
            }
            case "/indexing/editFamilystatus": {
                Familystatus familystatus = familystatusFacade.find(Short.parseShort(request.getParameter("id")));
                familystatus.setName(request.getParameter("name"));
                familystatusFacade.edit(familystatus);
                url = "familystatus";
                break;
            }
            case "/indexing/editMartialstatus": {
                Martialstatus martialstatus = martialstatusFacade.find(Short.parseShort(request.getParameter("id")));
                martialstatus.setName(request.getParameter("name"));
                martialstatusFacade.edit(martialstatus);
                url = "martialstatus";
                break;
            }
            case "/indexing/editWorkstatus": {
                Workstatus workstatus = workstatusFacade.find(Short.parseShort(request.getParameter("id")));
                workstatus.setName(request.getParameter("name"));
                workstatusFacade.edit(workstatus);
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

}
