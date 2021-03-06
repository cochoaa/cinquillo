package juegos.cinquillo.modelo;

import juegos.cinquillo.tipo.Palo;

public class Carta {
	private Palo palo;
	private int numero;
	public Carta(Palo palo, int numero) {
		super();
		this.palo = palo;
		this.numero = numero;
	}
	public Palo getPalo() {
		return palo;
	}
	public void setPalo(Palo palo) {
		this.palo = palo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@Override
	public boolean equals(Object o) {
		Carta carta= (Carta)o;
		if(this.numero==carta.getNumero() && this.palo.equals(carta.getPalo()))
			return true;
		else
		    return false;
	}
	
	@Override
	public String toString() {
		return this.numero+" "+this.palo.name();
	}
	
}
