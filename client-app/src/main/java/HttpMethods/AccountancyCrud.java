package HttpMethods;
import Models.ConsultantResume;
import Models.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
public class    AccountancyCrud {
    ConsultantCrud consultantCrud = new ConsultantCrud();
    private static final ObjectMapper om = new ObjectMapper();
    // post accountancy
    public String createPayment(String cardHolderName, String cardNumber, String cvc, Long workerId, ConsultantResume resume, String price) throws IOException {
        HttpPost post = new HttpPost("http://localhost:9001/payments");
        post.addHeader("Content-type", "application/json");
        StringBuilder json = new StringBuilder();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        json.append("{");
        json.append("\"cardNumber\":\"" + cardNumber + "\",");
        json.append("\"cardHolderName\":\"" + cardHolderName + "\",");
        json.append("\"date\":\"" + date+ "\",");
        json.append("\"cvc\":\"" + cvc + "\",");
        json.append("\"price\":\"" + price + "\",");
        json.append("\"workerId\":\"" + workerId + "\",");
        json.append("\"resume\":\"" + resume.toString() + "\"");
        json.append("}");
        // send a JSON data
        post.setEntity(new StringEntity(json.toString()));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode()==201)
            return "Payment created successfully.";
        else
            return "Error "+ EntityUtils.toString(response.getEntity());
    }
    public List<Payment> getAllPayments() throws IOException {
        HttpGet request = new HttpGet("http://localhost:9001/payments/all");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        // 1. convert JSON array to Array objects
        List<Payment> payments = Arrays.asList(om.readValue(EntityUtils.toString(response.getEntity()), Payment[].class));
        return payments;
    }
    public String getPaymentById(String paymentId) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9001/payments/"+paymentId);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode()==200)
        {
            Payment payment = om.readValue(EntityUtils.toString(response.getEntity()),Payment.class);
            String payment2String = "Payment ID:"+payment.getId()+", Worker ID:"+payment.getWorkerId()+", Customer's Name:"+payment.getCardHolderName()+", Consultant earning:"
                    +payment.getWorkerMoney()+", Company's Commission:"+payment.getCommissionCompany()+", Total payment:"+payment.getPrice();
            return  payment2String;
        }
        else if(response.getStatusLine().getStatusCode()==404)
            return "Payment with payment id "+paymentId+" not found";
        else
            return "Error "+EntityUtils.toString(response.getEntity());
    }

    public void deleteAllPayments() throws IOException {
        HttpDelete delete = new HttpDelete("http://localhost:9001/payments/delete/all");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);

        if(response.getStatusLine().getStatusCode()==200)
            JOptionPane.showMessageDialog(null, "All payments deleted successfully.","Successful Deletion.",JOptionPane.INFORMATION_MESSAGE);
        else
        {
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(EntityUtils.toString(response.getEntity()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane,"Warning",JOptionPane.WARNING_MESSAGE);
        }

    }
    public void deleteOnePayment(String id) throws IOException {
        HttpDelete delete = new HttpDelete("http://localhost:9001/payments/delete/"+id);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);
        if(response.getStatusLine().getStatusCode()==200)
            JOptionPane.showMessageDialog(null, "Deletion request to delete payment with id "+id+" passed.","Deletion request passed.",JOptionPane.INFORMATION_MESSAGE);
        else
        {
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(EntityUtils.toString(response.getEntity()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane,"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void updatePayment(String id,String cardHolderName, String cardNumber, String cvc, String workerId, ConsultantResume resume, String price) throws IOException {
        HttpPut put = new HttpPut("http://localhost:9001/payments/update/"+id);
        put.addHeader("Content-type", "application/json");
        StringBuilder json = new StringBuilder();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        json.append("{");
        json.append("\"cardNumber\":\"" + cardNumber + "\",");
        json.append("\"cardHolderName\":\"" + cardHolderName + "\",");
        json.append("\"date\":\"" + date+ "\",");
        json.append("\"cvc\":\"" + cvc + "\",");
        json.append("\"price\":\"" + price + "\",");
        json.append("\"workerId\":\"" + workerId + "\",");
        json.append("\"resume\":\"" + resume.toString() + "\"");
        json.append("}");
        // send a JSON data
        put.setEntity(new StringEntity(json.toString()));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(put);
        if(response.getStatusLine().getStatusCode()==200)
            JOptionPane.showMessageDialog(null, "Payment with payment id "+id+" have been updated successfully.","Successful update",JOptionPane.WARNING_MESSAGE);
        else
        {
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(EntityUtils.toString(response.getEntity()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane,"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    public void consultantEarningByPaymentId(String paymentId) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9001/payments/salary/payment/"+paymentId);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode()==200)
        {
            String consultantEarning = EntityUtils.toString(response.getEntity());
            JOptionPane.showMessageDialog(null, "Consultant earning for payment "+paymentId+" is $"+ consultantEarning ,"Result",JOptionPane.INFORMATION_MESSAGE);

        }
        else
        {
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(EntityUtils.toString(response.getEntity()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane,"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    public String exportChanges() throws IOException {
        //changing to comp. whitepsace

        HttpGet request = new HttpGet("http://localhost:9001/payments/export");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        // 1. convert JSON array to Array object
        if(response.getStatusLine().getStatusCode()==200)
            return "Payment changes saved successfully.";
        else
            return "Error: changes did not all save successfully.";
    }

    public void generateNetProfit() throws IOException {
        HttpGet request = new HttpGet("http://localhost:9001/payments/company/balance");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        // 1. convert JSON array to Array object
        if(response.getStatusLine().getStatusCode()==200) {
            String netProfit = EntityUtils.toString(response.getEntity());
            JOptionPane.showMessageDialog(null,"Company's net profit is "+netProfit, "Net Profit", JOptionPane.INFORMATION_MESSAGE);
        }
            else
        {
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(EntityUtils.toString(response.getEntity()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane,"Warning",JOptionPane.WARNING_MESSAGE);
        }

    }

    public void generateProfitByPaymentID(String paymentID) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9001/payments/company/balance/payment/"+paymentID);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        // 1. convert JSON array to Array object
        if(response.getStatusLine().getStatusCode()==200) {
            String netProfit = EntityUtils.toString(response.getEntity());
            JOptionPane.showMessageDialog(null,"Company's net profit is "+netProfit, "Net Profit", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(EntityUtils.toString(response.getEntity()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane,"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    public List<Payment> getPaymentByConsultant(String consultantId) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9001/payments/worker/"+consultantId);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        // 1. convert JSON array to Array objects
        List<Payment> payments = Arrays.asList(om.readValue(EntityUtils.toString(response.getEntity()), Payment[].class));
        return payments;

    }

    public void consultantNetEarning(String consultantId) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9001/payments/salary/worker/"+consultantId);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode()==200)
        {
            String consultantDetails = consultantCrud.getConsultantById(consultantId);
            String consultantEarning = EntityUtils.toString(response.getEntity());
            String message ="Consultant Details: "+consultantDetails+"\n"+"In total  consultant earned: $"+consultantEarning;
            JOptionPane.showMessageDialog(null, message,"Net Earning of consultant",JOptionPane.INFORMATION_MESSAGE);

        }
        else {
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(EntityUtils.toString(response.getEntity()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane,"Warning",JOptionPane.WARNING_MESSAGE);

        };

    }
}

