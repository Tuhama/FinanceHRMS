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
@WebServlet(name = "AddIndexServlet", urlPatterns = {"/indexing/addMainBranch","/indexing/addBranch","/indexing/addCertificate","/indexing/addPosition","/indexing/addHealthleavetype","/indexing/addPunishmenttype","/indexing/addUnpaidvacationtype", "/indexing/addDepartment","/indexing/addSection", "/indexing/addDevision","/indexing/addNatianality", "/indexing/addForeignlanguage", "/indexing/addCategory","/indexing/addFamilystatus", "/indexing/addMartialstatus", "/indexing/addWorkstatus"})
public class AddIndexServlet extends HttpServlet {

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
        //ضرورية حتى يتم ارسال البيانات من صفحة الويب باللغة العربية
        request.setCharacterEncoding("UTF-8");

        String userPath = request.getServletPath();

        String url = "";
        switch (userPath) {
            case "/indexing/addMainBranch": {
                Mainbranch m_branch = new Mainbranch();
                m_branch.setName(request.getParameter("name"));
                mainbranchFacade.create(m_branch);
                url = "mainbranch";
                break;
            }
            case "/indexing/addBranch": {
                Branch branch = new Branch();
                branch.setName(request.getParameter("name"));
                Mainbranch m_branch = mainbranchFacade.find(Short.parseShort(request.getParameter("mainbranch")));
                branch.setMainbranchId(m_branch);
                branchFacade.create(branch);
                url = "branch";
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
            case "/indexing/deleteBranch": {
                Branch branch = branchFacade.find(Short.parseShort(request.getParameter("id")));
                branchFacade.remove(branch);
                url = "branch";
                break;
            }
            case "/indexing/addCertificate":
                Certificate certificate = new Certificate();
                certificate.setName(request.getParameter("name"));
                certificateFacade.create(certificate);
                url = "certificate";
                break;
            case "/indexing/addPosition":
                Position position = new Position();
                position.setName(request.getParameter("name"));
                positionFacade.create(position);
                url = "position";
                break;
            case "/indexing/addHealthleavetype":
                Typehealthleave healthleavetype = new Typehealthleave();
                healthleavetype.setName(request.getParameter("name"));
                healthleavetypeFacade.create(healthleavetype);
                url = "healthleavetype";
                break;
            case "/indexing/addPunishmenttype":
                Typepunishment punishmenttype = new Typepunishment();
                punishmenttype.setName(request.getParameter("name"));
                punishmenttypeFacade.create(punishmenttype);
                url = "punishmenttype";
                break;
            case "/indexing/addUnpaidvacationtype":
                Typeunpaidvacation unpaidvtype = new Typeunpaidvacation();
                unpaidvtype.setName(request.getParameter("name"));
                unpaidvacationtypeFacade.create(unpaidvtype);
                url = "unpaidvacationtype";
                break;
            case "/indexing/addDepartment": {
                Department department = new Department();
                department.setName(request.getParameter("name"));
                departmentFacade.create(department);
                url = "department";
                break;
            }
            case "/indexing/addSection": {
                Section section = new Section();
                section.setName(request.getParameter("name"));
                Department department = departmentFacade.find(Short.parseShort(request.getParameter("department")));
                section.setDepartmentId(department);
                sectionFacade.create(section);
                url = "section";
                break;
            }
            case "/indexing/addDevision": {
                Devision devision = new Devision();
                devision.setName(request.getParameter("name"));
                Section section = sectionFacade.find(Short.parseShort(request.getParameter("section")));
                devision.setSectionId(section);
                devisionFacade.create(devision);
                url = "devision";
                break;
            }
            case "/indexing/addNatianality": {
                Natianality natianality = new Natianality();
                natianality.setName(request.getParameter("name"));
                natianalityFacade.create(natianality);
                url = "natianality";
                break;
            }
            case "/indexing/addForeignlanguage": {
                Foreignlanguage foreignlanguage = new Foreignlanguage();
                foreignlanguage.setName(request.getParameter("name"));
                foreignlanguageFacade.create(foreignlanguage);
                url = "foreignlanguage";
                break;
            }
            case "/indexing/addCategory": {
                Category category = new Category();
                category.setName(request.getParameter("name"));
                categoryFacade.create(category);
                url = "category";
                break;
            }
            case "/indexing/addFamilystatus": {
                Familystatus familystatus = new Familystatus();
                familystatus.setName(request.getParameter("name"));
                familystatusFacade.create(familystatus);
                url = "familystatus";
                break;
            }
            case "/indexing/addMartialstatus": {
                Martialstatus martialstatus = new Martialstatus();
                martialstatus.setName(request.getParameter("name"));
                martialstatusFacade.create(martialstatus);
                url = "martialstatus";
                break;
            }
            case "/indexing/addWorkstatus": {
                Workstatus workstatus = new Workstatus();
                workstatus.setName(request.getParameter("name"));
                workstatusFacade.create(workstatus);
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
