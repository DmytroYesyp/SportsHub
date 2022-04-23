package com.sportshub.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class IndexController {

    @GetMapping({"/", "index"})
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/";
    }
}