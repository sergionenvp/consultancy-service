package assets;

public abstract class Assets {
    private String consultantName;
    private ConsultancyPackage consultancyPackage;


    public Assets(String consultantName) {
        this.consultantName = consultantName;
    }

    public Assets(ConsultancyPackage consultancyPackage) {
        this.consultancyPackage = consultancyPackage;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public ConsultancyPackage getConsultancyPackage() {
        return consultancyPackage;
    }

    @Override
   public String toString() {
       return consultantName + " ";

    }
}
