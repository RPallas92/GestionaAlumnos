package es.rpallas.modelo;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

import java.util.Date;
@RealmClass
public class Calificacion extends RealmObject {

    private Date fecha;
    private int calificacion; //De 1 a 4
    private TipoCalificacion tipo;
    private Asignatura asignatura;




	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public TipoCalificacion getTipo() {
		return tipo;
	}
	public void setTipo(TipoCalificacion tipo) {
		this.tipo = tipo;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}





}
