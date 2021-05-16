
package unitTest;

import dao.ClassNameNotFound;
import java.sql.SQLException;
import main.RunService;
import org.junit.Test;
import service.Service;


public class ServiceTestTest {
    
    public ServiceTestTest() {
    }
    Service sv = new Service();
   
   
    @Test
    public void studentList() throws SQLException{
       sv.studentList(3);
       
    }
    @Test
    public void numPages(){
         sv.totalPages(); 
        
    }
    
    @Test
    public void deleted(){
        System.out.println(sv.deleteStudent(130));
    }
   
     
}
