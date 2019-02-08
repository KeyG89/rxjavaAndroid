package com.jonbott.learningrxjava.Activities.DatabaseExample

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jonbott.learningrxjava.ModelLayer.ModelLayer
import com.jonbott.learningrxjava.ModelLayer.PersistenceLayer.PhotoDescription
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DatabaseExamplePresenter {
    val modelLayer = ModelLayer.shared //normally injected

    val photoDescriptions: BehaviorRelay<List<PhotoDescription>>
        get() = modelLayer.photoDescriptions // bubbling up for the lower layer

    init {
        GlobalScope.launch {
            delay(3000) // 3 seconds
            modelLayer.loadAllPhotoDescriptions()

        }
    }

}