package com.example.salieri

import org.springframework.context.annotation.Bean
import java.io.File

class SoundSample {

    var sample:String;

    constructor(sample:String){
        this.sample=sample;
    }

    fun getSoundSample(): String{
        return sample;
    }

}