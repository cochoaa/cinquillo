package juegos.cinquillo.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import juegos.cinquillo.tipo.Palo;

public class Tablero {
	private Map<Palo, List<Carta>> cartas;

	public Tablero() {
		super();
		cartas = new HashMap<Palo, List<Carta>>(4);
		for (Palo palo : Palo.values()) {
			cartas.put(palo, new ArrayList<Carta>(0));
		}
	}

	public Map<Palo, List<Carta>> getCartas() {
		return cartas;
	}

	public void agregarCarta(Carta carta) {
		List<Carta> cartasPalo = cartas.get(carta.getPalo());
		if (cartasPalo.isEmpty() || cartasPalo.get(0).getNumero() < carta.getNumero()) {
			cartasPalo.add(carta);
		} else {
			cartasPalo.add(0, carta);
		}
	}

	private void imprimir() {
		for (Palo palo : Palo.values()) {
			List<Carta> cartasPalo = cartas.get(palo);
			StringBuffer sbuffer = new StringBuffer();
			sbuffer.append(palo.name());
			sbuffer.append(": ");
			sbuffer.append(cartasPalo.toString());
			System.out.println(sbuffer.toString());
		}
	}

	public void mostrarTablero() {
		System.out.println("**************************Tablero***********************");
		imprimir();
		System.out.println("********************************************************");
	}

}
