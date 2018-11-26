package com.myrepublic.numbermanage.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @date 2018年11月1日
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberManageControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mvc;

	private String json;

	@Before
	public void init() {

		json = "{\"account\":\"account_1\" ,\"number\":\"95555555\"}";

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

	}

	@Test
	public void testBindNumber() throws Exception {
		mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/bindNumber")
				.with(user("user").roles("USER"))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("200")));

	}

	@Test
	public void testgetAllMobileUnAuth() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getAllMobiles")
				.with(user("user").roles("USER"))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	@Test
	public void testgetAllMobileAuth() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/getAllMobiles")
				.with(user("admin").roles("ADMIN"))
				 .contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("200")));

	}


}