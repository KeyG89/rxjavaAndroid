package com.jonbott.learningrxjava.SimpleExamples

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.CompositeDisposable

object SimpleRx {
    var bag = CompositeDisposable()

    fun simpleValues() {
        println("~~~~~~simpleValues~~~~~~")
        val someInfo = BehaviorRelay.createDefault("1")
        println("❤ someInfo.value ${someInfo.value}") // WIND ;

        val plainString = someInfo.value
        println("❤ plainString $plainString.value")

        someInfo.accept("2")
        println("❤ someInfo.value ${ someInfo.value}")
    }
}