package TPE.src;

import TPE.src.Servicios;
import TPE.src.utils.CSVReader;

import java.util.LinkedList;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("TPE/src/datasets/Procesadores.csv", "TPE/src/datasets/Tareas.csv");

		System.out.println("-- SERVICIO 1 --");
		System.out.println(servicios.servicio1("T1"));
		System.out.println("-- SERVICIO 2 --");
		System.out.println(servicios.servicio2(false));
		System.out.println("-- SERVICIO 3 --");
		System.out.println(servicios.servicio3(50,100));
		System.out.println("-- FIN SERVICIOS --");

		Procesador p1 = new Procesador("id1", "cod1", true, 2001);
		LinkedList<Tarea> lt = new LinkedList<>();
		Tarea t1 = new Tarea("id1", "nom", 1, 11, false);
		Tarea t2 = new Tarea("id2", "nom", 1, 11, false);
		Tarea t3 = new Tarea("id3", "nom", 1, 11, false);
		lt.add(t1);
		LinkedList<Procesador> lp = new LinkedList<>();
		lp.add(p1);
		Solucion s = new Solucion(lp);
		s.asignarTarea(p1,t1);
		s.asignarTarea(p1,t2);
		s.asignarTarea(p1,t3);
		System.out.println("prueba toString solucion");
		System.out.println(s);
	}
}
