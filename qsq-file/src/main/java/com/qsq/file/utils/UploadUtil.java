package com.qsq.file.utils;

import com.qsq.file.constant.ConstantFilePath;

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
    public static File getAvatarDirFile() {
        String fileDirPath = ConstantFilePath.STATIC_FILE_PREFIX_PATH + ConstantFilePath.AVATAR_PATH_PREFIX;
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;
    }

}