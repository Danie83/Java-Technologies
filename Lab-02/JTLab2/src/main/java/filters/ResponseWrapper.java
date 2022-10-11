/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Wrapper class for response decoration
 * 
 * @author Home pc
 */
public class ResponseWrapper extends HttpServletResponseWrapper 
{
    private StringWriter output;
    
    public String toString() {
        return output.toString();
    }
    
    public ResponseWrapper(HttpServletResponse response) 
    {
        super(response);
        output = new StringWriter();
    }
    
    public PrintWriter getWriter() 
    {
        return new PrintWriter(output,false);
    }
    
    @Override
    public ServletOutputStream getOutputStream() throws IOException 
    {
        // virtual stream, prevents closing stream
        return new ServletOutputStream() 
        {
            @Override
            public void write(int b) throws IOException 
            {
            
            }
            
            @Override
            public void setWriteListener(WriteListener writeListener) 
            {
            
            }
            
            @Override
            public boolean isReady() 
            {
                return true;
            }
        };
    }
}
