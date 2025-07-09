package vo;

public class AnimalVo {

    private String nombre;
    private String especie;
    private String genero;
    private String propietarioId;

    public AnimalVo() {
    }

    public AnimalVo(String nombre, String especie, String genero, String propietarioId) {
        this.nombre = nombre;
        this.especie = especie;
        this.genero = genero;
        this.propietarioId = propietarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(String propietarioId) {
        this.propietarioId = propietarioId;
    }

    @Override
    public String toString() {
        return "Animal:\n" +
                "Nombre: " + nombre + "\n" +
                "Especie: " + especie + "\n" +
                "Género: " + genero + "\n" +
                "Propietario ID: " + propietarioId;
    }
}
