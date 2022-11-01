<%-- 
    Document   : team
    Created on : 01.11.2022, 17:23:13
    Author     : Home pc
--%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,com.ddf.Team"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Test</title>
    </head>

    <body>
        <hr><ol> <%
            @SuppressWarnings("unchecked") 
            List<Team> teams = (List<Team>)request.getAttribute("teams");
            if (teams != null) {
                for (Team team : teams){ %>
                    <li> <%= team %> </li> <%
                }
            } %>
        </ol><hr>
     </body>
 </html>