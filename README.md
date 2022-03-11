# ModbusProject
modbus4Android 라이브러리를 활용한 ModbusTCP 통신 학습용 Application

#1 Modbus 통신
모드버스(modbus)는 1979년 모디콘(Modicon)사에서 만든 통신 프로토콜이다. 
공장이나 기계 자동화.제어를 위해 사용되는 PLC들과의 통신에 사용된다. 
산업현장에서 주로 쓰이는 Modbus 프로토콜을 이용한 Application을 통해 
산업현장이 아닌 곳에서도 스마트폰 등을 이용해 현장을 관측가능할 수 있다는 점을 학습했다.

#2 Modbus 프로토콜
  fun modbusInit(ip: String , PORT: Int): String {}
PC의 IP주소와 포트번호를 입력해 Modbusㅆ 통신을 초기화한다.

  fun readCoilClickEvent() {}
modbusTCP 중 Digital Coil Outputs 값을 읽는다.

  fun readHoldingRegistersClickEvent() {}
modbusTCP 중 Analog Holding Registers 값을 읽는다.

  fun writeCoilClickEvent(address : Int , value:Boolean) {}
modbusTCP 중 Digital Coil Output 값을 쓴다.

  fun writeRegisterClickEvent(address: Int, value:Int) {}
modbusTCp 중 Analog Holding Registers 값을 쓴다.
