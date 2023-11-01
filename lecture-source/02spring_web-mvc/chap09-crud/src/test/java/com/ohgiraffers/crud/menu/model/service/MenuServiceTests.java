package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.configuration.Chap09CrudApplication;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = { Chap09CrudApplication.class })
public class MenuServiceTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void 전체_메뉴_조회용_서비스_메소드_테스트() {
        //given

        // when
        List<MenuDTO> menuList = menuService.findAllMenu();
        //then
        assertNotNull(menuList);
        System.out.println(menuList);
    }

    @Test
    public void 신규_메뉴_등록용_서비스_성공_테스트(){
        //given
        MenuDTO menu = new MenuDTO();
        menu.setName("성공");
        menu.setPrice(30000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");

        //when & then
        assertDoesNotThrow(() -> menuService.registNewMenu(menu));
    }


    @Test
    public void 신규_메뉴_등록용_서비스_실패_테스트(){
        //given
        MenuDTO menu = new MenuDTO();
        menu.setName("실패");
        menu.setPrice(30000);
        menu.setCategoryCode(100);
        menu.setOrderableStatus("Y");

        //when & then
        assertThrows(Exception.class, () -> menuService.registNewMenu(menu)); //이 동작을 하면 이런 익셉션이 발생하나?라는 의미
    }
}
