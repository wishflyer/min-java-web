package cn.dd.core.rest.rpc.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;


/**
 * Created by czdujb on 2015/10/28.
 */
public abstract class RestClientBase {

    private static final Logger logger = LoggerFactory.getLogger(RestClientBase.class);

    protected String baseHost = null;

    public String getBaseHost() {
        return baseHost;
    }

    public void setBaseHost(String baseHost) {
        this.baseHost = baseHost;
    }

    protected void setURI(HttpRequestBase requestBase, String requestURI) throws URISyntaxException {
        String uri;
        if (this.getBaseHost() != null) {
            uri = this.getBaseHost() + requestURI;
        } else {
            uri = requestURI;
        }
        requestBase.setURI(new URI(uri));
    }

    protected void initHttpHeader(HttpRequestBase requestBase, Map<String, String> headerParam) {
        if (headerParam != null) {
            for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                requestBase.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    protected String getResponse(HttpRequestBase requestBase) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();

            HttpResponse response = client.execute(requestBase);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Http request_bak failed on {}", requestBase.getURI().toString());
        }
        return null;
    }
}
