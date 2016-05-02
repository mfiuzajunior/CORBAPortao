package br.edu.ifce.mflj.museu.services;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import br.edu.ifce.mflj.museu.guarda.IGuarda;
import br.edu.ifce.mflj.museu.guarda.IGuardaHelper;

public class PortaoService implements Runnable {
	private ORB					orb;
	private Object				nameService;
	private NamingContext		namingContext;
	private IGuarda				guarda;

	public PortaoService( String[] args ){
		setUp( args );
		obterGuarda();
	}

	private void setUp(String[] args){
		try {
			orb				= ORB.init( args, null );
			nameService		= orb.resolve_initial_references( "NameService" );
			namingContext	= NamingContextExtHelper.narrow( nameService );
		}
		catch (InvalidName e) {
			e.printStackTrace();
		}
	}

	private void obterGuarda() {
		try {
			NameComponent	nameComponent[]		= { new NameComponent( "Guarda", "Guarda" ) };
			Object			referenciaGuarda	= namingContext.resolve( nameComponent );

			guarda = IGuardaHelper.narrow( referenciaGuarda );
		}
		catch (NotFound e) {
			System.err.println("Guarda ainda não registrado.");
		}
		catch (CannotProceed e) {
			e.printStackTrace();
		}
		catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			e.printStackTrace();
		}
	}

	public void notificarEntradaAoGuarda(){
		guarda.entradaDeCliente();
	}

	public void notificarSaidaAoGuarda(){
		guarda.saidaDeCliente();
	}

	public void run(){
		this.orb.run();
	}
}