/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home pc
 */
public class InitParameters 
{
    private static String defaultCategory;
    
    public InitParameters(String category)
    {
        this.defaultCategory = category;
    }
    
    public static void setDefaultCategory(String category)
    {
        InitParameters.defaultCategory = category;
    }
    
    public static String getDefaultCategory()
    {
        return InitParameters.defaultCategory;
    }
}

