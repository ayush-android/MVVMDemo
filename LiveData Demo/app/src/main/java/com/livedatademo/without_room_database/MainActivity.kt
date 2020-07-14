package com.livedatademo.without_room_database

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.livedatademo.R
import com.livedatademo.without_room_database.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact (implementation "androidx.activity:activity-ktx:1.1.0")
    // the field viewModel should be val otherwise it starts to give error
    private val viewModel: MainActivityViewModel by viewModels()
    /*OR
    * The Old way is:
    * val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    * */

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Not Proper Way
        *//*Below way to use firstName(MutableLiveData) of viewHolder class
         directly inside MainActivity is not ideal as firstName is a MutableLiveData.
         This means, it exposes firstName to be modified externally. or firstName can be
         modify externally*//*

        viewModel.firstName.observe(this, Observer { value ->
            value?.let { first_name.text = "First Name is ${it.length}" }
            *//*OR*//*
            value?.let { first_name.text = "First Name is ${value.length}" }
        })*/

        /*Proper Way*/
        viewModel.firstNameUpdate.observe(this, Observer {
            it?.let { first_name.text = it }
        })

        /*To modify the data .Using Transformations.map is helpful to ensure that
        the LiveData doesn’t pass information over when the destination
        e.g. ViewModel and View is dead.*/
        val transformations = Transformations.map(viewModel.firstNameUpdate) {
            "First Name is $it"
        }
        transformations.observe(this, Observer {
            first_name.text = it
        })

        viewModel.lastNameUpdate.observe(this, Observer {
            last_name.text = "Last Name is $it"
        })

        viewModel.ageUpdate.observe(this, Observer {
            it?.let { age.text = "Age is ${it.minus(1)}" }
        })

        Handler().postDelayed(Runnable {
            viewModel.setup()
        }, 3000)
    }
}