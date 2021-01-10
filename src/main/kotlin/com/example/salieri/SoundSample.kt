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