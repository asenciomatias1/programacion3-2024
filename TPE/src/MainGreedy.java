package TPE.src;

import TPE.src.utils.CSVReader;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class MainGreedy {

    public static void main(String[] args) {
        CSVReader readerGreedy = new CSVReader();
        String pathProcesadores = "TPE/src/datasets/Procesadores2.csv";
        String pathTareas = "TPE/src/datasets/Tareas2.csv";

        LinkedList<Procesador> procesadores = readerGreedy.readProcessorsBack(pathProcesadores);
        LinkedList<Tarea> tareas = readerGreedy.readTasksGreedy(pathTareas);

        Greedy g1 = new Greedy(procesadores);

        Solucion s1 = g1.greedy(procesadores, tareas, 60);
        System.out.println("-- PRUEBA GREEDY --");
        System.out.println(s1);
        System.out.print("Tiempo maximo de ejecucion: ");
        System.out.println(s1.getTiempo());
        System.out.print("Métrica para analizar el costo de la solución (cantidad de estados generados): ");
        System.out.println(g1.getCantEstados());
    }
}
