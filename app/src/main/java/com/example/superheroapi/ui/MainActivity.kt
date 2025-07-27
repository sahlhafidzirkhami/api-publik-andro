package com.example.superheroapi.ui // Pastikan package name Anda benar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat // Import untuk getColor yang lebih aman
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.superheroapi.R // <-- IMPORT YANG HILANG
import com.example.superheroapi.databinding.ActivityMainBinding
import com.example.superheroapi.domain.model.Superhero
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()

        // Panggil data untuk Goblin Queen (ID: 286)
        viewModel.fetchSuperheroDetail("286")
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UiState.Loading -> {

                    }
                    is UiState.Success -> {

                        bindDataToViews(state.superhero) // Hanya ada satu fungsi ini
                    }
                    is UiState.Error -> {

                        Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    // Hanya ada satu fungsi bindDataToViews
    private fun bindDataToViews(superhero: Superhero) {
        with(binding) {
            Glide.with(this@MainActivity)
                .load(superhero.imageUrl)
                .into(ivSuperhero)

            tvSuperheroName.text = superhero.name
            tvFullName.text = superhero.fullName

            // Panggil fungsi setup chart
            setupRadarChart(superhero)

            // Tampilkan data Appearance di kartu kedua
            tvGender.text = "Gender: ${superhero.gender}"
            tvRace.text = "Race: ${superhero.race}"
            tvHeight.text = "Height: ${superhero.height}"
            tvWeight.text = "Weight: ${superhero.weight}"
        }
    }

    private fun setupRadarChart(superhero: Superhero) {
        val entries = ArrayList<RadarEntry>()
        entries.add(RadarEntry(superhero.intelligence.toFloat()))
        entries.add(RadarEntry(superhero.strength.toFloat()))
        entries.add(RadarEntry(superhero.speed.toFloat()))
        entries.add(RadarEntry(superhero.durability.toFloat()))
        entries.add(RadarEntry(superhero.power.toFloat()))
        entries.add(RadarEntry(superhero.combat.toFloat()))

        val labels = listOf("INT:", "STR:", "SPD:", "DUR:", "POW:", "COM:")

        val dataSet = RadarDataSet(entries, "Stats")
        // Styling garis dan area chart
        dataSet.color = ContextCompat.getColor(this, R.color.purple_500) // Cara lebih aman
        dataSet.fillColor = ContextCompat.getColor(this, R.color.purple_200)
        dataSet.setDrawFilled(true)
        dataSet.lineWidth = 2f

        val chartData = RadarData(dataSet)
        // Styling teks data di chart
        chartData.setValueTextSize(12f)
        chartData.setDrawValues(false)

        val chart = binding.radarChartStats
        chart.data = chartData // Menggunakan variabel baru

        // Styling sumbu X (label di sudut)
        chart.xAxis.valueFormatter = StatsAxisValueFormatter(labels, entries)
        chart.xAxis.textColor = ContextCompat.getColor(this, android.R.color.white)
        chart.xAxis.textSize = 14f

        // Styling sumbu Y (garis dari tengah)
        chart.yAxis.textColor = ContextCompat.getColor(this, android.R.color.white)
        chart.yAxis.setLabelCount(6, true)
        chart.yAxis.axisMinimum = 0f
        chart.yAxis.axisMaximum = 100f
        chart.yAxis.setDrawLabels(false) // Sembunyikan angka di sumbu Y agar lebih bersih

        // Styling umum
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        chart.webColor = ContextCompat.getColor(this, android.R.color.darker_gray)
        chart.webColorInner = ContextCompat.getColor(this, android.R.color.darker_gray)

        chart.setExtraOffsets(30f, 30f, 30f, 30f)

        chart.invalidate()
    }
}