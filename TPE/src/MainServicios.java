package TPE.src;

import java.util.LinkedList;

public class MainServicios {

	public static void main(String args[]) {
		String pathTareas = "TPE/src/datasets/Tareas.csv";
		String pathProcesadores = "TPE/src/datasets/Procesadores.csv";
		Servicios servicios = new Servicios(pathProcesadores, pathTareas);

		// Dado un identificador de tarea obtener toda la información de la tarea asociada
		System.out.println("-- SERVICIO 1 --");
		System.out.println(servicios.servicio1("T1"));

		// Permitir que el usuario decida si quiere ver todas las tareas críticas o no críticas y generar
		// el listado apropiado resultante.
		System.out.println("-- SERVICIO 2 --");
		System.out.println(servicios.servicio2(false));

		// Obtener todas las tareas entre 2 niveles de prioridad indicados.
		System.out.println("-- SERVICIO 3 --");
		System.out.println(servicios.servicio3(50,100));
		System.out.println("-- FIN SERVICIOS --");
	}
}
