import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class SubMenu {
    ConsultantSubSubMenus consultantMenu = new ConsultantSubSubMenus();
    AccountancySubSubMenus accountancySubSubMenus = new AccountancySubSubMenus();
    public void ConsultantSubMenu() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton addConsultantsButton = new JButton();
        addConsultantsButton.setBackground(Color.WHITE);
        addConsultantsButton.setText("Add a Consultant");
        addConsultantsButton.setFont(new Font("Helvetica", Font.BOLD, 20));
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
        deleteConsultantButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        deleteConsultantButton.setText("Delete a Consultant");
        deleteConsultantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultantMenu.DeleteConsultantMenu();
            }
        });
        JButton updateConsultantButton = new JButton();
        updateConsultantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    consultantMenu.updateConsultant();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        updateConsultantButton.setBackground(Color.WHITE);
        updateConsultantButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        updateConsultantButton.setText("Update a Consultant");
        JButton listConsultantButton = new JButton();
        listConsultantButton.setBackground(Color.WHITE);
        listConsultantButton.setFont(new Font("Helvetica", Font.BOLD, 20));
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
    public void AccountancySubMenu() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton addPayment = new JButton();
        addPayment.setBackground(Color.WHITE);
        addPayment.setText("Add a payment");
        addPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    accountancySubSubMenus.addPaymentMenu();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        addPayment.setFont(new Font("Helvetica", Font.BOLD, 20));
        JButton listPaymentButton = new JButton();
        listPaymentButton.setBackground(Color.WHITE);
        listPaymentButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        listPaymentButton.setText("List Payments");
        listPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountancySubSubMenus.findPaymentMenu();
            }
        });
        JButton deletePaymentButton = new JButton();
        deletePaymentButton.setBackground(Color.WHITE);
        deletePaymentButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        deletePaymentButton.setText("Delete Payment Menu");
        deletePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountancySubSubMenus.deletePaymentMenu();
            }
        });
        JButton updatePaymentButton = new JButton();
        updatePaymentButton.setBackground(Color.WHITE);
        updatePaymentButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        updatePaymentButton.setText("Update a payment.");
        updatePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    accountancySubSubMenus.updatePayment();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        JButton manageAccountancy = new JButton();
        manageAccountancy.setBackground(Color.WHITE);
        manageAccountancy.setFont(new Font("Helvetica", Font.BOLD, 20));
        manageAccountancy.setText("Manage Accountancy.");
        manageAccountancy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountancySubSubMenus.accountancyMenu();
            }
        });
        panel.add(addPayment);
        panel.add(listPaymentButton);
        panel.add(deletePaymentButton);
        panel.add(updatePaymentButton);
        panel.add(manageAccountancy);
        frame.add(panel);
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void exportChanges() throws IOException {
        consultantMenu.saveChangesStatus();
        accountancySubSubMenus.saveChangesStatus();
    }
}
