package ru.regme.fmsPresenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/fms-presenter")
    public String welcome() {
        return "index";
    }
}
