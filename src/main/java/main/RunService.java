package main;

import com.google.gson.Gson;
import dao.ClassNameNotFound;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import pojo.Estudante;
import service.Service;

public class RunService extends HttpServlet {

  private static final long serialVersionUid = 1L;
  private static final Logger log = Logger.getLogger(RunService.class);


  Service service = new Service();
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String log4JClassPath = "C:\\Users\\Virgilio Mulungo\\Documents\\NetBeansProjects\\donzane\\src\\main\\resources\\log.properties";
    PropertyConfigurator.configure(log4JClassPath);
    PrintWriter out = response.getWriter();

    String url = request.getRequestURI();
   
    int pagina =0;
    if (url.equals("/donzane/runService/insert")) {
      Gson gson = new Gson();
      Estudante est = gson.fromJson(request.getReader(), Estudante.class);
      String name = est.getName();
      String surname = est.getSurname();
      String course = est.getCourse();
      String collge = est.getCollege();

      try {
        out.print(service.StudentRegister(name, surname, course, collge));
      } catch (ClassNameNotFound ex) {
        out.println("Haaaa problemas serios");
      }

    } else if (url.equals("/donzane/runService/list")) {
        int total;
        int nPage =1;
           String dados = IOUtils.toString(request.getReader());
           if(dados.length()!=0){
               JSONObject ob = new JSONObject(dados);
                if(ob.has("pagina")==true){
                   nPage = ob.getInt("pagina");
                }
            }
            total    = Service.totalPages();
            Map resp = new HashMap();
            resp.put("total", total);
            try {
                resp.put("list", service.studentList(nPage));
            } catch (SQLException ex) {
                log.info("Problemas com query");
            }
                String json = new Gson().toJson(resp);
                out.println(json);

        }else if(url.equals("/donzane/runService/delete")){
                  String data = IOUtils.toString(request.getReader());
                  JSONObject delete = new JSONObject(data);
                  int id_to_delete = delete.getInt("id_delete");
                  int deleteStatus = service.deleteStudent(id_to_delete);
                  JSONObject status = new JSONObject();
                  status.put("delete_status", deleteStatus);
                  out.println(status);
             
        }
    }
}