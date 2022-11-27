
import javax.servlet.http.Part;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home pc
 */
public final class Utils {

    private Utils() 
    {
    }

    public static String getFileNameFromPart(Part part) 
    {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) 
        {
            if (content.trim().startsWith("filename")) 
            {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }

}
