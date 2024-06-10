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
        this.cantEstados = 0;
    }

    public int getCantEstados(){
        return this.cantEstados;
    }

    /**
     * Técnica Backtracking:
     * Se plantea una clase Solución, de la cual se crea una instancia de solución parcial que mediante un método
     * ecursivo intenta asignar cada tarea a cada procesador mientras que las restricciones lo permitan. Si la pila de
     * tareas está vacía, se compara la solución parcial con la mejor solución que está guardada hasta el momento, si
     * la parcial fue mejor, se actualiza la mejor solución.
     * Si la pila todavía tiene tareas, durante la recursión, para cada tarea y procesador se verifica si la tarea
     * seleccionada cumple con las restricciones de asignación y si así fuese, se asigna la tarea y se plantea una
     * poda que establece que si la solución parcial es mejor que la mejor solución vuelve a llamar al método
     * recursivo, de lo contrario ya no explora esa rama. Luego se desasigna la tarea para explorar otras combinaciones
     * posibles, garantizando una exploración completa de todas las asignaciones posibles.
     * Al finalizar los llamados recursivos se retorna la solución final en el método público.
     */

    public Solucion back(LinkedList<Procesador> procesadores, Stack<Tarea> tareas, int tiempoMaxNoRefrigerado){
        Solucion solucionParcial = new Solucion(procesadores);
        int cantTotalTareas = tareas.size();
        this.back(procesadores, tareas, solucionParcial, tiempoMaxNoRefrigerado, cantTotalTareas);
        if (this.solucion.getCantTareasAsignadas() == 0){
            return null;
        }
        return this.solucion;
    }

    private void back(LinkedList<Procesador> procesadores, Stack<Tarea> tareas, Solucion solucionParcial,
                      int tiempoMaxNoRefrigerado, int cantTotalTareas){
        this.cantEstados++;
        if (tareas.empty()){
            if (this.solucion == null || (this.solucion.getTiempo() > solucionParcial.getTiempo()
                    && cantTotalTareas == solucionParcial.getCantTareasAsignadas())){
                this.solucion.copiarSolucion(solucionParcial);
            }
        }else {
            Tarea tareaActual = tareas.pop();
            for (Procesador p : procesadores){
                if (solucionParcial.esAsignable(p, tareaActual, tiempoMaxNoRefrigerado)){
                    solucionParcial.asignarTarea(p, tareaActual);
                    if (this.solucion == null || (solucionParcial.getTiempo() < this.solucion.getTiempo())){
                        back(procesadores, tareas, solucionParcial, tiempoMaxNoRefrigerado, cantTotalTareas);
                    }
                    solucionParcial.desasignarTarea(p, tareaActual);
                }
            }
            tareas.push(tareaActual);
        }
    }
}
