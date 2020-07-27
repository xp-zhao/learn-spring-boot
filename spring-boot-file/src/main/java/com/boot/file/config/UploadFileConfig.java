package com.boot.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * UploadFileConfig.java
 * 文件上传配置
 *
 * @author: zhaoxiaoping
 * @date: 2019/12/24
 **/
@Configuration
public class UploadFileConfig extends WebMvcConfigurationSupport {

	@Value("${uploadFile.location}")
	private String uploadFileLocation;
	@Value("${uploadFile.accessPath}")
	private String accessPath;

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler(accessPath)
//				.addResourceLocations("file:" + uploadFileLocation + "/");
//		super.addResourceHandlers(registry);
//	}
}