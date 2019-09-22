package com.example.jasy.Helpers

interface BaseView {
    fun showError(message: String)
    fun showActivityIndicator()
    fun hideActivityIndicator()
}