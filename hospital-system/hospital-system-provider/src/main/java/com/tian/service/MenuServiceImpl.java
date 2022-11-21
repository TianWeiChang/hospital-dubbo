package com.tian.service;

import com.tian.entity.Menu;
import com.tian.entity.User;
import com.tian.mapper.MenuMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(version = "1.0.0")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public User loginName(String loginname) {
        return menuMapper.loginByName(loginname);
    }

    @Override
    public List<Menu> queryMenuByUid(Integer userid) {
        return menuMapper.queryMenuByUid(userid);
    }

    @Override
    public List<Menu> loadAvailableMenu() {
        return menuMapper.loadAvailableMenu();
    }

    @Override
    public List<Menu> selAllMenuByUser(Integer userId) {
        return menuMapper.selAllMenuByUser(userId);
    }

    @Override
    public List<Menu> queryMenuAllList(Menu menu) {
        return menuMapper.queryMenuAllList(menu);
    }

    @Override
    public int addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public int deleteMenu(Integer id) {
        return menuMapper.deleteMenu(id);
    }

    @Override
    public int checkMenuHasChildren(Integer pid) {
        return menuMapper.checkMenuHasChildren(pid);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }
}
