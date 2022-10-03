/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Home pc
 */
@WebServlet(urlPatterns = {"/homework"})
public class HomeworkServlet extends HttpServlet {
    
    public static List<String> sequencesList = new ArrayList<>();
    public static List<String> permutationsList = new ArrayList<>();
    
    public static void addSubsequence(String word, String sub)
    {
        // found, add to list 
        if (word.length() == 0)
        {
            if (sub.length() != 0 && !sequencesList.contains(sub))
            {
                sequencesList.add(sub);
            }
            return;
        }
        
        // take out the first character from the main string and add it to the sub
        addSubsequence(word.substring(1), sub + word.charAt(0));
        // just take out the first character
        addSubsequence(word.substring(1), sub);
    }
    
    public static List<String> getSubStrings(String word, int permitedSize)
    {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < word.length(); i++)
        {
            for (int j = i + 1; j <= word.length(); j++)
            {
                String tmp = word.substring(i, j);
                if (tmp.length() == permitedSize)
                {
                    result.add(tmp);
                }
            }
        }
        return result;
    }
    
    public static void addPermutation(String word, String sub)
    {
        if (word.length() == 0)
        {
            if (sub.length() != 0 && !permutationsList.contains(sub))
            {
                permutationsList.add(sub);
            }
            return;
        }
        
        for (int i = 0; i < word.length(); i++)
        {
            // take a character from the ith position
            char character = word.charAt(i);
            
            // take out take character from the string
            String tmp = word.substring(0, i) + word.substring(i + 1);
            
            // recursive call
            addPermutation(tmp, sub + character);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            // clear previous sequence results
            sequencesList.clear();
            permutationsList.clear();
            
            // Take a request parameter 
            String requestParamName = "parameter";
            String requestParamValue = request.getParameter(requestParamName);
            
            String requestSizeName = "size";
            String requestSizeValue = request.getParameter(requestSizeName);
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeworkServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            // check if parameter exists
            if (!(requestParamValue.length() > 0))
            {
                out.println("<h1>Request parameter was not specified!</h1>");
            }
            else
            {
                // if size paramtere is not specified, set it to the default "0"
                if(!(requestSizeValue.length() > 0))
                {
                    requestSizeValue = "0";
                }
                
                // check if size parameter is a valid integer!
                boolean checked = true;
                try
                {
                    Integer.parseInt(requestSizeValue);
                }
                catch (NumberFormatException expection)
                {
                    checked = false;
                    out.println("<h1>Size is not an integer!</h1>");
                }
                
                if (checked == true)
                {
                    // check if the size option can be applied
                    if (requestParamValue.length() < Integer.parseInt(requestSizeValue))
                    {
                        out.println("<h1>Size should be less or equal to the size of the parameter!</h1>");
                    }
                    else
                    {
                        if (Integer.parseInt(requestSizeValue) == 0)
                        {
                            // get the sequences
                            addSubsequence(requestParamValue, "");
                        }
                        else
                        {
                            List<String> subStrings = getSubStrings(requestParamValue, Integer.parseInt(requestSizeValue));
                            for (String sub : subStrings)
                            {
                                addPermutation(sub, "");
                            }
                        }
                    }
                }
            }
            
            // comparator for sorting by length first and then alphabetically (for reading purposes)
            Comparator<String> byLengthAndThenAlphabetically = (String s1, String s2) -> {
                if (s1.length() != s2.length())
                {
                    return s1.length() - s2.length();
                }
                return s1.compareTo(s2);
            };
            
            // build the ordered list if list is not empty
            if (Integer.parseInt(requestSizeValue) == 0)
            {
                if (!sequencesList.isEmpty())
                {
                    Collections.sort(sequencesList, byLengthAndThenAlphabetically);
                    out.println("<ol>");
                    for (String sequence : sequencesList)
                    {
                        out.println("<li>" + sequence + "</li>");
                    }
                    out.println("</ol>");
                }
                else
                {
                    out.println("<h1> Problem requirements were not met! (seq) </h1>");
                }
            }
            else
            {
                if (!permutationsList.isEmpty())
                {
                    Collections.sort(permutationsList, byLengthAndThenAlphabetically);
                    out.println("<ol>");
                    for (String permutation : permutationsList)
                    {
                        out.println("<li>" + permutation + "</li>");
                    }
                    out.println("</ol>");
                }
                else
                {
                    out.println("<h1> Problem requirements were not met! (per) </h1>");
                }
            }
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
