
package dao;
;
import com.mysql.cj.protocol.Resultset;
import static dao.Dao.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojo.Estudante;
import pojo.Funcionario;
import static dao.Dao.prepare;
import java.util.ArrayList;
import static dao.Dao.dbConnect;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class DaoClient {
            private static final long serialVersionUID = 1L;
            private static final Logger log = Logger.getLogger(DaoClient.class);
      
        public static int login(String username, String pass) throws LoginQueryError, DatabaseDriverManagerError, ClassNameNotFound{
                String query = "select id_login from login where username = ? and pass = ?";
                   int id_login = 0;
                   conn = dbConnect();
            try {
                prepare = conn.prepareStatement(query);
                prepare.setString(1, username);
                prepare.setString(2, pass);
                ResultSet rs =  Dao.prepare.executeQuery();
                if(rs.next()==false){
                    
                    return 0;
                }
               
                while(rs.next()){
                    id_login = rs.getInt("id_login");
                }
                
            } catch (SQLException ex) {
                  log.info("Failed login sql");
                  throw new LoginQueryError("Problems with login query please review the query",ex);
            }
            return id_login;
        }
        
        public static Funcionario funcionario(int id_login) throws FetchFuncionarioError, NoFuncionarioFetch, DatabaseDriverManagerError, ClassNameNotFound{
            conn = dbConnect();
            String query = "select funcionario.id_funcionario, funcionario.nome_completo"
              + ",funcionario.telefone from login inner join funcionario where login.id_login = ? and funcionario.id_funcionario= ?";
 
            int id_funcionario = 0;
            String nome_completo = null;
            String telefone = null;
            
            try {
                prepare = conn.prepareStatement(query);
                prepare.setInt(1, id_login);
                prepare.setInt(2, id_login);
                ResultSet rs = prepare.executeQuery();
                if(rs.next() == false){
                   log.info("No funcionario fetching");
                   Funcionario funcio = new Funcionario(id_funcionario, nome_completo, telefone);
                   return funcio;
                }
          
                while(rs.next()){
                    id_funcionario = rs.getInt("id_funcionario");
                    nome_completo = rs.getString("nome_completo");
                    telefone = rs.getString("telefone");
                }
            } catch (SQLException ex) {
                log.info("Query to fetch Funcionario with login Id Failed");
                throw new FetchFuncionarioError("Fetch Funcionarios error", ex);
                
            }
            Funcionario func = new Funcionario(id_funcionario, nome_completo, telefone);
            return func;
        }
        
        public static int numeroEstudante() throws DatabaseDriverManagerError, ClassNameNotFound, SQLException{
            String query = "select count(*) as todos from estudante";
            conn = dbConnect();
            prepare = conn.prepareStatement(query);
           ResultSet rs = prepare.executeQuery();
            int num =0;
            while(rs.next()){
               num = rs.getInt("todos");
            }
            return num;
        }
        
        public static ArrayList<Estudante> daoLista(int pagina) throws EstudanteFetchProblemsError, DatabaseDriverManagerError, ClassNameNotFound, SQLException{
            conn = dbConnect();
            ArrayList<Estudante> est = new ArrayList<Estudante>();
            int limit = 10;
            int inicio = pagina-1;
            int valuePage = (inicio*limit);
            String query = "select *from estudante limit ?,?";
            try {
                prepare = conn.prepareStatement(query);
                prepare.setInt(1,valuePage);
                prepare.setInt(2,limit);
                ResultSet rs = prepare.executeQuery();
                
                if(rs.next()==false){
                  log.info("no Student in database!");
                  return est;
                }
                while(rs.next()){
                    Estudante estudante = new Estudante(rs.getInt("id"), rs.getString("name"),rs.getString("surname"), rs.getString("course"),rs.getString("college"));
                    est.add(estudante);
                }
            } catch (SQLException ex) {
                log.info("Problems with fetch all estudante query");
                throw new EstudanteFetchProblemsError("Problems with fetch all estudante query",ex);
            }
              return est;
        }
        
        public static int insert(String name,String surname ,String course, String college ) throws ErrorInserEstudanteDatabase, DatabaseDriverManagerError, ClassNameNotFound{
            conn = dbConnect();
            String query = "insert into estudante values(?,?,?,?,?)";
                
            try {
                prepare = conn.prepareStatement(query);
                prepare.setString(1, null);
                prepare.setString(2, name);
                prepare.setString(3, surname);
                prepare.setString(4, course);
                prepare.setString(5, college);
                int status = prepare.executeUpdate();
                
                if(status ==0){
                   log.info("Database insert operation error");
                   return 0;
                  
                }
            } catch (SQLException ex) {
                log.info("Problems with insert estudante to database");
                throw new ErrorInserEstudanteDatabase("Problems with insert estudante to database",ex);
  
            }
                log.info("Insert successfull");
                return 1;
        }
        
        public static int DaoEstudanteDelete(int id) throws DatabaseDriverManagerError, ClassNameNotFound, SQLException{
            conn = dbConnect();
            int status =0;
            String  query = "delete from estudante where id =?";
                try {
                    prepare = conn.prepareStatement(query);
                } catch (SQLException ex) {
                    log.info("Probelams com a query de registo!");
                    return status;
                }
                prepare.setInt(1,id);
                try {
                    status = prepare.executeUpdate();
                    status =1;
                } catch (SQLException ex) {
                    log.info("Problemas com a execucao da query");
                    return status;
                }
            return status;
        }
}
