package com.example.jasy.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.jasy.helpers.extensions.formattedCurrentDate
import java.util.*

class DatePickerFragment(private val onDatePick: (formattedDate: String) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c.set(year, month, dayOfMonth)
        onDatePick(c.time.formattedCurrentDate)
    }

    companion object {

        @JvmStatic
        fun newInstance(onDatePick: (formattedDate: String) -> Unit) = DatePickerFragment(onDatePick)
    }

}