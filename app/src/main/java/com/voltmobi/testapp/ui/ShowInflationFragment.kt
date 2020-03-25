package com.voltmobi.testapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.voltmobi.testapp.R
import com.voltmobi.testapp.arch.ViewModelFragment
import com.voltmobi.testapp.di.components.show_inflation.ShowInflationComponent
import com.voltmobi.testapp.ui.adapters.YearsAdapter
import com.voltmobi.testapp.viewmodel.ShowInflationViewModel
import kotlinx.android.synthetic.main.fragment_show_inflation.*
import javax.inject.Inject

class ShowInflationFragment : ViewModelFragment<ShowInflationViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getViewModelClass(): Class<ShowInflationViewModel> = ShowInflationViewModel::class.java

    override fun getFactory(): ViewModelProvider.Factory = viewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.applicationContext?.let { ShowInflationComponent.Initializer.init(it).inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_inflation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        containerLock.setOnClickListener {  }

        getViewModel().data.observe(this, Observer { data ->
            context?.let {
                containerLock.visibility = View.GONE
                tabBar?.visibility = View.VISIBLE
                yearRecyclerView?.apply {
                    layoutManager = LinearLayoutManager(it)
                    adapter = YearsAdapter(it, data.yearsAndMonthData)
                }
            }
        })

        getViewModel().errorData.observe(this, Observer { data ->
            showMessege(data.messegeError)
            retryButton.visibility = View.VISIBLE
        })

        retryButton.setOnClickListener {
            retryButton.visibility = View.GONE
            getViewModel().onUiAttach()
        }
    }

}
