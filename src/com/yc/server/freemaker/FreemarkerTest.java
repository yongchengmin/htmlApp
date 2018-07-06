package com.yc.server.freemaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yc.utils.FreemarkerUtil;

public class FreemarkerTest {
    @Test
    public void test(){
    	FreemarkerUtil util = new FreemarkerUtil();
        Map<String, Object> map = new HashMap<String, Object>();
 
        Group group = new Group();
        group.setName("IT");
         
        User user = new User();
        user.setId(001);
        user.setName("张三");
        user.setAge(12);
        user.setGroup(group);
         
        List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user);
        users.add(user);
         
        map.put("user", user);
        //普通EL赋值
        Map<String,Object> root=new HashMap<String,Object>();
        root.put("username", "永");//在ftl中要赋值的变量
//        util.print("01.ftl", root );
//        util.print("02.ftl", root );
        //判断
//        util.fprint("03.ftl", map, "03.html");
        //遍历
        map.put("users", users);
        util.fprint("04.ftl", map, "04.html");
//        util.print("05.ftl", map);
        //子元素判断
//        util.print("06.ftl", map);
    }
    public static void main(String[] args) {
    	FreemarkerTest t = new FreemarkerTest();
    	t.test();
	}
}
