package com.hx.dmcp.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hx.dmcp.constant.SystemConstant;


public class UploadUtils {

	public static String[] FILE_TYPE = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv" };

	public static String[] PHOTO_TYPE = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

	public static boolean isFileType(String fileName, String[] typeArray) {
		for (String type : typeArray) {
			if (fileName.toLowerCase().endsWith(type)) {
				return true;
			}
		}
		return false;
	}

	public static List<java.io.File> getFiles(String realpath, List<File> files, String[] fileType) {
		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getFiles(file.getAbsolutePath(), files, fileType);
				} else {
					if (isFileType(file.getName(), fileType)) {
						files.add(file);
					}
				}
			}
		}
		return files;
	}

	public static String getUploadPath(String fileName, long time) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		String uploadPath = "/upload/" + formater.format(new Date()) + "/" + time + getFileExt(fileName);
		File dir = new File(System.getProperty(SystemConstant.CMS_ROOT) + uploadPath);
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		return uploadPath;
	}

	public static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public static void deleteFile(String path) {
		File file = new File(System.getProperty(SystemConstant.CMS_ROOT) + path);
		file.delete();
	}

}
