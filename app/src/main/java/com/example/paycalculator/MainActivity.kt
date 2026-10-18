package com.example.paycalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hoursInput = findViewById<EditText>(R.id.hoursInput)
        val rateInput = findViewById<EditText>(R.id.rateInput)
        val taxInput = findViewById<EditText>(R.id.taxInput)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val aboutButton = findViewById<Button>(R.id.aboutButton)

        val payResult = findViewById<TextView>(R.id.payResult)
        val overtimeResult = findViewById<TextView>(R.id.overtimeResult)
        val totalResult = findViewById<TextView>(R.id.totalResult)
        val taxResult = findViewById<TextView>(R.id.taxResult)

        calculateButton.setOnClickListener {

            val hours = hoursInput.text.toString().toDoubleOrNull()
            val rate = rateInput.text.toString().toDoubleOrNull()
            val taxRate = taxInput.text.toString().toDoubleOrNull()

            if (hours == null || rate == null || taxRate == null ||
                hours < 0 || rate < 0 || taxRate < 0 || taxRate > 1) {

                Toast.makeText(
                    this,
                    "Please enter valid numbers",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                var pay: Double
                var overtimePay = 0.0

                if (hours > 40) {
                    pay = 40 * rate
                    overtimePay = (hours - 40) * rate * 1.5
                } else {
                    pay = hours * rate
                }

                val totalPay = pay + overtimePay
                val tax = pay * taxRate

                payResult.text = String.format("Pay: $%.2f", pay)
                overtimeResult.text = String.format("Overtime Pay: $%.2f", overtimePay)
                totalResult.text = String.format("Total Pay: $%.2f", totalPay)
                taxResult.text = String.format("Tax: $%.2f", tax)
            }
        }

        aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}