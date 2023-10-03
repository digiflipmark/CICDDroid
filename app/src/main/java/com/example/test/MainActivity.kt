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
            val interestRate = binding.interestEditText.text.toString().toFloat()
            val currentAge = binding.ageEditText.text.toString().toInt()
            val retirementAge = binding.retirementEditText.text.toString().toInt()

            if (interestRate <= 0) {
                Analytics.trackEvent("wrong_interest_rate")
            }

        }
    }
}