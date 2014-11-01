package es.rpallas.gestionalumnos;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import es.rpallas.modelo.Alumno;
import es.rpallas.modelo.Asignatura;
import io.realm.Realm;
import io.realm.RealmResults;


public class GestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NOMBRE_ALUMNO = "param1";
    private static final String FECHA_ACTUAL = "param2";


    private Button matematicasBtn;
    private Button lenguaBtn;
    private Button conocimientoBtn;
    private Button plasticaBtn;
    private TextView nombreAlumno;

    private Alumno alumnoActual;
    private long fechaActual = 0;


    private OnGestionInteractionListener mListener;


    // TODO: Rename and change types and number of parameters
    public static GestionFragment newInstance(Alumno alumno, long fechaActual) {
        GestionFragment fragment = new GestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(NOMBRE_ALUMNO, alumno);
        args.putLong(FECHA_ACTUAL, fechaActual);
        fragment.setArguments(args);
        return fragment;
    }

    public GestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            alumnoActual = (Alumno) getArguments().getSerializable(NOMBRE_ALUMNO);
            fechaActual = getArguments().getLong(FECHA_ACTUAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gestion_fragment, container,
                false);
        nombreAlumno = (TextView) rootView.findViewById(R.id.gestionNombreAlumno);
        matematicasBtn = (Button) rootView.findViewById(R.id.matematicasBtn);
        lenguaBtn = (Button) rootView.findViewById(R.id.lenguaBtn);
        conocimientoBtn = (Button) rootView.findViewById(R.id.conocimientoBtn);
        plasticaBtn = (Button) rootView.findViewById(R.id.plasticaBtn);

        OnClickListener btnListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                Realm realm = Realm.getInstance(getActivity());


                Asignatura asignatura = new Asignatura();
                RealmResults<Asignatura> asignaturaRealm;

                switch (v.getId()) {
                    case R.id.matematicasBtn:
                        asignaturaRealm = realm.where(Asignatura.class)
                                .equalTo("idAsignatura", 1)
                                .findAll();
                        asignatura = asignaturaRealm.get(0);
                        break;
                    case R.id.lenguaBtn:
                        asignaturaRealm = realm.where(Asignatura.class)
                                .equalTo("idAsignatura", 2)
                                .findAll();
                        asignatura = asignaturaRealm.get(0);
                        break;
                    case R.id.conocimientoBtn:
                        asignaturaRealm = realm.where(Asignatura.class)
                                .equalTo("idAsignatura", 3)
                                .findAll();
                        asignatura = asignaturaRealm.get(0);
                        break;
                    case R.id.plasticaBtn:
                        asignaturaRealm = realm.where(Asignatura.class)
                                .equalTo("idAsignatura", 4)
                                .findAll();
                        asignatura = asignaturaRealm.get(0);
                        break;

                    default:
                        break;
                }

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                CalificacionesFragment fragment = CalificacionesFragment.newInstance(alumnoActual, fechaActual, asignatura);
                ft.replace(R.id.fragment_gestion,fragment , "CalificacionesFragment");
                ft.addToBackStack(null);
                ft.commit();

            }
        };

        matematicasBtn.setOnClickListener(btnListener);
        lenguaBtn.setOnClickListener(btnListener);
        conocimientoBtn.setOnClickListener(btnListener);
        plasticaBtn.setOnClickListener(btnListener);


        nombreAlumno.setText(alumnoActual.getNombre() + " " + alumnoActual.getPrimerApellido());

        return rootView;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onGestionInteraction(uri);
        }
    }

    public void setAlumnoActual(Alumno alumno) {
        this.alumnoActual = alumno;
        nombreAlumno.setText(alumnoActual.getNombre() + " " + alumnoActual.getPrimerApellido());
    }

    public void setFechaActual(long fecha) {
        this.fechaActual = fecha;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnGestionInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnGestionInteractionListener {
        // TODO: Update argument type and name
        public void onGestionInteraction(Uri uri);
    }

}
