package com.example.pricer.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricer.R
import com.example.pricer.adapters.PriceChangeAdapter
import com.example.pricer.constants.Actions
import com.example.pricer.constants.ObjectType
import com.example.pricer.events.ObjectInstanceCreatedEvent
import com.example.pricer.models.PriceChange
import com.example.pricer.utils.JsonUtils
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.model.GradientColor
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class PriceGraphFragment: Fragment() {
    companion object {
        const val TAG = "PriceGraphFragment"
    }

    private lateinit var lineChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_price_graph, container, false)

        bindViews(view)

        styleChart()

        return view
    }

    private fun styleChart() {
        lineChart.setBackgroundColor(Color.WHITE)
        lineChart.description.isEnabled = false
        lineChart.isEnabled = true
        lineChart.setDrawGridBackground(false)

        val mv = MarkerView(context, R.layout.price_marker_view)
        mv.chartView = lineChart
        lineChart.marker = mv

        lineChart.isDragEnabled = true
        lineChart.isScaleXEnabled = true
        lineChart.isScaleYEnabled = true
        lineChart.setPinchZoom(true)

        val xAxis = lineChart.xAxis

        // vertical grid lines
        xAxis.enableGridDashedLine(10f, 10f, 0f)

        val yAxis = lineChart.axisLeft

        // disable dual axis (only use LEFT axis)
        lineChart.axisRight.isEnabled = false

        // horizontal grid lines
        yAxis.enableGridDashedLine(10f, 10f, 0f)
        yAxis.isEnabled = false

        // axis range
        // yAxis.axisMaximum = 200f
        // yAxis.axisMinimum = -50f
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReceivePriceChangeArray(objectInstanceCreatedEvent: ObjectInstanceCreatedEvent) {
        if (objectInstanceCreatedEvent.action == Actions.SEND_PRODUCT_DATA) {
            if (objectInstanceCreatedEvent.objectType == ObjectType.OBJ_PRICE_CHANGE_ARRAY) {
                val array = objectInstanceCreatedEvent.priceChangeArray!!
                Log.d(TAG, "onReceiveProduct: price change array data -> ${Gson().toJson(array)}")
                setChartData(array)
            }
        }
    }

    private fun setChartData(priceChangeArray: ArrayList<PriceChange>) {
        val entries = ArrayList<Entry>()

        for (index in priceChangeArray.indices) {
            entries.add(Entry(index.toFloat(), priceChangeArray[index].price.toFloat()))
        }

        val set1: LineDataSet

        if (lineChart.data != null && lineChart.data.dataSetCount > 0) {
            set1 = lineChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = entries
            lineChart.data.notifyDataChanged()
            lineChart.notifyDataSetChanged()

        } else {
            set1 = LineDataSet(entries, "Price evolution")

            set1.setDrawIcons(false)

            val startColor1 = ContextCompat.getColor(context!!, android.R.color.holo_orange_light)
            val startColor2 = ContextCompat.getColor(context!!, android.R.color.holo_blue_light)
            val startColor3 = ContextCompat.getColor(context!!, android.R.color.holo_orange_light)
            val startColor4 = ContextCompat.getColor(context!!, android.R.color.holo_green_light)
            val startColor5 = ContextCompat.getColor(context!!, android.R.color.holo_red_light)
            val endColor1 = ContextCompat.getColor(context!!, android.R.color.holo_blue_dark)
            val endColor2 = ContextCompat.getColor(context!!, android.R.color.holo_purple)
            val endColor3 = ContextCompat.getColor(context!!, android.R.color.holo_green_dark)
            val endColor4 = ContextCompat.getColor(context!!, android.R.color.holo_red_dark)
            val endColor5 = ContextCompat.getColor(context!!, android.R.color.holo_orange_dark)

            val gradientColors = ArrayList<GradientColor>()
            gradientColors.add(GradientColor(startColor1, endColor1))
            gradientColors.add(GradientColor(startColor2, endColor2))
            gradientColors.add(GradientColor(startColor3, endColor3))
            gradientColors.add(GradientColor(startColor4, endColor4))
            gradientColors.add(GradientColor(startColor5, endColor5))

            set1.gradientColors = gradientColors

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1)

            val data = LineData(dataSets as List<ILineDataSet>?)
            data.setValueTextSize(10f)

            data.setDrawValues(true)
            lineChart.data = data
        }
    }

    private fun bindViews(view: View) {
        lineChart = view.findViewById(R.id.line_chart)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }
}