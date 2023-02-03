package com.aruba.firma;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.aruba.firma.service.UserService;
import com.aruba.firma.service.pojo.ResponseConservazioneDocumenti;
import com.aruba.firma.service.pojo.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = FirmaApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class FirmaApplicationTests {
	String absolutePath;
	@MockBean
	RestTemplate restTemplate;
	@InjectMocks
	UserService userService;
	@Autowired
	private MockMvc mvc;
	@BeforeEach
	void init() {
		String path = "src/test/resources/firmatest";
		File file = new File(path);
		absolutePath = file.getAbsolutePath();
	}
	@Test
	void givenUser_whenSignAttachment_ThenReturnOK() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());
		MockMultipartFile idUtente = new MockMultipartFile("id_utente", "", "text/plain",
				"b46535df-50b5-4353-b9ca-31ffd812139e".getBytes());
		MockMultipartFile password = new MockMultipartFile("password", "", "text/plain", "1234".getBytes());
		User user = new User();
		user.setDocRelPath(absolutePath + "\\documenti\\mario_rossi\\dummy.pdf");
		user.setFirstName("mario");
		user.setLastName("rossi");
		user.setPasswordKey("1234");
		user.setPrivateKey(absolutePath + "\\privatekey");
		when(restTemplate.postForEntity(Mockito.eq("http://localhost:9080/api/pec/get-user-by-id-aruba"), Mockito.any(),
				Mockito.eq(User.class))).thenReturn(new ResponseEntity<User>(user, HttpStatus.OK));
		ResponseConservazioneDocumenti responseConv = new ResponseConservazioneDocumenti();
		responseConv.setResponse("File uploaded successfull!!");
		when(restTemplate.postForEntity(Mockito.eq("http://cons-documenti-service/api/cons-doc/post-documents-signed"),
				Mockito.any(), Mockito.eq(ResponseConservazioneDocumenti.class)))
						.thenReturn(new ResponseEntity<>(responseConv, HttpStatus.OK));
		mvc.perform(multipart("/api/firma/execute").file(file).file(idUtente).file(password)).andExpect(status().isOk())
				.andExpect(content().string("File uploaded successfull!!"));
	}

}
