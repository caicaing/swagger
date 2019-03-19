package com.example.demo.controller;

import com.example.demo.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liu
 * @title: HelloController
 * @projectName demo
 * @description: TODO
 * @date 2019/3/1821:48
 */
@RestController
@Api(tags = "用户模块")
@RequestMapping(value = "/user")
public class UserController {
    /**
     * 此处为了方便直接用一个Map来模拟数据库进行操作
     */
    static Map<Long, User> userMap;

    static {
        userMap = new HashMap<>();
        User user = new User();
        user.setId((long) 10000);
        user.setUsername("admin");
        userMap.put((long) 10000,user);
    }

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<User> getUserList() {

        return new ArrayList<User>(userMap.values());
    }


    @ApiOperation(value = "创建用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "id", dataType = "Long", required = true, value = "用户id", defaultValue = "10001"),
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户名字", defaultValue = "菜菜")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(User user) {

        userMap.put(user.getId(),user);
        return "添加成功";
    }



    @ApiOperation(value = "获取用户")
    @ApiImplicitParam(paramType = "query", required = true, name = "id", dataType = "Long", value = "用户id")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public User getUser(@RequestParam("id") Long id) {
        System.out.println(id);
        return userMap.get(id);
    }



    @ApiOperation(value = "更新用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "id", dataType = "Long", required = true, value = "用户id", defaultValue = "10001"),
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户名字", defaultValue = "菜菜")
    })
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String putUser(User user) {
        userMap.put(user.getId(),user);
        return "更新成功";
    }


    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(paramType = "query", required = true, name = "id", dataType = "Long", value = "用户id")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam("id") Long id) {
        userMap.remove(id);
        return "删除成功";
    }
}
