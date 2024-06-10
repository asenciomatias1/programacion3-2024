package TPE.src;

import TPE.src.utils.CSVReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Backtracking {
    private Solucion solucion;
    private int cantEstados;

    public Backtracking() {
        this.solucion = new Solucion();
        this.cantEstados = 0;
    }

    public Backtracking(LinkedList<Procesador> procesadores) {
        this.solucion = new Solucion(procesadores);
    }

    public int getCantEstados(){
        return this.cantEstados;
    }

    public Solucion back(LinkedList<Procesador> procesadores, Stack<Tarea> tareas, int tiempoMaxNoRefrigerado){
        Solucion solucionParcial = new Solucion(procesadores);
        int cantTotalTareas = tareas.size();
        this.back(procesadores, tareas, solucionParcial, tiempoMaxNoRefrigerado, cantTotalTareas);
        return this.solucion;
    }

    private void back(LinkedList<Procesador> procesadores, Stack<Tarea> tareas, Solucion solucionParcial,
                      int tiempoMaxNoRefrigerado, int cantTotalTareas){
        this.cantEstados++;
        if (tareas.empty()){
            // El tiempo que hay que buscar es el tiempo de ejecucion del peor procesador (el mas lento o
            // que tenga el mayor tiempo) -- la suma de todos los tiempos de las tareas del peor procesador
            if (this.solucion == null || this.solucion.getTiempo() > solucionParcial.getTiempo()
                    && cantTotalTareas == solucionParcial.getCantTareasAsignadas()){
                //this.solucion = solucionParcial;
                this.solucion.copiarSolucion(solucionParcial);
            }
        }else {
            Tarea tareaActual = tareas.pop();
            if (solucionParcial.getTiempo() > this.solucion.getTiempo()) {
                return; // Podar esta rama
            }
            for (Procesador p : procesadores){
                if (solucionParcial.esAsignable(p, tareaActual, tiempoMaxNoRefrigerado)){
                    solucionParcial.asignarTarea(p, tareaActual);
                    back(procesadores, tareas, solucionParcial, tiempoMaxNoRefrigerado, cantTotalTareas);
                    //tareas.push(tareaActual);
                    solucionParcial.desasignarTarea(p, tareaActual);
                }
            }
            tareas.push(tareaActual);
        }
    }
}
