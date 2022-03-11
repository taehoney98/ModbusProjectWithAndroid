# ModbusProject
modbus4Android 라이브러리를 활용한 ModbusTCP 통신 학습용 Application

#  Modbus 통신

모드버스(modbus)는 1979년 모디콘(Modicon)사에서 만든 통신 프로토콜이다. \
공장이나 기계 자동화. 제어를 위해 사용되는 PLC들과의 통신에 주로 사용된다. \
산업현장에서 주로 쓰이는 Modbus 프로토콜을 이용한 Application을 통해 \
산업현장이 아닌 곳에서도 스마트폰 등을 이용해 현장을 관측하고 제어 할 수 있다.

## Modbus4Android 라이브러리
### modbusInit() 함수
```Kotlin
  fun modbusInit(IP: String , PORT: Int): String {
    ModbusReq.getInstace().setParam(
    ModbusParam().sethost("IP주소")
    .setPort(502)
    ...
 
    )    
  }
```  
PC의 IP주소와 포트번호를 입력해 Modbus 통신을 초기화한다.

### readCoilClickEvent() 함수

```Kotlin
  fun readCoilClickEvent() {
    ModbusReq.getInstance().readCoil( ``` )
  }

```
modbusTCP 중 Digital Coil Outputs 값을 읽는다.

### readHoldingRegistersClickEvent() 함수
```Kotlin
  fun readHoldingRegistersClickEvent() {
    ModbusReq.getInstance().readHoldingRegisters( ```)
  }
  ```
modbusTCP 중 Analog Holding Registers 값을 읽는다.

### writeCoilClickEvent() 함수
```Kotlin
  fun writeCoilClickEvent(address : Int , value:Boolean) {
    ModbusReq.getInstace().writeCoil(```)
  }
```

modbusTCP 중 Digital Coil Output 값을 쓴다.

### writeRegisterClickEvent() 함수
```Kotlin
  fun writeRegisterClickEvent(address: Int, value:Int) {
    ModbusReq.getInstace().writeRegister(```)
  }
```

modbusTCP 중 Analog Holding Registers 값을 쓴다.

readDiscreteInputClickEvent() 함수와 readInputRegistersClickEvent() 함수는 사용하지 않았다

## MainActivity

### Coil Outputs read 버튼
```Kotlin
  val btnRead :TextView = findViewById(R.id.btnReadDigital)

  btnRead.setOnClickListener{
      modbusClass.readCoilClickEvent()
  }
```
입력된 Coil Outputs 값을 Log를 통해 확인 할 수 있다.
### Coil Outputs write 버튼
```Kotlin
  val btnWrite :TextView= findViewById(R.id.btnWriteDigital)
  var i : Int = 0
  var flag: Boolean= false

  btnWrite.setOnClickListener {

    if (i %10==0){
      flag=!flag
    }
    modbusClass.writeCoilClickEvent(i%10,flag) //size of array is 10
    i++
  }
```  
초기값은 모두 false 이며, 버튼이 클릭 될 때 마다 순서대로 false 에서 true , 혹은 true 에서 false로 변경된다.

### Holding Registers read 버튼  
```Kotlin
  val btnReadAnalog :TextView = findViewById(R.id.btnReadAnalog)

  btnReadAnalog.setOnClickListener {
    modbusClass.readHoldingRegistersClickEvent()
  }

```
입력된 Holding Registers 값을 Log를 통해 확인할 수 있다.
### Holding Registers write 버튼
```Kotlin
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
  
```
입력된 값이 없으면 Toast 메세지를 출력하고, 
입력값이 존재하면 버튼이 클릭 될 때 마다 순서대로 배열에 저장한다.

### editText 버튼
```Kotlin

  val textAnalogNumber: TextView = findViewById(R.id.editTextNumber)

  textAnalogNumber.setOnEditorActionListener { textView, i, keyEvent ->
    var handled =false
    if (textAnalogNumber.text.isEmpty()) {
      Toast.makeText(this,"숫자를 입력해주세요",Toast.LENGTH_SHORT).show()
    handled =true
    }
    handled
  }
```   
editText 를 통해 입력된 값이 없으면 Toast 메세지를 출력한다.   
      
***

안드로이드 스튜디오 내 Logcat 뿐만 아니라

ModbusHD by HadiSCADA 라는 윈도우 프로그램을 통해
GUI 형태로 modbus 프로토콜을 다룰 수 있다.

ModbusHD: [ModbusHD](https://github.com/hadiscada/modbushd)
