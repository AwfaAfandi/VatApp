package com.amaa.vatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amaa.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {


    // TODO [1] Create binding object
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO [2] Layout View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root // reference layout file
        setContentView(view) // link layout

        // TODO [3] Button binding and Calculate VAT
        binding.calculateButton.setOnClickListener {
            calculateVat()
        }// End setOnClickListener


    }// End onCreate

    private fun calculateVat() {

        // TODO [4] receive and save the inputs

        // EditText value
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()

        // Radio Group
        val selectedId = binding.tipOptions.checkedRadioButtonId

        // Radio Button Selected
        val vatPercentage = when(selectedId){
            R.id.option_ten -> 0.10
            R.id.option_fifteen -> 0.15
            else -> 0.20
        }

        var vat = vatPercentage * cost

        var total = cost + vat

        // Switch Value - True or False
        val roundVat = binding.roundUpSwitch.isChecked

        if (roundVat){
            total = kotlin.math.ceil(total)
        }

        // Total Formatting
        val formattedTotal = NumberFormat.getCurrencyInstance().format(total) // ربطت المبلغ بالعملة
        binding.totaltext.text = getString(R.string.total_amount, formattedTotal)

    }

}// End MainActivity