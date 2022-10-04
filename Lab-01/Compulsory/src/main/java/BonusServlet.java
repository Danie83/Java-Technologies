/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Home pc
 */
@WebServlet(urlPatterns = {"/bonus"})
public class BonusServlet extends HttpServlet {
    
    public static final Logger logger = Logger.getLogger("BonusServlet");  
    public static List<String> permutationsList = new ArrayList<>();
    public static List<String> wordsList = new ArrayList<>();
    public static boolean switcher = false;
    
    BonusServlet()
    {
        if (switcher == false)
        {
            setLogger();
            switcher = true;
        }
        
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
    
    public static List<String> readFile(String filename) throws FileNotFoundException, IOException
    {
        List<String> result;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            result = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null)
            {
                result.add(line.toLowerCase());
                line = bufferedReader.readLine();
            }
        }
        return result;
    }
    
    public static void setLogger()
    {
        FileHandler fileHandler;
        try
        {
            fileHandler = new FileHandler("C:\\Users\\Home pc\\Documents\\GitHub\\Java-Technologies\\Lab-01\\Compulsory\\src\\main\\resources\\server.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        }
        catch (IOException | SecurityException e)
        {
            System.out.println("filehandler");
        }
        
        logger.info("LOGGING BonusServlet");
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
        response.setContentType("text/plain");
        try ( PrintWriter out = response.getWriter()) {
            
            StringBuilder loggerBuilder = new StringBuilder();
            
            permutationsList.clear();
            
            // Take a request parameter 
            String requestParamName = "parameter";
            String requestParamValue = request.getParameter(requestParamName);
            
            String requestSizeName = "size";
            String requestSizeValue = request.getParameter(requestSizeName);
            
            String requestAccessName = "access";
            String requestAccessValue = request.getParameter(requestAccessName);
            
            try
            {
                // it just won't find it otherwise
                wordsList = readFile("C:\\Users\\Home pc\\Documents\\GitHub\\Java-Technologies\\Lab-01\\Compulsory\\src\\main\\resources\\word_file.txt");
            }
            catch (IOException e)
            {
                System.out.println("File was not found!");
            }
            
            // check if parameter exists
            if (!(requestParamValue.length() > 0))
            {
                out.print("Request parameter was not specified!");
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
                    out.println("Size is not an integer!");
                }
                
                if (checked == true)
                {
                    // check if the size option can be applied
                    if (requestParamValue.length() < Integer.parseInt(requestSizeValue))
                    {
                        out.println("Size should be less or equal to the size of the parameter!");
                    }
                    else
                    {
                        if (Integer.parseInt(requestSizeValue) == 0)
                        {
                            List<String> subStrings = new ArrayList<>();
                            for (int i = 1; i <= requestParamValue.length(); i++)
                            {
                                subStrings.addAll(getSubStrings(requestParamValue, i));
                            }
                            for (String sub : subStrings)
                            {
                                addPermutation(sub, "");
                            }
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
            
            loggerBuilder.append(request.getMethod()).append(" ")
                        .append(request.getRemoteAddr()).append(" ")
                        .append(request.getHeader("USER-AGENT")).append(" ")
                        .append("parameter: ").append(requestParamValue).append(" ")
                        .append("size: ").append(requestSizeValue).append(" ")
                        .append("access: ").append(requestAccessValue).append(" ");
            
            // getRemoteHost()
            
            // build the ordered list if list is not empty
            if (!permutationsList.isEmpty())
            {
                Collections.sort(permutationsList, byLengthAndThenAlphabetically); 
                if (requestAccessValue.equals("yes") && Integer.parseInt(requestSizeValue) == 0)
                {
                    StringBuilder sb = new StringBuilder();
                    for (String permutation : permutationsList)
                    {
                        for (String s : wordsList)
                        {
                            if (s.equals(permutation))
                            {
                                sb.append(permutation).append(",");
                                out.print(permutation);
                                out.print(",");
                                break;
                            }
                        }
                    }
                    loggerBuilder.append(" response: ").append(sb);
                }
                else
                {
                    StringBuilder sb = new StringBuilder();
                    for (String permutation : permutationsList)
                    {
                        sb.append(permutation).append(",");
                        out.print(permutation);
                        out.print(",");
                    }
                    loggerBuilder.append(" response: ").append(sb);
                }
            }
            else
            {
                out.println("Problem requirements were not met! (per)");
            }
            
            logger.info(loggerBuilder.toString());
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
