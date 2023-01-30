package uz.sodiqdev.rest_template.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllCurrencyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/currencies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void checkNotNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/currencies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();
    }

    @Test
    void getCurrencyById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/currencies/{code}", "840")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void checkNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/currencies/{code}", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();
    }


//    @Test
//    void getLocalCurrency() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/currencies/one")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//    }
//
//    @Test
//    void getCbuCurrency() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/currencies/two")
//                        .param("ccy1", "USD")
//                        .param("ccy2", "RUB")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//    }
//
//    @Test
//    void checkLocalCurrencyNull() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/currencies/one")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andDo(print())
//                .andReturn();
//    }
//
//    @Test
//    void checkCbuCurrencyNull() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/currencies/two")
//                        .param("ccy1", "USD")
//                        .param("ccy2", "RUB")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andDo(print())
//                .andReturn();
//    }
//
//
//    @Test
//    void getCurrencyLocalOrWeb() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//                        .get("currencies/one/{ccy}")
//                        .param("url", "USD")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//        assertThat(mvcResult).isNotNull();
//    }
}