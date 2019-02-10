package com.jonbott.learningrxjava.Activities.NetworkExample

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jonbott.learningrxjava.ModelLayer.Entities.Message
import com.jonbott.learningrxjava.ModelLayer.ModelLayer
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class NetworkExamplePresenter {

    //Old way
    private val modelLayer = ModelLayer.shared //normally injected
    private var bag = CompositeDisposable()

    val messages: BehaviorRelay<List<Message>>
        get() = modelLayer.messages

    //Old way
    init {
        modelLayer.getMessages()
    }

    //New Way

    fun getMessagesRx(): Single<List<Message>> {
        return modelLayer.getMessagesRx()
    }

}