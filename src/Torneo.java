
public class Torneo {
	// int MAX = 30;
	public static int[][] encuentros = new int[50][50]; // vector de encuentros entre los [jugadores][días]
	public static int n=2; // caso base
	public static boolean traza;
	public static boolean ayuda;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// n es la cantidad de jugadores
//		if (args[0] == "-t") {
//			traza = true;
//		}else if (args[0] == "-h") {
//			ayuda = true;
//		}
//		else {
			n= Integer.parseInt(args[0]);
//		}
		
		System.out.println("Número de jugadores: " + n);
		System.out.println();
		System.out.println();
		tablaTorneo(n);
		System.out.println();
		System.out.println();
		tablaPantalla(n);
	}

	private static void tablaPantalla(int jugadores) {
		// TODO Auto-generated method stub

		int dias, i, j;
		/*
		 * Si n es par la cantidad de dias es n-1, si es impar la cantidad de dias es n
		 * y hay día de descanso para los jugadores
		 */

		if (jugadores % 2 == 0) {
			dias = jugadores - 1;
		} else {
			dias = jugadores;
		}
		
		
		System.out.println("Partidos del Torneo de Tenis para " + jugadores + " jugadores en " + dias + " jornadas");
		System.out.println();
		
		System.out.print("[ JUG ]");		
		for (i = 0; i < dias; i++) {
			if (i + 1 < 10) {
				System.out.print("[Dia " + (i + 1) + " ]");
			} else {
				System.out.print("[Dia " + (i + 1) + "]");
			}
		}
		System.out.println();

		// Impresión de valores
		if (jugadores % 2 != 0) {
			for (i = 0; i < dias; i++) {
				if (i + 1 < 10) {
					System.out.print("[  J" + (i + 1) + " ]");
				} else {
					System.out.print("[ J" + (i + 1) + " ]");
				}
				
				for (j = 0; j < dias; j++) {
					if (encuentros[i][j] == 0) {
						System.out.print("[   -  ]");
					} else if (encuentros[i][j] < 10) {
						System.out.print("[   " + encuentros[i][j] + "  ]");
					} else {
						System.out.print("[  " + encuentros[i][j] + "  ]");
					}
				}
				System.out.println();
			}
		} else {
			System.out.println();
			for (i = 0; i < (dias+1); i++) {
				if (i + 1 < 10) {
					System.out.print("[  J" + (i + 1) + " ]");
				} else {
					System.out.print("[ J" + (i + 1) + " ]");
				}
				for (j = 0; j < dias; j++) {
					if (encuentros[i][j] == 0) {
						System.out.print("[   -  ]");
					} else if (encuentros[i][j] < 10) {
						System.out.print("[   " + encuentros[i][j] + "  ]");
					} else {
						System.out.print("[  " + encuentros[i][j] + "  ]");
					}
				}
				System.out.println();
			}
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
		System.out.println("Traza nº de jugadores " + n);

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
			/* Llenado del cuadrante inferior derecho */
			for (jugador = n / 2; jugador < n; jugador++) {
				for (dia = n / 2 - 1; dia < n - 1; dia++) {
					encuentros[jugador][dia] = (jugador + 1) - (dia + 1);
					if (encuentros[jugador][dia] <= 0) {
						encuentros[jugador][dia] = encuentros[jugador][dia] + (n / 2);
					}
				}
			}
			/* Llenado del cuadrante inferior izquierdo */
			for (jugador = n / 2; jugador < n; jugador++) {
				for (dia = 0; dia < n / 2 - 1; dia++) {
					encuentros[jugador][dia] = encuentros[jugador - (n / 2)][dia] + (n / 2);
				}
			}

		} else if (n % 2 == 0) {
			/**
			 * Si no es múltiplo de 2 pero el número de jugadores es par como el 6, 10, 12,
			 * 14, etc.
			 */

			/* Dividimos hasta llegar al caso base */
			tablaTorneo(n / 2);
			/* Llenado del cuadrante superior derecho */
			for (jugador = 0; jugador < n / 2; jugador++) {
				for (dia = n / 2; dia < n - 1; dia++) {
					encuentros[jugador][dia] = (jugador + 1) + (dia + 1);
					if (encuentros[jugador][dia] > n) {
						encuentros[jugador][dia] = encuentros[jugador][dia] - (n / 2);
					}
				}
			}
			/* Llenado del cuadrante inferior derecho */
			for (jugador = n / 2; jugador < n; jugador++) {
				for (dia = n / 2; dia < n - 1; dia++) {
					encuentros[jugador][dia] = (jugador + 1) - (dia + 1);
					if (encuentros[jugador][dia] <= 0) {
						encuentros[jugador][dia] = encuentros[jugador][dia] + (n / 2);
					}
				}
			}
			/* Llenado del cuadrante inferior izquierdo */
			for (jugador = n / 2; jugador < n; jugador++) {
				for (dia = 0; dia < n / 2; dia++) {
					if (encuentros[jugador - (n / 2)][dia] != 0) {
						encuentros[jugador][dia] = encuentros[jugador - (n / 2)][dia] + (n / 2);
					}
				}
			}
			/* Llenado del cuadrante superior izquierdo, sustituimos los valores de 0 */
			for (jugador = 0; jugador < n / 2; jugador++) {
				for (dia = 0; dia < (n / 2); dia++) {
					if (encuentros[jugador][dia] == 0) {
						encuentros[jugador][dia] = (jugador + 1) + (n / 2);
					}
				}
			}
			/* Llenado del cuadrante inferior izquierdo, sustituimos los valores de 0 */
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
			/*
			 * Eliminamos los valroes excedentes creados por llamar a la función con n+1
			 * estos valores excedentes son los días de descanso cuando la cantidad de
			 * jugadores es impar
			 */
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
