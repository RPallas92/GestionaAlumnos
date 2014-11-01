package es.rpallas.modelo;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;
@RealmClass
public class Asignatura extends RealmObject implements Serializable{



    private int idAsignatura;
    private String nombreAsignatura;
	

	public int getIdAsignatura() {
		return idAsignatura;
	}
	public void setIdAsignatura(int idAsignatura) {
		this.idAsignatura = idAsignatura;
	}
	public String getNombreAsignatura() {
		return nombreAsignatura;
	}
	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}
	
}
