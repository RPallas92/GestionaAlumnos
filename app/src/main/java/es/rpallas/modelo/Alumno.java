package es.rpallas.modelo;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Alumno extends RealmObject implements Serializable {


    private RealmList<Calificacion> calificaciones;
    private String nombre = "";
    private String primerApellido = "";
    private String segundoApellido = "";

    public RealmList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(RealmList<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }






}
