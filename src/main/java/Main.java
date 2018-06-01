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
        configuration.setClassForTemplateLoading(Main.class, "/Templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        ArrayList<Students> StudentList = new ArrayList<Students>();

        Spark.post("/AddStudents/", (request, response) -> {
            StringWriter writer = new StringWriter();

            try {
                String Nombre = request.queryParams("nombre");
                String Apellido = request.queryParams("apellido");
                String Matricula = request.queryParams("matricula");
                String Telefono = request.queryParams("telefono");
                StudentList.add(new Students(Integer.parseInt(Matricula),Nombre,Apellido,Telefono));
                response.redirect("/Students/");
            }catch (Exception e){
                System.out.println(e);
                response.redirect("/AddStudentForm/");
            }
            return writer;
        });

        Spark.post("/ModifyStudents/:index", (request, response) -> {
            StringWriter writer = new StringWriter();
            int index = Integer.parseInt(request.params("index"));
            try {
                String Nombre = request.queryParams("nombre");
                String Apellido = request.queryParams("apellido");
                String Matricula = request.queryParams("matricula");
                String Telefono = request.queryParams("telefono");
                for (Students s: StudentList) {
                    if (StudentList.indexOf(s)==index)
                    {
                        s.setMatricula(Integer.parseInt(Matricula));
                        s.setNombre(Nombre);
                        s.setApellido(Apellido);
                        s.setTelefono(Telefono);
                    }
                }
                response.redirect("/Students/");
            }catch (Exception e){
                System.out.println(e);
                response.redirect("/ModifyStudentForm/");
            }
            return writer;
        });

        get("/ModifyStudentForm/:Matricula", (request, response) -> {
            int Matricula = Integer.parseInt(request.params("Matricula"));
            int index = 0;
            for (Students students:StudentList) {
                if (students.getMatricula() == Matricula)
                    index=StudentList.indexOf(students);
            }
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Student", StudentList);

            return new ModelAndView(attributes, "ModifyStudent.ftl");
        }, freeMarkerEngine);

        get("/AddStudentForm/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "Formulario.ftl");
        }, freeMarkerEngine);

        Spark.get("/Students/", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Students", StudentList);

            //enviando los parametros a la vista.
            return new ModelAndView(attributes, "StudentData.ftl");
        }, freeMarkerEngine);

        Spark.get("/DeleteStudent/:matricula", (request, response) -> {

            String matricula = request.params("matricula");


            StudentList.removeIf(student -> student.matricula==Integer.parseInt(matricula));
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Students", StudentList);
            return new ModelAndView(attributes, "DeleteStudent.ftl");
        }, freeMarkerEngine);



    }
}
