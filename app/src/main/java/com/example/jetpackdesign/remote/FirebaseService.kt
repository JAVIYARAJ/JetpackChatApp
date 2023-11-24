package com.example.jetpackdesign.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackdesign.data.auth.User
import com.example.jetpackdesign.data.model.common.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseService {
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun registerUser(email:String,password:String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener { userAuth ->
                //save data to real time database
                firebaseDatabase.getReference("user").child(userAuth.user!!.uid).setValue(User(userAuth.user!!.uid,
                    userAuth.user!!.displayName!!,email,password))
                    .addOnSuccessListener {
                        if (userAuth.user!!.photoUrl != null) {
                            storage.getReference("user").putFile(userAuth.user!!.photoUrl!!)
                                .addOnSuccessListener {
                                    _errorMessage.postValue("Success")
                                }.addOnFailureListener {
                                    _errorMessage.postValue(it.message)
                                }
                        }
                    }.addOnFailureListener { error ->
                        _errorMessage.postValue(error.message)
                    }
            }.addOnFailureListener {
                _errorMessage.postValue(it.message)
            }
    }

}