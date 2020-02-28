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
	static int numeroJugadoresHumanos = 1;
	static int numeroJugadoresMaquina = 1;
	static int modoJuego=1;
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		boolean seguirJugando = true;
		Juego juego=null;
		do {
			numeroJugadoresHumanos=ingresarParametro("Numero de jugadores humanos: ");
			numeroJugadoresMaquina=ingresarParametro("Numero de jugadores PC: ");
			if(numeroJugadoresHumanos+numeroJugadoresMaquina<2) {
				System.out.print("Deben haber mas de 2 jugadores ...");
				continue;
			}
			modoJuego=ingresarParametro("Ingrese el modo de Juego: Simple (1) / Torneo (2): ");
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
			
			System.out.print("Desea seguir: ");
			Scanner sc = new Scanner(System.in);
			seguirJugando = sc.hasNext();
			System.out.println("Respuesta:"+seguirJugando);
		} while (seguirJugando);
		
	}
	
	@SuppressWarnings("resource")
	private static int  ingresarParametro(String mensje) {
		System.out.print(mensje);
		return new Scanner(System.in).nextInt();
	}

}
