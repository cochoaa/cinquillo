package juegos.cinquillo.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import juegos.cinquillo.tipo.TipoJugador;

public class Jugador {
	private String nombre;
	private TipoJugador tipo;
	private List<Carta> cartas;
	
	public Jugador(String nombre,TipoJugador tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.cartas=new ArrayList<Carta>(0);
	}
	
	public Carta lanzarCarta(List<Carta> posiblesCartasTablero) {
		List<Carta> cartasALanzar= getPosiblesCartasJugador(posiblesCartasTablero);
		int maximo=cartasALanzar.size();
		int indice=-1;
		do {
			if(tipo.equals(TipoJugador.HUMANO)) {
				System.out.println("Seleccione el una alternativa");
				imprimir(cartasALanzar);
				indice=seleccionarCartaHumano();
			}else {
				indice=new Random().nextInt(maximo);
			}
		}
		while(indice>=0 && indice <maximo);
		Carta carta=cartas.remove(indice);
		return carta;
		
	}
	private int seleccionarCartaHumano() {
		@SuppressWarnings("resource")
		Scanner scanner= new Scanner(System.in);
		String indice=scanner.nextLine();
		try {
			return Integer.parseInt(indice);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	
	private void imprimir(List<Carta> cartas) {
		int i=1;
		for(Carta carta: cartas) {
			System.out.println(i+" : "+carta.toString());
		}
	}
	
	private List<Carta> getPosiblesCartasJugador(List<Carta> posiblesCartasTablero){
		List<Carta> posiblesCartasJugador= new ArrayList<Carta>(0);
		for(Carta posibleCartaTablero:posiblesCartasTablero) {
			if(cartas.contains(posibleCartaTablero)) {
				posiblesCartasJugador.add(posibleCartaTablero);
			}
		}
		return posiblesCartasJugador;
	}
	
	public boolean tieneCartasParaLanzar(List<Carta> posiblesCartasTablero) {
		List<Carta> cartasALanzar= getPosiblesCartasJugador(posiblesCartasTablero);
		return !cartasALanzar.isEmpty();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoJugador getTipo() {
		return tipo;
	}

	public void setTipo(TipoJugador tipo) {
		this.tipo = tipo;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
}
