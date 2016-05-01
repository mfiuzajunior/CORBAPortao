package br.edu.ifce.mflj.sensores.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.ifce.mflj.estacao.sensor.acidez.ISensorAcidezPOA;
import br.edu.ifce.mflj.observer.SensorDeAcidezListener;
import br.edu.ifce.mflj.sensores.pojo.SensorDeAcidez;

public class SensorDeAcidezDAO extends ISensorAcidezPOA {

	private static final String LEITURA_PADRAO = "25";

	private SensorDeAcidez sensorDeAcidez;
	private List<SensorDeAcidezListener> sensorDeAcidezListeners = new ArrayList<SensorDeAcidezListener>();

	public SensorDeAcidezDAO(){
		this.setSensorDeAcidez( new SensorDeAcidez( true, SensorDeAcidezDAO.LEITURA_PADRAO ) );
	}

	public SensorDeAcidezDAO( SensorDeAcidez sensorDeAcidez ){
		this.sensorDeAcidez = sensorDeAcidez == null ? new SensorDeAcidez( true, SensorDeAcidezDAO.LEITURA_PADRAO ) : sensorDeAcidez;
	}

	public SensorDeAcidez getSensorDeAcidez(){
		return sensorDeAcidez;
	}

	public void setSensorDeAcidez( SensorDeAcidez sensorDeAcidez ){
		this.sensorDeAcidez = sensorDeAcidez;
	}

	@Override
	public boolean ligado(){
		return this.getSensorDeAcidez().getEstado();
	}

	@Override
	public String obterLeitura(){
		return this.getSensorDeAcidez().getLeitura();
	}

	@Override
	public void configurarLeitura( String leitura ){
		this.getSensorDeAcidez().setLeitura( leitura );
		notificarLeituraRecebida( this.getSensorDeAcidez().getLeitura() );
	}

	@Override
	public String obterIdentificador() {
		return this.getSensorDeAcidez().toString();
	}

	private void notificarLeituraRecebida( String leitura ){
		for( SensorDeAcidezListener sensorDeAcidezListener : sensorDeAcidezListeners ){
			sensorDeAcidezListener.leituraObtida( leitura );
		}
	}

	public void addSensorDeAcidezListener( SensorDeAcidezListener sensorDeAcidezListener ){
		this.sensorDeAcidezListeners.add( sensorDeAcidezListener );
	}
}
