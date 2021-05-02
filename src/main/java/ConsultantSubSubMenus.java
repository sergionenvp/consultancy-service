import HttpMethods.ConsultantCrud;
import Models.Consultant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ConsultantSubSubMenus {
    ConsultantCrud consultantCrud = new ConsultantCrud();
    public void AddConsultantMenu() throws IOException {
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
        if(option==JOptionPane.OK_OPTION){
            String entity = consultantCrud.create(fullName.getText(),age.getText(),phoneNumber.getText(),consultantResume.getSelectedItem().toString());
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(entity);
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane,"Result",JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void DeleteConsultantMenu(){
        ConsultantSubSubMenus consultantMenu = new ConsultantSubSubMenus();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton deleteOneConsultant = new JButton();
        deleteOneConsultant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteOneConsultant.setBackground(Color.PINK);
        deleteOneConsultant.setText("Delete one consultant");
        deleteOneConsultant.setFont(new Font("Helvetica",Font.BOLD,20));
        deleteOneConsultant.addActionListener(new ActionListener() {
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
        deleteConsultantButton.setBackground(Color.PINK);
        deleteConsultantButton.setFont(new Font("Helvetica",Font.BOLD,20));
        deleteConsultantButton.setText("Delete a Consultant");
        JButton deleteAllConsultants = new JButton();
        deleteAllConsultants.setBackground(Color.RED);
        deleteAllConsultants.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String entity = consultantCrud.deleteAllConsultants();
                            // create a JTextArea
                            JTextArea textArea = new JTextArea(6, 25);
                            textArea.setText(entity);
                            textArea.setEditable(false);
                            // wrap a scrollpane around it
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            // display them in a message dialog
                            JOptionPane.showMessageDialog(null, scrollPane,"Result",JOptionPane.INFORMATION_MESSAGE);




                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
        );
        deleteAllConsultants.setFont(new Font("Helvetica",Font.BOLD,20));
        deleteAllConsultants.setText("Delete all Consultants (Warning)");
        panel.add(deleteConsultantButton);
        panel.add(deleteAllConsultants);
        frame.add(panel);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        SuperMenu superMenu = new SuperMenu();
        superMenu.Menu();
    }
    public void findConsultantMenu(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton findConsultantByConsultantId = new JButton();
        findConsultantByConsultantId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findConsultantByConsultantId();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findConsultantByConsultantId.setBackground(Color.WHITE);
        findConsultantByConsultantId.setText("Find consultant by consultant ID");
        findConsultantByConsultantId.setFont(new Font("Helvetica",Font.BOLD,20));
        JButton findConsultantByConsultantName = new JButton();
        findConsultantByConsultantName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findConsultantByConsultantName();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findConsultantByConsultantName.setBackground(Color.WHITE);
        findConsultantByConsultantName.setText("Find consultant by consultant name");
        findConsultantByConsultantName.setFont(new Font("Helvetica",Font.BOLD,20));
        JButton findAllConsultants= new JButton();
        findAllConsultants.setBackground(Color.WHITE);
        findAllConsultants.setFont(new Font("Helvetica",Font.BOLD,20));
        findAllConsultants.setText("List all consultants");
        findAllConsultants.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findAllConsultants();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        JButton findConsultantByConsultantResume= new JButton();
        findConsultantByConsultantResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findConsultantByConsultantResume();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findConsultantByConsultantResume.setBackground(Color.WHITE);
        findConsultantByConsultantResume.setText("Find consultant by consultant resume");
        findConsultantByConsultantResume.setFont(new Font("Helvetica",Font.BOLD,20));


        panel.add(findConsultantByConsultantId);
        panel.add(findConsultantByConsultantName);
        panel.add(findConsultantByConsultantResume);
        panel.add(findAllConsultants);ThanMinAge(@PathVariable("age") int age) {
        return  consultantService.getConsultantsOlderThanMinAge(age);
    }
    @GetMapping("/consultant/export")
    public void write(){
        List<Consultant> consultants = con
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        SuperMenu superMenu = new SuperMenu();
        superMenu.Menu();



    }
    public void findConsultantByConsultantId() throws IOException {

            JTextField consultantId = new JTextField("Insert consultant id");
            Object[] consultantDetails = {
                    "Consultant ID:", consultantId
            };
            int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Find consultant by consultant id.", JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION){
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(consultantCrud.getConsultantById(consultantId.getText()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, textArea,"Result",JOptionPane.INFORMATION_MESSAGE);

        }

    }
    public void findConsultantByConsultantName() throws IOException {

        JTextField consultantName = new JTextField("Insert consultant of corresponding consultant");
        Object[] consultantDetails = {
                "Consultant Name:", consultantName
        };
        int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Find consultant by full name.", JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION){
            JPanel panel = new JPanel(new BorderLayout());
            List<String> consultants = covertConsultantListToStringList(consultantCrud.getConsultantsByName(consultantName.getText()));
            if(consultants.size()!=0) {
                final JList<String> list = new JList<String>(consultants.toArray(new String[consultants.size()]));
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewportView(list);
                list.setLayoutOrientation(JList.VERTICAL);
                panel.add(scrollPane);
                JFrame frame = new JFrame("List of consultants.");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(panel);
                frame.setSize(800, 250);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(null, "No consultant named "+consultantName.getText()+" found.","Warning",JOptionPane.INFORMATION_MESSAGE);




        }

    }
    public void findConsultantByConsultantResume() throws IOException {

        String resume[] = {"JUNIOR","EXECUTIVE","SENIOR"};
        JComboBox consultantResume  = new JComboBox(resume);
        Object[] consultantDetails = {
                "Consultant Name:", consultantResume
        };
        int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Find consultant by consultant resume.", JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION){
            JPanel panel = new JPanel(new BorderLayout());
            List<String> consultants = covertConsultantListToStringList(consultantCrud.getConsultantsByResume(consultantResume.getSelectedItem().toString()));
            if(consultants.size()!=0) {
                final JList<String> list = new JList<String>(consultants.toArray(new String[consultants.size()]));
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewportView(list);
                list.setLayoutOrientation(JList.VERTICAL);
                panel.add(scrollPane);
                JFrame frame = new JFrame("List of "+consultantResume.getSelectedItem().toString()+" consultants");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(panel);
                frame.setSize(800, 250);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(null, "No consultant with resume:"+consultantResume.getSelectedItem().toString()+" found.","Warning",JOptionPane.INFORMATION_MESSAGE);




        }

    }
public void findAllConsultants() throws IOException {
    JPanel panel = new JPanel(new BorderLayout());
    List<String> consultants = covertConsultantListToStringList(consultantCrud.getAllConsultants());

    final JList<String> list = new JList<String>(consultants.toArray(new String[consultants.size()]));
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setViewportView(list);
    list.setLayoutOrientation(JList.VERTICAL);
    panel.add(scrollPane);
    JFrame frame = new JFrame("List of consultants.");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(panel);
    frame.setSize(800, 250);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    }

public List<String> covertConsultantListToStringList(List<Consultant> consultants){
        List<String> consultants2String = new LinkedList<String>();
        for(int i=0;i<consultants.size();i++){
            consultants2String.add("Consultant ID:"+consultants.get(i).consultantId+", Full Name:"+consultants.get(i).fullName+", Age:"
                    +consultants.get(i).age+", Consultant Resume:"+consultants.get(i).consultantResume.toString()+", Phone Number:"+
                    consultants.get(i).phoneNumber);
        }
        return  consultants2String;
    }
}
