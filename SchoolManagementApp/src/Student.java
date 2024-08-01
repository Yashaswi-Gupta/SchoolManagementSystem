import java.util.Arrays;

public class Student {

    private  int id;
    private String sname;
    private String mobileNumber;

    private int fee;
    private String[] courses;


    public int getId() {
        return id;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getFee() {
        return fee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }


    public String getSname() {
        return sname;
    }

    public void setMobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber(){
        return mobileNumber;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public String[] getCourses() {
        return courses;
    }

    // Method to add a single course to the courses array
//    public void addCourse(String course) {
//        if (courses == null) {
//            courses = new String[]{course};
//        } else {
//            courses = Arrays.copyOf(courses, courses.length + 1);
//            courses[courses.length - 1] = course;
//        }
//    }

    // Method to convert courses array to a comma-separated string
//    public String coursesToString() {
//        return String.join(",", courses);
//    }


    public Student(int id, String sname,String mobileNumber){
        this.id=id;
        this.sname=sname;
        this.mobileNumber=mobileNumber;

    }

    // default method
    public Student(){
        super();
    }

}
