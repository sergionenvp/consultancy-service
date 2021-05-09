package com.consultancygroup.appointments.serialization;

import com.consultancygroup.appointments.model.Appointment;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Serialization {

    public void export(List<Appointment> appointments) {
        try
        {
            FileOutputStream fos = new FileOutputStream("appointments.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(appointments);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public List<Appointment> importAppointments() {
        List<Appointment> appointments = new LinkedList<Appointment>();
        try {
            FileInputStream in = new FileInputStream("appointments.txt");
            ObjectInputStream ois = new ObjectInputStream(in);
            appointments = (List<Appointment>) (ois.readObject());
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
        return  appointments;

    }
}
