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
            throw RuntimeException("The file is empty! Try again with another one!")
        }

        if(calculatedSize()>2){
            throw RuntimeException("The file is too big! Limit - 2MB.")
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

fun textToIntString(source: String):String{
    var result = ""
    result += "[\"${source[0].toInt()}\"";
    for(it in source){
        result += ", \""
        result += it.toInt().toString()
        result += '"'
    }
    result+= ']'
    return result
}