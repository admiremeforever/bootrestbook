package com.api.book.bootrestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.bootrestbook.helper.FileUplodeHelper;

@RestController
public class FileUplodeController {

	@Autowired
	private FileUplodeHelper fileUplodeHelper;

	@PostMapping("/uplode-file")
	public ResponseEntity<String> uplodeFile(@RequestParam("file") MultipartFile file) {

		try {

			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("File is empty please provide a file");
			}
			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Incorrect file type, please provide jpeg content only");
			}

			boolean uplodeFile = fileUplodeHelper.uplodeFile(file);
			if (uplodeFile) {
				// return ResponseEntity.ok("File is sucssfully uploded");
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/")
						.path(file.getOriginalFilename()).toUriString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong ! Try again");
	}
}
