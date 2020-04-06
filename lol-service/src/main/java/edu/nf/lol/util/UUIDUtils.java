package edu.nf.lol.util;

import java.util.UUID;

/**
 * @author wangl
 * @date 2019/10/9
 */
public class UUIDUtils {

    public static String createUUID(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(createUUID());
    }
}
