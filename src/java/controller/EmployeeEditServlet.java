/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Employee;
import entity.Employee_;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.ejb.EJB;
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

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import session.EmployeeFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "EmployeeEditServlet", urlPatterns = {})
public class EmployeeEditServlet extends HttpServlet {

    @PersistenceContext(unitName = "HRMSPU")
    private EntityManager em;

    Employee employee;

    @EJB
    private EmployeeFacade employeeFacade;

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
            case "/editEmployee":
                userPath = "employeeSearch.jsp";
                String url = "/WEB-INF/staffing/view/" + userPath;
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/autocomplete":
                String action = request.getParameter("action");
                String targetId = request.getParameter("id");

                if (targetId != null) {
                    targetId = targetId.trim().toLowerCase();
                } else {
                    getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                }

                if (action.equals("complete")) {

                    // check if user sent empty string
                    if (!targetId.equals("")) {
                        CriteriaBuilder cb = em.getCriteriaBuilder();
                        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
                        Root<Employee> emp = cq.from(Employee.class);

                        cb.like(emp.get(Employee_.firstname), targetId + "%");
                        cb.or(cb.like(emp.get(Employee_.lastname), targetId + "%"));

                        cq.where(cb.like(
                                cb.concat(cb.concat(emp.get(Employee_.firstname), " "), emp.get(Employee_.lastname)), targetId + "%"));

                        TypedQuery<Employee> q = em.createQuery(cq);
                        List<Employee> results = q.getResultList();

                        JsonArrayBuilder a = Json.createArrayBuilder();
                        results.stream().forEach((elem) -> {
                            JsonObjectBuilder empBuilder = Json.createObjectBuilder();
                            empBuilder.add("id", elem.getId())
                                    .add("firstname", elem.getFirstname())
                                    .add("lastname", elem.getLastname());
                            a.add(empBuilder);
                        });
                        StringWriter stringWriter = new StringWriter();
                        try (JsonWriter writer = Json.createWriter(stringWriter)) {
                            writer.writeArray(a.build());
                        }

                        response.setContentType("text/json");
                        response.setHeader("Cache-Control", "no-cache");
                        response.getWriter().write(stringWriter.getBuffer().toString());
                    }
                } else if (action.equals("lookup")) {
                    employee = employeeFacade.find(Integer.parseInt(targetId.trim()));
                    request.setAttribute("employee", employee);
                    getServletContext().getRequestDispatcher("/WEB-INF/staffing/view/editEmployee.jsp").forward(request, response);
                }
                break;
        }
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
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userPath = request.getServletPath();
        switch (userPath) {
            case "/editSaveEmployee":
                
                //userPath = "employeeSearch.jsp";
               // String url = "/WEB-INF/staffing/view/" + userPath;
                //request.getRequestDispatcher(url).forward(request, response);
                break;
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
