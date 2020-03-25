package com.voltmobi.testapp.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.voltmobi.testapp.R
import com.voltmobi.testapp.arch.ViewModelFragment
import com.voltmobi.testapp.di.components.calculate_inflation.CalculateInflationComponent
import com.voltmobi.testapp.ui.adapters.widgets.MonthYearPickerDialog
import com.voltmobi.testapp.viewmodel.CalculateInflationViewModel
import kotlinx.android.synthetic.main.fragment_calculate_inflation.*
import javax.inject.Inject

class CalculateInflationFragment : ViewModelFragment<CalculateInflationViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getViewModelClass(): Class<CalculateInflationViewModel> = CalculateInflationViewModel::class.java

    override fun getFactory(): ViewModelProvider.Factory = viewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.applicationContext?.let { CalculateInflationComponent.Initializer.init(it).inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate_inflation, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().dateSelectData.observe(this, Observer { data ->
            startDateEditText.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP)
                    setStartDate(data.minYear, data.maxYear)
                return@setOnTouchListener true
            }

            endDateEditeText.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP)
                    setEndDate(data.minYear, data.maxYear)
                return@setOnTouchListener true
            }
        })

        getViewModel().inflactionData.observe(this, Observer { data ->
            sumTextView.text = getString(R.string.sum_inflation_part1, data.countMonth.toString()) + " " +
                    getString(R.string.sum_inflation_part2, data.totalSum.toString())
        })
    }

    private fun setStartDate(minYear: Int, maxYear: Int) {
        activity?.let {
            MonthYearPickerDialog(
                maxYear, minYear, startDateListener
            ).show(it.supportFragmentManager, "MonthYearPickerDialog")
        }
    }

    private fun setEndDate(minYear: Int, maxYear: Int) {
        activity?.let {
            MonthYearPickerDialog(
                maxYear, minYear, endDateListener
            ).show(it.supportFragmentManager, "MonthYearPickerDialog")
        }
    }

    private var startDateListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        getViewModel().startYear = year
        getViewModel().startMonth = monthOfYear
        startDateEditText.setText(getData(year, monthOfYear))
    }

    private var endDateListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        getViewModel().endYear = year
        getViewModel().endMonth = monthOfYear
        endDateEditeText.setText(getData(year, monthOfYear))
        getViewModel().calculateInflationRange()
    }

    private fun getData(year: Int, monthOfYear: Int): String {
        return "$monthOfYear.$year"
    }

}
