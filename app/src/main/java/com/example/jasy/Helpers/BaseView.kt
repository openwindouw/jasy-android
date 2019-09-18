package com.example.jasy.Helpers

interface BaseView<T> {
    fun showActivityIndicator()
    fun hideActivityIndicator()
    fun setPresenter(presenter: T)
}