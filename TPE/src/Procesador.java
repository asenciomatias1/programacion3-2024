package TPE.src;

public class Procesador {
    private String id, codigo;
    private boolean esta_refrigerado;
    private int anio_funcionamiento;

    public Procesador (String id, String codigo, boolean esta_refrigerado, int anio_funcionamiento){
        this.id = id;
        this.codigo = codigo;
        this.esta_refrigerado = esta_refrigerado;
        this.anio_funcionamiento = anio_funcionamiento;
    }

    public boolean esRefrigerado(){
        return esta_refrigerado;
    }
}
