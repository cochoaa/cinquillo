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
		prepararTablero();
		configurarJugadores();
		repartirCartas();
		
	}

	private void configurarJugadores() {
		this.jugadores=new ColaCircular<Jugador>(0);
		int id=1;
		for(int i=1;i<=this.numeroJugadoresHumanos;i++) {
			jugadores.encolar(new Jugador(id,"Player "+i,TipoJugador.HUMANO));
			id=id+1;
		}
		
		for(int i=1;i<=this.numeroJugadoresMaquina;i++) {
			jugadores.encolar(new Jugador(id,"PC "+i,TipoJugador.MAQUINA));
			id=id+1;
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
	
	private boolean isGanador(Jugador jugador) {
		return jugador.getCartas().isEmpty();
	}
	
	private List<Carta> obtenerPosiblesCartasParaLanzar(){
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
	
	protected void prepararTablero() {
		this.tablero=new Tablero();
	}
	
	protected void repartirCartas() {
		int numeroJugadores=this.numeroJugadoresHumanos+this.numeroJugadoresMaquina;
		List<Carta> cartas=barajarCartas();
		int cantidadCartasJugador=cartas.size()/numeroJugadores;
		for(int i=0;i<numeroJugadores;i++) {
			Jugador j=jugadores.siguiente();
			j.setCartas(cartas.subList(i*cantidadCartasJugador,(i+1)*cantidadCartasJugador));
		}
	}
	
	protected ColaCircular<Jugador> getJugadores() {
		return jugadores;
	}


	protected void iniciarPartida() {
			boolean hayGanador = false;
			repartirCartas();
			/******************Inicio del Juego*************************/
			while (!hayGanador) {
				
				this.tablero.mostrarTablero();
				Jugador player =siguienteTurno();
				System.out.println("Turno: "+player.getNombre());
				System.out.println("Tu baraja: "+player.getCartasString());
				List<Carta> posiblesCartasTablero=obtenerPosiblesCartasParaLanzar();
				if(player.tieneCartasParaLanzar(posiblesCartasTablero)) {
					Carta carta=player.lanzarCarta(posiblesCartasTablero);
					tablero.agregarCarta(carta);
					System.out.println("Carta Lanzada: "+carta.toString());
				}else {
					System.out.println("Carta Lanzada: Jugador pasa el turno.");
				}
				if (isGanador(player)) {
					hayGanador = true;
				}
			}
	}
	
	private Jugador siguienteTurno() {
 		return jugadores.siguiente();
 	}
	
	public void iniciar() {
		iniciarPartida();
	}
	
	public void mostrarGanador() {
		for(Jugador jugador:jugadores) {
			if(isGanador(jugador)) {
				System.out.println("El Ganador es:----------------------> " + jugador.getNombre());
				return;
			}
		}
	}
	
	
	
}
