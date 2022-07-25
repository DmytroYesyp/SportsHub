package com.sportshub;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sportshub.entity.user.User;
import com.sportshub.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class SportsHubApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	private final UserRepository userRepository;

	@Autowired
	SportsHubApplicationTests(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Test
	void creatingUser() throws Exception {
		this.mockMvc.perform(post("http://localhost:8080/api/users/registerUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"firstName\" : \"Dmytro\",\n" +
								"    \"lastName\" : \"Yesyp\",\n" +
								"    \"email\" : \"testingMail@mail.com\",\n" +
								"    \"password\" : \"12345678\"\n" +
								"}"))
				.andDo(print())
				.andExpect(status().isCreated());
		assertThat(userRepository.findUserByEmail("testingMail@mail.com")).isNotEmpty();

	}



	@Test
	void getUserById() throws Exception {

		assertThat(userRepository.findUserByEmail("testingMail@mail.com")).isNotEmpty();

		User u = userRepository.findUserByEmail("testingMail@mail.com").orElseThrow();

		this.mockMvc.perform(get("http://localhost:8080/api/users/users?userId="+
						userRepository.findUserByEmail("testingMail@mail.com").orElseThrow().getId()))
				.andExpect(status().isOk())
				.andDo(print()).andExpect(result -> assertEquals("{\"id\":"+u.getId()+",\"firstName\":\"Dmytro\",\"lastName\":\"Yesyp\",\"password\":\""+u.getPassword()+"\",\"logo_url\":\"User.png\",\"email\":\""+u.getEmail()+"\"}",
						result.getResponse().getContentAsString()));

	}


	@Test
	void getUserByEmail() throws Exception {


		final String[] token = new String[1];

		assertThat(userRepository.findUserByEmail("testingMail@mail.com")).isNotEmpty();

		this.mockMvc.perform(post("http://localhost:8080/login?email=testingMail@mail.com&password=12345678")).andDo(result -> token[0] = result.getResponse().getContentAsString());

		JsonObject json = new Gson().fromJson(token[0], JsonObject.class);


		User u = userRepository.findUserByEmail("testingMail@mail.com").orElseThrow();

		this.mockMvc.perform(get("http://localhost:8080/api/users/getUserByEmail?email=testingMail@mail.com")
						.header("authorization", "Bearer " + json.get("token").getAsString()))
				.andExpect(status().isOk())
				.andDo(print()).andExpect(result -> assertEquals("{\"id\":"+u.getId()+",\"firstName\":\"Dmytro\",\"lastName\":\"Yesyp\",\"password\":\""+u.getPassword()+"\",\"logo_url\":\"User.png\",\"email\":\""+u.getEmail()+"\"}",
						result.getResponse().getContentAsString()));
	}

	@Test
	void putInUser() throws Exception {

		final String[] token = new String[1];
		assertThat(userRepository.findUserByEmail("testingMail@mail.com")).isNotEmpty();
		this.mockMvc.perform(post("http://localhost:8080/login?email=testingMail@mail.com&password=12345678")).andDo(result -> token[0] = result.getResponse().getContentAsString());
		JsonObject json = new Gson().fromJson(token[0], JsonObject.class);
		User u = userRepository.findUserByEmail("testingMail@mail.com").orElseThrow();
		this.mockMvc.perform(put("http://localhost:8080/api/users/users?userId=" + u.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
						"    \"firstName\" : \"DmytroTest\",\n" +
						"    \"lastName\" : \"YesypTest\",\n" +
						"    \"email\" : \"testingMail@mail.com\",\n" +
						"    \"password\" : \"12345678\"\n" +
						"}")
				.header("authorization", "Bearer " + json.get("token").getAsString()))
				.andExpect(status().isCreated());
		u = userRepository.findUserByEmail("testingMail@mail.com").orElseThrow();
		assertEquals("DmytroTestYesypTest",u.getFirstName() + u.getLastName());

	}

	@Test
	void deleteUser() throws Exception {

		final String[] token = new String[1];
		assertThat(userRepository.findUserByEmail("testingMail@mail.com")).isNotEmpty();
		this.mockMvc.perform(post("http://localhost:8080/login?email=testingMail@mail.com&password=12345678")).andDo(result -> token[0] = result.getResponse().getContentAsString());
		JsonObject json = new Gson().fromJson(token[0], JsonObject.class);
		User u = userRepository.findUserByEmail("testingMail@mail.com").orElseThrow();

		this.mockMvc.perform(delete("http://localhost:8080/api/users/users?userId=" + u.getId())
						.header("authorization", "Bearer " + json.get("token").getAsString()))
				.andExpect(status().isOk());

		assertThat(userRepository.findUserByEmail("testingMail@mail.com")).isEmpty();

	}
}
