package es.rpallas.gestionalumnos;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import es.rpallas.modelo.Alumno;
import es.rpallas.modelo.Asignatura;
import es.rpallas.modelo.Calificacion;
import es.rpallas.modelo.TipoCalificacion;
import es.rpallas.widget.CustomNumberPicker;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link CalificacionesFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class CalificacionesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ALUMNO = "param1";
    private static final String ARG_FECHA = "param2";
    private static final String ARG_ASIGNATURA = "param3";


    // TODO: Rename and change types of parameters
    private Alumno mAlumno;
    private long mDate;
    private Asignatura mAsignatura;


    //Views
    private TextView asignaturaNombreAlumno;
    private Button aceptarBtn;
    private Button cancelarBtn;
    //Views - NumberPickers
    private CustomNumberPicker actitudPreguntaDudas;
    private CustomNumberPicker actitudAtiende;
    private CustomNumberPicker actitudSeEsfuerza;
    private CustomNumberPicker actitudPresentacion;

    private CustomNumberPicker deberesAcabaEnCasa;
    private CustomNumberPicker deberesPresentacion;

    private CustomNumberPicker autonomiaAutonomia;
    private CustomNumberPicker autonomiaAcabaEnClase;

    //DB
    private Realm realm;



    public static CalificacionesFragment newInstance(Alumno alumno,
                                                     long date, Asignatura asignatura) {
        CalificacionesFragment fragment = new CalificacionesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ALUMNO, alumno);
        args.putLong(ARG_FECHA, date);
        args.putSerializable(ARG_ASIGNATURA, asignatura);

        fragment.setArguments(args);
        return fragment;
    }

    public CalificacionesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAlumno = (Alumno) getArguments().getSerializable(ARG_ALUMNO);
            mAsignatura = (Asignatura) getArguments().getSerializable(ARG_ASIGNATURA);
            mDate = getArguments().getLong(ARG_FECHA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calificaciones, container,
                false);
        realm = Realm.getInstance(getActivity());
        //Referenciar vistas
        asignaturaNombreAlumno = (TextView) rootView.findViewById(R.id.calificacionesTitutlo);
        aceptarBtn = (Button) rootView.findViewById(R.id.calificacionesAceptarBtn);
        cancelarBtn = (Button) rootView.findViewById(R.id.calificacionesCancelarBtn);

        //Referencias pickers
        actitudPreguntaDudas = (CustomNumberPicker) rootView.findViewById(R.id.actitudPreguntaDudas);
        actitudAtiende = (CustomNumberPicker) rootView.findViewById(R.id.actitudAtiende);
        actitudSeEsfuerza = (CustomNumberPicker) rootView.findViewById(R.id.actitudSeEsfuerza);
        actitudPresentacion = (CustomNumberPicker) rootView.findViewById(R.id.actitudPresentacion);

        deberesAcabaEnCasa = (CustomNumberPicker) rootView.findViewById(R.id.deberesAcabaEnCasa);
        deberesPresentacion = (CustomNumberPicker) rootView.findViewById(R.id.deberesPresentacion);

        autonomiaAutonomia = (CustomNumberPicker) rootView.findViewById(R.id.autonomiaAutonomia);
        autonomiaAcabaEnClase = (CustomNumberPicker) rootView.findViewById(R.id.autonomiaAcabaEnClase);


        asignaturaNombreAlumno.setText(mAlumno.getNombre() + " " + mAlumno.getPrimerApellido() + " - " + mAsignatura.getNombreAsignatura());
        aceptarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCalificacion();
            }
        });
        cancelarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });



        //TODO comprobar si ya habia regsitro guardado y rellenar campos
        return rootView;
    }


    private void guardarCalificacion(){
        //TODO Crear o actualizar depende si habia



        realm.beginTransaction();
        RealmList<Calificacion> calificaciones =mAlumno.getCalificaciones();



        if(actitudPreguntaDudas.getValue()!=0){
            calificaciones.add(createCalification(actitudPreguntaDudas.getValue(),"actitudPreguntaDudas"));
        }
        if(actitudAtiende.getValue()!=0){
            calificaciones.add(createCalification(actitudAtiende.getValue(),"actitudAtiende"));
        }
        if(actitudSeEsfuerza.getValue()!=0){
            calificaciones.add(createCalification(actitudSeEsfuerza.getValue(),"actitudSeEsfuerza"));
        }
        if(actitudPresentacion.getValue()!=0){
            calificaciones.add(createCalification(actitudPresentacion.getValue(),"actitudPresentacion"));
        }
        if(deberesAcabaEnCasa.getValue()!=0){
            calificaciones.add(createCalification(deberesAcabaEnCasa.getValue(),"deberesAcabaEnCasa"));
        }
        if(deberesPresentacion.getValue()!=0){
            calificaciones.add(createCalification(deberesPresentacion.getValue(),"deberesPresentacion"));
        }
        if(autonomiaAutonomia.getValue()!=0){
            calificaciones.add(createCalification(autonomiaAutonomia.getValue(),"autonomiaAutonomia"));
        }
        if(autonomiaAcabaEnClase.getValue()!=0){
            calificaciones.add(createCalification(autonomiaAcabaEnClase.getValue(),"autonomiaAcabaEnClase"));
        }
        realm.commitTransaction();
    }

    private Calificacion createCalification(int valor, String tipo){
        Calificacion c = realm.createObject(Calificacion.class);
        c.setAsignatura(mAsignatura);
        c.setFecha(new Date(mDate));
        TipoCalificacion t = realm.createObject(TipoCalificacion.class);
        c.setCalificacion(valor);
        t.setNombre(tipo);
        c.setTipo(t);
        return c;
    }

    public Asignatura getCurrentAsignatura(){
        return mAsignatura;
    }


}
