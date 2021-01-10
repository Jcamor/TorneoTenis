
public class Ayuda {
	boolean correcto;

	public Ayuda(boolean correcto) {
		super();
		this.correcto = correcto;
	}

	public void imprimirAyuda() {
		System.out.println("SINTAXIS: Torneo [-t][-h] n [fichero entrada]");
		System.out.println("         -t       Traza la parametrización de cada invocación recursiva");
		System.out.println("         -h       Muestra esta ayuda");
		System.out.println("          n       Numero de jugadores");
		System.out.println("[fichero entrada] Listado de los nombres de los jugadores del torneo");
	}
}
