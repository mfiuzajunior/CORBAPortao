package br.com.ifce.mflj.estacao.sensor.acidez;


/**
* br/com/ifce/mflj/estacao/sensor/acidez/ISensorAcidezOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SensorDeAcidez.idl
* Quarta-feira, 8 de Abril de 2015 08h45min25s BRT
*/

public interface ISensorAcidezOperations 
{
  boolean ligado ();
  String obterLeitura ();
  String obterIdentificador ();
  void configurarLeitura (String leitura);
} // interface ISensorAcidezOperations