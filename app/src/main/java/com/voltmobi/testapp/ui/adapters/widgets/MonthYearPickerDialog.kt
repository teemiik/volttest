package com.voltmobi.testapp.ui.adapters.widgets

import android.R.string.cancel
import android.R.string.ok
import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.voltmobi.testapp.R
import java.util.*

class MonthYearPickerDialog(
    private var maxYear: Int,
    private var minYear: Int,
    private var listener: OnDateSetListener
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        // Get the layout inflater
        val inflater: LayoutInflater = activity!!.layoutInflater
        val cal: Calendar = Calendar.getInstance()
        val dialog = inflater.inflate(R.layout.month_year_picker_dialog, null)
        val monthPicker = dialog.findViewById(R.id.picker_month) as NumberPicker
        val yearPicker = dialog.findViewById(R.id.picker_year) as NumberPicker
        monthPicker.minValue = 1
        monthPicker.maxValue = 12
        monthPicker.value = cal.get(Calendar.MONTH) + 1
        yearPicker.minValue = minYear
        yearPicker.maxValue = maxYear
        yearPicker.value = maxYear
        builder.setView(dialog) // Add action buttons
            .setPositiveButton(ok
            ) { _, _ ->
                listener.onDateSet(
                    null,
                    yearPicker.value,
                    monthPicker.value,
                    0
                )
            }
            .setNegativeButton(cancel
            ) { _, _ ->
                this@MonthYearPickerDialog.dialog?.cancel()
            }
        return builder.create()
    }
}