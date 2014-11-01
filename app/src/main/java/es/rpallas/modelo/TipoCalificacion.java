package es.rpallas.modelo;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;
@RealmClass
public class TipoCalificacion extends RealmObject {

    private String nombre;
    private int id;


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
