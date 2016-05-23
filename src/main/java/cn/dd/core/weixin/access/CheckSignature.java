package cn.dd.core.weixin.access;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by wishflyer on 2016/5/23.
 */
@RestController
public class CheckSignature {

    @RequestMapping(value="/weixin/checkSignature", method= RequestMethod.GET)
    public String checkSignature(@RequestParam(value ="signature") String signature
                                 ,@RequestParam(value ="timestamp") String timestamp
                                 ,@RequestParam(value ="nonce")      String nonce
                                 ,@RequestParam(value ="echostr") String echostr){
        //）将token、timestamp、nonce三个参数进行字典序排序
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        System.out.println("signature:"+signature);
        System.out.println("timestamp:"+timestamp);
        System.out.println("nonce:"+nonce);
        System.out.println("echostr:"+echostr);
        String[] signatureArrays = new String[]{signature,timestamp,nonce};
        Arrays.sort(signatureArrays);
        System.out.println("排序后："+signatureArrays[0]+","+signatureArrays[1]+","+signatureArrays[2]);
        //拼接
        String str = signatureArrays[0]+signatureArrays[1]+signatureArrays[2];
        //进行SHA1加密
        String mySignature = new String(DigestUtils.sha1Hex(str));

        System.out.println("mySignature:"+mySignature);
        if(mySignature.equals(signature)){
            System.out.println("验证成功！");
            return echostr;
        }else{
            System.out.println("FAIL!!!!！");
            return null;
        }

    }

}
