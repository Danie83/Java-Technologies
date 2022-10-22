
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home pc
 */
public class JDBCConnection 
{
    public static PGSimpleDataSource getDBDataSource()
    {
        try 
        {
            Class.forName("org.postgresql.Driver");
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        // makes no sense why I need arrays
        String[] serverNames = null;
        serverNames[0] = "localhost";
        int[] portNumbers = null;
        portNumbers[0] = 5432;
        dataSource.setServerNames(serverNames);
        dataSource.setPortNumbers(portNumbers);
        dataSource.setPortNumbers(portNumbers);
        dataSource.setDatabaseName("JTLab3");
        dataSource.setUser("potgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }
}
