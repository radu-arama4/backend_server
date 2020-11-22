package com.example.salieri

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class Controller {

    @GetMapping
    fun home(model: Model): String {
        model["title"] = "Welcome"
        return "home"
    }

}