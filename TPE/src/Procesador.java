package TPE.src;

public class Procesador{
    private String id, codigo;
    private boolean estaRefrigerado;
    private int anioFuncionamiento;

    public Procesador (String id, String codigo, boolean estaRefrigerado, int anioFuncionamiento){
        this.id = id;
        this.codigo = codigo;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
    }

    public boolean esRefrigerado(){
        return estaRefrigerado;
    }

    @Override
    public String toString() {
        return "Procesador{" +
                "id='" + id + '\'' +
                ", codigo='" + codigo + '\'' +
                ", esta_refrigerado=" + estaRefrigerado +
                ", anio_funcionamiento=" + anioFuncionamiento +
                '}';
    }
}
