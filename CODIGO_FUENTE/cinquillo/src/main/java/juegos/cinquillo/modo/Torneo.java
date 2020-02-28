package juegos.cinquillo.modo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import juegos.cinquillo.modelo.Juego;
import juegos.cinquillo.modelo.Jugador;

public class Torneo extends Juego {
	private Map<Jugador, Integer> puntos;
	private Jugador campeon;

	public Torneo(int numeroJugadoresHumanos, int numeroJugadoresMaquina) {
		super(numeroJugadoresHumanos, numeroJugadoresMaquina);
		inicializarPuntos();
	}

	private void inicializarPuntos() {
		puntos=new HashMap<Jugador, Integer>(0);
		for (Jugador jugador : super.getJugadores()) {
			puntos.put(jugador, 0);
		}
	}

	@Override
	public void iniciar() {
		int rondas=1;
		while (campeon==null) {
			System.out.println("....................Ronda #"+rondas+".........................");
			super.iniciarPartida();
			sumarPuntos();
			campeon=getCampeon();
			mostrarPuntajes();
			if (campeon==null) {
				super.prepararTablero();
				super.repartirCartas();
			}
			rondas=rondas+1;
		}

	}
	
	private Jugador getCampeon() {
		Map<Jugador,Integer> puntajesGanadores=new HashMap<Jugador, Integer>(0);
		for(Jugador jugador: puntos.keySet()) {
			int puntaje=puntos.get(jugador);
			if(puntaje>=10) {
				puntajesGanadores.put(jugador,puntaje);
			}
		}
		if(!puntajesGanadores.isEmpty()) {
			if(puntajesGanadores.size()==1) {
				return puntajesGanadores.keySet().iterator().next();
			}else {
				List<Integer> puntajes= new ArrayList<Integer>(puntajesGanadores.values());
				Collections.sort(puntajes,Collections.reverseOrder());
				if(puntajes.get(0)>puntajes.get(1))
					for(Jugador jugador :puntajesGanadores.keySet()) {
						if(puntajesGanadores.get(jugador)==puntajes.get(0))
							return jugador;
					}
				else
					return null;
			}
		}
		return null;
	}

	private void sumarPuntos() {
		List<Jugador> perdedores = new ArrayList<Jugador>(0);
		int mayorcantidadCartas = 0;
		for (Jugador jugador : super.getJugadores()) {
			if (jugador.getCartas().isEmpty()) {
				int puntaje = puntos.get(jugador);
				puntos.put(jugador, puntaje + 3);
			} else {
				perdedores.add(jugador);
				if (mayorcantidadCartas < jugador.getCartas().size()) {
					mayorcantidadCartas = jugador.getCartas().size();
				}
			}

		}

		for (Jugador jugador : perdedores) {
			int puntaje = puntos.get(jugador);
			if (jugador.getCartas().size() == mayorcantidadCartas) {
				puntos.put(jugador, puntaje + 0);
			} else {
				puntos.put(jugador, puntaje + 1);
			}
		}
	}
	
	private void mostrarPuntajes() {
		System.out.println("Puntajes: ");
		for(Jugador jugador: puntos.keySet()) {
			int puntaje=puntos.get(jugador);
			System.out.println(jugador.getNombre()+" ----------------------> " + puntaje + " ptos");
		}
	}
	
	@Override
	public void mostrarGanador() {
		System.out.println("El Ganador es:----------------------> " + this.campeon.getNombre());
	}

}
