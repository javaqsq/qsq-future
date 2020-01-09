package com.qsq.common.uitl;

import com.qsq.common.enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author QSQ
 * @create 2019/12/30 11:37
 * No, again
 * 〈图片生成器〉
 */
@Slf4j
public class RandomImageGenerator {

    /**
     * @param response
     * @throws IOException
     */
    public static void getRandomCode(HttpServletResponse response) throws IOException {
        // 响应头信息
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expries", 0);
        response.setContentType("image/png");
        // 随机数生成类
        Random random = new Random();

        // 定义验证码的位数
        int size = 5;

        // 定义变量保存生成的验证码
        StringBuilder vCode = new StringBuilder();
        char c;
        // 产生验证码
        for (int i = 0; i < size; i++) {
            // 产生一个26以内的随机整数
            int number = random.nextInt(26);
            // 如果生成的是偶数，则随机生成一个数字
            if (number % 2 == 0) {
                c = (char) ('0' + (char) ((int) (Math.random() * 10)));
                // 如果生成的是奇数，则随机生成一个字母
            } else {
                c = (char) ((char) ((int) (Math.random() * 26)) + 'A');
            }
            vCode.append(c);
        }

        // 验证码图片的生成
        // 定义图片的宽度和高度
        int width = (int) Math.ceil(size * 20);
        int height = 37;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图片的上下文
        Graphics gr = image.getGraphics();
        // 设定图片背景颜色
//        gr.setColor(Color.GREEN);
        gr.setColor(getRandColor(200, 250));
        gr.fillRect(0, 0, width, height);
        // 设定图片边框
        gr.setColor(Color.GRAY);
        gr.drawRect(0, 0, width - 1, height - 1);
        // 画十条干扰线 100 显示100条干扰线
//        for (int i = 0; i < 10; i++) {
//            int x1 = random.nextInt(width);
//            int y1 = random.nextInt(height);
//            int x2 = random.nextInt(width);
//            int y2 = random.nextInt(height);
//            gr.setColor(randomColor());
//            gr.drawLine(x1, y1, x2, y2);
//        }
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gr.drawLine(x, y, x + xl, y + yl);
        }
        // 设置字体，画验证码
        gr.setColor(randomColor());
        gr.setFont(randomFont());
        gr.drawString(vCode.toString(), 10, 26);
        // 图像生效
        gr.dispose();
        // 输出到页面
        ServletOutputStream sos = null;
        log.info(" ### 生成随机验证码{}", vCode.toString());
        try {
            sos = response.getOutputStream();
            ImageIO.write(image, "png", sos);
            sos.close();
        } catch (IOException e) {
            throw ExceptionEnum.IMAGE_CODE_GENERATOR.getException();
        } finally {
            if (sos != null) {
                sos.close();
            }
        }
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    // 生成随机的颜色
    private static Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }

    private static String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    private static Random r = new Random();

    // 生成随机的字体
    private static Font randomFont() {
        int index = r.nextInt(fontNames.length);
        // 生成随机的字体名称
        String fontName = fontNames[index];
        int style = r.nextInt(4);
        // 生成随机字号, 24 ~ 28
        int size = r.nextInt(3) + 24;
        return new Font(fontName, Font.ITALIC, size);
    }


}