package TPE.src;

import java.util.Collections;
import java.util.LinkedList;

public class Greedy {
    private Solucion solucion;
    private int cantEstados;

    public Greedy(LinkedList<Procesador> procesadores){
        this.solucion = new Solucion(procesadores);
        this.cantEstados = 0;
    }

    public int getCantEstados(){
        return this.cantEstados;
    }

    /**
     * Técnica Greedy:
     * Con este algoritmo optimizamos la asignación de tareas a procesadores, priorizando minimizar el tiempo de
     * ejecución. Primero, ordena las tareas por tiempo de ejecución ascendente. Luego, en un bucle mientras queden
     * tareas, selecciona la tarea con menor tiempo y busca el procesador óptimo, que será el que tenga el menor
     * tiempo de ejecución hasta el momento.
     * Si encuentra un procesador adecuado que cumpla con las restricciones, asigna la tarea a ese procesador y la
     * elimina de la lista de tareas, si no, devuelve null indicando que no se puede completar la asignación y
     * terminando el bucle ya que no fue posible asignar una de las tareas.
     * El proceso se repite hasta asignar todas las tareas, construyendo así la solución que parece mejor en ese momento.
     */
    public Solucion greedy(LinkedList<Tarea>tareas, int tiempoMaxNoRefrigerado){
        // Se ordenan las tareas por tiempo de ejecucion ascendente
        Collections.sort(tareas);

        //Si tareas esta vacia, significa que las asigne todas
        while (!tareas.isEmpty()){
            Tarea mejorTarea = tareas.get(0);
            Procesador procesadorOptimo = this.getProcesadorOptimo(mejorTarea, tiempoMaxNoRefrigerado);
            if (procesadorOptimo != null) {
                this.solucion.asignarTarea(procesadorOptimo, mejorTarea);
                tareas.remove(mejorTarea);
            } else {
                // No ha sido posible una asignacion completa de tareas
                return null;
            }
            this.cantEstados++;
        }

        return solucion;
    }

    private Procesador getProcesadorOptimo(Tarea t, int tiempoMaxNoRefrigerado){
        Procesador procesadorOptimo = null;
        int menorTiempo = Integer.MAX_VALUE;
        for (Procesador p : this.solucion.getSolucion().keySet()){
            int tiempoActual = this.solucion.getTiempoEjecucionTareas(p);
            if (this.solucion.esAsignable(p, t, tiempoMaxNoRefrigerado) && tiempoActual < menorTiempo){
                menorTiempo = tiempoActual;
                procesadorOptimo = p;
            }
        }
        return procesadorOptimo;
    }

}
