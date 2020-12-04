package com.example.salieri

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.lang.RuntimeException

@RestController
@RequestMapping("/sound", consumes = arrayOf(MediaType.ALL_VALUE))
class SoundController{

    @GetMapping
    fun getSoundProcessing(): String {
        return "machine"
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun postSoundProcessing(@RequestParam("file") file: MultipartFile): String {
        var newFile:File = File(File("sample.mid").absolutePath);
        file.transferTo(newFile);
        var newSample = SoundSample(newFile);
        return "File type = " + newSample.getFormat() + "\nProcessing result = " + newSample.process(newFile);
    }
}