package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.configuration.Chap09CrudApplication;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = { Chap09CrudApplication.class })
public class MenuMapperTests {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    @Disabled
    public void 매퍼_인터페이스_의존성_주입_테스트() {
        assertNotNull(menuMapper);
    }

    @Test
    public void 전체_메뉴_조회용_매퍼_테스트() {
        // given

        // when
        List<MenuDTO> menuList = menuMapper.findAllMenu();
        //then
        assertNotNull(menuList);
        System.out.println(menuList);
    }

    @Test
    public void 전체_카테고리_조회용_매퍼_테스트() {
        // given

        // when
        List<CategoryDTO> categoryList = menuMapper.findAllCategory();
        //then
        assertNotNull(categoryList);
        System.out.println(categoryList);
    }

    @Test
    @DisplayName("신규메뉴가 잘 추가 되었는지 매퍼 인터페이스릐 메소드 확인")
    public void testRegistMenu(){
        //given
        MenuDTO menu = new MenuDTO();
        menu.setName("테스트칼국수");
        menu.setPrice(13000);
        menu.setCategoryCode(6);
        menu.setOrderableStatus("Y");

        //when & then
        assertDoesNotThrow(() -> menuMapper.registMenu(menu));
    }
}
