package ConsultancyPackages;



import Model.Consultant;
import Model.ConsultantResume;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class testCompositePackageBuilders {
    @Test
    public void testPackageCreationIsCorrect()
    {
        //set up phase
        Consultant consultant1 = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Leaf cons1 = new Leaf(consultant1);
        Consultant consultant2 = new Consultant(2L, "Jason Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Leaf cons2 = new Leaf(consultant2);
        Composite asset1 = new Composite();
        asset1.addConsultant(cons1);
        asset1.addConsultant(cons2);
        asset1.setAssetName("Managers");
        asset1.setStartingDate(LocalDate.of(2021, Month.JANUARY, 8));
        asset1.setEndDate(LocalDate.of(2021, Month.FEBRUARY, 8));
        Consultant consultant3 = new Consultant(3L,"Kelsey Marie Debono", 21, 79263256, ConsultantResume.EXECUTIVE);
        Leaf cons3 = new Leaf(consultant3);
        Consultant consultant4 = new Consultant(4l,"Mark Micallef", 50, 79223256, ConsultantResume.SENIOR);
        Leaf cons4 = new Leaf(consultant4);

        Composite asset2 = new Composite();
        asset2.addConsultant(cons3);
        asset2.addConsultant(cons4);
        asset2.setAssetName("Pro Managers");
        asset2.setStartingDate(LocalDate.of(2021, Month.JANUARY, 8));
        asset2.setEndDate(LocalDate.of(2022, Month.FEBRUARY, 12));



        Composite _package = new Composite();
        _package.addConsultant(asset1);
        _package.addConsultant(asset2);
        _package.setAssetName("Business Design");
        _package.setStartingDate(LocalDate.of(2021, Month.JANUARY, 8));
        _package.setEndDate(LocalDate.of(2022, Month.FEBRUARY, 22));

        //verify if final package consists of these 2 composites
        List<ConsultantInterface> expectedAssets = new LinkedList<ConsultantInterface>();
        expectedAssets.add(asset1);
        expectedAssets.add(asset2);
        Assert.assertEquals(_package.getConsultantList(),expectedAssets);




    }
}
