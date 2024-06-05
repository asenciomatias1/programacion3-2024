package TPE.src;

import java.util.HashMap;
import java.util.LinkedList;

public class Solucion {
    private int tiempo;
    private HashMap<Procesador, LinkedList<Tarea>> solucion;

    public Solucion(){
        this.tiempo = 0;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void asignarTarea(Procesador p, Tarea t){
        if (this.solucion.containsKey(p) && !solucion.get(p).contains(t)){
            LinkedList<Tarea> listaTareas = solucion.get(p);
            listaTareas.add(t);
            this.solucion.put(p,listaTareas);
        }
    }

    public void desasignarTarea(Procesador p, Tarea t){
        if (this.solucion.containsKey(p)){
            solucion.get(p).remove(t);
        }
    }
//    TO DO
//       separar esAsignable en 2 metodos, uno que se encargue de chequear si es asignable por tiempo
//       y otro que se encargue de saber si es asignable por tareas criticas

    public boolean esAsignable(Procesador p, Tarea t, int tiempoMaxNoRefrigerado){
        int tareasCriticas = 0;
        int tiempoProcesador = 0;

        if (!t.esCritica() && p.esRefrigerado()){
            return true;
        } else if (!p.esRefrigerado()) {
            for (Tarea tActual : this.solucion.get(p)){
                tiempoProcesador += tActual.getTiempo();
            }
        }

        for (Tarea tActual : this.solucion.get(p)){
            tareasCriticas++;
        }

        return tareasCriticas < 2;
    }

}
