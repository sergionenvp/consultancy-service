package HttpMethods;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HttpMethodTest {
    @Test
    public void postTest() throws IOException {
        HttpPost post = new HttpPost("http://localhost:9003/consultant");
        post.addHeader("Content-type", "application/json");
        StringBuilder json = new StringBuilder();
        json.append("{");
        String name = "Francesca Galea";
        json.append("\"fullName\":\""+name+"\",");
        json.append("\"age\":\"20\",");
        json.append("\"phoneNumber\":\"9999999\",");
        json.append("\"consultantResume\":\"SENIOR\"");
        json.append("}");
        // send a JSON data
        post.setEntity(new StringEntity(json.toString()));
        CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post);
             assertThat(response.getStatusLine().getStatusCode(), equalTo(201));
    }
    @Test
    public void DeleteTest() throws IOException {
        HttpDelete delete = new HttpDelete("http://localhost:9003/consultant/deleteAll");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);
        assertThat(response.getStatusLine().getStatusCode(),equalTo(200));
    }
    }
