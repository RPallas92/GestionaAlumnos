package es.rpallas.gestionalumnos;

import android.app.Application;

public class GestionAlumnosApplication extends Application{
	
	// Intent args
	public static final String DATE_ARG = "dateArg";
	public static final String ASIGNATURA_ARG = "asignaturaArg";
	public static final String ALUMNO_ARG = "alumnoArg";
	
	
	//Asignaturas
	public static final String MATEMATICAS = "matematicas";
	public static final String LENGUA = "lengua";
	public static final String CONOCIMIENTO = "conocimiento";
	public static final String PLASTICA = "plastica";

    //Broadcast
    public static final String ALUMNO_CLICKED = "alumnoClicked";

@Override
public void onCreate() {
	super.onCreate();
}
}
