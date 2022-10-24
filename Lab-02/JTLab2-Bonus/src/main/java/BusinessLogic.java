/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Home pc
 */
public class BusinessLogic {
    private static final String filename = "C:\\Users\\Home pc\\Documents\\GitHub\\Java-Technologies\\Lab-02\\JTLab2-Bonus\\src\\main\\resources\\server.log";
    public BufferedWriter bw = null;
    private static BusinessLogic instance = null;
    
    private BusinessLogic() throws IOException
    {
        FileWriter fw = new FileWriter(BusinessLogic.filename, true);
        this.bw = new BufferedWriter(fw);
    }
    
    public static BusinessLogic getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new BusinessLogic();
        }
        return instance;
    }
}
