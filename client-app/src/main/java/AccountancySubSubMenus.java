import HttpMethods.AccountancyCrud;
import HttpMethods.ConsultantCrud;
import Models.ConsultantResume;
import Models.Payment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
public class AccountancySubSubMenus {
    ConsultantSubSubMenus consultantSubSubMenus = new ConsultantSubSubMenus();
    ConsultantCrud consultantCrud = new ConsultantCrud();
    AccountancyCrud accountancyCrud = new AccountancyCrud();
    public void addPaymentMenu() throws IOException {
        List<String> consultants =  consultantSubSubMenus.covertConsultantListToStringList(consultantCrud.getAllConsultants());
        if(consultants.size()!=0) {
            String[] consultantArray = new String[consultants.size()];
            consultants.toArray(consultantArray);
            JComboBox consultantBox = new JComboBox(consultantArray);
            Object[] consultantDetails = {
                    "Select consultant to create payment on:", consultantBox,
            };
            int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Adding a new payment", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String[] worker = consultantSubSubMenus.getConsultant(consultantArray[consultantBox.getSelectedIndex()]);
                // 0-> id
                // 3-> consultantResume
                String consultantId = worker[0];
                Long workerId = Long.parseLong(consultantId);
                String consultantResume = worker[3];
                ConsultantResume resume = ConsultantResume.valueOf(consultantResume);
                JTextField cardNumber = new JTextField("Insert customer's cardNumber");
                JTextField cardHolderName = new JTextField("Insert customer's name");
                JTextField cvc = new JTextField("Insert cvc");
                JTextField price = new JTextField("Insert price");
                Object[] paymentDetails = {
                        "Card Number:", cardNumber,
                        "Card Holder Name: ", cardHolderName,
                        "Cvc", cvc,
                        "Price: ", price
                };
                int option2 = JOptionPane.showConfirmDialog(null, paymentDetails, "Adding a new consultant", JOptionPane.OK_CANCEL_OPTION);
                if (option2 == JOptionPane.OK_OPTION) {
                    String entity = accountancyCrud.createPayment(cardHolderName.getText(), cardNumber.getText(), cvc.getText(), workerId, resume, price.getText());
                    // create a JTextArea
                    JTextArea textArea = new JTextArea(6, 25);
                    textArea.setText(entity);
                    textArea.setEditable(false);
                    // wrap a scrollpane around it
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    // display them in a message dialog
                    JOptionPane.showMessageDialog(null, scrollPane, "Result", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        else
            JOptionPane.showMessageDialog(null, "No consultants in system.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
    public void findAllPayments() throws IOException {
        JPanel panel = new JPanel(new BorderLayout());
        List<String> payments = convertPaymentListToStringList(accountancyCrud.getAllPayments());
        if(payments.size()!=0) {
            final JList<String> list = new JList<String>(payments.toArray(new String[payments.size()]));
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(list);
            list.setLayoutOrientation(JList.VERTICAL);
            panel.add(scrollPane);
            JFrame frame = new JFrame("List of payments.");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(panel);
            frame.setSize(800, 250);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "No payments in system ","Warning",JOptionPane.INFORMATION_MESSAGE);

        }
    }
    public void findPaymentMenu(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton findAllPayments = new JButton();
        findAllPayments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findAllPayments();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findAllPayments.setBackground(Color.WHITE);
        findAllPayments.setText("Find all payments");
        findAllPayments.setFont(new Font("Helvetica",Font.BOLD,20));
        JButton findPaymentByPaymentId = new JButton();
        findPaymentByPaymentId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findPaymentByPaymentId();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findPaymentByPaymentId.setBackground(Color.WHITE);
        findPaymentByPaymentId.setText("Find payment by payment id");
        findPaymentByPaymentId.setFont(new Font("Helvetica",Font.BOLD,20));
        JButton findPaymentByConsultant = new JButton();
        findPaymentByConsultant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    findPaymentByConsultant();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        findPaymentByConsultant.setBackground(Color.WHITE);
        findPaymentByConsultant.setText("Find payment by consultant");
        findPaymentByConsultant.setFont(new Font("Helvetica",Font.BOLD,20));

        panel.add(findAllPayments);
        panel.add(findPaymentByPaymentId);
        panel.add(findPaymentByConsultant);
        frame.add(panel);
        frame.setSize(500, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public List<String> convertPaymentListToStringList(List<Payment> payments){
        List<String> payments2String = new LinkedList<String>();
        for(int i=0;i<payments.size();i++){
                payments2String.add("Payment ID:"+payments.get(i).getId()+", Consultant ID:"+payments.get(i).getWorkerId()+", Consultant Resume:"+payments.get(i).getResume().toString()+", Customer Name:"+payments.get(i).getCardHolderName()+", Consultant earning:"
                    +payments.get(i).getWorkerMoney()+", Company's Commission:"+payments.get(i).getCommissionCompany()+", Total Payment:"+payments.get(i).getPrice());
        }
        return  payments2String;
    }
    public void findPaymentByPaymentId() throws IOException {
        List<String> payments = convertPaymentListToStringList(accountancyCrud.getAllPayments());
        if(payments.size()!=0) {
            JTextField paymentId = new JTextField("Insert payment id");
            Object[] paymentDetails = {
                    "Payment ID:", paymentId
            };
            int option = JOptionPane.showConfirmDialog(null, paymentDetails, "Find payment by payment id.", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                // create a JTextArea
                JTextArea textArea = new JTextArea(6, 25);
                textArea.setText(accountancyCrud.getPaymentById(paymentId.getText()));
                textArea.setEditable(false);
                // wrap a scrollpane around it
                JScrollPane scrollPane = new JScrollPane(textArea);
                // display them in a message dialog
                JOptionPane.showMessageDialog(null, textArea, "Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } else{
            JOptionPane.showMessageDialog(null, "No payments in system.", "Warning", JOptionPane.WARNING_MESSAGE);

        }
    }
    public void findPaymentByConsultant() throws IOException {
        List<String> payments = convertPaymentListToStringList(accountancyCrud.getAllPayments());


        if(payments.size()!=0) {

            List<String> consultants = consultantSubSubMenus.covertConsultantListToStringList(consultantCrud.getAllConsultants());
            String[] consultantArray = new String[consultants.size()];
            consultants.toArray(consultantArray);

            JComboBox consultantBox  = new JComboBox(consultantArray);
            Object[] consultantDetails = {
                    "Select consultant for which payments you want to see:",consultantBox,

            };
            int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Getting payments by consultant.", JOptionPane.OK_CANCEL_OPTION);
            if(option==JOptionPane.OK_OPTION) {
                JPanel panel = new JPanel(new BorderLayout());
                String consultantId = String.valueOf(consultantCrud.getAllConsultants().get(consultantBox.getSelectedIndex()).getConsultantId());
                List<String> paymentList= convertPaymentListToStringList(accountancyCrud.getPaymentByConsultant(consultantId));
                if(paymentList.size()!=0) {
                    final JList<String> list = new JList<String>(payments.toArray(new String[payments.size()]));
                    JScrollPane scrollPane = new JScrollPane();
                    scrollPane.setViewportView(list);
                    list.setLayoutOrientation(JList.VERTICAL);
                    panel.add(scrollPane);
                    JFrame frame = new JFrame("List of payments for consultant "+consultantCrud.getAllConsultants().get(consultantBox.getSelectedIndex()).getFullName());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.add(panel);
                    frame.setSize(800, 250);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "No payments for consultant "+consultantCrud.getAllConsultants().get(consultantBox.getSelectedIndex()).getFullName(),"Warning",JOptionPane.INFORMATION_MESSAGE);

                }

            }

        }else {
            JOptionPane.showMessageDialog(null, "No payments in system.", "Warning", JOptionPane.WARNING_MESSAGE);
        }




    }
    public void deletePaymentMenu() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton deletePaymentButton = new JButton();
        deletePaymentButton.setBackground(Color.PINK);
        deletePaymentButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        deletePaymentButton.setText("Delete a payment");
        deletePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deletePaymentById();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        JButton deleteAllPayments = new JButton();
        deleteAllPayments.setBackground(Color.RED);
        deleteAllPayments.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            accountancyCrud.deleteAllPayments();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
        );
        deleteAllPayments.setFont(new Font("Helvetica", Font.BOLD, 20));
        deleteAllPayments.setText("Delete all Payments (Warning)");
        panel.add(deletePaymentButton);
        panel.add(deleteAllPayments);
        frame.add(panel);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void deletePaymentById() throws IOException {
        List<String> payments = convertPaymentListToStringList(accountancyCrud.getAllPayments());
        String[] consultantArray = new String[payments.size()];
        payments.toArray(consultantArray);

        JComboBox consultantBox = new JComboBox(consultantArray);
        Object[] consultantDetails = {
                "Select payment to delete:", consultantBox,

        };
        int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Deleting a consultant.", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String payment = consultantBox.getSelectedItem().toString();
            String paymentID = payment.split(",")[0].split(":")[1];
            accountancyCrud.deleteOnePayment(paymentID);
        }
    }
    public void updatePayment() throws IOException {
        List<String> payments = convertPaymentListToStringList(accountancyCrud.getAllPayments());
        String[] paymentsArray = new String[payments.size()];
        payments.toArray(paymentsArray);
        JComboBox paymentsBox  = new JComboBox(paymentsArray);
        if(payments.size()!=0) {
            Object[] consultantUpdate = {
                    "Select payment to update:", paymentsBox,

            };
            int option = JOptionPane.showConfirmDialog(null, consultantUpdate, "Updating a payment.", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {

                String resume[] = {"JUNIOR", "EXECUTIVE", "SENIOR"};
                int index = paymentsBox.getSelectedIndex();
                Long paymentID = accountancyCrud.getAllPayments().get(index).getId();
                String id = String.valueOf(paymentID);
                JTextField cardHolderName = new JTextField(accountancyCrud.getAllPayments().get(index).getCardHolderName());
                JTextField cardNumber = new JTextField(accountancyCrud.getAllPayments().get(index).getCardNumber());
                JTextField cvc = new JTextField(accountancyCrud.getAllPayments().get(index).getCvc());
                JTextField workerId = new JTextField(accountancyCrud.getAllPayments().get(index).getWorkerId().toString());
                JComboBox consultantResume = new JComboBox(resume);
                if (accountancyCrud.getAllPayments().get(index).getResume().toString().equals("JUNIOR"))
                    consultantResume.setSelectedIndex(0);
                else if (accountancyCrud.getAllPayments().get(index).getResume().toString().equals("EXECUTIVE"))
                    consultantResume.setSelectedIndex(1);
                else if (accountancyCrud.getAllPayments().get(index).getResume().toString().equals("SENIOR"))
                    consultantResume.setSelectedIndex(2);
                String x = java.lang.String.valueOf(accountancyCrud.getAllPayments().get(index).getPrice());
                JTextField price = new JTextField(x);
                Object[] consultantDetails = {
                        "Card holder name:", cardHolderName,
                        "Card Number:", cardNumber,
                        "CVC:", cvc,
                        "Worker ID: ", workerId,
                        "Consultant Resume:", consultantResume,
                        "Total price:",price
                };
                int option2 = JOptionPane.showConfirmDialog(null, consultantDetails, "Updating a payment", JOptionPane.OK_CANCEL_OPTION);
                if (option2 == JOptionPane.OK_OPTION) {
                    ConsultantResume resume1 = ConsultantResume.valueOf(consultantResume.getSelectedItem().toString());
                    accountancyCrud.updatePayment(id,cardHolderName.getText(),cardNumber.getText(),cvc.getText(),workerId.getText(),resume1,price.getText());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No payments in system.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void accountancyMenu(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        JButton netProfit = new JButton();
        netProfit.setBackground(Color.WHITE);
        netProfit.setText("Company's net profit");
        netProfit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generateNetProfit();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        netProfit.setFont(new Font("Helvetica",Font.BOLD,20));
        JButton consultantEarningByPaymentId = new JButton();
        consultantEarningByPaymentId.setBackground(Color.WHITE);
        consultantEarningByPaymentId.setText("Find consultant's earning  by payment id");
        consultantEarningByPaymentId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    consultantEarningByPaymentId();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        consultantEarningByPaymentId.setFont(new Font("Helvetica",Font.BOLD,20));
        JButton companyCommissionByPaymentID = new JButton();
        companyCommissionByPaymentID.setBackground(Color.WHITE);
        companyCommissionByPaymentID.setText("Find company's profit by payment id.");
        companyCommissionByPaymentID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generateProfitByPaymentID();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        companyCommissionByPaymentID.setFont(new Font("Helvetica",Font.BOLD,20));


        JButton consultantNetEarning = new JButton();
        consultantNetEarning.setBackground(Color.WHITE);
        consultantNetEarning.setText("Find some consultant's net earning.");
        consultantNetEarning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    consultantNetEarning();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        consultantNetEarning.setFont(new Font("Helvetica",Font.BOLD,20));

        panel.add(netProfit);
        panel.add(companyCommissionByPaymentID);
        panel.add(consultantEarningByPaymentId);
        panel.add(consultantNetEarning);
        frame.add(panel);
        frame.setSize(600, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void consultantNetEarning() throws IOException {
        List<String> consultants =  consultantSubSubMenus.covertConsultantListToStringList(consultantCrud.getAllConsultants());
        if(consultants.size()!=0)
        {

            String[] consultantArray = new String[consultants.size()];
            consultants.toArray(consultantArray);
            JComboBox consultantBox = new JComboBox(consultantArray);
            Object[] consultantDetails = {
                    "Select consultant to find his/her net earning:", consultantBox,
            };
            int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Finding Consultant Net Earning", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String consultantId = String.valueOf(consultantCrud.getAllConsultants().get(consultantBox.getSelectedIndex()).getConsultantId());
                accountancyCrud.consultantNetEarning(consultantId);
            }
          }else {
            JOptionPane.showMessageDialog(null, "No consultant in system", "Warning", JOptionPane.WARNING_MESSAGE);

        }

    }



    private void generateProfitByPaymentID() throws IOException {
        List<String> payments =  convertPaymentListToStringList(accountancyCrud.getAllPayments());
        if(payments.size()!=0) {
            JTextField paymentId = new JTextField("Insert payment id here.");
            Object[] consultantDetails = {
                    "Insert payment id to find the respective consultant earning:", paymentId,
            };
            int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Get consultant earning by payment id", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                accountancyCrud.generateProfitByPaymentID(paymentId.getText());
            }
        }
        else
            JOptionPane.showMessageDialog(null, "No payments in system.", "Warning", JOptionPane.WARNING_MESSAGE);

    }

    private void generateNetProfit() throws IOException {
        accountancyCrud.generateNetProfit();
    }

    public void consultantEarningByPaymentId() throws IOException {
        List<String> payments =  convertPaymentListToStringList(accountancyCrud.getAllPayments());
        if(payments.size()!=0) {
            JTextField paymentId = new JTextField("Insert payment id here.");
            Object[] consultantDetails = {
                    "Insert payment id to find the respective consultant earning:", paymentId,
            };
            int option = JOptionPane.showConfirmDialog(null, consultantDetails, "Get consultant earning by payment id", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                accountancyCrud.consultantEarningByPaymentId(paymentId.getText());
            }
        }else
            JOptionPane.showMessageDialog(null, "No payments in system.", "Warning", JOptionPane.WARNING_MESSAGE);

    }
    public void saveChangesStatus() throws IOException {
        String status = accountancyCrud.exportChanges();
        JOptionPane.showMessageDialog(null, status,"Status",JOptionPane.INFORMATION_MESSAGE);
    }

}
