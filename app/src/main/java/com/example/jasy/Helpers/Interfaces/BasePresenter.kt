package com.example.jasy.Helpers.Interfaces

interface BasePresenter<T> {
    fun onCreate(view: T)
    fun onDestroy()
}