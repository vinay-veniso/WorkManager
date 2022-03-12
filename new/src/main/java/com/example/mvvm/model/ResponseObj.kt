package com.example.mvvm.model

data class ResponseObj( var firstName: String,
                        val lastName: String,
                        val gender: String,
                        val age: Long,
                        val address: Address,
                        val phoneNumbers: List<PhoneNumber>)



