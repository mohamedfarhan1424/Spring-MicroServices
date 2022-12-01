package com.farhan.blog.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerNumberMngtControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void TestAllBlogs() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/blogs/allBlogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("New DSA Course")));
    }

    @Test
    void TestAddBlog() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/blogs/addblog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"title\":\"New DSA Course\",\n" +
                                "    \"description\":\"This is the full course of DSA\",\n" +
                                "    \"userId\":1\n" +
                                "}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New DSA Course")));
    }
}
