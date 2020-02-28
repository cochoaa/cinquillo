package juegos.cinquillo.util;

public class Metodo {

	public static void pausa(int segundos) {
		try {
			Thread.sleep(segundos*1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}
