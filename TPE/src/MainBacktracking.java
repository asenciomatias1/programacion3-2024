package TPE.src;

import TPE.src.utils.CSVReader;

import java.util.LinkedList;
import java.util.Stack;

public class MainBacktracking {
    public static void main(String[] args) {
        CSVReader readerBack = new CSVReader();
        String pathProcesadores = "TPE/src/datasets/Procesadores.csv";
        String pathTareas = "TPE/src/datasets/Tareas.csv";

        LinkedList<Procesador> procesadores = readerBack.readProcessors(pathProcesadores);
        Stack<Tarea> tareas = readerBack.readTasksBack(pathTareas);

        Backtracking b1 = new Backtracking(procesadores);
        Solucion s1 = b1.back(procesadores, tareas, 50);
        System.out.println("-- PRUEBA BACKTRACKING --");
        if (s1 != null){
            System.out.println(s1);
        } else {
            System.out.println("Tiempo máximo de ejecución: 0. No se encontró solución");
        }
        System.out.print("Métrica para analizar el costo de la solución (cantidad de estados generados): ");
        System.out.println(b1.getCantEstados());
    }
}
