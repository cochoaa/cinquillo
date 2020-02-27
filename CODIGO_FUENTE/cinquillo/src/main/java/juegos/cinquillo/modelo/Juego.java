package juegos.cinquillo.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import juegos.cinquillo.tipo.Palo;
import juegos.cinquillo.tipo.TipoJugador;
import juegos.cinquillo.util.ColaCircular;

public class Juego {
	private int numeroJugadoresHumanos;
	private int numeroJugadoresMaquina;
	private Tablero tablero;
	
	private ColaCircular<Jugador> jugadores;

	public Juego(int numeroJugadoresHumanos, int numeroJugadoresMaquina) {
		super();
		this.numeroJugadoresHumanos = numeroJugadoresHumanos;
		this.numeroJugadoresMaquina = numeroJugadoresMaquina;
		this.tablero=new Tablero();
		this.jugadores=new ColaCircular<Jugador>(0);
		configurarJugadores();
		
	}

	private void configurarJugadores() {
		for(int i=1;i<=this.numeroJugadoresHumanos;i++) {
			jugadores.encolar(new Jugador("Player "+i,TipoJugador.HUMANO));
		}
		
		for(int i=1;i<=this.numeroJugadoresMaquina;i++) {
			jugadores.encolar(new Jugador("PC "+i,TipoJugador.MAQUINA));
		}
	}
	
	public void repartirCartas() {
		int numeroJugadores=this.numeroJugadoresHumanos+this.numeroJugadoresMaquina;
		List<Carta> cartas=barajarCartas();
		int cantidadCartasJugador=cartas.size()/numeroJugadores;
		for(int i=0;i<numeroJugadores;i++) {
			Jugador j=jugadores.siguiente();
			j.setCartas(cartas.subList(i*cantidadCartasJugador,(i+1)*cantidadCartasJugador));
		}
	}
	
	private List<Carta> barajarCartas(){
		List<Carta> mazo=new ArrayList<Carta>(48);
		for(Palo palo: Palo.values()) {
			for(int i=1;i<=12;i++) {
				mazo.add(new Carta(palo,i));
			}
		}
		Collections.shuffle(mazo);
		return mazo;
	}
	
	public boolean isGanador(Jugador jugador) {
		return jugador.getCartas().isEmpty();
	}
	
	public List<Carta> obtenerPosiblesCartasParaLanzar(){
		List<Carta> posiblesCartasParaLanzar= new ArrayList<Carta>(0);
		Map<Palo,List<Carta>> cartas=tablero.getCartas();
		if(!hayCartas(cartas)) {
			posiblesCartasParaLanzar.add(new Carta(Palo.OROS,5)) ;
		}else {
			for(Palo palo: Palo.values()) {
				List<Carta> cartasPalo=cartas.get(palo);
				if(cartasPalo.isEmpty()) {
					posiblesCartasParaLanzar.add(new Carta(palo,5)) ;
				}else if(cartasPalo.size()==1){
					posiblesCartasParaLanzar.add(new Carta(palo,4)) ;
					posiblesCartasParaLanzar.add(new Carta(palo,6)) ;
				}else {
					int primerNumero=cartasPalo.get(0).getNumero();
					if(primerNumero-1>0)
						posiblesCartasParaLanzar.add(new Carta(palo,primerNumero-1)) ;
					int ultimoNumero=cartasPalo.get(cartasPalo.size()-1).getNumero();
					if(ultimoNumero+1<13)
						posiblesCartasParaLanzar.add(new Carta(palo,ultimoNumero+1)) ;
				}
			}
		}
		return posiblesCartasParaLanzar;
	}
	
	private boolean hayCartas(Map<Palo,List<Carta>> cartas) {
		int cantidad=0;
		for(Palo palo: Palo.values()) {
			List<Carta> cartasPalo=cartas.get(palo);
			cantidad=cantidad+cartasPalo.size();
		}
		return cantidad>0;
	}
	
	public Jugador siguienteTurno() {
		return jugadores.siguiente();
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
}
