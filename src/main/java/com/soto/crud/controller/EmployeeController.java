package com.soto.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soto.crud.bean.Employee;
import com.soto.crud.bean.Msg;
import com.soto.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 处理CRUD请求
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 导入jackson包
     * @param pn
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn) {
//        这不是一个分页查询
//        引入PageHelper插件
//        在查询之前只需要调用,传入页码，以及每页大小
//        startPage后面紧跟的查询就是一个分页查询
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();
//        使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
//        封装了详细的分页信息，包括我们查出的数据,传入连续显示的页数
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo", page);
    }


    /**
     * 查询员工数据(分页查询)
     *
     * @return
     */
//    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
                          Model model) {
//        这不是一个分页查询
//        引入PageHelper插件
//        在查询之前只需要调用,传入页码，以及每页大小
//        startPage后面紧跟的查询就是一个分页查询
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();
//        使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
//        封装了详细的分页信息，包括我们查出的数据,传入连续显示的页数
        PageInfo page = new PageInfo(emps,5);
        model.addAttribute("pageInfo", page);
        return "list";
    }

}
