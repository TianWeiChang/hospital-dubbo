package com.tian.controller;

import com.alibaba.fastjson.JSONObject;
import com.tian.config.RedisConfig;
import com.tian.entity.Menu;
import com.tian.entity.OuterPatientRegister;
import com.tian.entity.TreeNode;
import com.tian.entity.User;
import com.tian.service.MenuService;
import com.tian.util.RedisPrefixConstant;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月19日 22:14
 */
@Controller
public class UserLoginController {

    @Reference(version = "1.0.0")
    private MenuService menuService;

    @Resource
    private RedisConfig redisConfig;


    @RequestMapping("/")
    public String toLogin() {
        return "view/login";
    }

    /**
     * 加载首页左边导航栏
     */
    @RequestMapping("/menu/tree")
    @ResponseBody
    public List<TreeNode> menuTree(HttpServletRequest request) {
        String result = redisConfig.get(RedisPrefixConstant.SESSION_KEY_PREFIX + request.getSession().getId());
        OuterPatientRegister outerPatientRegister = JSONObject.parseObject(result, OuterPatientRegister.class);
        //获取用户登陆id根据不同的用户有不停菜单
        ///获取用户登陆id根据不同的用户有不停菜单
        List<Menu> list = menuService.queryMenuByUid(outerPatientRegister.getId());
        //创建list集合
        //把list放入nodes
        List<TreeNode> nodes = new ArrayList<>();
        for (Menu menus : list) {
            Integer id = menus.getId();
            Integer pid = menus.getPid();
            String title = menus.getTitle();
            String icon = menus.getIcon();
            String href = menus.getHref();
            Boolean spread = menus.getSpread();
            String target = menus.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        //组装菜单
        List<TreeNode> treeNodes = new ArrayList<>();
        //n1.getPid() == 1 为父级菜单
        for (TreeNode n1 : nodes) {
            if (n1.getPid() == 1) {
                treeNodes.add(n1);
            }
            //将n2放入n1的子级中   id为子级
            for (TreeNode n2 : nodes) {
                if (n2.getPid() == n1.getId()) {
                    n1.getChildren().add(n2);
                }
            }
        }
        return treeNodes;
    }
}
