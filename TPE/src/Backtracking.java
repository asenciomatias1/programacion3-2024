package TPE.src;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Backtracking {
    private Solucion solucion;

    public Backtracking() {
        this.solucion = new Solucion();
    }

    public Solucion back(LinkedList<Procesador> procesadores, Stack<Tarea> tareas, int tiempoMaxNoRefrigerado){
        Solucion solucionParcial = new Solucion();
        this.back(procesadores, tareas, solucionParcial, tiempoMaxNoRefrigerado);
        return this.solucion;
    }

    private void back(LinkedList<Procesador> procesadores, Stack<Tarea> tareas, Solucion solucionParcial,
                      int tiempoMaxNoRefrigerado){
        if (tareas.empty()){
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
