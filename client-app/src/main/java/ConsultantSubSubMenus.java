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
    Verifiers verifiers = new Verifiers();
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
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);

        JButton deleteConsultantButton = new JButton();
        deleteConsultantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteConsultantById();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
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
                            consultantCrud.deleteAllConsultants();
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

        JButton findConsultantsByMinimumAge= new JButton();
        findConsultantsByMinimumAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findConsultantByMinimumAge();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findConsultantsByMinimumAge.setBackground(Color.WHITE);
        findConsultantsByMinimumAge.setText("Find consultant by minimum age");
        findConsultantsByMinimumAge.setFont(new Font("Helvetica",Font.BOLD,12));
        panel.add(findConsultantByConsultantId);
        panel.add(findConsultantByConsultantResume);
        panel.add(findConsultantByConsultantName);
        panel.add(findConsultantsByMinimumAge);
        panel.add(findAllConsultants);
        frame.add(panel);
        frame.setSize(500, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
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
    public void findConsultantByMinimumAge() throws IOException {

        JTextField minimumAge = new JTextField("Insert minimum age here");
        Object[] consultantDetails = {
                "Minimum Age:", minimumAge
        };
        int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Find consultant by minimum age.", JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION){
            String minAge = minimumAge.getText();
            //removing whitespace
            minAge = minAge.replace(" ","");
            List<Consultant> consultants = consultantCrud.getConsultantsByMinimumAge(minAge);
            if(consultants.size()!=0){
                JPanel panel = new JPanel();
                List<String> consultant2String = covertConsultantListToStringList(consultants);
                final JList<String> list = new JList<String>(consultant2String.toArray(new String[consultant2String.size()]));
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewportView(list);
                list.setLayoutOrientation(JList.VERTICAL);
                panel.add(scrollPane);
                JFrame frame = new JFrame("List of consultants older than "+minAge);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(panel);
                frame.setSize(800, 250);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);


            }


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
    else {
        JOptionPane.showMessageDialog(null, "No consultants in system ","Warning",JOptionPane.INFORMATION_MESSAGE);

    }
    }
    public void deleteConsultantById() throws IOException {
        List<String> consultants = covertConsultantListToStringList(consultantCrud.getAllConsultants());
        String[] consultantArray = new String[consultants.size()];
        consultants.toArray(consultantArray);

        JComboBox consultantBox  = new JComboBox(consultantArray);
        Object[] consultantDetails = {
                    "Select consultant to delete:",consultantBox,

        };
        int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Deleting a consultant.", JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION) {
            consultantCrud.deleteOneConsultant(getConsultant(consultantArray[consultantBox.getSelectedIndex()])[0]);
        }

        }


    public void updateConsultant() throws IOException {

        List<String> consultants = covertConsultantListToStringList(consultantCrud.getAllConsultants());
        String[] consultantArray = new String[consultants.size()];
        consultants.toArray(consultantArray);

        JComboBox consultantBox = new JComboBox(consultantArray);
        Object[] consultantUpdate = {
                "Select consultant to update:", consultantBox,

        };
        if(consultants.size()!=0){
        int option = JOptionPane.showConfirmDialog(null, consultantUpdate, "Updating a consultant.", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String resume[] = {"JUNIOR", "EXECUTIVE", "SENIOR"};
            JTextField fullName = new JTextField(getConsultant(consultantArray[consultantBox.getSelectedIndex()])[1]);
            JTextField age = new JTextField(getConsultant(consultantArray[consultantBox.getSelectedIndex()])[2]);
            JTextField phoneNumber = new JTextField(getConsultant(consultantArray[consultantBox.getSelectedIndex()])[4]);
            JComboBox consultantResume = new JComboBox(resume);
            if (getConsultant(consultantArray[consultantBox.getSelectedIndex()])[3].equals("JUNIOR"))
                consultantResume.setSelectedIndex(0);
            else if (getConsultant(consultantArray[consultantBox.getSelectedIndex()])[3].equals("EXECUTIVE"))
                consultantResume.setSelectedIndex(1);
            else if (getConsultant(consultantArray[consultantBox.getSelectedIndex()])[3].equals("SENIOR"))
                consultantResume.setSelectedIndex(2);
            Object[] consultantDetails = {
                    "Full Name:", fullName,
                    "Age: ", age,
                    "Phone Number", phoneNumber,
                    "Resume: ", consultantResume
            };
            int option2 = JOptionPane.showConfirmDialog(null, consultantDetails, "Adding a new consultant", JOptionPane.OK_CANCEL_OPTION);
            if (option2 == JOptionPane.OK_OPTION) {
                consultantCrud.updateConsultant(getConsultant(consultantArray[consultantBox.getSelectedIndex()])[0], fullName.getText(), age.getText(), phoneNumber.getText(), consultantResume.getSelectedItem().toString());
            }
        }
    }
        else {
            JOptionPane.showMessageDialog(null, "No consultants in system.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    //method to get consultant id from entity
    /*
    array values
    0-> id
    1-> fullName
    2-> age
    3-> consultantResume
    4-> phoneNumber
     */
    public String[] getConsultant(String consultant){
        String[] tokens1 = consultant.split(",");
        String[] tokens2 = tokens1[0].split(":");
        String[] consultantData = new String[5];
        consultantData[0] = tokens2[1];
        tokens2 = tokens1[1].split(":");
        consultantData[1]=tokens2[1];
        tokens2 = tokens1[2].split(":");
        consultantData[2]=tokens2[1];
        tokens2 = tokens1[3].split(":");
        consultantData[3]=tokens2[1];
        tokens2 = tokens1[4].split(":");
        consultantData[4]=tokens2[1];
        return  consultantData;

    }
    public List<String> covertConsultantListToStringList(List<Consultant> consultants){
        List<String> consultants2String = new LinkedList<String>();
        for(int i=0;i<consultants.size();i++){
            consultants2String.add("Consultant ID:"+consultants.get(i).getConsultantId()+", Full Name:"+consultants.get(i).getFullName()+", Age:"
                    +consultants.get(i).getAge()+", Consultant Resume:"+consultants.get(i).getConsultantResume().toString()+", Phone Number:"+
                    consultants.get(i).getPhoneNumber());
        }
        return  consultants2String;
    }
    public void saveChangesStatus() throws IOException {
        String status = consultantCrud.exportChanges();
        JOptionPane.showMessageDialog(null, status,"Status",JOptionPane.INFORMATION_MESSAGE);
    }

}
