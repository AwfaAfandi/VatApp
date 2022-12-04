package com.amaa.vatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amaa.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.calculateButton.setOnClickListener {
            calculateVat()
        }
    }
private fun calculateVat() {

        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val vatPercentage = when(selectedId){
            R.id.option_ten -> 0.10
            R.id.option_fifteen -> 0.15
            else -> 0.20
        }
        var vat = vatPercentage * cost

        var total = cost + vat


        val roundVat = binding.roundUpSwitch.isChecked

        if (roundVat){
            total = kotlin.math.ceil(total)
        }

        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.totaltext.text = getString(R.string.total_amount, formattedTotal)

    }

}