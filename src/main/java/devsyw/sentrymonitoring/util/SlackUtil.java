package devsyw.sentrymonitoring.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SlackUtil {

    public void sendWebHookMessage(String user, String channel, String message) {

        String url = "https://hooks.slack.com/services/xxxxx";

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        JSONObject json = new JSONObject();
        try {

            json.put("channel", channel);
            json.put("text", message);
            //json.put("icon_emoji", ":원하는 아이콘:");	//커스터마이징으로 아이콘 만들수도 있다!
            json.put("username", user);
            post.addParameter("payload", json.toString());
            post.setRequestHeader("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");

            int responseCode = client.executeMethod(post);
            String response = post.getResponseBodyAsString();

            if (responseCode != HttpStatus.SC_OK) {
                log.error("Fail. Slack Sent. response : " + responseCode);
            }else {
                log.info("==> Slack Sent. Sucess.!!");
            }

        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException posting to Slack " + e.getMessage());
        } catch (IOException e) {
            log.error("IOException to Slack " + e.getMessage());
        } finally {
            post.releaseConnection();
        }

    }

}
