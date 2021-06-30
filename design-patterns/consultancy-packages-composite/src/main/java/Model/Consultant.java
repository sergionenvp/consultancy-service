package Model;

import java.io.Serializable;




public class  Consultant implements Serializable {

    private Long consultantId;
    private String fullName;
    private int age;

    private int phoneNumber;
    private  ConsultantResume consultantResume;

    public Consultant(Long consultantId,String fullName, int age, int phoneNumber, ConsultantResume consultantResume) {
        this.consultantId = consultantId;
        this.fullName=fullName;
        this.age=age;
        this.consultantResume=consultantResume;
        this.phoneNumber=phoneNumber;
    }
    public String getFullName(){
        return fullName;
    }

    public ConsultantResume getConsultantResume() {
        return consultantResume;
    }
}

