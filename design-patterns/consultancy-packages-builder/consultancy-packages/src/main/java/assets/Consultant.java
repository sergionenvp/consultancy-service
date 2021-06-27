package assets;

public class Consultant extends Assets {

    private int age;
    private int phoneNumber;
    private  ConsultantResume consultantResume;

    public Consultant(String fullName){
        super(fullName);
    }
    public Consultant(String fullName, int age, int phoneNumber, ConsultantResume consultantResume) {
        super(fullName);
        this.age=age;
        this.consultantResume=consultantResume;
        this.phoneNumber=phoneNumber;
    }


}
