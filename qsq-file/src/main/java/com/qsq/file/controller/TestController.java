package com.qsq.file.controller;

import cn.hutool.core.date.DateUtil;
import com.qsq.common.model.ResultResponse;
import com.qsq.file.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author QSQ
 * @create 2020/1/13 20:36
 * No, again
 * 〈〉
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class TestController {

    @RequestMapping("/")
    public ResultResponse test() throws FileNotFoundException {
        String path = ResourceUtils.getURL("file").getPath();
        return ResultResponse.success(path);
    }


    @PostMapping("/uploadFile")
    public ResultResponse uploadFile(@RequestParam("file") MultipartFile file) {
        //判断非空
        if (file.isEmpty()) {
            return ResultResponse.fail("上传的文件不能为空");
        }
        String filename = DateUtil.format(new Date(), "yyyyMMddHHmmss") + file.getOriginalFilename();
        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getAvatarDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        log.info("文件路径: {}", fileDir.getAbsolutePath());
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir + File.separator + filename);
            log.info("生成文件路径: {}", newFile.getAbsolutePath());
            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);
            return ResultResponse.success(newFile.getAbsolutePath());
        } catch (IOException e) {
            return ResultResponse.fail("文件上传失败");
        }
    }

}