package com.example.modbusproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val modbusClass = ModbusClass()

        modbusClass.modbusInit("198.168.0.60",502) // Allocate  Own IP

        val btnRead :TextView = findViewById(R.id.btnReadDigital)
        val btnWrite :TextView= findViewById(R.id.btnWriteDigital)

        btnRead.setOnClickListener{
            modbusClass.readCoilClickEvent()
        }

        var i : Int = 0
        var flag: Boolean= false

        btnWrite.setOnClickListener {

            if (i %10==0){
                flag=!flag
            }
            modbusClass.writeCoilClickEvent(i%10,flag) //size of array is 10
            i++

        }

        val btnReadAnalog :TextView = findViewById(R.id.btnReadAnalog)

        btnReadAnalog.setOnClickListener {
            modbusClass.readHoldingRegistersClickEvent()
        }

        val textAnalogNumber: TextView = findViewById(R.id.editTextNumber)

        textAnalogNumber.setOnEditorActionListener { textView, i, keyEvent ->
            var handled =false
            if (textAnalogNumber.text.isEmpty()) {
                Toast.makeText(this,"숫자를 입력해주세요",Toast.LENGTH_SHORT).show()
                handled =true
            }
            handled

        }


        var j :Int =0
        val btnWriteAnalog :TextView =findViewById(R.id.btnWriteAnalog)

        btnWriteAnalog.setOnClickListener{
            if (textAnalogNumber.text.isEmpty()) {
                Toast.makeText(this,"숫자를 입력해주세요",Toast.LENGTH_SHORT).show()
            }
            else{
                val textToInt = textAnalogNumber.text.toString().toInt()
                modbusClass.writeRegisterClickEvent(j%10,textToInt) //size of Array is 10
                j++
            }
            textAnalogNumber.setText("") // clear textInput
        }
    }


}