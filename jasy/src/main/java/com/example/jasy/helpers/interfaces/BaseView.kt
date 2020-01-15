package com.example.jasy.helpers.interfaces

interface BaseView {
    fun showError(message: String)
    fun showActivityIndicator()
    fun hideActivityIndicator()
}