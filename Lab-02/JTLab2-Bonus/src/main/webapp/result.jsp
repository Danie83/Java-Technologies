<%-- 
    Document   : result
    Created on : 24.10.2022, 20:36:09
    Author     : Home pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Cookie categoryCookie = new Cookie("category", request.getParameter("category"));
    categoryCookie.setMaxAge(60*10); // 10 minutes
    response.addCookie(categoryCookie);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        table, th, td {
          border:1px solid black;
        }
    </style>
    <body>
        <%
            Cookie cookie = null;
            Cookie[] cookies = null;
            
            cookies = request.getCookies();
            
            if (cookies != null)
            {
                for (int i = 0; i < cookies.length; i++)
                {
                    cookie = cookies[i];
                    out.println("Cookie name: " + cookie.getName());
                    out.println("Cookie value: " + cookie.getValue());
                }
            }
            else
            {
                out.println("No cookie");
            }
        %>
        <%
            String category1 = request.getParameter("category");
            if (category1 != null && !category1.isEmpty())
            {
                out.println("<h1>Category is: "+category1+"</h1>");
            }
        %>
        <h1>Word is: ${param['letters']}</h1>
        <table>
            <tr>
                <%
                    String letters1 = request.getParameter("letters");
                    char[] lettersArray1 = letters1.toCharArray();
                    for (char ch : lettersArray1)
                    {
                        out.println("<th>"+ch+"</th>");
                    }
                %>
            </tr>
            <tr>
                <%
                    String letters2 = request.getParameter("letters");
                    String size2 = request.getParameter("size");
                    char[] lettersArray2 = letters2.toCharArray();
                    for (char ch : lettersArray2)
                    {
                        StringBuilder build = new StringBuilder();
                        for (int i = 0; i < Integer.parseInt(size2); i++)
                        {
                            build.append(ch);
                        }
                        out.println("<th>"+build.toString()+"</th>");
                    }
                %>
            </tr>
        </table>
    </body>
</html>
