package com.qsq.file.utils;

import java.io.File;

/**
 * @author QSQ
 * @create 2020/1/13 22:15
 * No, again
 * 〈文件上传路径地址设置〉
 */
public class UploadUtil {

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    /**
     * 头像路径
     */
    public final static String AVATAR_PATH_PREFIX = "avatar";

    public static File getAvatarDirFile() {

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = "E:\\java-code\\future-project-shop\\file-path\\" + AVATAR_PATH_PREFIX;

        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

}