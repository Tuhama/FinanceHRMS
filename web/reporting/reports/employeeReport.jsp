<%-- 
    Document   : employeeReport
    Created on : Dec 27, 2015, 8:09:42 AM
    Author     : TUHAMA
--%>

<%@page import="controller.ControllerServlet"%>
<%@page import="controller.reporting.SearchServlet"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="application/pdf" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>

<%@page import="net.sf.jasperreports.engine.*" %>

<%@page import="java.io.File" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.FileNotFoundException" %>
<%@page import="java.io.InputStream" %>

<%@page import="java.sql.Connection" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.SQLException" %>

<%

    File reportFile = new File(application.getRealPath("/resources/jReports/emp_rep.jasper"));//your report_name.jasper file
    
    
    java.sql.Connection conn =//(Connection) getServletContext().getAttribute("DBConnection"); 
            ControllerServlet.getJDBCConnection();
    
    Map parameters = new HashMap();
    int emp_id =(Integer)getServletContext().getAttribute("emp_id");
    parameters.put("emp_id", emp_id);
    
    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters,conn);
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition","inline;filename=" + "empRep.pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outStream = response.getOutputStream();
    outStream.write(bytes, 0, bytes.length);
    outStream.flush();
    outStream.close();
%>