package HttpMethods;
import Models.Consultant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class ConsultantCrud {
    private static final ObjectMapper om = new ObjectMapper();
    public String create(String fullName, String age, String phoneNumber, String consultantResume) throws IOException {
        HttpPost post = new HttpPost("http://localhost:9003/consultant");
        post.addHeader("Content-type", "application/json");
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"fullName\":\"" + fullName + "\",");
        json.append("\"age\":\"" + age + "\",");
        json.append("\"phoneNumber\":\"" + phoneNumber + "\",");
        json.append("\"consultantResume\":\"" + consultantResume + "\"");
        json.append("}");
        // send a JSON data
        post.setEntity(new StringEntity(json.toString()));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode()==201)
            return "Consultant "+ fullName+" created successfully.";
        else
            return "Error "+EntityUtils.toString(response.getEntity());
    }
    public void deleteAllConsultants() throws IOException {
        HttpDelete delete = new HttpDelete("http://localhost:9003/consultant/deleteAll");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);

        if(response.getStatusLine().getStatusCode()==200)
            JOptionPane.showMessageDialog(null, "All consultants deleted successfully.","Successful Deletion.",JOptionPane.INFORMATION_MESSAGE);
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
    public void deleteOneConsultant(String id) throws IOException {
        HttpDelete delete = new HttpDelete("http://localhost:9003/consultant/id/"+id);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);
        if(response.getStatusLine().getStatusCode()==200)
            JOptionPane.showMessageDialog(null, "Deletion request to delete consultant with id "+id+" passed.","Deletion request passed.",JOptionPane.INFORMATION_MESSAGE);
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
    public List<Consultant> getAllConsultants() throws IOException {
        HttpGet request = new HttpGet("http://localhost:9003/consultant");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        // 1. convert JSON array to Array objects
        List<Consultant> consultants = Arrays.asList(om.readValue(EntityUtils.toString(response.getEntity()), Consultant[].class));
        return consultants;
    }
    public String getConsultantById(String consultantId) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9003/consultant/id/"+consultantId);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode()==200)
        {
            Consultant consultant = om.readValue(EntityUtils.toString(response.getEntity()),Consultant.class);
            String consultant2String = "Consultant ID:"+consultant.getConsultantId()+", Full Name:"+consultant.getFullName()+", Age:"
                    +consultant.getAge()+", Consultant Resume:"+consultant.getConsultantResume().toString()+", Phone Number:"+
                    consultant.getPhoneNumber();
            return  consultant2String;
        }
        else if(response.getStatusLine().getStatusCode()==404)
            return "Consultant with consultant id "+consultantId+" not found";
        else
            return "Error "+EntityUtils.toString(response.getEntity());
    }
    public List<Consultant> getConsultantsByResume(String consultantResume) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9003/consultant/consultantResume/"+consultantResume);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
            // 1. convert JSON array to Array object
        if(response.getStatusLine().getStatusCode()==200) {
            List<Consultant> consultants = Arrays.asList(om.readValue(EntityUtils.toString(response.getEntity()), Consultant[].class));
            return consultants;
        }
        else
        {List<Consultant> consultants = new LinkedList<>();
        return  consultants;}
    }
    public List<Consultant> getConsultantsByName(String fullName) throws IOException {
        //changing to comp. whitepsace
        fullName=fullName.replace(" ","%20");
        HttpGet request = new HttpGet("http://localhost:9003/consultant/fullName/"+fullName);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        // 1. convert JSON array to Array object
        if(response.getStatusLine().getStatusCode()==200) {
            List<Consultant> consultants = Arrays.asList(om.readValue(EntityUtils.toString(response.getEntity()), Consultant[].class));
            return consultants;
        }
        else
        {List<Consultant> consultants = new LinkedList<>();
            return  consultants;}

    }
    public List<Consultant> getConsultantsByMinimumAge(String age) throws IOException {
        HttpGet request = new HttpGet("http://localhost:9003/consultant/ageMinimum/"+age);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode()==200) {
            // 1. convert JSON array to Array object
            List<Consultant> consultants = Arrays.asList(om.readValue(EntityUtils.toString(response.getEntity()), Consultant[].class));
            return consultants;
        }
        else if(response.getStatusLine().getStatusCode() == 404){
            //return empty list
            List<Consultant> consultants = new LinkedList<>();
            JOptionPane.showMessageDialog(null, "No consultant older than "+age+" found.","Warning 404 not found",JOptionPane.WARNING_MESSAGE);
            return consultants;
        }
        else
        {
            //return empty list
            List<Consultant> consultants = new LinkedList<>();
            JOptionPane.showMessageDialog(null, "Ensure that input is an integer","Bad request",JOptionPane.WARNING_MESSAGE);
            return consultants;
        }
    }

    public String exportChanges() throws IOException {
        //changing to comp. whitepsace

        HttpGet request = new HttpGet("http://localhost:9003/consultant/export");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        // 1. convert JSON array to Array object
        if(response.getStatusLine().getStatusCode()==200)
            return "Consultant changes saved successfully.";
        else
            return "Error: changes did not all save successfully.";
    }

    public void updateConsultant(String consultantId, String fullName, String age, String phoneNumber, String consultantResume) throws IOException {
        HttpPut put = new HttpPut("http://localhost:9003/consultant/id/"+consultantId);
        put.addHeader("Content-type", "application/json");
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"fullName\":\"" + fullName + "\",");
        json.append("\"age\":\"" + age + "\",");
        json.append("\"phoneNumber\":\"" + phoneNumber + "\",");
        json.append("\"consultantResume\":\"" + consultantResume + "\"");
        json.append("}");
        // send a JSON data
        put.setEntity(new StringEntity(json.toString()));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(put);
        if(response.getStatusLine().getStatusCode()==200)
            JOptionPane.showMessageDialog(null, "Consultant with consultant id "+consultantId+" have been updated successfully.","Successfully update",JOptionPane.WARNING_MESSAGE);
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
