package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCalculator();

    }

    fun initCalculator (){

        val buttonMap = hashMapOf("00" to btn00, "0" to btn0, "1" to btn1, "2" to btn2, "3" to btn3, "4" to btn4, "5" to btn5, "6" to btn6, "7" to btn7, "8" to btn8, "9" to btn9, "." to btnDot)
        val operatorMap = hashMapOf("+" to btnPlus, "/" to btnDivide, "-" to btnMinus, "*" to btnMultiply, "(" to btnLeftB, ")" to btnRightB)

        buttonMap.forEach { (key, value) ->  value.setOnClickListener {appendOnclick(true, key)}  }
        operatorMap.forEach { (key, value) ->  value.setOnClickListener {appendOnclick(false, key)}  }

        btnClear.setOnClickListener{clear()}
        btnEquals.setOnClickListener{ calculate()}
    }
    fun appendOnclick(clear: Boolean, buttonValueInput: String){
        if (clear){
            tvOutput.text =""
            tvInput.append(buttonValueInput)
        }
        else{
            tvInput.append(tvOutput.text)
            tvInput.append(buttonValueInput)
            tvOutput.text=""
        }
    }
    fun clear(){
        tvInput.text =""
        tvOutput.text=""
    }
    fun calculate(){
        try {
            val input = ExpressionBuilder(tvInput.text.toString()).build();
            val output = input.evaluate();
            val longOutput = output.toLong();
            if(output == longOutput.toDouble() ){
                tvOutput.text= longOutput.toString();
            }
            else{
                tvOutput.text = output.toString();
            }
        }catch (e:Exception){
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()

        }
    }
}
