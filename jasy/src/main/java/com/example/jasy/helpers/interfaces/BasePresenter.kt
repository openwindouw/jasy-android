package com.example.jasy.helpers.interfaces

interface BasePresenter<T> {
    fun onCreate(view: T)
    fun onDestroy()
}