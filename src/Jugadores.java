import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Jugadores {

	public static void jugadoresSet(String archivo, int n) throws FileNotFoundException, IOException {
        String nombre;
        String nombreJugadores[];
        nombreJugadores = new String[n+1];
        
        FileReader fichero = new FileReader(archivo);
        BufferedReader buffer = new BufferedReader(fichero);
        int i=1;
        while((nombre = buffer.readLine())!=null) {
        	nombreJugadores[i] = nombre;
        	
            System.out.print(nombre);
            System.out.println("  i="+i);
            i++;
        }
        buffer.close();
    }

}
