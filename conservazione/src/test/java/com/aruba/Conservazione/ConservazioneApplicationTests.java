package com.aruba.Conservazione;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.aruba.conservazione.ConservazioneApplication;
import com.aruba.conservazione.services.exception.FileServiceException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ConservazioneApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@TestPropertySource(properties={"upload.path = src/test/resources/filesicuri"})
class ConservazioneApplicationTests {
	@Autowired
	private MockMvc mvc;
	String absolutePath;

	@Test
	void givenUser_whenSignDocuments_ThenUploadFileToTheRepository() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "test" + "_" + new Date().getTime() + ".txt",
				"text/plain", "test data".getBytes());
		mvc.perform(multipart("/api/cons-doc/post-documents-signed").file(file)).andExpect(status().isOk())
				.andExpect(jsonPath("$.response", is("File uploaded successfull!!")));

	}

	@Test
	void givenUser_whenCallGetDocuments_ThenReturnLists() throws Exception {
		mvc.perform(get("/api/cons-doc/get-documents-signed").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200));
	}

	@Test
	void givenOther_whenFailUpload_ThenFileServiceException() throws Exception {
		String exceptionParam = "fileException";
		mvc.perform(
				get("/api/cons-doc/exception/{exception_id}", exceptionParam).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof FileServiceException))
				.andExpect(result -> assertEquals("Impossibile salvare il file",
						result.getResolvedException().getMessage()))
				.andExpect(jsonPath("$.errorDescription", is("Impossibile salvare il file")));

	}

}
