package com.example.modbusproject

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zgkxzx.modbus4And.requset.ModbusParam
import com.zgkxzx.modbus4And.requset.ModbusReq
import com.zgkxzx.modbus4And.requset.OnRequestBack
//import kotlinx.android.synthetic.main.activity_communication_setting.*
//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

var msg2: String = "통신 상태"

class ModbusClass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun modbusInit(IP: String, PORT: Int): String { //initialize Modbus Communication
        ModbusReq.getInstance().setParam(
            ModbusParam()
                .setHost("192.168.0.60")
                .setPort(502)
                .setEncapsulated(false)
                .setKeepAlive(true)
                .setTimeout(2000)
                .setRetries(0)
        )
            .init(object : OnRequestBack<String> {
                override fun onSuccess(s: String) {
                    Log.d(ContentValues.TAG, "onSuccess $s")
                    val msgSuccess: String = "통신 연결에 성공하였습니다"
                    msg2 = msgSuccess
                }

                override fun onFailed(msg: String) {
                    Log.d(ContentValues.TAG, "onFailed $msg")
                    val msgFailed: String = "통신 연결에 실패하였습니다"
                    msg2 = msgFailed
                }
            })
        return msg2
    }

    fun readCoilClickEvent() { // read Coil Outputs
        ModbusReq.getInstance().readCoil(object : OnRequestBack<BooleanArray?> {
            override fun onSuccess(booleen: BooleanArray?) {
                Log.d(
                    ContentValues.TAG,
                    "readCoil onSuccess " + Arrays.toString(booleen)
                )
            }

            override fun onFailed(msg: String) {
                Log.e(ContentValues.TAG, "readCoil onFailed $msg")
            }
        }, 1, 0, 10)
    }

    fun readDiscreteInputClickEvent() { //Only read Digital Inputs
        ModbusReq.getInstance().readDiscreteInput(object : OnRequestBack<BooleanArray?> {
            override fun onSuccess(booleen: BooleanArray?) {
                Log.d(
                    ContentValues.TAG,
                    "readDiscreteInput onSuccess " + Arrays.toString(booleen)
                )
                //Digital Inputs  READ ONlY

            }

            override fun onFailed(msg: String) {
                Log.e(ContentValues.TAG, "readDiscreteInput onFailed $msg")
            }
        }, 1, 0, 15)
    }

    fun readHoldingRegistersClickEvent() { //read Holding Registers
        ModbusReq.getInstance().readHoldingRegisters(object : OnRequestBack<ShortArray?> {
            override fun onSuccess(data: ShortArray?) {
                Log.d(
                    ContentValues.TAG,
                    "readHoldingRegisters onSuccess " + Arrays.toString(data)
                )
            }

            override fun onFailed(msg: String) {
                Log.e(ContentValues.TAG, "readHoldingRegisters onFailed $msg")
            }
        }, 1, 0, 100)
    }

    fun readInputRegistersClickEvent() { //Only read Analog Inputs
        ModbusReq.getInstance().readInputRegisters(object : OnRequestBack<ShortArray?> {
            override fun onSuccess(data: ShortArray?) {
                Log.d(
                    ContentValues.TAG,
                    "readInputRegisters onSuccess " + Arrays.toString(data)
                )
                // Analog Inputs READ ONLY

            }

            override fun onFailed(msg: String) {
                Log.e(ContentValues.TAG, "readInputRegisters onFailed $msg")
            }
        }, 1, 30, 50)
    }

    fun writeCoilClickEvent(address: Int, value: Boolean) { //write Coil Outputs
        ModbusReq.getInstance().writeCoil(object : OnRequestBack<String> {
            override fun onSuccess(s: String) {
                Log.e(ContentValues.TAG, "writeCoil onSuccess $s")
            }

            override fun onFailed(msg: String) {
                Log.e(ContentValues.TAG, "writeCoil onFailed $msg")
            }
        }, 1, address, value)
    }

    fun writeRegisterClickEvent(address: Int, value: Int) { // write Holding Resgisters
        ModbusReq.getInstance().writeRegister(object : OnRequestBack<String> {
            override fun onSuccess(s: String) {
                Log.e(ContentValues.TAG, "writeRegister onSuccess $s")
            }

            override fun onFailed(msg: String) {
                Log.e(ContentValues.TAG, "writeRegister onFailed $msg")
            }
        }, 1, address, value)
    }
}
