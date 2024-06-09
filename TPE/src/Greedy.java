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

    public Solucion greedy(LinkedList<Procesador>procesadores, LinkedList<Tarea>tareas, int tiempoMaxNoRefrigerado){
        Collections.sort(tareas); // Se ordenan las tareas por tiempo de ejecucion ascendente

        while (!tareas.isEmpty()){  //Si tareas esta vacia, significa que las asigne todas
            int tiempoMenor = Integer.MAX_VALUE; //Criterio de seleccion: elijo la tarea de menor tiempo
            Tarea mejorTarea = tareas.get(0);
            Procesador procesadorOptimo = this.getProcesadorOptimo(mejorTarea, tiempoMaxNoRefrigerado);
            if (procesadorOptimo != null) {
                this.solucion.asignarTarea(procesadorOptimo, mejorTarea);
                tareas.remove(mejorTarea);
            } else {
                return null;
            }
            this.cantEstados++;
        }//Al salir del while, deberia tener la solucion armada

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

    private void ordenarProcesadoresPorRefrigeracion(LinkedList<Procesador> procesadores){
        LinkedList<Procesador> refrigerados = new LinkedList<>();
        LinkedList<Procesador> noRefrigerados = new LinkedList<>();

        for (Procesador p : procesadores){
            if (p.esRefrigerado()){
                refrigerados.add(p);
            }else {
                noRefrigerados.add(p);
            }
        }

        refrigerados.addAll(noRefrigerados);
        procesadores = refrigerados;
    }
}
