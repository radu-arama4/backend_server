package com.example.salieri

import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import soundProcessing.SoundProcessing
import java.io.File
import java.util.*


@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RestController
@RequestMapping("/sound", consumes = arrayOf(MediaType.ALL_VALUE))
class SoundController{

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
                 produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun postSoundProcessing(@RequestParam("file") file: MultipartFile): ResponseEntity<Any> {
        val url = "http://localhost:5000/api/"
        val newFile = File(File("sample.mid").absolutePath)

        file.transferTo(newFile);
        val processingEngine = SoundProcessing(newFile);
        val midiText = processingEngine.toText()
        val jsonData = "{\"generateLen\":\"2000\",\"source\":\"$midiText\"}"

        println("Sending data to ML: this is source data\n$midiText\n")
        val responseData = RestTemplate().postForEntity(url, jsonData, String::class.java)

        val responseBuffer = responseData.body?: return ResponseEntity("Unexpected error while generating",
                                                                             HttpStatus.INTERNAL_SERVER_ERROR)
        println("Received from ML: this is received data\n$responseBuffer")

        processingEngine.toMidi(responseBuffer, "output")
        val outputFile = Base64.getEncoder().encodeToString(File("output.mid").readBytes())
        return ResponseEntity(outputFile, HttpStatus.OK)
    }
}