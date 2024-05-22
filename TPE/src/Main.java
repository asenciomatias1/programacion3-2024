package TPE.src;

import TPE.src.Servicios;
import TPE.src.utils.CSVReader;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");

	}
}
