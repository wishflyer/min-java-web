package cn.dd.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * UUIDUtils 用于生成32位的GUID
 */
public class UUIDUtils {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return StringUtils.replace(uuid.toString().trim(), "-", "");
    }

}
