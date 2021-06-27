package assets;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConsultancyPackage extends Assets {

    private  String name;
    private  LocalDate startDate;
    private  LocalDate endDate;
    private  ArrayList<Assets> consultancyAssets;


    public ConsultancyPackage(String name,LocalDate startDate,LocalDate endDate,ArrayList<Assets> consultancyAssets,ConsultancyPackage consultancyPackage) {
        super(consultancyPackage);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.consultancyAssets = consultancyAssets;
    }

    public String getName(){
        return name;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ArrayList<Assets> getConsultancyAssets() {

        return consultancyAssets;
    }

    public String toString() {
        return " \n"+ name + " " + startDate + " - " + endDate +  " \n " + consultancyAssets;
    }


}
