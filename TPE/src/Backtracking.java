package TPE.src;

import TPE.src.utils.CSVReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Backtracking {
    private Solucion solucion;

    public Backtracking() {
        this.solucion = new Solucion();
    }

    public Backtracking(LinkedList<Procesador> procesadores) {
        this.solucion = new Solucion(procesadores);
    }

    public Solucion back(LinkedList<Procesador> procesadores, Stack<Tarea> tareas, int tiempoMaxNoRefrigerado){
        Solucion solucionParcial = new Solucion(procesadores);
        this.back(procesadores, tareas, solucionParcial, tiempoMaxNoRefrigerado);
        return this.solucion;
    }

    private void back(LinkedList<Procesador> procesadores, Stack<Tarea> tareas, Solucion solucionParcial,
                      int tiempoMaxNoRefrigerado){
        if (tareas.empty()){
            // El tiempo que hay que buscar es el tiempo de ejecucion del peor procesador (el mas lento o
            // que tenga el mayor tiempo) -- la suma de todos los tiempos de las tareas del peor procesador
            if (this.solucion == null || this.solucion.getTiempo() > solucionParcial.getTiempo()){
                this.solucion = solucionParcial;
            }
        }else {
            Tarea tareaActual = tareas.pop();
            for (Procesador p : procesadores){
                if (solucionParcial.esAsignable(p, tareaActual, tiempoMaxNoRefrigerado)){
                    solucionParcial.asignarTarea(p, tareaActual);
                    back(procesadores, tareas, solucionParcial, tiempoMaxNoRefrigerado);
                    //tareas.push(tareaActual);
                    solucionParcial.desasignarTarea(p, tareaActual);
                }
            }
        }
    }
}
