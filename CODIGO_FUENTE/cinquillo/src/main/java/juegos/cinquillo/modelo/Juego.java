package juegos.cinquillo.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import juegos.cinquillo.tipo.Palo;
import juegos.cinquillo.tipo.TipoJugador;
import juegos.cinquillo.util.ColaCircular;

public class Juego {
	private int numeroJugadoresHumanos;
	private int numeroJugadoresMaquina;
	
	private ColaCircular<Jugador> jugadores;

	public Juego(int numeroJugadoresHumanos, int numeroJugadoresMaquina) {
		super();
		this.numeroJugadoresHumanos = numeroJugadoresHumanos;
		this.numeroJugadoresMaquina = numeroJugadoresMaquina;
		configurarJugadores();
		repartirCartas();
	}

	private void configurarJugadores() {
		for(int i=1;i<=this.numeroJugadoresHumanos;i++) {
			jugadores.encolar(new Jugador("Player "+i,TipoJugador.HUMANO));
		}
		
		for(int i=1;i<=this.numeroJugadoresMaquina;i++) {
			jugadores.encolar(new Jugador("PC "+i,TipoJugador.MAQUINA));
		}
	}
	
	private void repartirCartas() {
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
	
	public Jugador siguienteTurno() {
		return jugadores.siguiente();
	}
}
