package cn.dd.core.rest.rpc.client;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by czdujb on 2015/10/28.
 */
public class RestGetClient extends RestClientBase {

    private static final Logger logger = LoggerFactory.getLogger(RestGetClient.class);

    public String callRestRPC(String requestURI, Map<String, String> headerParam,boolean useURLTransferParam) {
        try {
            HttpGet httpGet = new HttpGet();
            if(useURLTransferParam){
                setURI(httpGet, requestURI,headerParam);
            }else{
                setURI(httpGet, requestURI);
                initHttpHeader(httpGet, headerParam);
            }
            return getResponse(httpGet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            logger.error("Error request_bak uri: {}", requestURI);
        }
        return null;
    }
}
