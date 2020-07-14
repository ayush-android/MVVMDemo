package com.livedatademo.without_room_database.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//Never pass context into ViewModel instances.
//Do not store Activity, Fragment, or View instances or their Context in the ViewModel. It can be a memory leak. If you need the application context, use //AndroidViewModel. Extend the class from AndroidViewModel instead of ViewModel and create a constructor using one parameter of "Application" class.

/*With MVVM it’s best practices to have one ViewModel per Activity or Fragment*/
class MainActivityViewModel : ViewModel() {

    /*Below variables should be private so that other classes can not access them
    * otherwise they can call setValue() on below objects and can change the value*/
    private val firstName = MutableLiveData<String>()
    private val lastName = MutableLiveData<String>()
    private val age = MutableLiveData<Int>()

    /*Below variables should be of type "LiveData" so that no one can modify its value
    * or can call setValue() on these objects. Because LiveData doesn't allow to call setValue
    * */
    val firstNameUpdate: LiveData<String>  = firstName
    val lastNameUpdate: LiveData<String> = lastName
    val ageUpdate: LiveData<Int> = age


    fun setup() {
        /*You can call an api here to get the data from server*/

        val apiFirstName = "Ayush"
        val apiLastName = "Kaushik"
        val apiAge = 25

        firstName.value = apiFirstName
        lastName.value = apiLastName
        age.value = apiAge
    }

}