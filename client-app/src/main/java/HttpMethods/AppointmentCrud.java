package HttpMethods;
import Models.Appointment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class AppointmentCrud {
    private static final ObjectMapper om = new ObjectMapper();
    public void createAppointment(Long consultantId, String clientId, LocalDateTime date, String clientContactNum, String price, String status, String consultantResume) throws IOException {
        HttpPost post = new HttpPost("http://localhost:9002/appointments");
        post.addHeader("Content-type", "application/json");
        StringBuilder json = new StringBuilder();
        String date2 = date.toString().replace("T"," ");
        json.append("{");
        json.append("\"consultantId\":\"" + consultantId + "\",");
        json.append("\"clientId\":\"" + clientId + "\",");
        json.append("\"clientContactNum\":\"" + clientContactNum + "\",");
        json.append("\"date\":\"" + date2+ "\",");
        json.append("\"price\":\"" + price + "\",");
        json.append("\"status\":\"" + status + "\",");
        json.append("\"consultantResume\":\"" + consultantResume + "\"");
        json.append("}");
        // send a JSON data
        post.setEntity(new StringEntity(json.toString()));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == 201)
            JOptionPane.showMessageDialog(null, "Appointment created successfully.", "Appointment created", JOptionPane.INFORMATION_MESSAGE);
        else {
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(EntityUtils.toString(response.getEntity()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane, "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public String getAppointmentByAppointmentNum(String appointmentId) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9002/appointments/id/" + appointmentId);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == 200) {
            Appointment appointment = om.readValue(EntityUtils.toString(response.getEntity()), Appointment.class);
            String appointmentDetails = "Appointment Details:\nAppointment number: " + appointment.getAppointmentNum() + "\nClient ID: " + appointment.getClientId()+ "\nClient contact number: " + appointment.getClientContactNum()
                    + "\nConsultant ID: " + appointment.getConsultantId() + "\nConsultant Resume: " + appointment.getConsultantResume().toString()
                    + "\nDate: " + appointment.getDate().toString() + "\nAppointment price: " + appointment.getPrice() + "\nAppointment Status: " + appointment.getStatus().toString();
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(appointmentDetails);
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane, "Appointment Details", JOptionPane.INFORMATION_MESSAGE);
            return  appointmentDetails;
        } else {
            // create a JTextArea
            JTextArea textArea = new JTextArea(6, 25);
            textArea.setText(EntityUtils.toString(response.getEntity()));
            textArea.setEditable(false);
            // wrap a scrollpane around it
            JScrollPane scrollPane = new JScrollPane(textArea);
            // display them in a message dialog
            JOptionPane.showMessageDialog(null, scrollPane, "Warning", JOptionPane.WARNING_MESSAGE);
            return  EntityUtils.toString(response.getEntity());
        }
    }

    public List<Appointment> getConsultantAppointmentByDate(String consultantID,String date) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9002/appointments/date/"+consultantID+"/"+date);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == 200) {
            List<Appointment> appointments = Arrays.asList(om.readValue(EntityUtils.toString(response.getEntity()), Appointment[].class));
            return appointments;
        } else {
            List<Appointment> appointments = new LinkedList<>();
            return appointments;
        }
    }

    public List<Appointment> getAppointmentByConsultantId (String consultantId) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9002/appointments/consultant/"+consultantId);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == 200) {
            List<Appointment> appointments = Arrays.asList(om.readValue(EntityUtils.toString(response.getEntity()), Appointment[].class));

            return appointments;
        } else {
            List<Appointment> appointments = new LinkedList<>();
            return appointments;
        }
    }

    public void updateAppointment(String appointNumber, Long consultantId, String clientId, LocalDateTime date, String clientContactNum, String price, String status, String consultantResume) throws IOException {
        HttpPut put = new HttpPut("http://localhost:9002/appointments/update/"+appointNumber);
        put.addHeader("Content-type", "application/json");
        String date2 = date.toString().replace("T"," ");
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"consultantId\":\"" + consultantId + "\",");
        json.append("\"clientId\":\"" + clientId + "\",");
        json.append("\"clientContactNum\":\"" + clientContactNum + "\",");
        json.append("\"date\":\"" + date2+ "\",");
        json.append("\"price\":\"" + price + "\",");
        json.append("\"status\":\"" + status + "\",");
        json.append("\"consultantResume\":\"" + consultantResume + "\"");
        json.append("}");

        // send a JSON data
        put.setEntity(new StringEntity(json.toString()));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(put);
        if(response.getStatusLine().getStatusCode()==200)
            JOptionPane.showMessageDialog(null, "Appointment with appointment number "+appointNumber+" have been updated successfully.","Successfully update",JOptionPane.INFORMATION_MESSAGE);
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

        HttpGet request = new HttpGet("http://localhost:9002/appointment/export");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        // 1. convert JSON array to Array object
        if(response.getStatusLine().getStatusCode()==200)
            return "Appointment changes saved successfully.";
        else
            return "Error: changes did not all save successfully.";
    }


    public void deleteAllAppointments() throws IOException {
        HttpDelete delete = new HttpDelete("http://localhost:9002/appointments/delete/all");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);

        if(response.getStatusLine().getStatusCode()==200)
            JOptionPane.showMessageDialog(null, "All appointments deleted successfully.","Successful Deletion.",JOptionPane.INFORMATION_MESSAGE);
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
    public void deleteOneAppointment(String id) throws IOException {
        HttpDelete delete = new HttpDelete("http://localhost:9002/appointments/id/"+id);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);
        if(response.getStatusLine().getStatusCode()==200)
            JOptionPane.showMessageDialog(null, "Deletion request to delete appointment with id "+id+" passed.","Deletion request passed.",JOptionPane.INFORMATION_MESSAGE);
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

}
