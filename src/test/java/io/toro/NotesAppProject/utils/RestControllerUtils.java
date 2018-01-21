package io.toro.NotesAppProject.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestControllerUtils {

    public static String createFullUri(int port, String endpoint){
        return "http://localhost:" + port + endpoint;
    }

    public static String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString( object );
    }

    public static void performGet(MockMvc mvc, String url, String expectedContent) throws Exception {
        mvc.perform(get(url)
                .accept( MediaType.APPLICATION_JSON )
                .contentType( MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect( content().contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ))
                .andExpect(content().string( expectedContent ));
    }
    public static void performPost( MockMvc mvc ,String url,String jsonInput,String expectedContent ) throws Exception {
        mvc.perform( post(url)
                .contentType( MediaType.APPLICATION_JSON )
                .accept( MediaType.APPLICATION_JSON )
                .content( jsonInput ))
                .andExpect(status().isCreated())
                .andExpect(content().contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ))
                .andExpect(content().string( expectedContent ));
    }

    public static void performDelete(MockMvc mvc, String url) throws Exception{
        mvc.perform(delete(url)).andExpect(status().isOk());
    }

    public static void performPut( MockMvc mvc ,String url,String jsonInput) throws Exception {
        mvc.perform( put(url)
                .contentType( MediaType.APPLICATION_JSON )
                .accept( MediaType.APPLICATION_JSON )
                .content( jsonInput ))
                .andExpect(status().isOk())
                .andExpect(content().contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ));

    }
}
