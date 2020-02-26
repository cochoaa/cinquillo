package juegos.cinquillo;

import juegos.cinquillo.modelo.Jugador;
import juegos.cinquillo.tipo.TipoJugador;
import juegos.cinquillo.util.ColaCircular;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int cantidad=3;
    	System.out.println( "Hello World!" );
        
        ColaCircular<Jugador> jugadores=new ColaCircular<Jugador>();
        for(int i=0;i<cantidad;i++) {
        	jugadores.encolar(new Jugador(i+"",TipoJugador.MAQUINA));
        }
        
        for(int i=0;i<cantidad*3;i++) {
        	Jugador j=jugadores.siguiente();
        	System.out.println(j.getNombre());
        }
        
    }
}
