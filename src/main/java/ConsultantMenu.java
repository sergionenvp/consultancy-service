import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultantMenu {
    public void ConsultantMenu(){
        String resume[] = {"JUNIOR","EXECUTIVE","SENIOR"};
        JTextField fullName = new JTextField("Insert name e.g. John Doe");
        JTextField age = new JTextField("Insert age e.g. 50");
        JTextField phoneNumber = new JTextField("Insert phone e.g. 79663256");
        JComboBox consultantResume  = new JComboBox(resume);
        Object[] consultantDetails = {
                "Full Name:",fullName,
                "Age: ",age,
                "Phone Number",phoneNumber,
                "Resume: ", consultantResume

        };
        int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Adding a new consultant", JOptionPane.OK_CANCEL_OPTION);

    }
}
