package Models;
public class Consultant {
    private Long consultantId;
    private String fullName;
    private int age;
    private int phoneNumber;
    private ConsultantResume consultantResume;
    public void setConsultantId(Long consultantId) {
        this.consultantId = consultantId;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setConsultantResume(ConsultantResume consultantResume) {
        this.consultantResume = consultantResume;
    }
    public Long getConsultantId() {
        return consultantId;
    }
    public String getFullName() {
        return fullName;
    }
    public int getAge() {
        return age;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public ConsultantResume getConsultantResume() {
        return consultantResume;
    }
}
