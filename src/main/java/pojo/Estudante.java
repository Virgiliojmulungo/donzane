
package pojo;

public class Estudante {
      private int id;
      private String name;
      private String surname;
      private String course;
      private String college;
      
     public Estudante(int id, String name, String surname,String course, String college){
       this.id = id;
       this.name = name;
       this.surname = surname;
       this.course = course;
       this.college = college;
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
     
     public String toString(){
       return "name: "+name+" surname :"+surname+" course:"+course+" college"+college;
     }
}
