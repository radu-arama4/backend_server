package com.example.blog

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Controller {
    @GetMapping("/")
    fun home(model: Model): String {
        model["title"] = "Welcome"
        return "home"
    }

    @GetMapping("/machine")
    fun Machine(model: Model): String {
        model["title"] = "ML"
        return "machine"
    }

}