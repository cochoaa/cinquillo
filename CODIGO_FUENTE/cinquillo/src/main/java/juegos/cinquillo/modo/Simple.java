package juegos.cinquillo.modo;

import juegos.cinquillo.modelo.Juego;

public class Simple extends Juego {

	public Simple(int numeroJugadoresHumanos, int numeroJugadoresMaquina) {
		super(numeroJugadoresHumanos, numeroJugadoresMaquina);
	}
	
	@Override
	public void iniciar() {
		super.iniciarPartida();
	}
}
