package juegos.cinquillo.util;

import java.util.ArrayList;
import java.util.Collection;

public class ColaCircular<T> extends ArrayList<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColaCircular() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColaCircular(Collection<? extends T> arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ColaCircular(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public void encolar(T dato) {
		if (dato != null) {
			this.add(dato);
		} else {
			System.out.println("Introduzca un dato no nulo");
		}
	}

	public T siguiente() {
		T siguiente = frente();
		desencolar();
		encolar(siguiente);
		return siguiente;
	}

	// se elimina el elemento frontal de la cola, es decir, el primer elemento que
	// entró.
	private void desencolar() {
		if (this.size() > 0) {
			this.remove(0);
		}
	}

	// se devuelve el elemento frontal de la cola, es decir, el primer elemento que
	// entró.
	private T frente() {
		T datoAuxiliar = null;
		if (this.size() > 0) {
			datoAuxiliar = this.get(0);
		}
		return datoAuxiliar;
	}

	// devuelve cierto si la pila está vacía o falso en caso contrario (empty).
	public boolean vacia() {
		return this.isEmpty();
	}
}
