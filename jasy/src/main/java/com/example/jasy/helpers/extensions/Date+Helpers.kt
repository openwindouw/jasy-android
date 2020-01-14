package com.example.jasy.helpers.extensions

import com.example.jasy.helpers.constants.DateFormatConstants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

val Date.formattedCurrentDate : String
    get(){
        return SimpleDateFormat(DateFormatConstants.default).format(this)
    }

val formattedFirstDateOfCurrentMonth: String
    get() {
        val c = Calendar.getInstance()
        c.set(Calendar.DAY_OF_MONTH, 1)
        val d = c.time
        return SimpleDateFormat(DateFormatConstants.default).format(d)
    }