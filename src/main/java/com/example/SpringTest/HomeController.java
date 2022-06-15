package com.example.SpringTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //anotado como controlador
public class HomeController {

    @RequestMapping("/") //donde quieres el mapeo
    public @ResponseBody String greeting() {
        return "Me encanta programar";
    }

}