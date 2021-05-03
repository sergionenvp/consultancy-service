package HttpMethods;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
public class DeleteTest {
    @Test
    public void test() throws IOException {
        String id = "8";
        HttpDelete delete = new HttpDelete("http://localhost:9003/consultant/id/"+id);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(delete);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
    }
}
