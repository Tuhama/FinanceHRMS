/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reporting;

//import entity.Employee;
import entity.Branch;
import entity.Category;
import entity.Certificate;
import entity.Department;
import entity.Devision;
import entity.Devision_;
import entity.Employee;
import entity.Employee_;
import entity.Familystatus;
import entity.Martialstatus;
import entity.Natianality;
import entity.Section;
import entity.Workstatus;
import session.EmployeeFacade;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import session.BranchFacade;
import session.CategoryFacade;
import session.CertificateFacade;
import session.DepartmentFacade;
import session.DevisionFacade;
import session.FamilystatusFacade;
import session.MartialstatusFacade;
import session.NatianalityFacade;
import session.WorkstatusFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "ReportingServlet", urlPatterns = {"/reporting/allEmployeeRep",
    "/reporting/empRepView", "/reporting/freeRepView", "/reporting/printFreeRep"})
public class ReportingServlet extends HttpServlet {

    @PersistenceContext(unitName = "HRMSPU")
    private EntityManager em;
    @EJB
    private EmployeeFacade employeeFacade;
    @EJB
    private NatianalityFacade natianalityFacade;
    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private WorkstatusFacade workstatusFacade;
    @EJB
    private MartialstatusFacade martialstatusFacade;
    @EJB
    private BranchFacade branchFacade;
    @EJB
    private CertificateFacade certificateFacade;
    @EJB
    private DevisionFacade devisionFacade;
    @EJB
    private DepartmentFacade departmentFacade;
    @EJB
    private FamilystatusFacade familystatusFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        String userPath = request.getServletPath();
        switch (userPath) {
//طباعة بيانات كافة الموظفين 
            case "/reporting/allEmployeeRep":
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(employeeFacade.findAll());
                getServletContext().setAttribute("data_source", beanCollectionDataSource);

                userPath = "allEmployeeReport.jsp";
                url = "/reporting/reports/" + userPath;
                break;
            //طباعة ذاتية موظف
            case "/reporting/empRepView":
                int targetEmpId = Integer.parseInt(request.getParameter("id").trim().toLowerCase());
                getServletContext().setAttribute("emp_id", targetEmpId);
                getServletContext().getRequestDispatcher("/reporting/reports/employeeReport.jsp").forward(request, response);
                break;
            //طباعةبيانات الموظفين المتقاعدين
            case "/reporting/retiredRepView":
                getServletContext().getRequestDispatcher("/reporting/reports/retieredReport.jsp").forward(request, response);
                break;
            //طباعةبيانات الموظفين المنقولين
            case "/reporting/transferedRepView":
                getServletContext().getRequestDispatcher("/reporting/view/transferedReport.jsp").forward(request, response);
                break;
            //طباعةبيانات الموظفين المنقولين
            case "/reporting/freeRepView":
                getServletContext().getRequestDispatcher("/reporting/view/freeReportView.jsp").forward(request, response);
                break;
        }
        if (!"".equals(url)) {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userPath = request.getServletPath();

        switch (userPath) {

            case "/reporting/printFreeRep": {
                build_free_query(request, response);
                break;
            }

        }

    }

    private void build_free_query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rep_title = "قائمة الموظفين  ";

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> emp = cq.from(Employee.class);

        cq.select(emp);
        Predicate where = cb.conjunction();

        if (request.getParameter("nationality") != null) {
            Natianality nationality = natianalityFacade.find(Short.parseShort(request.getParameter("nationality")));
            where = cb.and(where, cb.equal(emp.get(Employee_.natianalityId), nationality));
            rep_title += "من الجنسية " + nationality.getName() + " ";
        }
        if (request.getParameter("gender") != null) {
            String gender = request.getParameter("gender");
            where = cb.and(where, cb.and(cb.equal(emp.get(Employee_.gender), gender)));
            if (gender.matches("ذكر")) {
                rep_title += "الذكور ";
            } else {
                rep_title += "الإناث ";
            }

        }
        if (request.getParameter("familystatus") != null) {
            Familystatus familystatus = familystatusFacade.find(Short.parseShort(request.getParameter("familystatus")));
            where = cb.and(where, cb.and(cb.equal(emp.get(Employee_.familystatusId), familystatus)));
            rep_title += "ذوو الحالة العائلية: " + familystatus.getName() + " ";

        }
        if (request.getParameter("martialstatus") != null) {
            Martialstatus martialstatus = martialstatusFacade.find(Short.parseShort(request.getParameter("martialstatus")));
            where = cb.and(where, cb.equal(emp.get(Employee_.martialstatusId), martialstatus));
            rep_title += " حالة الخدمة العسكرية:" + martialstatus.getName() + " ";

        }
        if (request.getParameter("certificate") != null) {
            Certificate cert = certificateFacade.find(Short.parseShort(request.getParameter("certificate")));
            where = cb.and(where, cb.and(cb.equal(emp.get(Employee_.certificateId), cert)));
            rep_title += "الذين يحملون شهادة " + cert.getName() + " ";

        }
        if (request.getParameter("branch") != null) {
            Branch branch = branchFacade.find(Short.parseShort(request.getParameter("branch")));
            where = cb.and(where, cb.and(cb.equal(emp.get(Employee_.branchId), branch)));
            rep_title += " من فرع محافظة " + branch.getName() + " ";

        }
        if (request.getParameter("department") != null) {

            //get sections of department
            Department dep = departmentFacade.find(Short.parseShort(request.getParameter("department")));
            Collection<Section> sec = dep.getSectionCollection();

            //get devisions of sections
            CriteriaBuilder cb1 = em.getCriteriaBuilder();
            CriteriaQuery<Devision> cq1 = cb.createQuery(Devision.class);
            Root<Devision> dev = cq1.from(Devision.class);
            cq1.where(cb1.in(dev.get(Devision_.sectionId)).in(sec));
            TypedQuery<Employee> q = em.createQuery(cq);
            List<Employee> devs = q.getResultList();

            //get employees of devisions of sections in chosen department
            where = cb.and(where, cb.and(cb.in(emp.get(Employee_.devisionId)).in(devs)));
            rep_title += "من قسم " + dep.getName() + " ";

        }
        if (request.getParameter("category") != null) {
            Category category = categoryFacade.find(Short.parseShort(request.getParameter("category")));
            where = cb.and(where, cb.and(cb.equal(emp.get(Employee_.categoryId), category)));
            rep_title += "من الفئة " + category.getName() + " ";

        }
        if (request.getParameter("workstatus") != null) {
            Workstatus workstatus = workstatusFacade.find(Short.parseShort(request.getParameter("workstatus")));
            where = cb.and(where, cb.and(cb.equal(emp.get(Employee_.workstatusId), workstatus)));
            rep_title += "ذوو الوضع الوظيفي :" + workstatus.getName() + " ";

        }
        cq.where(where);

        TypedQuery<Employee> q = em.createQuery(cq);
        List<Employee> results = q.getResultList();
        //  System.out.println(results);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(results);
        getServletContext().setAttribute("data_source", beanCollectionDataSource);
        getServletContext().setAttribute("rep_title", rep_title);
         getServletContext().getRequestDispatcher("/reporting/reports/freeReport.jsp").forward(request, response);
                
    }

}
