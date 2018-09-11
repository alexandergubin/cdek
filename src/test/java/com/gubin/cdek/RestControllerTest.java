package com.gubin.cdek;

import com.gubin.cdek.controller.RestController;
import com.gubin.cdek.model.User;
import com.gubin.cdek.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestApp.class, UserRepository.class})
public class RestControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getUserListTest() throws Exception {
        this.mockMvc
                .perform(post("/addUser")
                        .param("name", "test")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED));
        this.mockMvc.perform(get("/userList"))
                .andExpect(model().attribute("users", any(List.class)))
                .andExpect(status().isOk());
    }

    @Test
    public void addUserTest() throws Exception {
        this.mockMvc
                .perform(post("/addUser")
                        .param("name", "test")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());
               // .andExpect(model().attribute("userForm", hasProperty("name", equalTo("test"))));
    }
}
