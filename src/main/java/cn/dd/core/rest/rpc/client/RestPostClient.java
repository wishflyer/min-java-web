package cn.dd.core.rest.rpc.client;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by czdujb on 2015/10/28.
 */
public class RestPostClient extends RestClientBase {

    private static final Logger logger = LoggerFactory.getLogger(RestPostClient.class);

    public String callRestRPC(String requestURI, Map<String, String> headerParam, Map<String, String> postParam) {
        try {
            HttpPost httpPost = new HttpPost();

            setURI(httpPost, requestURI);

            initHttpHeader(httpPost, headerParam);

            if (postParam != null) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : postParam.entrySet()) {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
                httpPost.setEntity(entity);
            }

            return getResponse(httpPost);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            logger.error("Error request_bak uri: {}", requestURI);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("Error request_bak param on : {}", requestURI);
        }
        return null;
    }

}
