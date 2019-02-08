package com.jonbott.learningrxjava.SimpleExamples

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jonbott.learningrxjava.Common.disposedBy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

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

        //1 onError
//        val someException = IllegalArgumentException("some fake error")
//        behaviorSubject.onError(someException)
//        behaviorSubject.onNext(109) // will never show because of an error pushed to the behaviorSubject

        //2 onComplete
        behaviorSubject.onComplete()
        behaviorSubject.onNext(109349) // this will never show due to completion

    }

    fun basicObservable() {
        // The observable
        val observable = Observable.create<String> { observer ->
            // The lambda is called for every subscriber - by default
            println("🙌 ~~~~~ Observable logic being triggered ~~")

            // Do work on a background thread
            GlobalScope.launch {
                delay(1000) // artificial delay 1 second

                observer.onNext("some value 23")
                observer.onComplete()
            }

        }

        observable.subscribe { someString ->
            println("🙌 ~~~~~ new value: $someString ~~")
        }.disposedBy(bag)

        val observer = observable.subscribe { someString ->
            println("🙌 ~~~~~ Another subscriber: $someString ~~")
        }

        observer.disposedBy(bag)
    }

    fun creatingObservables() {
        val observable = Observable.just(23)
        val observableTime = Observable.interval(300, TimeUnit.MILLISECONDS).timeInterval(AndroidSchedulers.mainThread())
        val userIds = arrayOf(1, 2, 3, 4, 5, 6)
        val observableArray = Observable.fromArray(*userIds) // When we call a vararg-function, we can pass arguments one-by-one,
        // e.g. asList(1, 2, 3), or, if we already have an array and want to pass
        //  its contents to the function, we use the spread operator (prefix the array with *):
        val observableArrays2 = userIds.toObservable()


    }
}
