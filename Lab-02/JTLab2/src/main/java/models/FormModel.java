/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Home pc
 */
public class FormModel {
    private String letters;
    private String size;
    
    public FormModel(String letters, String size)
    {
        this.letters = letters;
        this.size = size;
    }
    
    public void setLetters(String letters)
    {
        this.letters = letters;
    }
    
    public String getLetters()
    {
        return this.letters;
    }
    
    public void setSize(String size)
    {
        this.size = size;
    }
    
    public String getSize()
    {
        return this.size;
    }
}
