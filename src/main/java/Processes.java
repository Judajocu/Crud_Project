import java.util.ArrayList;

public class Processes {

    public ArrayList<Students> AddStudent(ArrayList<Students> students, Students student)
    {
        students.add(student);
        return students;
    }

    public ArrayList<Students> DeleteStudent(ArrayList<Students> students, Students student)
    {
        students.removeIf((Students temp) -> temp.equals(student));
        return students;
    }

    public ArrayList<Students> EditStudent(ArrayList<Students> students, Students student, int index)
    {
        students.get(index).nombre = student.nombre;
        students.get(index).apellido = student.apellido;
        students.get(index).matricula = student.matricula;
        students.get(index).telefono = student.telefono;

        return students;
    }
}
