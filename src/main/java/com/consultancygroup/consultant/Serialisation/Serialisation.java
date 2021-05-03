package com.consultancygroup.consultant.Serialisation;

import com.consultancygroup.consultant.Model.Consultant;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Serialisation {

    public void export(List<Consultant> consultants) {
        try
        {
            FileOutputStream fos = new FileOutputStream("consultant");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(consultants);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public List<Consultant> importConsultant() {
        List<Consultant> consultants = new LinkedList<Consultant>();
        try {
            FileInputStream in = new FileInputStream("consultant");
            ObjectInputStream ois = new ObjectInputStream(in);
            consultants = (List<Consultant>) (ois.readObject());
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
        return  consultants;

    }
}
