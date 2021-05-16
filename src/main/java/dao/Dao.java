
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class Dao {
         
      static Connection conn = null;
      static PreparedStatement prepare = null;
      private static final long serialVersionUID = 1L;
      private static final Logger log = Logger.getLogger(Dao.class);
      
      public static Connection dbConnect() throws DatabaseDriverManagerError, ClassNameNotFound{
          String log4JClassPath = "C:\\Users\\Virgilio Mulungo\\Documents\\NetBeansProjects\\donzane\\src\\main\\resources\\log.properties";
          PropertyConfigurator.configure(log4JClassPath);
           try {
              Class.forName("com.mysql.cj.jdbc.Driver");
          } catch (ClassNotFoundException ex) {
              log.info("Database connect fatal error");
              throw new ClassNameNotFound(ex,"Please review you ClassForName");
          }
           
          try {
              conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/escola?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root","tryitn0w");
          } catch (SQLException ex) {
                log.info("problems with drive manager", ex);
                throw new DatabaseDriverManagerError(ex,"problems with Drivemanager please review that");
          }
          return conn;
     }
      
}
