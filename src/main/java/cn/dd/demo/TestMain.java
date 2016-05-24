package cn.dd.demo;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.Timestamp;
import java.util.Arrays;

/**
 * Created by wishflyer on 2016/5/23.
 */
public class TestMain {


    public static void main(String[] args){
        String signature = "9eb72371ac5afe5ae39a1c388ca70aab5aa74ce4";
        String token = "UUDDLRLRABAB";
        String timestamp = "1464020123";
        String nonce = "1278105718";
        String echostr = "5637206339097717915";

        System.out.println("signature:"+signature);
        System.out.println("token:"+token);
        System.out.println("timestamp:"+timestamp);
        System.out.println("nonce:"+nonce);
        System.out.println("echostr:"+echostr);
        String[] signatureArrays = new String[]{token,timestamp,nonce};
        Arrays.sort(signatureArrays);
        System.out.println("排序后："+signatureArrays[0]+","+signatureArrays[1]+","+signatureArrays[2]);
        //拼接
        String str = signatureArrays[0]+signatureArrays[1]+signatureArrays[2];
        //进行SHA1加密
        String mySignature = new String(DigestUtils.sha1Hex(str));

        System.out.println("mySignature:"+mySignature);
        if(mySignature.equals(signature)){
            System.out.println("验证成功！");
        }else{
            System.out.println("FAIL!!!!！");
        }
        String testNum = new String(DigestUtils.sha1Hex("123123123"));
        System.out.println(testNum);

    }



}
