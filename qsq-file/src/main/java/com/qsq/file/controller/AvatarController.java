package com.qsq.file.controller;

import cn.hutool.core.date.DateUtil;
import com.qsq.common.enums.ExceptionEnum;
import com.qsq.common.model.ResultResponse;
import com.qsq.file.constant.ConstantFilePath;
import com.qsq.file.utils.UploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author QSQ
 * @create 2020/1/15 10:11
 * No, again
 * 〈头像上传〉
 */
@RestController
@RequestMapping("/file/avatar")
public class AvatarController {


    @PostMapping("/upload")
    public ResultResponse uploadAvatar(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw ExceptionEnum.UPLOAD_FILE_IS_EMPTY.getException();
        }
        String[] avatarSuffix = {".jpg", ".jpng", ".png"};
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        if (!Arrays.asList(avatarSuffix).contains(fileSuffix)) {
            throw ExceptionEnum.UPLOAD_FILE_SUFFIX_NOT_MATCH.getException();
        }
        // 新的文件名
        String newFilename = DateUtil.format(new Date(), "yyyyMMddHHmmss") + file.getOriginalFilename();
        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getAvatarDirFile();
        //生成的文件路径
        File productFile = new File(fileDir + File.separator + newFilename);
        try {
            file.transferTo(productFile);
        } catch (IOException e) {
            throw ExceptionEnum.UPLOAD_FILE_ERROR.getException();
        }
        //服务器的地址
        return ResultResponse.success(ConstantFilePath.FILE_SERVER_PATH + ConstantFilePath.AVATAR_PATH_PREFIX + newFilename);
    }


}