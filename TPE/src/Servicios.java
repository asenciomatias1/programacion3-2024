package TPE.src;
import TPE.src.utils.CSVReader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	HashMap<String, Tarea> criticas, noCriticas;
	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		this.criticas = new HashMap<>();
		this.noCriticas = new HashMap<>();
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas, criticas, noCriticas);
	}
	
	/*
     * Complejidad temporal: O(1) debido a que los métodos containsKey() y get() tienen complejidades constantes.
     */
	public Tarea servicio1(String ID) {
		if (criticas.containsKey(ID))
        	return criticas.get(ID);
		else if (noCriticas.containsKey(ID)) {
			return noCriticas.get(ID);
		} else
			return null;
	}
    
    /*
     * Complejidad temporal: O(n) donde n es es la cantidad de Tareas.
     */
	public List<Tarea> servicio2(boolean esCritica) {
		if (esCritica){
			return new LinkedList<>(criticas.values());
		}
		return new LinkedList<>(noCriticas.values());
    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		List<Tarea> tareas = new LinkedList<>();
		criticas.forEach((id, tarea) -> {
			if (tarea.getPrioridad()>=prioridadInferior && tarea.getPrioridad()<=prioridadSuperior){
				tareas.add(tarea);
			}
		});
		noCriticas.forEach((id, tarea) -> {
			if (tarea.getPrioridad()>=prioridadInferior && tarea.getPrioridad()<=prioridadSuperior){
				tareas.add(tarea);
			}
		});
        return tareas;
    }

}
