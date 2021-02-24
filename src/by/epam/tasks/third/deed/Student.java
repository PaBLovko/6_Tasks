package by.epam.tasks.third.deed;

/*
    Класс для представления студента
    Возможности:
    1) Получить/установить имя, факультет, курс, год зачисления
    2) Увеличение курса
    3) Вывод на консоль
    P.S. Хранит статус студента (выпущен, зачислен, отчислен)
 */

import java.util.Objects;

public class Student {

    public static final int TRAINING_PERIOD=4;

    private String name="My name";
    private String faculty ="FAMCS";
    private int course=1;
    private int yearOfEnrolling=2019;
    private StudentStatus status=StudentStatus.ENROLLED;

    public Student(){

    }

    public Student(String name, String faculty, int course, int yearOfEnrolling){
        setName(name);
        setFaculty(faculty);
        setCourse(course);
        setYearOfEnrolling(yearOfEnrolling);
    }

    public void setName(String name){
        if(name!=null && !name.isEmpty()){
            this.name=name;
        }
    }

    public void setFaculty(String faculty){
        if(faculty !=null && !faculty.isEmpty()){
            this.faculty = faculty;
        }
    }

    public void setCourse(int course){
        if(course>=1 && course<=TRAINING_PERIOD){
            this.course=course;
        }
    }

    public void setYearOfEnrolling(int year){
        if(year>1900 && year<2100){
            yearOfEnrolling=year;
        }
    }

    public StudentStatus getStatus(){
        return status;
    }

    public String getName(){
        return name;
    }

    public String getFaculty(){
        return faculty;
    }

    public int getCourse(){
        return course;
    }

    public int getYearOfEnrolling(){
        return yearOfEnrolling;
    }

    public boolean incCourse(){
        if(course<=TRAINING_PERIOD-1){
            course++;
            return true;
        }else{
            return false;
        }
    }

    protected void setStatus(StudentStatus status){
        if(status!=null) {
            this.status = status;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return course == student.course &&
                yearOfEnrolling == student.yearOfEnrolling &&
                Objects.equals(name, student.name) &&
                Objects.equals(faculty, student.faculty) &&
                status == student.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, faculty, course, yearOfEnrolling, status);
    }

    @Override
    public String toString(){
        return String.format("%15s %15s %7s %9s %10s",name, faculty,Integer.toString(course),Integer.toString(yearOfEnrolling),status.toString());
    }
}
