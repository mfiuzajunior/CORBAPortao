package br.edu.ifce.mflj.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.edu.ifce.mflj.museu.services.PortaoService;

public class PortaoGUI extends JFrame implements ActionListener {

	private static final long	serialVersionUID = 5883698476830081453L;
	private static final int	LARGURA			= 200,
								ALTURA			= 100;

	private PortaoService	portaoService;

	private JButton			botaoEntrada,
							botaoSaida;

	public PortaoGUI( String[] args ){
		super();

		iniciarPortaoService( args );
		iniciarGUI();

		this.repaint();
	}

	public JButton getBotaoEntrada() {
		if( botaoEntrada == null ){
			botaoEntrada = new JButton( "Entrada" );
			botaoEntrada.setBounds( 5, 5, 100, 30 );
			botaoEntrada.addActionListener( this );
		}
		return botaoEntrada;
	}

	public JButton getBotaoSaida() {
		if( botaoSaida == null ){
			botaoSaida = new JButton( "Saida" );
			botaoSaida.setBounds( 5, 45, 100, 30 );
			botaoSaida.addActionListener( this );
		}
		return botaoSaida;
	}

	private void iniciarPortaoService( String[] args ) {
		portaoService = new PortaoService( args );
		new Thread( portaoService ).start();
	}

	private void iniciarGUI(){
		setDefaulLookAndFeel();

		this.setTitle( "Portão" );
		this.setResizable( false );
		this.setBounds( 100, 100, PortaoGUI.LARGURA, PortaoGUI.ALTURA );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.getContentPane().setLayout( null );

		this.setVisible( true );

		iniciarComponentes();
	}

	private void iniciarComponentes() {
		this.getContentPane().add( getBotaoEntrada() );
		this.getContentPane().add( getBotaoSaida() );
	}

	private void setDefaulLookAndFeel(){
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		}
		catch( UnsupportedLookAndFeelException unsupportedLookAndFeelException ){}
		catch( ClassNotFoundException classNotFoundException ){}
		catch( InstantiationException instantiationException ){}
		catch( IllegalAccessException illegalAccessException ){}		
	}

	@Override
	public void actionPerformed( ActionEvent actionEvent ){
		if( actionEvent.getSource().equals( botaoEntrada ) ){
			try {
				portaoService.notificarEntradaAoGuarda();	
			} catch( NullPointerException nullPointerException ){
				JOptionPane.showMessageDialog(this, "Não foi possível notificar guarda");
			}

		} else if( actionEvent.getSource().equals( botaoSaida ) ){
			try {
				portaoService.notificarSaidaAoGuarda();	
			} catch( NullPointerException nullPointerException ){
				JOptionPane.showMessageDialog(this, "Não foi possível notificar guarda");
			}
		}
	}

	public static void main(String[] args) {
		new PortaoGUI( args );
	}
}