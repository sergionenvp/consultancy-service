import assets.Assets;
import assets.ConsultancyPackage;
import assets.Consultant;
import assets.ConsultantResume;
import builders.ConsultancyAssetsBuilder;
import builders.PackageBuilder;

import java.time.LocalDate;
import java.util.ArrayList;

public class Director {

    private Assets consultant = new Consultant("Joe Smith");
    private Assets consultant1= new Consultant("Karen Mifsud");
    private Assets consultant2 = new Consultant("John Formosa");

    public void constructProductDevelopmentConsultancy(PackageBuilder builder){
        builder.setName("Product Development Consultancy");
        builder.setStartDate(LocalDate.of(2022,4,1));
        builder.setEndDate(LocalDate.of(2022,8,15));
    }

    public void constructBudgetPresentationConsultancy(PackageBuilder builder) {
        //create list of consultancy assets
        ConsultancyAssetsBuilder build = new ConsultancyAssetsBuilder();
        ArrayList<Assets> consultancyAssets = build.addAsset(consultant2).buildAssetList();

        //build BudgetPresentationConsultancy package
        builder.setName("Budget Presentation Consultancy");
        builder.setStartDate(LocalDate.of(2022,2,1));
        builder.setEndDate(LocalDate.of(2022,2,15));
        builder.setConsultancyAssets(consultancyAssets);
    }

    public void constructPitchConsultancy(PackageBuilder builder) {
        ConsultancyAssetsBuilder build = new ConsultancyAssetsBuilder();
        ArrayList<Assets> consultancyAssets = assetsOfPitchCons(build);

        //build PitchConsultancy package
        builder.setName("Pitch Consultancy");
        builder.setStartDate(LocalDate.of(2022,1,1));
        builder.setEndDate(LocalDate.of(2022,3,31));
        builder.setConsultancyAssets(consultancyAssets);
    }

    public ArrayList<Assets> assetsOfPitchCons(ConsultancyAssetsBuilder builder){
        PackageBuilder build = new PackageBuilder();
        constructBudgetPresentationConsultancy(build);
        ConsultancyPackage consultancyPackage = build.build();

        //build list of consultancy assets
        ArrayList<Assets> consultancyAssets = builder.addAsset(consultant).addAsset(consultant1).addAsset(consultancyPackage).buildAssetList();
        return consultancyAssets;
    }


}
