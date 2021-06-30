import ConsultancyPackages.Composite;
import ConsultancyPackages.Leaf;
import Model.Consultant;
import Model.ConsultantResume;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        Consultant consultant1 = new Consultant(1L,"Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Leaf cons1 = new Leaf(consultant1);
        Consultant consultant2 = new Consultant(2L,"Sergio Vega Pineda", 20, 79463256, ConsultantResume.SENIOR);
        Leaf cons2 = new Leaf(consultant2);

        Composite asset1 = new Composite();
        asset1.addConsultant(cons1);
        asset1.addConsultant(cons2);
        asset1.setAssetName("Managers");
        asset1.setStartingDate(LocalDate.of(2021, Month.JANUARY, 8));
        asset1.setEndDate(LocalDate.of(2021, Month.FEBRUARY, 8));


        Consultant consultant3 = new Consultant(3L,"Kelsey Marie Debono", 21, 79263256, ConsultantResume.EXECUTIVE);
        Leaf cons3 = new Leaf(consultant3);
        Consultant consultant4 = new Consultant(4L,"Mark Micallef", 50, 79223256, ConsultantResume.SENIOR);
        Leaf cons4 = new Leaf(consultant4);

        Composite asset2 = new Composite();
        asset2.addConsultant(cons3);
        asset2.addConsultant(cons4);
        asset2.setAssetName("Pro Managers");
        asset2.setStartingDate(LocalDate.of(2021, Month.JANUARY, 8));
        asset2.setEndDate(LocalDate.of(2022, Month.FEBRUARY, 12));

        Consultant consultant5 = new Consultant(5L,"Jason Micallef", 50, 79223256, ConsultantResume.SENIOR);
        Leaf cons5 = new Leaf(consultant5);
        Composite asset3 = new Composite();
        asset3.addConsultant(cons5);
        asset3.setAssetName("Budget Analysis");
        asset3.setStartingDate(LocalDate.of(2021, Month.JANUARY, 5));
        asset3.setEndDate(LocalDate.of(2022, Month.FEBRUARY, 1));
        asset1.addConsultant(asset3);


        Composite _package = new Composite();
        _package.addConsultant(asset1);
        _package.addConsultant(asset2);

        _package.setAssetName("Business Design");
        _package.setStartingDate(LocalDate.of(2021, Month.JANUARY, 8));
        _package.setEndDate(LocalDate.of(2022, Month.FEBRUARY, 22));
        _package.showPackageContents();


    }
}
