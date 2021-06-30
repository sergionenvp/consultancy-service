package ConsultancyPackages;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Composite implements ConsultantInterface {
    private String assetName;
    private LocalDate startingDate;
    private LocalDate endDate;
    private List<ConsultantInterface> consultantList = new LinkedList<ConsultantInterface>();
    //shows assets details
    public void showConsultantDetails() {
        System.out.println(assetName+"-"+startingDate+" - "+endDate);
        for(ConsultantInterface consultant:consultantList)
        {
            System.out.print("\t");
            consultant.showConsultantDetails();

        }
    }

    // list all assets inside a package
    public void showPackageContents() {
        System.out.print("Package info : ");
        System.out.println(assetName+"-"+startingDate+" - "+endDate);
        for(int i = 0; i<consultantList.size();i++)
        {
            System.out.println("Asset "+(i+1)+":");
            //get asset details
            consultantList.get(i).showConsultantDetails();

        }



    }
    public void addConsultant(ConsultantInterface consultant){consultantList.add(consultant);}
    public void removeConsultant(ConsultantInterface consultant)
    {
        consultantList.remove(consultant);
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }
    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public List<ConsultantInterface> getConsultantList() {
        System.out.println(consultantList);
        return consultantList;
    }
}
