package com.example.test

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test.databinding.ActivityMainBinding
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCenter.start(
            application,
            "2c8c6b8c-de04-4775-b1f9-3f7110df84de",
            Analytics::class.java,
            Crashes::class.java
        )

        binding.calculateButton.setOnClickListener {
            // Crashes.generateTestCrash()
            try {
                val interestRate = binding.interestEditText.text.toString().toFloat()
                val currentAge = binding.ageEditText.text.toString().toInt()
                val retirementAge = binding.retirementEditText.text.toString().toInt()
                val monthly = binding.monthlySavingsEditText.text.toString().toFloat()
                val current = binding.currentEditText.text.toString().toFloat()

                val properties: HashMap<String, String> = HashMap<String, String>()
                properties.put("interest_rate", interestRate.toString())
                properties.put("current_age", currentAge.toString())
                properties.put("retirement_age", retirementAge.toString())
                properties.put("monthly_savings", monthly.toString())
                properties.put("current_savings", current.toString())

                if (interestRate <= 0) {
                    Analytics.trackEvent("wrong_interest_rate")
                }
                if (retirementAge <= currentAge) {
                    Analytics.trackEvent("wrong_age", properties)
                }
                binding.resultTextView.text="${interestRate} - ${monthly}"
            } catch (e: Exception) {
                Analytics.trackEvent(e.message)
            }


        }
    }
}