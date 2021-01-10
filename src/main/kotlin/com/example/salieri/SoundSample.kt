package com.example.salieri

import soundProcessing.MidiProcessing
import java.io.File
import soundProcessing.SoundProcessing;

class SoundSample {
    var file:File;

    constructor(file: File) {
        this.file = file;
    }

    fun getThisFile():File{
        return file;
    }

    fun setThisFile(file: File){
        this.file=file;
    }

    fun process(outFile:File):Int?{
        val processing = SoundProcessing(file);
        val text = processing.toText()
        if(text.isNullOrEmpty()){
            println("Unexpected error: You suck")
            return 0;
        }
        println(text);
        return processing.toMidi(text, outFile.name);
    }

    fun getFormat():Int {
        return MidiProcessing().getType(file)
    }

    private fun calculatedSize():Double{
        val fileSize = file.length()
        val sizeInMb = fileSize / (1024.0 * 1024)
        return sizeInMb;
    }

}