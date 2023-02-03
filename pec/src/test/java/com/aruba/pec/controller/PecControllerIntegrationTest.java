package com.aruba.pec.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.aruba.pec.PecApplication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PecApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integrationtest.properties")
class PecControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Test
	void givenUserEmail_whenGetUserByEmail_ThenReturnUser() throws Exception {
		mvc.perform(post("/api/pec/get-user-by-email").content("{\"email\" : \"mario.rossi@gmail.com\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.firstName", is("Mario")))
				.andExpect(jsonPath("$.lastName", is("Rossi")));
	}

	@Test
	void givenUserIdWrong_whenGetPecByUser_ThenStatus404() throws Exception {
		mvc.perform(post("/api/pec/get-user-by-id-aruba").content("{\"idUtente\" : 102}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode", is("100")))
				.andExpect(jsonPath("$.errorDescription", is("User with id 102 not found")));
	}

	@Test
	void givenUserId_whenGetMessagesByFilter_ThenReturnMessages() throws Exception {
		mvc.perform(
				post("/api/pec/get-messages-by-filter")
						.content("{\r\n" + "  \"idUtente\": \"b46535df-50b5-4353-b9ca-31ffd812139e\",\r\n" + "  \"sender\": \"tizio\",\r\n"
								+ "  \"subject\": \"05\",\r\n" + "  \"text\": \"speriamo\",\r\n"
								+ "  \"pecName\": \"test01@pec.it\",\r\n" 
								+ "  \"attachment\": true,\r\n" + "  \"priority\": \"alta\"\r\n" + "}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].sender", is("tizio.caio@gmail.it")))
				.andExpect(jsonPath("$[0].subject", is("oggetto05")))
				.andExpect(jsonPath("$[0].attachment", is("/mario_rossi/test_allegato.pdf")));
	}

	@Test
	void givenUserId_whenGetMessagesByFilter_ThenReturnMessagesWithoutAttach() throws Exception {
		mvc.perform(
				post("/api/pec/get-messages-by-filter")
						.content("{\r\n" + "  \"idUtente\": \"b46535df-50b5-4353-b9ca-31ffd812139e\",\r\n" + "  \"sender\": \"tizio\",\r\n"
								+ "  \"subject\": \"06\",\r\n" + "  \"text\": \"\",\r\n"
								+ "  \"pecName\": \"test01@pec.it\",\r\n" 
								+ "  \"attachment\": false,\r\n" + "  \"priority\": \"alta\"\r\n" + "}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].sender", is("tizio.caio@gmail.it")))
				.andExpect(jsonPath("$[0].subject", is("oggetto06")));
	}
}
