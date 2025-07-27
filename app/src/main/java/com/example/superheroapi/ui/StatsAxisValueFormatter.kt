package com.example.superheroapi.ui

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter

class StatsAxisValueFormatter(
    private val labels: List<String>,
    private val entries: List<RadarEntry>
) : ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val index = value.toInt()
        return if (index >= 0 && index < labels.size) {
            val statValue = entries[index].value.toInt()
            // Menggabungkan label dan angka dengan spasi
            "${labels[index]}  ${statValue}"
        } else {
            ""
        }
    }
}