package ru.easycode.zerotoheroandroidtdd.main

class HandleDepth {
    private var depthOccurred = false

    fun depthState() = depthOccurred

    fun depthDiffused() {
        depthOccurred = true
    }
}