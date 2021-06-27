import assets.Assets;
import assets.ConsultancyPackage;
import builders.ConsultancyAssetsBuilder;
import builders.PackageBuilder;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]){

        Director director = new Director();
        PackageBuilder builder = new PackageBuilder();
        ConsultancyAssetsBuilder build = new ConsultancyAssetsBuilder();


        director.constructProductDevelopmentConsultancy(builder);
        ConsultancyPackage productDevConsultancy = builder.build();

        director.constructPitchConsultancy(builder);
        ConsultancyPackage pitchConsultancyPackage = builder.build();


        ArrayList<Assets> consultancyAssets = build.addAsset(pitchConsultancyPackage)
                                            .addAsset(productDevConsultancy)
                                            .buildAssetList();


        ConsultancyPackage startUpPackage = builder.setName("Startup Package")
                                            .setStartDate(LocalDate.of(2022,1,1))
                                            .setEndDate(LocalDate.of(2022,12,31))
                                            .setConsultancyAssets(consultancyAssets)
                                            .build();

        System.out.println("Consultancy Package:\n" + startUpPackage.getName() + " " + startUpPackage.getStartDate()
                            + " - " + startUpPackage.getEndDate() + "\n" + startUpPackage.getConsultancyAssets().toString());

    }

}

