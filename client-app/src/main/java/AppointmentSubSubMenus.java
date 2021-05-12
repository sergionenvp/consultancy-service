import HttpMethods.AppointmentCrud;
import HttpMethods.ConsultantCrud;
import Models.Appointment;
import Models.ConsultantResume;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
public class AppointmentSubSubMenus {
    ConsultantSubSubMenus consultantSubSubMenus = new ConsultantSubSubMenus();
    ConsultantCrud consultantCrud = new ConsultantCrud();
    AppointmentCrud appointmentCrud = new AppointmentCrud();
    public void addAppointmentMenu() throws IOException {
        List<String> consultants =  consultantSubSubMenus.covertConsultantListToStringList(consultantCrud.getAllConsultants());
        if(consultants.size()!=0) {
            String[] consultantArray = new String[consultants.size()];
            consultants.toArray(consultantArray);
            JComboBox consultantBox = new JComboBox(consultantArray);
            Object[] consultantDetails = {
                    "Select consultant to create appointment on:", consultantBox,
            };
            int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Adding a new  appointment", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String[] worker = consultantSubSubMenus.getConsultant(consultantArray[consultantBox.getSelectedIndex()]);
                // 0-> id
                // 3-> consultantResume
                String consultantId = worker[0];
                Long workerId = Long.parseLong(consultantId);
                String consultantResume = worker[3];
                ConsultantResume resume = ConsultantResume.valueOf(consultantResume);
                JTextField clientId = new JTextField("Insert client's id:");
                JTextField price = new JTextField("Insert price:");
                JTextField clientContactNum = new JTextField("Insert client contact num:");
                String  appointmentStatusList[] = {"AVAILABLE","BOOKED"};
                JComboBox appointmentStatus = new JComboBox(appointmentStatusList);
                String  dayList[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                JComboBox days = new JComboBox(dayList);
                String monthList[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
                JComboBox months = new JComboBox(monthList);
                String yearList[] = {"2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
                JComboBox years = new JComboBox(yearList);
                String hourList[] = {"00","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
                JComboBox hours = new JComboBox(hourList);
                String minuteList[] = {"00","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
                JComboBox minutes = new JComboBox(minuteList);
                Object[] appointmentDetails = {
                        "Client ID:", clientId,
                        "Price:", price,
                        "Appointment status:", appointmentStatus,
                        "Client contact number: ", clientContactNum,
                        "Appointment Day:",days,
                        "Appointment Month:",months,
                        "Appointment Year:",years,
                        "Appointment Hour", hours,
                        "Appointment Minutes", minutes,

                };
                int option2 = JOptionPane.showConfirmDialog(null, appointmentDetails, "Adding a new appointment", JOptionPane.OK_CANCEL_OPTION);
                if (option2 == JOptionPane.OK_OPTION) {
                    int year = Integer.parseInt(years.getSelectedItem().toString());
                    int month = Integer.parseInt(months.getSelectedItem().toString());
                    int day = Integer.parseInt(days.getSelectedItem().toString());
                    int hour = Integer.parseInt(hours.getSelectedItem().toString());
                    int minute = Integer.parseInt(minutes.getSelectedItem().toString());
                    LocalDateTime date = LocalDateTime.of(year,month,day,hour,minute,00);
                     appointmentCrud.createAppointment(workerId, clientId.getText(), date,clientContactNum.getText(), price.getText(), appointmentStatus.getSelectedItem().toString(), consultantResume);
                }
            }
        }
        else
            JOptionPane.showMessageDialog(null, "No consultants in system.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
    public void updateAppointment() throws IOException {

            JTextField appointmentNum = new JTextField("Input appointment number of appointment you want to update here.");
            Object[] consultantDetails = {
                    "Appointment number:", appointmentNum,
            };
            int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Updating an appointment", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                HttpGet request = new HttpGet("http://localhost:9002/appointments/id/" + appointmentNum.getText());
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request);
                if (response.getStatusLine().getStatusCode() == 200) {
                    String appointmentDet[] = appointmentDetails(appointmentCrud.getAppointmentByAppointmentNum(appointmentNum.getText()));
                    List<String> consultants = consultantSubSubMenus.covertConsultantListToStringList(consultantCrud.getAllConsultants());
                    if (consultants.size() != 0) {
                        String[] consultantArray = new String[consultants.size()];
                        consultants.toArray(consultantArray);
                        JComboBox consultantBox = new JComboBox(consultantArray);
                        JTextField clientId = new JTextField(appointmentDet[1]);
                        JTextField price = new JTextField(appointmentDet[11]);
                        JTextField clientContactNum = new JTextField(appointmentDet[2]);
                        String appointmentStatusList[] = {"AVAILABLE", "BOOKED"};
                        JComboBox appointmentStatus = new JComboBox(appointmentStatusList);
                       if(appointmentDet[12].equals("BOOKED")){
                           appointmentStatus.setSelectedIndex(1);
                       } else if(appointmentDet[12].equals("AVAILABLE")){
                           appointmentStatus.setSelectedIndex(0);
                       }
                        String dayList[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
                        JComboBox days = new JComboBox(dayList);
                        String monthList[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
                        JComboBox months = new JComboBox(monthList);
                        String yearList[] = {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
                        JComboBox years = new JComboBox(yearList);
                        String hourList[] = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
                        JComboBox hours = new JComboBox(hourList);
                        String minuteList[] = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
                        JComboBox minutes = new JComboBox(minuteList);

                        Object[] appointmentDetails = {
                                "Update Consultant:", consultantBox,
                                "Update Client ID:", clientId,
                                "Price:", price,
                                "Appointment status:", appointmentStatus,
                                "Client contact number: ", clientContactNum,
                                "Appointment Day:", days,
                                "Appointment Month:", months,
                                "Appointment Year:", years,
                                "Appointment Hour", hours,
                                "Appointment Minutes", minutes,

                        };
                        int option2 = JOptionPane.showConfirmDialog(null, appointmentDetails, "Updating an appointment", JOptionPane.OK_CANCEL_OPTION);
                        if (option2 == JOptionPane.OK_OPTION) {

                            String[] worker = consultantSubSubMenus.getConsultant(consultantArray[consultantBox.getSelectedIndex()]);
                            // 0-> id
                            // 3-> consultantResume
                            String consultantId = worker[0];
                            String consultantResume = worker[3];
                            Long workerId = Long.parseLong(consultantId);
                            int year = Integer.parseInt(years.getSelectedItem().toString());
                            int month = Integer.parseInt(months.getSelectedItem().toString());
                            int day = Integer.parseInt(days.getSelectedItem().toString());
                            int hour = Integer.parseInt(hours.getSelectedItem().toString());
                            int minute = Integer.parseInt(minutes.getSelectedItem().toString());
                            LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute, 00);
                            appointmentCrud.updateAppointment(appointmentNum.getText(), workerId, clientId.getText(), date, clientContactNum.getText(), price.getText(), appointmentStatus.getSelectedItem().toString(), consultantResume);
                        }
                        }
                }
            }
        }

    public void searchMenu() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton findAppointmentByConsultantId = new JButton();
        findAppointmentByConsultantId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findAppointmentByConsultant();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findAppointmentByConsultantId.setBackground(Color.WHITE);
        findAppointmentByConsultantId.setText("Find appointments of some consultant.");
        findAppointmentByConsultantId.setFont(new Font("Helvetica",Font.BOLD,20));
        JButton findAppointmentByAppointmentNum = new JButton();
        findAppointmentByAppointmentNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findAppointmentByAppointmentNum();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findAppointmentByAppointmentNum.setBackground(Color.WHITE);
        findAppointmentByAppointmentNum.setText("Find appointments by appointment number.");
        findAppointmentByAppointmentNum.setFont(new Font("Helvetica",Font.BOLD,20));

        JButton findAppointmentByDate = new JButton();
        findAppointmentByDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findAppointmentByDate();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findAppointmentByDate.setBackground(Color.WHITE);
        findAppointmentByDate.setText("Find appointments by date.");
        findAppointmentByDate.setFont(new Font("Helvetica",Font.BOLD,20));
        panel.add(findAppointmentByConsultantId);
        panel.add(findAppointmentByAppointmentNum);
        panel.add(findAppointmentByDate);
        frame.add(panel);
        frame.setSize(600, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void findAppointmentByAppointmentNum() throws IOException {
        JTextField appointmentNum = new JTextField("Input appointment number here.");
        Object[] consultantDetails = {
                "Appointment number:", appointmentNum,
        };
        int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Find an appointment by appointment id", JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION){
            appointmentCrud.getAppointmentByAppointmentNum(appointmentNum.getText());
        }
    }
    public void findAppointmentByDate() throws IOException {
        List<String> consultants =  consultantSubSubMenus.covertConsultantListToStringList(consultantCrud.getAllConsultants());
        String[] consultantArray = new String[consultants.size()];
        consultants.toArray(consultantArray);
        JComboBox consultantBox = new JComboBox(consultantArray);
        String  dayList[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        JComboBox days = new JComboBox(dayList);
        String monthList[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        JComboBox months = new JComboBox(monthList);
        String yearList[] = {"2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
        JComboBox years = new JComboBox(yearList);
        String hourList[] = {"00","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
        JComboBox hours = new JComboBox(hourList);
        String minuteList[] = {"00","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
        JComboBox minutes = new JComboBox(minuteList);
        Object[] appointmentDetails = {
               "Select consultant",consultantBox,
                "Appointment Day:",days,
                "Appointment Month:",months,
                "Appointment Year:",years,
                "Appointment Hour", hours,
                "Appointment Minutes", minutes,
        };
        String[] worker = consultantSubSubMenus.getConsultant(consultantArray[consultantBox.getSelectedIndex()]);
        // 0-> id
        // 3-> consultantResume
        String consultantId = worker[0];
        int option = JOptionPane.showConfirmDialog(null, appointmentDetails, "Finding appointment by date.", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int year = Integer.parseInt(years.getSelectedItem().toString());
            int month = Integer.parseInt(months.getSelectedItem().toString());
            int day = Integer.parseInt(days.getSelectedItem().toString());
            int hour = Integer.parseInt(hours.getSelectedItem().toString());
            int minute = Integer.parseInt(minutes.getSelectedItem().toString());
            LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute, 00);
            String date2 = date.toString();
            List<String> appointments = convertAppointmentsListToStringList(appointmentCrud.getConsultantAppointmentByDate(consultantId, date2));
            JPanel panel = new JPanel(new BorderLayout());
            final JList<String> list = new JList<String>(appointments.toArray(new String[appointments.size()]));
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(list);
            list.setLayoutOrientation(JList.VERTICAL);
            panel.add(scrollPane);
            JFrame frame = new JFrame("List of appointments for chosen date. " + consultantCrud.getAllConsultants().get(consultantBox.getSelectedIndex()).getFullName());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(panel);
            frame.setSize(800, 250);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }


    private void findAppointmentByConsultant() throws IOException {
        List<String> consultants =  consultantSubSubMenus.covertConsultantListToStringList(consultantCrud.getAllConsultants());
        if(consultants.size()!=0) {
        String[] consultantArray = new String[consultants.size()];
        consultants.toArray(consultantArray);
        JComboBox consultantBox = new JComboBox(consultantArray);
        Object[] consultantDetails = {
                "Select consultant to find his/her respective appointments:", consultantBox,
        };
        int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Finding appointments for some consultant", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {

            String[] worker = consultantSubSubMenus.getConsultant(consultantArray[consultantBox.getSelectedIndex()]);
            // 0-> id
            // 3-> consultantResume
            String consultantId = worker[0];
            List<String> appointments = convertAppointmentsListToStringList(appointmentCrud.getAppointmentByConsultantId(consultantId));
            if(appointments.size()!=0) {
                JPanel panel = new JPanel(new BorderLayout());
                final JList<String> list = new JList<String>(appointments.toArray(new String[appointments.size()]));
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewportView(list);
                list.setLayoutOrientation(JList.VERTICAL);
                panel.add(scrollPane);
                JFrame frame = new JFrame("List of appointments for consultant "+consultantCrud.getAllConsultants().get(consultantBox.getSelectedIndex()).getFullName());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(panel);
                frame.setSize(800, 250);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "No appointments for consultant "+consultantCrud.getAllConsultants().get(consultantBox.getSelectedIndex()).getFullName(),"Warning",JOptionPane.INFORMATION_MESSAGE);

            }

        }
    }
        else
                JOptionPane.showMessageDialog(null, "No consultants in system.", "Warning", JOptionPane.WARNING_MESSAGE);
}
    private List<String> convertAppointmentsListToStringList(List<Appointment> appointment) {
        List<String> payments2String = new LinkedList<String>();
        for(int i=0;i<appointment.size();i++)
        {
            payments2String.add("Appointment number:"+appointment.get(i).getAppointmentNum().toString()+" ,Consultant ID:"+appointment.get(i).getConsultantId().toString()+" ,Consultant Resume:"+appointment.get(i).getConsultantResume().toString()+", Client ID:"+appointment.get(i).getClientId()+" ,Date:"+appointment.get(i).getDate()+" ,Price:"+appointment.get(i).getPrice()+" ,Appointment Status:"+appointment.get(i).getStatus().toString());
        }
        return  payments2String;
    }
    String[] appointmentDetails(String appointment){
        String details[] = new String[13];
        String tokens[]=appointment.split("\n");
        String appointmentNumber = tokens[1].split(" ")[2];
        String clientId = tokens[2].split(" ")[2];
        String clientContactNumber = tokens[3].split(" ")[3];
        String consultantId = tokens[4].split(" ")[2];
        String consultantResume = tokens[5].split(" ")[2];
        String date = tokens[6].split(" ")[1].replace("T","-");
        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String day = date.split("-")[2];
        String hour = date.split("-")[3].split(":")[0];
        String minute = date.split("-")[3].split(":")[1];
        String price = tokens[7].split(" ")[2];
        String appointmentStatus = tokens[8].split(" ")[2];
        details[0]=appointmentNumber;
        details[1]=clientId;
        details[2]=clientContactNumber;
        details[3]=consultantId;
        details[4]=consultantResume;
        details[5]=date;
        details[6]=year;
        details[7]=month;
        details[8]=day;
        details[9]=hour;
        details[10]=minute;
        details[11]=price;
        details[12]=appointmentStatus;

        return details;
    }
    public void deleteAppointmentById() throws IOException {

        JTextField appointmentNum = new JTextField("Input appointment number of appointment you want to delete here.");
        Object[] consultantDetails = {
                "Appointment number:", appointmentNum,
        };
        int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Updating an appointment", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            appointmentCrud.deleteOneAppointment(appointmentNum.getText());
        }
        }


    public void deleteAppointmentsMenu() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton deleteAppointmentButton = new JButton();
        deleteAppointmentButton.setBackground(Color.PINK);
        deleteAppointmentButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        deleteAppointmentButton.setText("Delete an appointment");
        deleteAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteAppointmentById();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        JButton deleteAllAppointments = new JButton();
        deleteAllAppointments.setBackground(Color.RED);
        deleteAllAppointments.setFont(new Font("Helvetica", Font.BOLD, 20));
        deleteAllAppointments.setText("Delete all appointments (Warning)");
        deleteAllAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    appointmentCrud.deleteAllAppointments();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        panel.add(deleteAppointmentButton);
        panel.add(deleteAllAppointments);
        frame.add(panel);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void saveChangesStatus() throws IOException {
        String status = appointmentCrud.exportChanges();
        JOptionPane.showMessageDialog(null, status,"Status",JOptionPane.INFORMATION_MESSAGE);
    }
}
