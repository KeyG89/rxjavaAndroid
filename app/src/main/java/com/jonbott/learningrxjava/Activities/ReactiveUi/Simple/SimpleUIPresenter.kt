package com.jonbott.learningrxjava.Activities.ReactiveUi.Simple

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jonbott.learningrxjava.ModelLayer.Entities.Friend
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimpleUIPresenter {

    val friends = BehaviorRelay.createDefault(listOf<Friend>())
    val title = BehaviorSubject.createDefault("Default Title")

    init {
        loadFriends()
    }

    private fun loadFriends() {
        title.onNext("LoadFriends")

        GlobalScope.launch {
            delay(300)
            title.onNext("Friends Loaded")

            var newFriends = listOf(Friend("Debi", "Darlington"),
                    Friend("Arlie", "Abalos"),
                    Friend("Jessica", "Jetton"),
                    Friend("Tonia", "Threlkeld"),
                    Friend("Donte", "Derosa"),
                    Friend("Nohemi", "Notter"),
                    Friend("Rod", "Rye"),
                    Friend("Simonne", "Sala"),
                    Friend("Kathaleen", "Kyles"),
                    Friend("Loan", "Lawrie"),
                    Friend("Elden", "Ellen"),
                    Friend("Felecia", "Fortin"),
                    Friend("Fiona", "Fiorini"),
                    Friend("Joette", "July"),
                    Friend("Beverley", "Bob"),
                    Friend("Artie", "Aquino"),
                    Friend("Yan", "Ybarbo"),
                    Friend("Armando", "Araiza"),
                    Friend("Dolly", "Delapaz"),
                    Friend("Juliane", "Jobin"))

            MainScope().launch() {
                friends.accept(newFriends)
            }
        }

        GlobalScope.launch {
            delay(6000) // 6 seconds
            title.onError(Exception("Faked Error"))
        }

        GlobalScope.launch {
            delay(7000) // 7 seconds
            println("🎶🎶 pushing new value")
            title.onNext("new Value")
        }
    }


}

