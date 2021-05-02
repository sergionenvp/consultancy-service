package HttpMethods;

import Models.Consultant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    public String deleteAllConsultants() throws IOException {
        HttpDelete delete = new HttpDelete("http://localhost:9003/consultant/deleteAll");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);
        if(response.getStatusLine().getStatusCode()==200)
            return "All consultants deleted successfully.";
        else
            return "Error "+EntityUtils.toString(response.getEntity());
    }
    public String deleteOneConsultant(String id) throws IOException {
        HttpDelete delete = new HttpDelete("http://localhost:9003/consultant/id/"+id);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);
        if(response.getStatusLine().getStatusCode()==200)
            return "Deletion request ran successfully.";
        else
            return "Error "+EntityUtils.toString(response.getEntity());
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
            String consultant2String = "Consultant ID:"+consultant.consultantId+", Full Name:"+consultant.fullName+", Age:"
                    +consultant.age+", Consultant Resume:"+consultant.consultantResume.toString()+", Phone Number:"+
                    consultant.phoneNumber;
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




}
