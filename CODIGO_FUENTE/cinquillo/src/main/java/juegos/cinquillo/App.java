package juegos.cinquillo;

import java.util.Scanner;

import juegos.cinquillo.modelo.Juego;

/**
 * Hello world!
 *
 */
public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int numeroJugadoresHumanos = 1;
		int numeroJugadoresMaquina = 1;
		boolean seguirJugando = true;
		
		do {
			Juego juego = new Juego(numeroJugadoresHumanos, numeroJugadoresMaquina);
			juego.iniciarPartida();
			juego.mostrarGanador();
			/*
			boolean hayGanador = false;
			
			juego.repartirCartas();
			Tablero tablero = juego.getTablero();*/
			/******************Inicio del Juego*************************/
			/*while (!hayGanador) {
				
				tablero.mostrarTablero();
				Jugador player = juego.siguienteTurno();
				System.out.println("Turno: "+player.getNombre());
				System.out.println("Tu baraja: "+player.getCartasString());
				List<Carta> posiblesCartasTablero=juego.obtenerPosiblesCartasParaLanzar();
				if(player.tieneCartasParaLanzar(posiblesCartasTablero)) {
					Carta carta=player.lanzarCarta(posiblesCartasTablero);
					tablero.agregarCarta(carta);
					System.out.println("Carta Lanzada: "+carta.toString());
				}else {
					System.out.println("Carta Lanzada: Jugador pasa el turno.");
				}
				if (juego.isGanador(player)) {
					System.out.println("El Ganador es:" + player.getNombre());
					hayGanador = true;
				}
			}*/
			/******************Fin del Juego*************************/

			System.out.print("Desea seguir: ");
			Scanner sc = new Scanner(System.in);
			seguirJugando = sc.hasNext();
			System.out.println("Respuesta:"+seguirJugando);
		} while (seguirJugando);

	}
}
