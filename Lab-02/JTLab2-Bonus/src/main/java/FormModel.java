/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home pc
 */
public class FormModel {
    private String letters;
    private String size;
    private String category;
    
    public FormModel(String letters, String size, String category)
    {
        this.letters = letters;
        this.size = size;
        this.category = category;
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
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getCategory()
    {
        return this.category;
    }
}
