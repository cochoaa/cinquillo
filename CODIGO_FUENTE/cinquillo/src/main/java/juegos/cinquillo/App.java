package juegos.cinquillo;

import java.util.Scanner;

import juegos.cinquillo.modelo.Juego;
import juegos.cinquillo.modo.Simple;
import juegos.cinquillo.modo.Torneo;

/**
 * Hello world!
 *
 */
public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int numeroJugadoresHumanos = 1;
		int numeroJugadoresMaquina = 1;
		int modoJuego=1;
		boolean seguirJugando = true;
		Juego juego=null;
		do {
			System.out.print("Numero de jugadores humanos: ");
			Scanner scan1=new Scanner(System.in);
			numeroJugadoresHumanos=scan1.nextInt();
			System.out.print("Numero de jugadores PC: ");
			Scanner scan2=new Scanner(System.in);
			numeroJugadoresMaquina=scan2.nextInt();
			if(numeroJugadoresHumanos+numeroJugadoresMaquina<2) {
				System.out.print("Deben haber mas de 2 jugadores ...");
				continue;
			}
			System.out.print("Ingrese el modo de Juego: Simple (1) / Torneo (2): ");
			Scanner scan3=new Scanner(System.in);
			modoJuego=scan3.nextInt();
			if(modoJuego==1) {
				System.out.println("====================Cinquillo: Modo Partida Simple Juego =============================");
				System.out.println();
				juego = new Simple(numeroJugadoresHumanos, numeroJugadoresMaquina);
			}else {
				System.out.println("=============================Cinquillo: Modo Torneo =============================");
				System.out.println();
				juego = new Torneo(numeroJugadoresHumanos, numeroJugadoresMaquina);
			}
			juego.iniciar();
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
