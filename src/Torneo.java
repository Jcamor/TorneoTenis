import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.FileInputStream;

import java.io.InputStreamReader;

public class Torneo {
	// MAXIMO JUGADORES = 50;
	public static int[][] encuentros = new int[50][50]; // [jugadores][días]
	public static int n = 2; // caso base
	public static boolean traza = false;
	public static boolean correcto = false;
	public static boolean jugadoresNombre = false;
	public static String nombreArchivo;
	public static String[] nombreJugadores;

	public static void main(String[] args) {

		switch (args.length) {
		case 0:
			// imprimirAyuda();
			break;
		case 1:
			if (args[0].equals("-h")) {
				// imprimirAyuda();
				break;
			} else {
				n = Integer.parseInt(args[0]);
				correcto = true;
				break;
			}

		case 2:
			if (args[0].equals("-t")) {
				traza = true;
				n = Integer.parseInt(args[1]);
				correcto = true;
				break;
			} else {
				n = Integer.parseInt(args[0]);
				nombreArchivo = args[1];
				jugadoresNombre = true;
				try {
					jugadores(nombreArchivo, n);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				correcto = true;
				break;
			}
		case 3:
			if (args[0].equals("-t")) {
				traza = true;
				n = Integer.parseInt(args[1]);
				nombreArchivo = args[2];
				try {
					jugadores(nombreArchivo, n);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jugadoresNombre = true;
				correcto = true;
				break;
			}

		}

		if (correcto) {
			System.out.println("Número de jugadores: " + n);
			System.out.println();
			System.out.println();
			tablaTorneo(n);
			System.out.println();
			System.out.println();
			TablaPantalla tabla = new TablaPantalla();
			tabla.pantalla(n, jugadoresNombre, nombreJugadores, encuentros);
		} else {
			Ayuda ayuda = new Ayuda(correcto);
			ayuda.imprimirAyuda();
		}
	}

	public static void jugadores(String archivo, int n) throws FileNotFoundException, IOException {
		
		nombreJugadores = new String[n + 1];
		
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));
			String nombre;
			int i = 1;
			while ((nombre = buffer.readLine()) != null & i <= n) {
				nombreJugadores[i] = nombre;
				i++;
			}
			buffer.close();
		} catch (IOException ex) {
			/* Error en el primer parámetro */
			System.err.println("No se puede abrir el fichero jugadores para su lectura.");
		}
		
	}
	
	// Verificar que n es potencia de 2
	static boolean potenciaDos(int n) {
		int potencia = 2;

		while (potencia <= n) {
			if (n == potencia) {
				return true;
			}
			potencia *= 2;

		}
		return false;
	}

	static // Llena la tabla de encuentros entre jugadores
	// @param n (cantidad de jugadores)
	void tablaTorneo(int n) {
		int jugador;
		int dia;
		if (traza) {
			System.out.println("Traza nº de jugadores " + n);
		}
		// caso base
		if (n == 2) {
			encuentros[0][0] = 2;
			encuentros[1][0] = 1;
		} else if (potenciaDos(n)) { // primero analizamos el caso cuando es potencia de 2

			// dividimos entre 2 hasta llegar al caso base
			tablaTorneo(n / 2);
			/*
			 * Una vez que el caso base ha sido llenado, se llena el cuadrante superior
			 * derecho
			 */
			for (jugador = 0; jugador < n / 2; jugador++) {
				for (dia = n / 2 - 1; dia < n - 1; dia++) {
					encuentros[jugador][dia] = (jugador + 1) + (dia + 1);
					if (encuentros[jugador][dia] > n) {
						encuentros[jugador][dia] = encuentros[jugador][dia] - (n / 2);
					}
				}
			}
			/* cuadrante inferior derecho */
			for (jugador = n / 2; jugador < n; jugador++) {
				for (dia = n / 2 - 1; dia < n - 1; dia++) {
					encuentros[jugador][dia] = (jugador + 1) - (dia + 1);
					if (encuentros[jugador][dia] <= 0) {
						encuentros[jugador][dia] = encuentros[jugador][dia] + (n / 2);
					}
				}
			}
			/* cuadrante inferior izquierdo */
			for (jugador = n / 2; jugador < n; jugador++) {
				for (dia = 0; dia < n / 2 - 1; dia++) {
					encuentros[jugador][dia] = encuentros[jugador - (n / 2)][dia] + (n / 2);
				}
			}

		} else if (n % 2 == 0) {
			// Si numero de jugadores es PAR

			/* Dividimos hasta llegar al caso base */
			tablaTorneo(n / 2);
			/* cuadrante superior derecho */
			for (jugador = 0; jugador < n / 2; jugador++) {
				for (dia = n / 2; dia < n - 1; dia++) {
					encuentros[jugador][dia] = (jugador + 1) + (dia + 1);
					if (encuentros[jugador][dia] > n) {
						encuentros[jugador][dia] = encuentros[jugador][dia] - (n / 2);
					}
				}
			}
			/* cuadrante inferior derecho */
			for (jugador = n / 2; jugador < n; jugador++) {
				for (dia = n / 2; dia < n - 1; dia++) {
					encuentros[jugador][dia] = (jugador + 1) - (dia + 1);
					if (encuentros[jugador][dia] <= 0) {
						encuentros[jugador][dia] = encuentros[jugador][dia] + (n / 2);
					}
				}
			}
			/* cuadrante inferior izquierdo */
			for (jugador = n / 2; jugador < n; jugador++) {
				for (dia = 0; dia < n / 2; dia++) {
					if (encuentros[jugador - (n / 2)][dia] != 0) {
						encuentros[jugador][dia] = encuentros[jugador - (n / 2)][dia] + (n / 2);
					}
				}
			}
			/* cuadrante superior izquierdo, sustituimos por 0 */
			for (jugador = 0; jugador < n / 2; jugador++) {
				for (dia = 0; dia < (n / 2); dia++) {
					if (encuentros[jugador][dia] == 0) {
						encuentros[jugador][dia] = (jugador + 1) + (n / 2);
					}
				}
			}
			/* cuadrante inferior izquierdo, sustituimos por 0 */
			for (jugador = (n / 2); jugador < n; jugador++) {
				for (dia = 0; dia < (n / 2); dia++) {
					if (encuentros[jugador][dia] == 0) {
						encuentros[jugador][dia] = (jugador + 1) - (n / 2);
					}
				}
			}
		}
		// Si el número de jugadores es IMPAR
		else {
			/*
			 * Si n es impar, le sumamos 1 y lo volvemos par, para que en la llamada
			 * recursiva lleguemos nuevamente al caso base
			 */
			tablaTorneo(n + 1);
			
			 // Eliminaos los dias de descando cuando es impar el número de jugadores
			 
			for (jugador = 0; jugador < n; jugador++) {
				for (dia = 0; dia < n; dia++) {
					if (encuentros[jugador][dia] == (n + 1)) {
						encuentros[jugador][dia] = 0;
					}
				}
			}
		}

	}
}
