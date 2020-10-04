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
    var signoAct = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSing.setOnClickListener {
            var numero = txtResultado.text.toString().toDouble()
            if (numero != 0.00) {
                numero *= (-1)
                txtResultado.text = "$numero"
            }
        }
        btnClean.setOnClickListener { borrar() }
        btnEquals.setOnClickListener { resultado() }
    }

    fun obtenerNum(view: View) {
        if (!punto){
            if (resultado) {
                borrar()
                resultado = !resultado
            }
            if (signoAct) {
                txtResultado.text = "0"
                signoAct = !signoAct
            }
        }

        val btn = (view as Button)
        val numAnterior = txtResultado.text.toString()
        val numNuevo = btn.text.toString()

        txtResultado.text = "$numAnterior$numNuevo"
        if (num != 0.00){
            num2 = txtResultado.text.toString().toDouble()
        }
    }

    fun obtenerSig(view: View) {
        resultado = false
        val btn = (view as Button)
        val numNum = txtResultado.text.toString().toDouble()
            txtResultado.text = "0"
        if (!signoAct) {
            if(num2 != 0.00){
                resultado()
                resultado = false
            }else{
                num = numNum
            }
        }
        signo = btn.text.toString()
        punto = false
        signoAct = true
    }

    fun punto(view: View) {
        if (!punto) {
            obtenerNum(view)
            punto = !punto
        }
    }

    fun resultado() {
        if (!resultado && num != 0.00) {
            when (signo){
                "+"->{
                    num+=num2
                }
                "-"->{
                    num-=num2
                }
                "*"->{
                    num*=num2
                }
                "/"->{
                    num/=num2
                }
            }
            txtResultado.text = "$num"
            num2 = 0.00
            resultado = !resultado
            punto = false
            signoAct = false
        }
    }

    fun borrar(){
        punto = false
        resultado = false
        num = 0.00
        num2 = 0.00
        signo = null
        signoAct = false
        txtResultado.text = "0"
    }
}
