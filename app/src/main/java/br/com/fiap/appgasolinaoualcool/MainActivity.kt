package br.com.fiap.appgasolinaoualcool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        processCalculation()
    }

    private fun processCalculation() {
        btCalculate.setOnClickListener {
            if(validToStartCalculation()) {
                val totalAlcool = etConsumeAlcool.convertToDouble() / etPriceAlcool.convertToDouble()
                val totalGasoline = etConsumeGasoline.convertToDouble() / etPriceGasoline.convertToDouble()

                tvResult.text = when {
                    totalAlcool>totalGasoline -> getString(R.string.alcoolIsBetter)
                    totalGasoline>totalAlcool -> getString(R.string.gasolineIsBetter)
                    else -> getString(R.string.bothIsEquals)
                }
            } else {
                Toast.makeText(this@MainActivity, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validToStartCalculation(): Boolean =
        listOf(
            etConsumeAlcool.verifyIsEmpty(),
            etPriceAlcool.verifyIsEmpty(),
            etConsumeGasoline.verifyIsEmpty(),
            etPriceGasoline.verifyIsEmpty()
        ).all {
            it!=null
        }

    private fun EditText.verifyIsEmpty() = this.text.toString().takeIf { it.trim()!="" }
    private fun EditText.convertToDouble() = this.text.toString().toDouble()
}