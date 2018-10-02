package com.jade.swp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.jade.swp.domain.Board;

import org.springframework.web.bind.annotation.RequestMethod;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class TestControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TestControllerTest.class);
	
	@Inject
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.debug("setup TestControllerTest mockMvc...");
	}

	@Test
	public void testList() throws Exception {
		this.mockMvc.perform(get("/test/list")).andDo(print()).andExpect(status().isOk());
	}
	
	@Controller
	@RequestMapping("/test/*")
	public class TestController {
		
//		@RequestMapping(value = "/list", method = RequestMethod.)
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public void registerGET(Board board, Model model) throws Exception {
			logger.info("regist GET .....");
		}

	}

}
