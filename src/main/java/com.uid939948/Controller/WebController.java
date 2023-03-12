package com.uid939948.Controller;

import com.uid939948.Service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 静态页面接口
 */
@Controller
@Lazy
public class WebController {
    @RequestMapping("/index")
    public String show(){
        return "index";
    }
}