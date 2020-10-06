package com.example.jessi.calculadora

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var num: Double = 0.00
    var num2: Double = 0.00
    var signo: String? = null
    var punto = false
    var resultado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSing.setOnClickListener { cambiarSigno() }
        btnClean.setOnClickListener { borrar() }
        btnEquals.setOnClickListener { resultado() }
        btnPorcen.setOnClickListener { sacarPorcentaje() }
    }

    fun obtenerNum(view: View) {
        if (resultado){
            txtResultado.text = "0"
            resultado = !resultado
        }
        val btn = (view as Button)

        if (punto || txtResultado.text.toString() != "0" ){
            txtResultado.text = "${txtResultado.text}${btn.text}"
        }else{
            txtResultado.text = btn.text.toString()
        }

        if (signo == null){
            num = txtResultado.text.toString().toDouble()
        }else{
            num2 = txtResultado.text.toString().toDouble()
        }
    }

    fun obtenerSig(view: View) {
        txtResultado.text = "0"
        if (signo != null && num2 != 0.00){
            resultado()
        }
        val btn = (view as Button)
        signo = btn.text.toString()
        punto = false
    }

    fun punto(view: View) {
        if (!punto) {
            punto = !punto
            obtenerNum(view)
        }
    }

    fun resultado() {
        if (!resultado && num != 0.00) {
            when (signo){
                "+"->{ num+=num2 }
                "-"->{ num-=num2 }
                "*"->{ num*=num2 }
                "/"->{ num/=num2 }
            }
            txtResultado.text = num.toString()
            num2 = 0.00
            resultado = !resultado
            punto = false
            signo = null
        }
    }

    fun borrar(){
        punto = false
        resultado = false
        num = 0.00
        num2 = 0.00
        signo = null
        txtResultado.text = "0"
    }

    fun sacarPorcentaje(){
        if (txtResultado.text.toString().toDouble() != 0.00) {
            if (num2!=0.00){
                num2 /= 100
                txtResultado.text = num2.toString()
            }else{
                num /= 100
                txtResultado.text = num.toString()
            }
        }
    }

    fun cambiarSigno(){
        if (txtResultado.text.toString().toDouble() != 0.00){
            if (num2 == 0.00){
                num *= (-1)
                txtResultado.text = num.toString()
            }else{
                num2 *= (-1)
                txtResultado.text = num2.toString()
            }
        }
    }
}
