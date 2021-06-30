package ConsultancyPackages;


import Model.Consultant;

public class Leaf implements ConsultantInterface {
    Consultant consultant;
    public Leaf(Consultant consultant){
        this.consultant = consultant;

    }
    public void showConsultantDetails() {
        System.out.print("\t");
        System.out.println("Consultant Name: "+consultant.getFullName()+", Consultant Resume: "+consultant.getConsultantResume().toString());
    }
}
