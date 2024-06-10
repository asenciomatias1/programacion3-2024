package TPE.src;

import TPE.src.utils.CSVReader;

import java.util.Collections;
import java.util.LinkedList;

public class MainGreedy {

    public static void main(String[] args) {
        CSVReader readerGreedy = new CSVReader();
        String pathProcesadores = "TPE/src/datasets/Procesadores.csv";
        String pathTareas = "TPE/src/datasets/Tareas.csv";

        LinkedList<Procesador> procesadores = readerGreedy.readProcessors(pathProcesadores);
        LinkedList<Tarea> tareas = readerGreedy.readTasksGreedy(pathTareas);

        Greedy g1 = new Greedy(procesadores);

        Solucion s1 = g1.greedy(tareas, 60);
        System.out.println("-- PRUEBA GREEDY --");
        if (s1 != null){
            System.out.println(s1);
        } else {
            System.out.println("Tiempo máximo de ejecución: 0. No se encontró solución");
        }
        System.out.print("Métrica para analizar el costo de la solución (cantidad de candidatos considerados): ");
        System.out.println(g1.getCantCandidatos());
    }
}
