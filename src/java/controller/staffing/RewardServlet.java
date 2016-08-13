/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staffing;

import entity.EmpReward;
import entity.Employee;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EmpRewardFacade;
import session.EmployeeFacade;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "RewardServlet", urlPatterns = {"/addReward", "/editReward", "/deleteReward"})
public class RewardServlet extends HttpServlet {

    public static final int INSERT_MODE = 0;
    public static final int UPDATE_MODE = 1;
    public static final int REPORT_MODE = 2;

    //to know if the operation is update or insert to the database
    private static int op_mode = INSERT_MODE;

    private Employee employee = new Employee();

    private final SimpleDateFormat vSDF = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat vSDF2 = new SimpleDateFormat("dd/MM/yyyy");

    @EJB
    private EmpRewardFacade empRewardFacade;
    @EJB
    private EmployeeFacade employeeFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        employee = (Employee) getServletContext().getAttribute("current_employee");
        String userPath = request.getServletPath();

        try {
            switch (userPath) {

                case "/addReward": {
                    setOp_mode(INSERT_MODE);
                    flushReward(request, response);
                    break;
                }
                case "/editReward": {
                    setOp_mode(UPDATE_MODE);
                    flushReward(request, response);
                    break;
                }
                case "/deleteReward": {
                    deleteReward(request, response);
                    break;
                }
            }

            //response.sendRedirect(url);
            //} catch (NumberFormatException | ParseException | IOException ex) {
        } catch (NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void flushReward(HttpServletRequest request, HttpServletResponse response) throws ParseException, NumberFormatException {

        if (request.getParameter("currentEmp") != null) {
            employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        } else {
            employee = (Employee) getServletContext().getAttribute("employee");
        }

        EmpReward empReward = new EmpReward();
        if (request.getParameter("id") != null) {
            empReward = empRewardFacade.find(Integer.parseInt(request.getParameter("id")));
        }
        empReward.setKind(request.getParameter("kind"));
        empReward.setAmount(Integer.parseInt(request.getParameter("amount")));
        empReward.setDocdate(vSDF.parse(request.getParameter("docdate")));
        empReward.setDocnumber(request.getParameter("docnumber"));
        empReward.setDoctype(request.getParameter("doctype"));
        employee = employeeFacade.find(Integer.parseInt(request.getParameter("currentEmp").trim()));
        empReward.setEmployeeId(employee);
        String json;
        if (getOp_mode() == INSERT_MODE) {
            empRewardFacade.create(empReward);
            employee.getEmpRewardCollection().add(empReward);
            getServletContext().setAttribute("emp_rewards", employee.getEmpRewardCollection());

            json = reward2json(empReward);

        } else {
            json = "ok";
            empRewardFacade.edit(empReward);
        }

        try {
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            /////no space befor or after..it causes problems with json parsing
            response.getWriter().write(json);

        } catch (IOException ex) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    private String reward2json(EmpReward empReward) {
        String s = "{";
        s += "\"" + "id" + "\"" + ":" + "\"" + empReward.getId() + "\"";
        s += ",";
        s += "\"" + "kind" + "\"" + ":" + "\"" + empReward.getKind() + "\"";
        s += ",";
        s += "\"" + "amount" + "\"" + ":" + "\"" + empReward.getAmount() + "\"";
        s += ",";
        s += "\"" + "doctype" + "\"" + ":" + "\"" + empReward.getDoctype() + "\"";
        s += ",";
        s += "\"" + "docnumber" + "\"" + ":" + "\"" + empReward.getDocnumber() + "\"";
        s += ",";
        s += "\"" + "docdate" + "\"" + ":" + "\"" + vSDF2.format(empReward.getDocdate()) + "\"";
        s += "}";

        return s;
    }

    private void deleteReward(HttpServletRequest request, HttpServletResponse response) {
        EmpReward empReward = empRewardFacade.find(Integer.parseInt(request.getParameter("id")));
        empRewardFacade.remove(empReward);
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
}
