package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuDTO> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    public List<CategoryDTO> findAllCategory() {return menuMapper.findAllCategory();}

    @Transactional //익셉션이 안나면 잘 수행했네 하고 커밋함 익셉션 나면 커밋안함!!!
    public void registNewMenu(MenuDTO newMenu){
        menuMapper.registMenu(newMenu); //이거 해야 커밋한다 ㅠ
    }
}
