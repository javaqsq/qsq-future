package com.qsq.common.uitl;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author QSQ
 * @create 2020/1/15 21:12
 * No, again
 * 〈文字生成器〉
 */
public class CharacterGeneratorUtil {

    public static String getRandomJianHan(int len) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            // 获取高位值
            hightPos = (176 + Math.abs(random.nextInt(39)));
            // 获取低位值
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                // 转成中文
                str = new String(b, "GBK");
            } catch (UnsupportedEncodingException ex) {
                str = "乾";
            }
            ret.append(str);
        }
        return ret.toString();
    }

    /**
     * 生成随机用户名，数字和字母组成
     */
    public static String getStringRandom(int length, String type) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            //输出字母还是数字
            if ("char".equalsIgnoreCase(type)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else if ("num".equalsIgnoreCase(type)) {
                val.append(String.valueOf(random.nextInt(10)));
            }
        }
        return val.toString().toLowerCase();
    }
}