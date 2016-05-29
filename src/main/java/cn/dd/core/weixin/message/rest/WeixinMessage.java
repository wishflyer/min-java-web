package cn.dd.core.weixin.message.rest;

import cn.dd.core.weixin.config.WinXinConfig;
import cn.dd.core.weixin.message.entity.ImageMessage;
import cn.dd.core.weixin.message.entity.InputMessage;
import cn.dd.core.weixin.message.entity.MsgType;
import cn.dd.core.weixin.message.entity.OutputMessage;
import cn.dd.core.weixin.utils.SerializeXmlUtil;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wishflyer on 2016/5/23.
 */
@RestController
public class WeixinMessage {

    private static final Logger logger = LoggerFactory.getLogger(WeixinMessage.class);

    @RequestMapping(value="/weixin/message", method= RequestMethod.GET)
    public String message(@RequestParam(value ="signature") String signature
                                 ,@RequestParam(value ="timestamp") String timestamp
                                 ,@RequestParam(value ="nonce")      String nonce
                                 ,@RequestParam(value ="echostr") String echostr){
        //）将token、timestamp、nonce三个参数进行字典序排序
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String[] signatureArrays = new String[]{WinXinConfig.TOKEN,timestamp,nonce};
        Arrays.sort(signatureArrays);
        //进行SHA1加密
        String mySignature = new String(DigestUtils.sha1Hex(signatureArrays[0]+signatureArrays[1]+signatureArrays[2]));
        //判断是否一致
        if(mySignature.equals(signature)){
            logger.info("验证成功！");
            return echostr;
        }else{
            logger.error("FAIL!!!!！");
            return null;
        }
    }

    @RequestMapping(value="/weixin/message", method= RequestMethod.POST)
    public void message(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("receive message...");
        // 处理接收消息
        ServletInputStream in = null;
        in = request.getInputStream();

        // 将POST流转换为XStream对象
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(InputMessage.class);
        xs.processAnnotations(OutputMessage.class);
        // 将指定节点下的xml节点数据映射为对象
        xs.alias("xml", InputMessage.class);
        // 将流转换为字符串
        StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            xmlMsg.append(new String(b, 0, n, "UTF-8"));
        }
        // 将xml内容转换为InputMessage对象
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());

        String servername = inputMsg.getToUserName();// 服务端
        String custermname = inputMsg.getFromUserName();// 客户端
        long createTime = inputMsg.getCreateTime();// 接收时间
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间

        // 取得消息类型
        String msgType = inputMsg.getMsgType();
        // 根据消息类型获取对应的消息内容
        if(msgType.equals(MsgType.TEXT)) {
            // 文本消息
            System.out.println("开发者微信号：" + inputMsg.getToUserName());
            System.out.println("发送方帐号：" + inputMsg.getFromUserName());
            System.out.println("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));
            System.out.println("消息内容：" + inputMsg.getContent());
            System.out.println("消息Id：" + inputMsg.getMsgId());

            StringBuffer str = new StringBuffer();
            str.append("<xml>");
            str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");
            str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");
            str.append("<CreateTime>" + returnTime + "</CreateTime>");
            str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
            str.append("<Content><![CDATA[你说的是：" + inputMsg.getContent() + "，吗？]]></Content>");
            str.append("</xml>");
            logger.info(str.toString());
            response.getWriter().write(str.toString());
        }
        // 获取并返回多图片消息
        if (msgType.equals(MsgType.IMAGE)) {
            System.out.println("获取多媒体信息");
            System.out.println("多媒体文件id：" + inputMsg.getMediaId());
            System.out.println("图片链接：" + inputMsg.getPicUrl());
            System.out.println("消息id，64位整型：" + inputMsg.getMsgId());

            OutputMessage outputMsg = new OutputMessage();
            outputMsg.setFromUserName(servername);
            outputMsg.setToUserName(custermname);
            outputMsg.setCreateTime(returnTime);
            outputMsg.setMsgType(msgType);
            ImageMessage images = new ImageMessage();
            images.setMediaId(inputMsg.getMediaId());
            outputMsg.setImage(images);
            logger.info("xml转换：\n" + xs.toXML(outputMsg));
            response.getWriter().write(xs.toXML(outputMsg));
        }
        //return "success";
    }

}
