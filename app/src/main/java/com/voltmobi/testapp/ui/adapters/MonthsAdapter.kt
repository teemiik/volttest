package com.voltmobi.testapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voltmobi.testapp.R
import com.voltmobi.testapp.data.model.Month

class MonthsAdapter(
    private val context: Context,
    private val months: List<Month>
) : RecyclerView.Adapter<MonthsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.month_layout_item, parent, false)

        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = months[position]
        holder.monthTextView?.text = context.getString(R.string.month) + ": " + getMonthName(info.name)
        holder.valueTextView?.text = context.getString(R.string.inflation) + ": " + info.value
    }

    override fun getItemCount(): Int {
        return months.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val monthTextView: TextView? = itemView.findViewById(R.id.monthTextView)
        val valueTextView: TextView? = itemView.findViewById(R.id.valueTextView)
    }

    private fun getMonthName(name: String) = when (name) {
            "january" -> context.getString(R.string.january)
            "february" -> context.getString(R.string.february)
            "march" -> context.getString(R.string.march)
            "april" -> context.getString(R.string.april)
            "may" -> context.getString(R.string.may)
            "june" -> context.getString(R.string.june)
            "july" -> context.getString(R.string.july)
            "august" -> context.getString(R.string.august)
            "september" -> context.getString(R.string.september)
            "october" -> context.getString(R.string.october)
            "november" -> context.getString(R.string.november)
            "december" -> context.getString(R.string.december)
            else -> context.getString(R.string.month)
        }

}