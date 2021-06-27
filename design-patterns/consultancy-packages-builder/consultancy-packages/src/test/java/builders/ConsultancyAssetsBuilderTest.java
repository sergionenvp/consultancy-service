package builders;

import assets.Assets;
import assets.ConsultancyPackage;
import assets.Consultant;
import assets.ConsultantResume;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;


public class ConsultancyAssetsBuilderTest {

    Assets consultant1,consultant2;

    ArrayList<Assets> assetsList = new ArrayList<>();
    ArrayList<Assets> assetsList2 = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        consultant1 = new Consultant("Tom Smith");
        consultant2 = new Consultant("Mary Borg");
    }


    @Test
    public void testAssetBuilder()
    {
        ConsultancyAssetsBuilder builder = new ConsultancyAssetsBuilder();
        assetsList = builder.addAsset(consultant1).addAsset(consultant2).buildAssetList();

        Assertions.assertEquals(consultant1.getConsultantName(), assetsList.get(0).getConsultantName());
        Assertions.assertEquals(2,assetsList.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testforDuplicates()
    {
        ConsultancyAssetsBuilder builder = new ConsultancyAssetsBuilder();

        PackageBuilder packageBuilder = new PackageBuilder();
        ConsultancyPackage newPackage = packageBuilder.setName("Consultancy Package")
                .setStartDate(LocalDate.of(2022,5,1))
                .setEndDate(LocalDate.of(2022,6,1))
                .build();

        assetsList2 = builder.addAsset(newPackage).addAsset(newPackage).addAsset(consultant2).buildAssetList();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            builder.checkForDuplicateAssets(assetsList2);
        });

    }

    @Test
    public void testforNonDuplicates()
    {
        ConsultancyAssetsBuilder builder = new ConsultancyAssetsBuilder();

        PackageBuilder packageBuilder = new PackageBuilder();
        ConsultancyPackage newPackage = packageBuilder.setName("Consultancy Package")
                .setStartDate(LocalDate.of(2022,5,1))
                .setEndDate(LocalDate.of(2022,6,1))
                .build();

        assetsList2 = builder.addAsset(newPackage).addAsset(consultant1).addAsset(consultant2).buildAssetList();

        Assertions.assertDoesNotThrow( () -> {
            builder.checkForDuplicateAssets(assetsList2);
        });

    }


}