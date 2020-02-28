package juegos.cinquillo.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import juegos.cinquillo.tipo.TipoJugador;
import juegos.cinquillo.util.Metodo;

public class Jugador {
	private int id;
	private String nombre;
	private TipoJugador tipo;
	private List<Carta> cartas;
	
	public Jugador(int id,String nombre,TipoJugador tipo) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.cartas=new ArrayList<Carta>(0);
	}
	
	public Carta lanzarCarta(List<Carta> posiblesCartasTablero) {
		List<Carta> cartasALanzar= getPosiblesCartasJugador(posiblesCartasTablero);
		int maximo=cartasALanzar.size();
		int indice=-1;
		imprimir(cartasALanzar);
		if(this.tipo.equals(TipoJugador.HUMANO)) {
			boolean esIndiceCorrecto=false;
			while(!esIndiceCorrecto) {
				System.out.print("Seleccione una carta: ");
				indice=seleccionarCartaHumano();
				if(indice-1>=0 && indice-1 <maximo) {
					esIndiceCorrecto=true;
					indice=indice-1;
				}
			}
			
		}else {
			Metodo.pausa(1);
			indice=new Random().nextInt(maximo);
		}
		Carta cartaSeleccionada=cartasALanzar.get(indice);
		eliminarCarta(cartaSeleccionada);
		return cartaSeleccionada;
		
	}
	
	private  void eliminarCarta(Carta  carta) {
		List<Carta> cartasTemp= new ArrayList<Carta>(0);
		for (Carta c : this.cartas) {
			if(!c.equals(carta))
				cartasTemp.add(c);
		}
		this.cartas=cartasTemp;
	}
	
	public String getCartasString() {
		return this.cartas.toString();
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
		System.out.println("Elija una opcion:");
		for(Carta carta: cartas) {
			System.out.println(i+" : "+carta.toString());
			i=i+1;
		}
	}
	
	private List<Carta> getPosiblesCartasJugador(List<Carta> posiblesCartasTablero){
		List<Carta> posiblesCartasJugador= new ArrayList<Carta>(0);
		for(Carta posibleCartaTablero:posiblesCartasTablero) {
			if(this.cartas.contains(posibleCartaTablero)) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		Jugador jugador= (Jugador)o;
		if(this.id==jugador.getId())
			return true;
		else
		    return false;
	}
}
