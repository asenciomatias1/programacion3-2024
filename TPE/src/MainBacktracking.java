package TPE.src;

import TPE.src.utils.CSVReader;

import java.util.LinkedList;
import java.util.Stack;

public class MainBacktracking {
    public static void main(String[] args) {
        CSVReader readerBack = new CSVReader();
        String pathProcesadores = "TPE/src/datasets/Procesadores.csv";
        String pathTareas = "TPE/src/datasets/Tareas.csv";

        LinkedList<Procesador> procesadores = readerBack.readProcessorsBack(pathProcesadores);
        Stack<Tarea> tareas = readerBack.readTasksBack(pathTareas);

        Backtracking b1 = new Backtracking(procesadores);
        Solucion s1 = b1.back(procesadores, tareas, 60);
        System.out.println("-- PRUEBA BACKTRACKING --");
        System.out.println(s1);
        System.out.print("Tiempo maximo de ejecucion: ");
        System.out.println(s1.getTiempo());
        System.out.print("Métrica para analizar el costo de la solución (cantidad de estados generados): ");
        System.out.println(b1.getCantEstados());
    }
}
