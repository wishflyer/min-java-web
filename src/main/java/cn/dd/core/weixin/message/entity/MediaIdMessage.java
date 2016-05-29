package cn.dd.core.weixin.message.entity;

import cn.dd.core.weixin.message.annotation.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by wishflyer on 2016/5/29.
 */

public class MediaIdMessage {
    @XStreamAlias("MediaId")
    @XStreamCDATA
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

}
