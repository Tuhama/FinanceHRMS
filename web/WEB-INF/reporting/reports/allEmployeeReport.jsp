<%-- 
    Document   : employeeReport
    Created on : Dec 27, 2015, 8:09:42 AM
    Author     : TUHAMA
--%>

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
 
 JRBeanCollectionDataSource beanCollectionDataSource = (JRBeanCollectionDataSource)getServletContext().getAttribute("data_source");
 //JRDataSource  
 //java.sql.Connection conn =  (java.sql.Connection)getServletContext().getAttribute("conn");                    
    File reportFile = new File(application.getRealPath("/resources/jReports/allEmpRep.jasper"));//your report_name.jasper file
    Map parameters = new HashMap();
    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters,beanCollectionDataSource);
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outStream = response.getOutputStream();
    outStream.write(bytes, 0, bytes.length);
    outStream.flush();
    outStream.close();
%>