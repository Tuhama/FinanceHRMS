/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reporting;

//import entity.Employee;
import session.EmployeeFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author TUHAMA
 */
@WebServlet(name = "ReportingServlet", urlPatterns = {"/reporting/allEmployeeRep"})
public class ReportingServlet extends HttpServlet {
    
    @EJB
    private EmployeeFacade employeeFacade;
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        String userPath = request.getServletPath();
        switch (userPath) {

            case "/reporting/allEmployeeRep":
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(employeeFacade.findAll());
                getServletContext().setAttribute("data_source", beanCollectionDataSource);
                
                userPath = "allEmployeeReport.jsp";
                url = "/reporting/reports/" + userPath;
                break;
            case "/reporting/emp_report":
                /*String targetId = request.getParameter("id");
                 if (targetId != null) {
                 targetId = targetId.trim().toLowerCase();
                
                 Employee employee = employeeFacade.find(Integer.parseInt(targetId));
                
                 request.setAttribute("employee", employee);
                 userPath = "employeeReport.jsp";
                 url = "/WEB-INF/reporting/reports/" + userPath;*/
                   // java.sql.Connection conn = em.unwrap(java.sql.Connection.class);

                //url = "";
                try {
                    /*java.sql.Connection conn = em.unwrap(java.sql.Connection.class);
                        
                     // String targetFileName = reportFileName.replace(".jrxml", ".pdf");
                     //JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
                     String reportFileName = "Blank_A4.jrxml";
                     String reportPath = "C:\\Users\\TUHAMA\\Documents\\jReports\\" + reportFileName;
                        
                     //String targetFileName = reportFileName.replace(".jrxml", ".pdf");
                     JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
                     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
                     ServletOutputStream outputstream = response.getOutputStream();
                     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                     JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
                     response.setContentType("application/pdf");
                     outputstream.write(byteArrayOutputStream.toByteArray());
                     response.setHeader("Cache-Control", "max-age=0");
                     response.setHeader("Content-Disposition", "attachment; filename=" + "report.pdf");
                     outputstream.flush();
                     outputstream.close();*/

                    /*                        //for parameters
                     HashMap map = new HashMap();
                     map.put("empID", "MCA001");
                        
                     JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(employeeFacade.findAll());
                     JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, map, beanCollectionDataSource);*/
                        //  jasperPrint = JasperFillManager.fillReport(jasperReport, null,conn);
                        /*                        ServletOutputStream outputstream = response.getOutputStream();
                     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                     JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
                     response.setContentType("application/pdf");
                     outputstream.write(byteArrayOutputStream.toByteArray());
                     response.setHeader("Cache-Control", "max-age=0");
                     response.setHeader("Content-Disposition", "attachment; filename=" + targetFileName);
                     outputstream.flush();
                     outputstream.close();*/
                        //response.addHeader("Content-disposition", "attachment; filename=report.pdf");
                    //ServletOutputStream servletOutputStream=response.getOutputStream();
                    //JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                    //this.getServletContext().responseComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //}
                break;
        }
        if (!"".equals(url)) {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

}
