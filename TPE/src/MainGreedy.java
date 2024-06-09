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
        Collections.sort(tareas); // Se ordenan las tareas por tiempo de ejecucion ascendente

        Greedy g1 = new Greedy();

        Solucion s1 = g1.greedy(procesadores, tareas);
    }
}
