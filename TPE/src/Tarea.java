package TPE.src;

public class Tarea {
    private String id, nombre;
    private Integer tiempo, prioridad;
    private boolean critica;

    public Tarea (String id, String nombre, Integer tiempo, Integer prioridad, boolean critica){
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.prioridad = prioridad;
        this.critica = critica;
    }

}