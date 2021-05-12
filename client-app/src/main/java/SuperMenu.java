import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class SuperMenu {
    SubMenu subMenu = new SubMenu();
    Icon icon = new ImageIcon("background.png");
    WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {

                JFrame frame = new JFrame();
                JPanel panel = new JPanel();
                panel.setBackground(Color.yellow);
                JLabel label = new JLabel();
                label.setFont(new Font("Helvetica",Font.BOLD,20));
                label.setText("Are you sure you want to exit ?");
                JButton yes = new JButton();
                yes.setBackground(Color.green);
                yes.setFont(new Font("Helvetica",Font.BOLD,20));
                yes.setText("Yes");
                yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                JButton no = new JButton();
                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                no.setBackground(Color.red);
                no.setFont(new Font("Helvetica",Font.BOLD,20));
                no.setText("No");
                panel.add(label);
                panel.add(yes);
                panel.add(no);
                frame.add(panel);
                frame.setSize(710, 150);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setTitle("Changes not saved");

        }
    };
    public void Menu(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JButton manageConsultantsButton = new JButton();
       manageConsultantsButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               subMenu.ConsultantSubMenu();
           }
       });
        manageConsultantsButton.setBackground(Color.WHITE);
        manageConsultantsButton.setText("Manage Consultants");
        manageConsultantsButton.setFont(new Font("Helvetica",Font.BOLD,20));
        JButton manageAccountsAndPaymentsButton = new JButton();
        manageAccountsAndPaymentsButton.setBackground(Color.WHITE);

        manageAccountsAndPaymentsButton.setFont(new Font("Helvetica",Font.BOLD,20));
        manageAccountsAndPaymentsButton.setText("Manage Accounts and Payments");
        manageAccountsAndPaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subMenu.AccountancySubMenu();
            }
        });
        JButton manageAppointmentsButton = new JButton();
        manageAppointmentsButton.setBackground(Color.WHITE);
        manageAppointmentsButton.setFont(new Font("Helvetica",Font.BOLD,20));
        manageAppointmentsButton.setText("Manage Appointments");
        manageAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subMenu.AppointmentSubMenu();
            }
        });

        JButton export = new JButton();
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    subMenu.exportChanges();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        export.setBackground(Color.GREEN);
        export.setFont(new Font("Helvetica",Font.BOLD,20));
        export.setText("Save Changes");
        panel.add(manageConsultantsButton);
        panel.add(manageAccountsAndPaymentsButton);
        panel.add(manageAppointmentsButton);
        panel.add(export);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(exitListener);
        frame.setTitle("Consultancy Service Application Ⓒ");
    }
}
