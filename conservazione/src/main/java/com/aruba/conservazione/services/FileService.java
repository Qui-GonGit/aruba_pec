package com.aruba.conservazione.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aruba.conservazione.services.exception.FileServiceException;

@Service
public class FileService {
	private static Logger logger = LoggerFactory.getLogger(FileService.class);
	@Value("${upload.path}")
	private String uploadPath;

	@PostConstruct
	private void init() throws FileServiceException {
		try {
			Files.createDirectories(Paths.get(uploadPath));
			logger.debug("create directory");
		} catch (IOException e) {
			throw new FileServiceException("Could not create upload folder!");
		}
	}

	public void save(MultipartFile file) throws FileServiceException {
		try {
			Path root = Paths.get(uploadPath);
			if (!Files.exists(root)) {
				init();
			}
			Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
			logger.debug("copy file");
		} catch (Exception e) {
			throw new FileServiceException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public List<String> getDocumentsByUser() {
		File directoryPath = new File(uploadPath);
		return Arrays.asList( directoryPath.list());

	}

}