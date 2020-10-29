package com.example.salieri

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
    @GetMapping("/")
    fun home(model: Model): String {
        model["title"] = "Welcome"
        return "home"
    }

    @GetMapping("api/v1/sound")
    fun getSoundProcessing(model: Model): String {
        model["title"] = "Sound Processing"
        return "machine"
    }

    @PostMapping("api/v1/sound")
    fun postSoundProcessing(@RequestBody newSample:String): String {
        var soundSample = SoundSample(newSample);
        return soundSample.getSoundSample();
    }

}