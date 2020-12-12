package com.example.salieri

import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import soundProcessing.SoundProcessing
import java.io.File
import java.lang.RuntimeException


@RestController
@RequestMapping("/sound", consumes = arrayOf(MediaType.ALL_VALUE))
class SoundController{

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun postSoundProcessing(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        val url = "http://localhost:5000/api/"
        val newFile = File(File("sample.mid").absolutePath)

        file.transferTo(newFile);
        val processingEngine = SoundProcessing(newFile);
        val midiText = processingEngine.toText() ?: return ResponseEntity("Bad text", HttpStatus.BAD_REQUEST)

        val jsonData = "{\"generateLen\":\"2000\",\"source\":\"$midiText\"}"
        val responseData = RestTemplate().postForEntity(url, jsonData, String::class.java)


        val responseBuffer = responseData.body?: return ResponseEntity("Unexpected error while generating", HttpStatus.INTERNAL_SERVER_ERROR)
        processingEngine.toMidi(responseBuffer, "test")
        return ResponseEntity("Created", HttpStatus.OK)
    }
}