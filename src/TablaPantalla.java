
public class TablaPantalla {

	public TablaPantalla() {
		// TODO Auto-generated constructor stub
	}
	public void pantalla(int jugadores, boolean jugadoresNombre, String[] nombreJugadores, int[][] encuentros) {
		// TODO Auto-generated method stub
		int dias, i, j;

		if (jugadores % 2 == 0) {
			dias = jugadores - 1;   //Si jugadores es par los dias son  igual a jugadores - un dia
		} else {
			dias = jugadores;		//Si jugadores es impar lod dias son igual a jugadores hay
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

		if (jugadores % 2 != 0) {
			for (i = 0; i < dias; i++) {
				if (i + 1 < 10) {
					if (jugadoresNombre) {
						System.out.print("[" + nombreJugadores[i + 1] + "]");
					} else {
						System.out.print("[  J" + (i + 1) + " ]");
					}
				} else {
					if (jugadoresNombre) {
						System.out.print("[" + nombreJugadores[i + 1] + "]");
					} else {
						System.out.print("[ J" + (i + 1) + " ]");
					}
				}

				for (j = 0; j < dias; j++) {
					if (encuentros[i][j] == 0) {
						System.out.print("[   -  ]");
					} else if (encuentros[i][j] < 10) {
						if (jugadoresNombre) {
							System.out.print("[" + nombreJugadores[encuentros[i][j]] + "]");
						} else {
							System.out.print("[   " + encuentros[i][j] + "  ]");
						}
					} else {
						if (jugadoresNombre) {
							System.out.print("[" + nombreJugadores[encuentros[i][j]] + "]");
						} else {
							System.out.print("[  " + encuentros[i][j] + "  ]");
						}
					}
				}
				System.out.println();
			}
		} else {
			System.out.println();
			for (i = 0; i < (dias + 1); i++) {
				if (i + 1 < 10) {
					if (jugadoresNombre) {
						System.out.print("[" + nombreJugadores[i + 1] + "]");
					} else {
						System.out.print("[  J" + (i + 1) + " ]");
					}
				} else {
					if (jugadoresNombre) {
						System.out.print("[" + nombreJugadores[i + 1] + "]");
					} else {
						System.out.print("[ J" + (i + 1) + " ]");
					}
				}
				for (j = 0; j < dias; j++) {
					if (encuentros[i][j] == 0) {
						System.out.print("[   -  ]");
					} else if (encuentros[i][j] < 10) {
						if (jugadoresNombre) {
							System.out.print("[" + nombreJugadores[encuentros[i][j]] + "]");
						} else {
							System.out.print("[   " + encuentros[i][j] + "  ]");
						}
					} else {
						if (jugadoresNombre) {
							System.out.print("[" + nombreJugadores[encuentros[i][j]] + "]");
						} else {
							System.out.print("[  " + encuentros[i][j] + "  ]");
						}
					}
				}
				System.out.println();
			}
		}

	}

}
