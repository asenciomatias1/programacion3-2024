package TPE.src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solucion {
    private int tiempo; //El tiempo mayor del peor procesador
    private HashMap<Procesador, LinkedList<Tarea>> solucion;

    public Solucion(){
        this.solucion = new HashMap<>();
        this.tiempo = Integer.MAX_VALUE;
    }

    public Solucion(LinkedList<Procesador> procesadores){
        this.tiempo = Integer.MAX_VALUE;
        this.solucion = new HashMap<>();
        for (Procesador p : procesadores){
            solucion.put(p, new LinkedList<Tarea>());
        }
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public HashMap<Procesador, LinkedList<Tarea>> getSolucion() {
        return solucion;
    }

    public void asignarTarea(Procesador p, Tarea t){
        if (this.solucion.containsKey(p) && !solucion.get(p).contains(t)){
            LinkedList<Tarea> listaTareas = solucion.get(p);
            listaTareas.add(t);
            this.solucion.put(p,listaTareas);
            this.updateTiempo();
        }
    }

    private void updateTiempo(){
        int tiempoActual = 0;
        for (LinkedList<Tarea> list : solucion.values()){
            int tiempoParcialTarea = 0;
            for (Tarea t : list){
                tiempoParcialTarea += t.getTiempo();
            }
            if (tiempoParcialTarea > tiempoActual){
                tiempoActual = tiempoParcialTarea;
            }
        }

        this.setTiempo(tiempoActual);
    }

    public int getTiempoEjecucionTareas(Procesador p){
        int tiempo = 0;
        LinkedList<Tarea> tareas = this.solucion.get(p);
        for (Tarea t : tareas){
            tiempo += t.getTiempo();
        }
        return tiempo;
    }

    public int getTiempoEjecucionTareas(){
        int res = 0;
        for (Procesador p : this.solucion.keySet()){
            if (this.getTiempoEjecucionTareas(p) > res){
                res = this.getTiempoEjecucionTareas(p);
            }
        }
        return res;
    }

    public void desasignarTarea(Procesador p, Tarea t){
        if (this.solucion.containsKey(p)){
            solucion.get(p).remove(t);
            this.updateTiempo();
        }
    }

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

    public void copiarSolucion(Solucion s){
        this.tiempo = s.tiempo;
        HashMap<Procesador, LinkedList<Tarea>> copia = new HashMap<>();
        for (Procesador p : s.solucion.keySet()){
            copia.put(p, new LinkedList<>());
            for (Tarea t : s.solucion.get(p)){
                copia.get(p).add(t);
            }
        }
        this.solucion = copia;
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

    public int getCantTareasAsignadas() {
        int res = 0;
        for (Procesador p : this.solucion.keySet()){
            res += this.solucion.get(p).size();
        }

        return res;
    }
}
