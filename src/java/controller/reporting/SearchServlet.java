/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reporting;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import session.EmployeeFacade;
import entity.Employee;
import entity.Employee_;
import java.sql.Connection;
import java.sql.DriverManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Tuhama
 */
@WebServlet(name = "SearchRepServlet",
        urlPatterns = {
            "/reporting/searchRepView",
            "/reporting/searchRepEmp"})

public class SearchServlet extends HttpServlet {

    private Employee employee = new Employee();

    @PersistenceContext(unitName = "HRMSPU")
    private EntityManager em;

    @EJB
    private EmployeeFacade employeeFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userPath = request.getServletPath();

        switch (userPath) {

            case "/reporting/searchRepView":
                String url = "/reporting/search_reporting/employeeSearch.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                break;

            case "/reporting/searchRepEmp":
                String targetEmpStr = request.getParameter("term").trim().toLowerCase();
                sendMatchingEmployees(targetEmpStr, response);
                /* switch (action) {
                 case "complete":
                 String targetEmpStr = request.getParameter("id").trim().toLowerCase();
                 sendMatchingEmployees(targetEmpStr, response);
                 break;
                 case "lookup":
                 int targetEmpId = Integer.parseInt(request.getParameter("id").trim().toLowerCase());
                 // setEmployeeInfo(targetEmpId);
                 // java.sql.Connection conn = getJDBCConnection();//em.unwrap(java.sql.Connection.class);
                 // JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(employeeFacade.find(em));
                 getServletContext().setAttribute("emp_id", targetEmpId);
                 getServletContext().getRequestDispatcher("/WEB-INF/reporting/reports/employeeReport.jsp").forward(request, response);
                
                 break;
                 }*/
                break;
            
        }

    }

    private void setEmployeeInfo(int targetId) throws NumberFormatException {
        employee = employeeFacade.find(targetId);
        getServletContext().setAttribute("employee", employee);
        getServletContext().setAttribute("emp_punishments", employee.getEmpPunishmentCollection());
        getServletContext().setAttribute("emp_rewards", employee.getEmpRewardCollection());
        getServletContext().setAttribute("emp_trainingcourses", employee.getEmpTrainingcourseCollection());
        getServletContext().setAttribute("emp_unpaid_vs", employee.getEmpUnpaidvacationCollection());
        getServletContext().setAttribute("emp_helthleaves", employee.getEmpHealthleavCollection());
        getServletContext().setAttribute("emp_service_js", employee.getEmpServicejoinCollection());
        getServletContext().setAttribute("emp_events", employee.getEmpEventCollection());
        /*        if (SearchServlet.getOp_mode() == SearchServlet.UPDATE_MODE) {
         getServletContext().getRequestDispatcher("/WEB-INF/staffing/view/editEmployeeT.jsp").forward(request, response);
         } else if (SearchServlet.getOp_mode() == SearchServlet.REPORT_MODE) //getServletContext().getRequestDispatcher("/WEB-INF/reporting/reports/employeeReport.jsp").forward(request, response);
         {
         //java.sql.Connection conn = em.unwrap(java.sql.Connection.class);
         JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(employeeFacade.findAll());
         getServletContext().setAttribute("data_source", beanCollectionDataSource);
         getServletContext().getRequestDispatcher("/WEB-INF/reporting/reports/allEmployeeReport.jsp").forward(request, response);
         }*/
    }

    //send the search results to user
    private void sendMatchingEmployees(String emp_name, HttpServletResponse response) throws IOException {
        // check if user sent empty string
        if (!emp_name.equals("")) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
            Root<Employee> emp = cq.from(Employee.class);

            cb.like(emp.get(Employee_.firstname), emp_name + "%");
            cb.or(cb.like(emp.get(Employee_.lastname), emp_name + "%"));

            cq.where(cb.like(cb.concat(cb.concat(emp.get(Employee_.firstname), " "), emp.get(Employee_.lastname)), emp_name + "%"));

            TypedQuery<Employee> q = em.createQuery(cq);
            List<Employee> results = q.getResultList();

            JsonArrayBuilder a = Json.createArrayBuilder();
            results.stream().forEach((elem) -> {
                JsonObjectBuilder empBuilder = Json.createObjectBuilder();
                String value = elem.getFirstname() + " " + elem.getFathername() + " " + elem.getLastname();

                empBuilder.add("id", elem.getId())
                        .add("value", value);
                a.add(empBuilder);
            });
            StringWriter stringWriter = new StringWriter();
            try (JsonWriter writer = Json.createWriter(stringWriter)) {
                writer.write(a.build());
            }

            response.setContentType("text/json;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(stringWriter.getBuffer().toString());
        }
    }

}
