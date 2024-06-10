package TPE.src;

import TPE.src.utils.CSVReader;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class MainGreedy {

    public static void main(String[] args) {
        CSVReader readerGreedy = new CSVReader();
        String pathProcesadores = "TPE/src/datasets/Procesadores.csv";
        String pathTareas = "TPE/src/datasets/Tareas.csv";

        LinkedList<Procesador> procesadores = readerGreedy.readProcessorsBack(pathProcesadores);
        LinkedList<Tarea> tareas = readerGreedy.readTasksGreedy(pathTareas);

        Greedy g1 = new Greedy(procesadores);

        Solucion s1 = g1.greedy(procesadores, tareas, 60);
        System.out.println("-- PRUEBA GREEDY --");
        System.out.println(s1);
        System.out.print("Tiempo maximo de ejecucion: ");
        System.out.println(s1.getTiempoEjecucionTareas());
        System.out.print("Métrica para analizar el costo de la solución (cantidad de estados generados): ");
        System.out.println(g1.getCantEstados());
    }
}
