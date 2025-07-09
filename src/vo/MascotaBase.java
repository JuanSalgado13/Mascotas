package vo;

public class MascotaBase {
    protected String nombre;
    protected String especie;
    protected String genero;
    protected String propietarioId;

    public MascotaBase() {}

    public MascotaBase(String nombre, String especie, String genero, String propietarioId) {
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
        return "MascotaBase{" +
                "nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", genero='" + genero + '\'' +
                ", propietarioId='" + propietarioId + '\'' +
                '}';
    }
}
