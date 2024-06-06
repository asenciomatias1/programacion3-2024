package TPE.src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solucion {
    private int tiempo;
    private HashMap<Procesador, LinkedList<Tarea>> solucion;

    public Solucion(){
        this.solucion = new HashMap<>();
        this.tiempo = 0;
    }

    public Solucion(LinkedList<Procesador> procesadores){
        this.tiempo = 0;
        this.solucion = new HashMap<>();
        for (Procesador p : procesadores){
            solucion.put(p, new LinkedList<Tarea>());
        }
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
        if (!t.esCritica() && p.esRefrigerado()){
            return true;
        }
        else if(t.esCritica() && p.esRefrigerado()) {
            return esAsignableCritica(p, t);
        }
        else if (t.esCritica() && !p.esRefrigerado()){
            return esAsignableCritica(p, t) && esAsignablePorTiempo(p, t, tiempoMaxNoRefrigerado);
        }
        //!t.esCritica() && !p.esRefrigerado()) {
        return esAsignablePorTiempo(p, t, tiempoMaxNoRefrigerado);

    }

    private boolean esAsignableCritica(Procesador p, Tarea t){
        int tareasCriticas = 0;
        for (Tarea tActual : this.solucion.get(p)){
            if (tActual.esCritica())
                tareasCriticas++;
        }
        return tareasCriticas < 2;
    }

    private boolean esAsignablePorTiempo(Procesador p, Tarea t, int tiempoMaxNoRefrigerado){
        int tiempoProcesador = 0;
        for (Tarea tActual : this.solucion.get(p)){
            tiempoProcesador += tActual.getTiempo();
        }
        return ((tiempoProcesador + t.getTiempo()) <= tiempoMaxNoRefrigerado);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Procesador, LinkedList<Tarea>> entry : solucion.entrySet()) {
            Procesador procesador = entry.getKey();
            LinkedList<Tarea> tareas = entry.getValue();

            sb.append(procesador.toString()).append(" -> "); // Información del procesador

            for (Tarea tarea : tareas) {
                sb.append(tarea.toString()); // Información de cada tarea
            }

            sb.append("\n"); // Salto de línea entre procesadores
        }
        return sb.toString();
    }
}
