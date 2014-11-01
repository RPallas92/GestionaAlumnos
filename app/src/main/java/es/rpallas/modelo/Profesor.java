package es.rpallas.modelo;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Profesor extends RealmObject {

    private RealmList<Alumno> alumnos;
    private String nombre = "";
    private String primerApellido = "";
    private String segundoApellido = "";

    public RealmList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(RealmList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }










}
