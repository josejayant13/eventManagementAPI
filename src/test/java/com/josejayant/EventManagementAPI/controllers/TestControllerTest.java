package com.josejayant.EventManagementAPI.controllers;

import com.josejayant.EventManagementAPI.config.JwtFilter;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtFilter jwtFilter;

    @Test
    public void helloWorld()throws Exception
    {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    public void endpoint1() throws Exception {
        mockMvc.perform(get("/endpoint1"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is endpoint1"));
    }
}
