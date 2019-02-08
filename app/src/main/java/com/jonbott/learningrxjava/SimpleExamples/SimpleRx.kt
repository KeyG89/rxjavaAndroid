package com.jonbott.learningrxjava.SimpleExamples

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

object SimpleRx {
    var bag = CompositeDisposable()

    fun simpleValues() {
        println("~~~~~~simpleValues~~~~~~")
        val someInfo = BehaviorRelay.createDefault("1")
        println("❤ someInfo.value ${someInfo.value}") // WIND ;

        val plainString = someInfo.value
        println("❤ plainString $plainString.value")

        someInfo.accept("2")
        println("❤ someInfo.value ${someInfo.value}")

        someInfo.subscribe { newValue ->
            println("🤣 value has changed $newValue")

        }

        someInfo.accept("3")

        // NOTE: Relays will never receive onError, and onComplete events
    }

    fun subjects() {
        val behaviorSubject = BehaviorSubject.createDefault(24)

        val disposable = behaviorSubject.subscribe({ newValue ->
            // onNext
            println("🤷‍♀️ behaviorSubject subscription: $newValue")
        }, { error ->
            // onError
            println("🤷‍♀️ error ${error.localizedMessage}")
        }, {
            // onCompleted
            println("🤷‍♀️ completed")
        }, { disposable ->
            // onSubscribed
            println("🤷‍♀️ subscribed")
        })

        behaviorSubject.onNext(34)
        behaviorSubject.onNext(48)
        behaviorSubject.onNext(48) // duplicates show as new events by default

    }

}
