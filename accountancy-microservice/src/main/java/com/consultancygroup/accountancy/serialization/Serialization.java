package com.consultancygroup.accountancy.serialization;


import com.consultancygroup.accountancy.model.Payment;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Serialization<T> {

    public void export(List<Payment> payments) {
        try
        {
            FileOutputStream fos = new FileOutputStream("payment.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(payments);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public List<Payment> importPayment() {
        List<Payment> payments = new LinkedList<Payment>();
        try {
            FileInputStream in = new FileInputStream("payment.txt");
            ObjectInputStream ois = new ObjectInputStream(in);
            payments = (List<Payment>) (ois.readObject());
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
        return  payments;

    }


}