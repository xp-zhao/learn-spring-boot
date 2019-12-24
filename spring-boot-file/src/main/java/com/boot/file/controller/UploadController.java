package com.boot.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * UploadController.java 文件上传控制层
 *
 * @author: zhaoxiaoping
 * @date: 2019/09/02
 **/
@RestController
public class UploadController {

	public static final Logger log = LoggerFactory.getLogger(UploadController.class);

	@Value("${uploadFile.location}")
	private String uploadFileLocation;
	@Value("${uploadFile.accessPrefix}")
	private String accessPrefix;

	@PostMapping("/upload")
	public String upload(MultipartFile file) throws IOException {
		if (file == null || file.isEmpty()) {
			return "文件为空";
		}
		String originName = file.getOriginalFilename();
		// 文件后缀
		String suffix = originName.substring(originName.lastIndexOf("."));
		String fileName = UUID.randomUUID().toString().replace("-", "");
		File saveFile = new File(uploadFileLocation, fileName + suffix);
		file.transferTo(saveFile);
		log.info("上传成功");
		return accessPrefix + fileName + suffix;
	}
}