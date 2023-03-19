package com.api.book.bootrestbook.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUplodeHelper {
	//hardcoded path for source image
//	public final String UPLODE_DIR = "C:\\Users\\podda\\eclipse-workspace\\bootrestbook\\src\\main\\resources\\static\\image";
	//dynamic path for source image 
	public final String UPLODE_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	

	public FileUplodeHelper() throws IOException {

	}

	public boolean uplodeFile(MultipartFile multipartFile) {
		boolean f = false;
		try {

			// pro method
			Files.copy(multipartFile.getInputStream(),
					Paths.get(UPLODE_DIR + File.separator + multipartFile.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			// naive method
//			InputStream is = multipartFile.getInputStream();
//			byte data[] = new byte[is.available()];
//			is.read();
//			// write
//			FileOutputStream fos = new FileOutputStream(
//					UPLODE_DIR + File.separator + multipartFile.getOriginalFilename());
//			fos.write(data);
//			fos.close();
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}
}
