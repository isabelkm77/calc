package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonMap = HashMap<String, Button>();
        val operatorMap = HashMap<String, Button>();

        buttonMap.put("00" ,btn0);
        buttonMap.put("1" , btn1);
        buttonMap.put("2" , btn2);
        buttonMap.put("3" , btn3);
        buttonMap.put("4" , btn4);
        buttonMap.put("5" , btn5);
        buttonMap.put("6" , btn6);
        buttonMap.put("7" , btn7);
        buttonMap.put("8" , btn8);
        buttonMap.put("9" , btn9);
        buttonMap.put("0" , btn0);
        buttonMap.put("." , btnDot);

        operatorMap.put("+" , btnPlus);
        operatorMap.put("/" , btnDivide);
        operatorMap.put("-" , btnMinus);
        operatorMap.put("*" , btnMultiply);
        operatorMap.put("(" , btnLeftB);
        operatorMap.put(")" , btnRightB);

        buttonMap.forEach { (key, value) ->  value.setOnClickListener {appendOnclick(true, key)}  }
        operatorMap.forEach { (key, value) ->  value.setOnClickListener {appendOnclick(false, key)}  }

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
