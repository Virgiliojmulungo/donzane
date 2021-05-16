


package service;

import com.google.gson.Gson;
import dao.ClassNameNotFound;
import dao.DaoClient;
import org.json.JSONObject;
import static dao.DaoClient.insert;
import dao.DatabaseDriverManagerError;
import dao.ErrorInserEstudanteDatabase;
import java.util.ArrayList;
import pojo.Estudante;
import static dao.DaoClient.daoLista;
import static dao.DaoClient.numeroEstudante;
import dao.EstudanteFetchProblemsError;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

public class Service {
     
      private static final long serialVersionUID = 1L;
      private static final Logger log = Logger.getLogger(Service.class);
       
      public  JSONObject StudentRegister(String name,String surname,String course, String college) throws ClassNameNotFound{
              JSONObject obj = new JSONObject();
              int insertStatus = 0;
          try {
              insertStatus = insert(name, surname, course, college);
              obj.put("insert_status", insertStatus);
          } catch (ErrorInserEstudanteDatabase ex) {
                log.info("Problems with insert estudante failed");
                obj.put("insert_status", insertStatus);
                return obj;
          } catch (DatabaseDriverManagerError ex) {
                obj.put("insert_status",insertStatus);
                 return obj;
          }
          
         return obj;
      }
      
      public ArrayList<Estudante> studentList(int pagina) throws SQLException{
//           Gson gson = new Gson();

            ArrayList<Estudante> estudante = new ArrayList();
            int id =0;
            String name;
            String surname;
            String course;
            String college;
            
                        
          try {
                estudante = daoLista(pagina);
                for(int i=0; i<estudante.size(); i++){
                   id = estudante.get(i).getId();
                   name = estudante.get(i).getName();
                   surname = estudante.get(i).getSurname();
                   course = estudante.get(i).getCourse();
                   college = estudante.get(i).getCollege();
                   Estudante est = new Estudante(id,name,surname,course,college);
                }
               
                
          } catch (EstudanteFetchProblemsError ex) {
              log.info("Fetching data problems! Check Estudante fetching data query");
              return estudante;
             
          } catch (DatabaseDriverManagerError ex) {
              log.info("Problems with your databse connect data!");      
              return estudante;
          } catch (ClassNameNotFound ex) {
              log.info("Problems with your ClassName!!!Please check your classPath");
              return estudante;
          }
          return estudante;
      }
      
      public static int totalPages(){
            int all_students = 0;
            int total_paginas = 0;

            try {
              all_students = numeroEstudante();
            } catch (DatabaseDriverManagerError ex) {
              log.info("Problemas com o driver do contador de pessoas");
            } catch (ClassNameNotFound ex) {
              log.info("Problemas com o ClassName do contador de pessoas");
            } catch (SQLException ex) {
              log.info("Problemas com a query para contar as pessoas ");
            }
            total_paginas = all_students / 10;
            return total_paginas;
      }
      
       public static int deleteStudent(int id){
            int status = 0;
          try {
              status = DaoClient.DaoEstudanteDelete(id);
          } catch (DatabaseDriverManagerError ex) {
              log.info("Problemas com o drive de conexao a base de dados");
          } catch (ClassNameNotFound ex) {
               log.info("problemas com o ClassForName da connexao a base de dados");
          } catch (SQLException ex) {
              log.info("Problemas com a query por favor reveja a query para apagar os estudantes");
          }
          return status;
       }
    
}
