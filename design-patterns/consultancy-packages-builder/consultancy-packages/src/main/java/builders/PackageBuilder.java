package builders;

import java.time.LocalDate;
import java.util.ArrayList;

import assets.Assets;
import assets.ConsultancyPackage;

public class PackageBuilder {
    private  String name;
    private  LocalDate startDate;
    private  LocalDate endDate;
    private  ArrayList<Assets> consultancyAssets = new ArrayList<>();
    private ConsultancyPackage consultancyPackage;

    public PackageBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PackageBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public PackageBuilder setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public PackageBuilder setConsultancyAssets(ArrayList<Assets> consultancyAssets) {
        this.consultancyAssets = consultancyAssets;
        return this;
    }


    public ConsultancyPackage build()
    {
        return new ConsultancyPackage(name,startDate,endDate,consultancyAssets,consultancyPackage);
    }


}
