package com.battcn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class UPLoadOss {
	/**
	 * 上传文件至阿里云，返回图片路径
	 * @param file
	 * @param request
	 * @return
	 */
	public static String getResult(MultipartFile file,HttpServletRequest request){
					String filePath = "";
					// 判断文件是否为空
					if (!file.isEmpty()) {
						try {
							// 文件保存路径
							filePath = request.getSession().getServletContext()
									.getRealPath("/")
									+ file.getOriginalFilename();
							// 转存文件
							file.transferTo(new File(filePath));
						} catch (Exception e) {
							e.printStackTrace();
							return "h";
						}
					}else{
						return "图片不能为空！！";
					}
					String s = MD5Util.getMD5String(filePath);
					String ossKey = "banner/" + s;
					String result = "";
					OSSClientUtil clientUtil = new OSSClientUtil();
					Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
							* 365 * 10);
					// 上传
					InputStream instream = null;
					try {
						instream = new FileInputStream(filePath);
						clientUtil.uploadFile2OSS(instream, ossKey);
						// 获取url
						URL url = clientUtil.createUrl(ossKey, expiration);
						clientUtil.destory();
						result = url.toString();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						return "出问题了！！！稍后再试！！";
					}
					return result;
	}

}
