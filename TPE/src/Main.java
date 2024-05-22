package TPE.src;

import TPE.src.Servicios;
import TPE.src.utils.CSVReader;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("C://Users/vic/Desktop/Prog 3/programacion3-2024/TPE/src/datasets/Procesadores.csv", "C://Users/vic/Desktop/Prog 3/programacion3-2024/TPE/src/datasets/Tareas.csv");

		System.out.println(servicios.servicio1("T1"));
		System.out.println(servicios.servicio2(false));
	}
}
