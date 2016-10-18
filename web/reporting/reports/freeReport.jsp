<%-- 
    Document   : employeeReport
    Created on : Dec 27, 2015, 8:09:42 AM
    Author     : TUHAMA
--%>


<%@page contentType="application/pdf" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>

<%@page import="net.sf.jasperreports.engine.*" %>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.FileNotFoundException" %>
<%@page import="java.io.InputStream" %>

<%
 
 JRBeanCollectionDataSource beanCollectionDataSource = (JRBeanCollectionDataSource)getServletContext().getAttribute("data_source");
     String title=(String)getServletContext().getAttribute("rep_title");              
    File reportFile = new File(application.getRealPath("/resources/jReports/freeRep.jasper"));
    Map parameters = new HashMap();
    parameters.put("title", title);
    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters,beanCollectionDataSource);
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outStream = response.getOutputStream();
    outStream.write(bytes, 0, bytes.length);
    outStream.flush();
    outStream.close();
%>