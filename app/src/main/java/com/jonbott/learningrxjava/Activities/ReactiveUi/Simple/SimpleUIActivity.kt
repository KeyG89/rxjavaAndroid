package com.jonbott.learningrxjava.Activities.ReactiveUi.Simple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.jonbott.learningrxjava.Common.disposedBy
import com.jonbott.learningrxjava.ModelLayer.Entities.Friend
import com.jonbott.learningrxjava.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_simple_ui.*

class SimpleUIActivity : AppCompatActivity() {


    private var bag = CompositeDisposable()
    private val presenter = SimpleUIPresenter()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_ui)

        rxExamples()
    }

    private fun rxExamples() {

        simpleRx()
        rxSimpleListBind()
    }

    private fun simpleRx() {
//        presenter.title.observeOn(AndroidSchedulers.mainThread())
//                .subscribe{ simpleUITitleTextView.text = it}
//                .disposedBy(bag)

        presenter.title.observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn { "Default Value" }
                .share() // code triggered once for every subscriber without duplicating an action each and every time
                .subscribe{ simpleUITitleTextView.text = it}
                .disposedBy(bag)
    }


    private fun rxSimpleListBind() {
        val listItems = presenter.friends.value.map { it.toString() }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)

        simpleUIListView.adapter = adapter

        presenter.friends.subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(::updateList)

    }

    private fun updateList(item: List<Friend> ) {
        val itemsArray = item.map { it.description}.toTypedArray()

        adapter.clear()
        adapter.addAll(*itemsArray) // turns an array to list of items
        adapter.notifyDataSetChanged()

    }

    override fun onDestroy() {
        super.onDestroy()
        bag.clear()
    }
}
