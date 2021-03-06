package com.example.SpringTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController //anotado como controlador
public class HomeController {

    @RequestMapping("/") //donde quieres el mapeo
    public @ResponseBody String greeting() {
        return "Me encanta programar";
    }
    @GetMapping("/add")
    public Float add(
        @RequestParam(value = "a", defaultValue = "0") Float a,
        @RequestParam(value = "b", defaultValue = "0") Float b
    ){
        if(a == 0){

            return b;
        }
        if(b == 0){

            return a;
        }
        return a + b;
    }
    @GetMapping("/substract")
    public Float substract(
            @RequestParam(value = "a", defaultValue = "0") Float a,
            @RequestParam(value = "b", defaultValue = "0") Float b
    ){
        return a - b;
    }
    @GetMapping("/multiply")
    public Float multiply(
            @RequestParam(value = "a", defaultValue = "0") Float a,
            @RequestParam(value = "b", defaultValue = "0") Float b
    ){
        return a * b;
    }
    @GetMapping("/divide")
    public Float divide(
            @RequestParam(value = "a", defaultValue = "0") Float a,
            @RequestParam(value = "b", defaultValue = "0") Float b
    ){
        return a / b;
    }
}