package es.rpallas.gestionalumnos;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;

import es.rpallas.gestionalumnos.GestionFragment.OnGestionInteractionListener;
import es.rpallas.gestionalumnos.ListadoAlumnosFragment.OnFragmentInteractionListener;
import es.rpallas.modelo.Alumno;
import es.rpallas.modelo.Asignatura;
import io.realm.Realm;
import io.realm.RealmResults;

public class GestorActivity extends Activity implements OnFragmentInteractionListener, OnGestionInteractionListener {

    private long mDate;
    private Alumno primerAlumno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        mDate = extras.getLong(GestionAlumnosApplication.DATE_ARG);


        setContentView(R.layout.activity_gestor);
        //ListadoAlumnosFragment.insertAlumnos(this);
        //ListadoAlumnosFragment.insertAsignaturas(this);

        Realm realm = Realm.getInstance(this);

        RealmResults<Alumno> alumnos = realm.where(Alumno.class)
                .findAll();

        primerAlumno = alumnos.get(0);

        Fragment alumnosFragment = new ListadoAlumnosFragment();
        Fragment gestionFragment = GestionFragment.newInstance(primerAlumno, mDate);


        getFragmentManager().beginTransaction().add(R.id.fragment_alumnos, alumnosFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.fragment_gestion, gestionFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Alumno alumno) {

        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_gestion);
        if (fragment instanceof GestionFragment) {
            GestionFragment gFragment = (GestionFragment) fragment;
            gFragment.setAlumnoActual(alumno);
            gFragment.setFechaActual(mDate);
        } else {

            CalificacionesFragment calificacionesFragment = (CalificacionesFragment) fragment;
            Asignatura laAsignatura = calificacionesFragment.getCurrentAsignatura();
            getFragmentManager().popBackStack();


            FragmentTransaction ft = getFragmentManager().beginTransaction();
            CalificacionesFragment newCalificacionesFragment = CalificacionesFragment.newInstance(alumno, mDate, laAsignatura);
            ft.replace(R.id.fragment_gestion,newCalificacionesFragment , "CalificacionesFragment");
            ft.addToBackStack(null);
            ft.commit();
        }

    }

    @Override
    public void onGestionInteraction(Uri uri) {
        // TODO Auto-generated method stub

    }


}
