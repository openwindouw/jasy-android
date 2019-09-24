package com.example.jasy.Helpers

interface BasePresenter<T> {
    fun onCreate(view: T)
    fun onDestroy()
}