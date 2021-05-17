
package unitTest;

import dao.ClassNameNotFound;
import org.junit.Test;
import dao.DaoClient;
import dao.DatabaseDriverManagerError;
import dao.ErrorInserEstudanteDatabase;
import dao.EstudanteFetchProblemsError;
import dao.FetchFuncionarioError;
import dao.LoginQueryError;
import dao.NoFuncionarioFetch;
import java.util.ArrayList;
import pojo.Estudante;
import java.sql.SQLException;





public class AllTestTest {
    
    public AllTestTest() {
    }

    @org.junit.Test
    public void testSomeMethod() {
    }
    
    @Test
    public void login() throws DatabaseDriverManagerError, ClassNameNotFound, LoginQueryError{
        DaoClient.login("aaa", "aa");
    }
    
    @Test
    public void funcionario() throws DatabaseDriverManagerError, ClassNameNotFound, LoginQueryError, FetchFuncionarioError, NoFuncionarioFetch{
          DaoClient.funcionario(1);
    }
    
    @Test 
    public void listarEstudantes() throws EstudanteFetchProblemsError, DatabaseDriverManagerError, ClassNameNotFound, SQLException{
        ArrayList<Estudante> est = new ArrayList(); 
           DaoClient.daoLista(4);
    }
    
     @Test 
    public void insert() throws EstudanteFetchProblemsError, DatabaseDriverManagerError, ClassNameNotFound, ErrorInserEstudanteDatabase{
          //Assert.assertSame(1, DaoClient.insert("Tomas", "Guindza", "Etica", "C.humanas"));
    }
    

    @Test
    public void numEstudante() throws DatabaseDriverManagerError, ClassNameNotFound, SQLException{
       int a;
        a = DaoClient.numeroEstudante();
        //System.out.println(a);
    }
    
     @Test
     public void list() throws DatabaseDriverManagerError, ClassNameNotFound, SQLException, EstudanteFetchProblemsError{
           ArrayList<Estudante>estudante = DaoClient.daoLista(20);
    }
    
     @Test
     public void delete() throws DatabaseDriverManagerError, ClassNameNotFound, SQLException{
         DaoClient.DaoEstudanteDelete(200);
     }
}
