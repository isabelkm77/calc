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

        btn00.setOnClickListener{appendOnclick(true, "00")}
        btn0.setOnClickListener{appendOnclick(true, "0")}
        btn1.setOnClickListener{appendOnclick(true, "1")}
        btn2.setOnClickListener{appendOnclick(true, "2")}
        btn3.setOnClickListener{appendOnclick(true, "3")}
        btn4.setOnClickListener{appendOnclick(true, "4")}
        btn5.setOnClickListener{appendOnclick(true, "5")}
        btn6.setOnClickListener{appendOnclick(true, "6")}
        btn7.setOnClickListener{appendOnclick(true, "7")}
        btn8.setOnClickListener{appendOnclick(true, "8")}
        btn9.setOnClickListener{appendOnclick(true, "9")}
        btnDot.setOnClickListener{appendOnclick(true, ".")}


        btnPlus.setOnClickListener{appendOnclick(false, "+")}
        btnDivide.setOnClickListener{appendOnclick(false, "/")}
        btnMinus.setOnClickListener{appendOnclick(false, "-")}
        btnMultiply.setOnClickListener{appendOnclick(false, "*")}
        btnLeftB.setOnClickListener{appendOnclick(false, "(")}
        btnRightB.setOnClickListener{appendOnclick(false, ")")}
        btnClear.setOnClickListener{clear()}
        btnEquals.setOnClickListener{ calculate()}



    }
    fun appendOnclick(clear: Boolean,string: String){
        if (clear){
            tvOutput.text =""
            tvInput.append(string)
        }
        else{
            tvInput.append(tvOutput.text)
            tvInput.append(string)
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
