package com.voltmobi.testapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voltmobi.testapp.R
import com.voltmobi.testapp.data.model.YearsWithMonths

class YearsAdapter(
    private val context: Context,
    private val yearsWithMonths: List<YearsWithMonths>
) : RecyclerView.Adapter<YearsAdapter.ViewHolder>() {

    var positionSelect = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.year_layout_item, parent, false)

        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val yearWithMonths = yearsWithMonths[position]

        holder.yearTextView?.text = context.getString(R.string.year) + ": " + yearWithMonths.year.name
        holder.valueTextView?.text = context.getString(R.string.inflation) + ": " + yearWithMonths.year.value

        holder.monthRecycleView?.apply {
            layoutManager = LinearLayoutManager(context)
        }

        if (positionSelect != position) {
            holder.monthRecycleView?.adapter = null
        }

        var isShow = false
        holder.yearsMonthLinearLayout?.setOnClickListener {
            if (!isShow) {
                isShow = true
                positionSelect = position
                val monthsAdapter = MonthsAdapter(context, yearWithMonths.months)
                holder.monthRecycleView?.adapter = monthsAdapter
            } else {
                isShow = false
                positionSelect = -1
                holder.monthRecycleView?.adapter = null
            }
        }

    }

    override fun getItemCount(): Int {
        return yearsWithMonths.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val yearsMonthLinearLayout: LinearLayout? = itemView.findViewById(R.id.yearsMonthLinearLayout)
        val yearTextView: TextView? = itemView.findViewById(R.id.yearTextView)
        val valueTextView: TextView? = itemView.findViewById(R.id.valueTextView)
        val monthRecycleView: RecyclerView? = itemView.findViewById(R.id.monthRecycleView)

    }
}