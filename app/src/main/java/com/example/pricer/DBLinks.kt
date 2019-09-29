package com.example.pricer

class DBLinks {
    companion object {
        private const val baseLink: String = "http://192.168.0.19:3000/"
        const val registerUserEmail: String = baseLink + "users/register-user-email/"
        const val registerStore: String = baseLink + "stores/register-store/"
    }
}