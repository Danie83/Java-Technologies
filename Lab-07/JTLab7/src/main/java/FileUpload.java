
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home pc
 */
@Named(value="fileUploadBean")
@ManagedBean
@ViewScoped
public class FileUpload implements Serializable {

    private static final long serialVersionUID = 3L;

    private Part file1;
    private String message;
    
    @PersistenceContext
    private EntityManager em;

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String uploadFile() throws IOException 
    {
        boolean file1Success = false;

        if (file1 != null && file1.getSize() > 0) 
        {
            String fileName = Utils.getFileNameFromPart(file1);
            
            File savedFile = new File("/jsf2-file-upload", fileName);

            try (InputStream input = file1.getInputStream()) 
            {
                Files.copy(input, savedFile.toPath());
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }

            file1Success = true;
        }

        if (file1Success) 
        {
            setMessage("File successfully uploaded");
        } 
        else 
        {
            setMessage("Error, select a file!");
        }

        return null;
    }
}
