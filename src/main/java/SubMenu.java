import HttpMethods.ConsultantCrud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SubMenu {

    public void ConsultantSubMenu(){
        ConsultantSubSubMenus consultantMenu = new ConsultantSubSubMenus();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton addConsultantsButton = new JButton();
        addConsultantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addConsultantsButton.setBackground(Color.WHITE);
        addConsultantsButton.setText("Add a Consultant");
        addConsultantsButton.setFont(new Font("Helvetica",Font.BOLD,20));
        addConsultantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    consultantMenu.AddConsultantMenu();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        JButton deleteConsultantButton = new JButton();
        deleteConsultantButton.setBackground(Color.WHITE);

        deleteConsultantButton.setFont(new Font("Helvetica",Font.BOLD,20));
        deleteConsultantButton.setText("Delete a Consultant");
        deleteConsultantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultantMenu.DeleteConsultantMenu();
            }
        });
        JButton updateConsultantButton = new JButton();
        updateConsultantButton.setBackground(Color.WHITE);
        updateConsultantButton.setFont(new Font("Helvetica",Font.BOLD,20));
        updateConsultantButton.setText("Update a Consultant");
        JButton listConsultantButton = new JButton();
        listConsultantButton.setBackground(Color.WHITE);
        listConsultantButton.setFont(new Font("Helvetica",Font.BOLD,20));
        listConsultantButton.setText("Find Consultants");
        listConsultantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               consultantMenu.findConsultantMenu();
            }
        });
        panel.add(addConsultantsButton);
        panel.add(deleteConsultantButton);
        panel.add(listConsultantButton);
        panel.add(updateConsultantButton);
        frame.add(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
}
