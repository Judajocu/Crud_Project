import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class Main {
    public static void main(String[] args) {
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        ArrayList<Students> StudentList = new ArrayList<Students>();

        Spark.post("/AddStudents/", (request, response) -> {
            String Nombre = request.queryParams("nombre");
            String Apellido = request.queryParams("apellido");
            String Matricula = request.queryParams("matricula");
            String Telefono = request.queryParams("telefono");
            StudentList.add(new Students(Integer.parseInt(Matricula),Nombre,Apellido,Telefono));

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("List",StudentList);

            return new ModelAndView(attributes, "AddStudent.ftl");
        }, freeMarkerEngine);

        Spark.get("/Students/", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("students", StudentList);

            //enviando los parametros a la vista.
            return new ModelAndView(attributes, "StudentData.ftl");
        }, freeMarkerEngine);

        Spark.get("/DeleteStudents/", (request, response) -> {
            String Matricula = request.queryParams("matricula");

            //StudentList.add(new Students(Integer.parseInt(Matricula),Nombre,Apellido,Telefono));

            for (Students p: StudentList) {
                if (p.getMatricula() == Integer.parseInt(Matricula))
                {
                    int t =StudentList.indexOf(p);
                    StudentList.remove(t);
                }
            }

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Lista",StudentList);

            return new ModelAndView(attributes, "DeleteStudent.ftl");
        }, freeMarkerEngine);

        Spark.post("/ModifyStudents/", (request, response) -> {
            String Matricula = request.queryParams("matricula");

            //StudentList.add(new Students(Integer.parseInt(Matricula),Nombre,Apellido,Telefono));

            for (Students p: StudentList) {
                if (p.getMatricula() == Integer.parseInt(Matricula))
                {
                    String Nombre = request.queryParams("nombre");
                    String Apellido = request.queryParams("apellido");
                    String MatriculaN = request.queryParams("matricula");
                    String Telefono = request.queryParams("telefono");
                    p.matricula = Integer.parseInt(MatriculaN);
                    p.nombre = Nombre;
                    p.apellido = Apellido;
                    p.telefono = Telefono;

                }
            }

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Lista",StudentList);

            return new ModelAndView(attributes, "ModifyStudent.ftl");
        }, freeMarkerEngine);


    }
}
