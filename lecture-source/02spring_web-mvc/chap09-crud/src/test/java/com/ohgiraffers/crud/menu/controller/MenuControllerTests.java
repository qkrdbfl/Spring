package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.configuration.Chap09CrudApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@ContextConfiguration(classes = { Chap09CrudApplication.class })
public class MenuControllerTests {

    @Autowired
    private MenuController menuController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
    }

    @Test
    @Disabled
    public void 전체_메뉴_조회용_컨트롤러_테스트() throws Exception {
        //given

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("menu/list"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 전체_카테고리_조회용_컨트롤러_테스트() throws Exception {
        //given

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/category"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 신규_메뉴_등록용_컨트롤러_테스트() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "컨트롤러 테스트");
        params.add("price", "20000");
        params.add("categoryCode", "5");
        params.add("orderableStatus", "Y");

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/menu/regist").params(params))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) //300번때 리다렉션이 맞나?
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/list")) //리다이렉션이 메뉴/리스트가 맞나?
                .andDo(MockMvcResultHandlers.print());
    }

}
