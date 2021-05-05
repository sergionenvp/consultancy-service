package com.consultancygroup.accountancy.serialization;


import com.consultancygroup.accountancy.model.Payment;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Serialization<T> {

    public void export(List<T> objects, String filename) {
        try
        {
            FileOutputStream fos = new FileOutputStream(filename + ".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objects);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public List<T> importObjects(String filename) {
        List<T> objects = new LinkedList<T>();
        try {
            FileInputStream in = new FileInputStream(filename + ".txt");
            ObjectInputStream ois = new ObjectInputStream(in);
            objects = (List<T>) (ois.readObject());
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
        return  objects;

    }
}