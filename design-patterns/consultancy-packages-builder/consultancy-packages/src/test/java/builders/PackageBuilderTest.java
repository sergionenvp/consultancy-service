package builders;

import assets.Assets;
import assets.ConsultancyPackage;
import assets.Consultant;
import assets.ConsultantResume;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PackageBuilderTest {
    Assets consultant1,consultant2;

    ArrayList<Assets> assetsList = new ArrayList<>();
    ArrayList<Assets> assetsList2 = new ArrayList<>();

    ConsultancyAssetsBuilder builder = new ConsultancyAssetsBuilder();

    @Before
    public void setUp() throws Exception {
        consultant1 = new Consultant("Tom Smith");
        consultant2 = new Consultant("Mary Borg");

        assetsList = builder.addAsset(consultant1).addAsset(consultant2).buildAssetList();

    }

    @Test
    public void build() {
        PackageBuilder packageBuilder = new PackageBuilder();
        ConsultancyPackage newPackage = packageBuilder.setName("Consultancy Package")
                                        .setStartDate(LocalDate.of(2022,5,1))
                                        .setEndDate(LocalDate.of(2022,6,1))
                                        .setConsultancyAssets(assetsList).build();



        Assertions.assertEquals("Consultancy Package",newPackage.getName());
        Assertions.assertEquals(true,newPackage.getConsultancyAssets().contains(consultant2));
    }
}