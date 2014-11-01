package es.rpallas.gestionalumnos;

import es.rpallas.modelo.Asignatura;
import io.realm.Realm;
import io.realm.RealmResults;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import es.rpallas.modelo.Alumno;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link ListadoAlumnosFragment.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the {@link ListadoAlumnosFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class ListadoAlumnosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LISTADO = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private List<Alumno> mAlumnos;
    private ListView mListView;
    private ArrayAdapter<Alumno> mAdapter;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ListadoAlumnosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListadoAlumnosFragment newInstance(String param1) {
        ListadoAlumnosFragment fragment = new ListadoAlumnosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LISTADO, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ListadoAlumnosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_LISTADO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_listado_alumnos, container,
                false);
        mAlumnos = getAlumnos();
        mListView = (ListView) rootView.findViewById(R.id.listadoAlumnos);
        mAdapter = new AlumnosListAdapter(getActivity(), mAlumnos);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemPressed(mAlumnos.get(position));
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onItemPressed(Alumno alumno) {
        if (mListener != null) {
            mListener.onFragmentInteraction(alumno);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Alumno alumno);
    }


    public class AlumnosListAdapter extends ArrayAdapter<Alumno> {
        private List<Alumno> mAlumnos;

        public AlumnosListAdapter(Context context, List<Alumno> alumnos) {
            super(context, R.layout.item_listado_alumnos, alumnos);
            this.mAlumnos = alumnos;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Alumno alumno = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_listado_alumnos, parent, false);
                viewHolder.nombre = (TextView) convertView.findViewById(R.id.nombreAlumno);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // Populate the data into the template view using the data object
            viewHolder.nombre.setText(alumnoToString(alumno));

            // Return the completed view to render on screen
            return convertView;
        }


        private String alumnoToString(Alumno alumno) {
            return alumno.getNombre() + " " + alumno.getPrimerApellido() + alumno.getSegundoApellido();
        }

    }

    /**
     * Patrón ViewHolder para aumentar la fluidez de listView
     * https://github.com/thecodepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
     */
    private static class ViewHolder {
        TextView nombre;

    }


    private List<Alumno> getAlumnos() {
        Realm realm = Realm.getInstance(getActivity());
        RealmResults<Alumno> result = realm.where(Alumno.class).findAll();
        return result;
    }

    public static void insertAsignaturas(Context context) {
        Realm realm = Realm.getInstance(context);

        realm.beginTransaction();


        Asignatura asignatura = realm.createObject(Asignatura.class);
        asignatura.setIdAsignatura(1);
        asignatura.setNombreAsignatura("Matemáticas");

        Asignatura asignatura2 = realm.createObject(Asignatura.class);
        asignatura2.setIdAsignatura(2);
        asignatura2.setNombreAsignatura("Lengua");

        Asignatura asignatura3 = realm.createObject(Asignatura.class);
        asignatura3.setIdAsignatura(3);
        asignatura3.setNombreAsignatura("Conocimiento");

        Asignatura asignatura4 = realm.createObject(Asignatura.class);
        asignatura4.setIdAsignatura(4);
        asignatura4.setNombreAsignatura("Plástica");

        realm.commitTransaction();
    }

    public static void insertAlumnos(Context context) {
        // Obtain a Realm instance
        Realm realm = Realm.getInstance(context);

        realm.beginTransaction();


        Alumno alumno = realm.createObject(Alumno.class); // Create a new object
        alumno.setNombre("Alexandra");
        alumno.setPrimerApellido("Pufu");

        Alumno alumno2 = realm.createObject(Alumno.class); // Create a new object
        alumno2.setNombre("Denisa");
        alumno2.setPrimerApellido("Rezenau");

        Alumno alumno3 = realm.createObject(Alumno.class); // Create a new object
        alumno3.setNombre("Victor");
        alumno3.setPrimerApellido("Garcia");

        Alumno alumno4 = realm.createObject(Alumno.class); // Create a new object
        alumno4.setNombre("Martina");
        alumno4.setPrimerApellido("Samper");

        Alumno alumno5 = realm.createObject(Alumno.class); // Create a new object
        alumno5.setNombre("Azahara");
        alumno5.setPrimerApellido("Lopez");

        Alumno alumno6 = realm.createObject(Alumno.class); // Create a new object
        alumno6.setNombre("Sandra");
        alumno6.setPrimerApellido("Granados");

        Alumno alumno7 = realm.createObject(Alumno.class); // Create a new object
        alumno7.setNombre("Laura");
        alumno7.setPrimerApellido("Baños");


        Alumno alumno8 = realm.createObject(Alumno.class); // Create a new object
        alumno8.setNombre("Beth");
        alumno8.setPrimerApellido("Paul");

        Alumno alumno9 = realm.createObject(Alumno.class); // Create a new object
        alumno9.setNombre("Ainhoa");
        alumno9.setPrimerApellido("Lopez");

        Alumno alumno10 = realm.createObject(Alumno.class); // Create a new object
        alumno10.setNombre("Bianca");
        alumno10.setPrimerApellido("Selistean");

        Alumno alumno11 = realm.createObject(Alumno.class); // Create a new object
        alumno11.setNombre("Nerea");
        alumno11.setPrimerApellido("Guitart");


        Alumno alumno12 = realm.createObject(Alumno.class); // Create a new object
        alumno12.setNombre("Laura");
        alumno12.setPrimerApellido("del Castillo");

        Alumno alumno13 = realm.createObject(Alumno.class); // Create a new object
        alumno13.setNombre("Johevi");
        alumno13.setPrimerApellido("Cuesta");


        Alumno alumno14 = realm.createObject(Alumno.class); // Create a new object
        alumno14.setNombre("Daniel");
        alumno14.setPrimerApellido("Barcelo");

        Alumno alumno15 = realm.createObject(Alumno.class); // Create a new object
        alumno15.setNombre("Eduardo");
        alumno15.setPrimerApellido("Pufulete");


        Alumno alumno16 = realm.createObject(Alumno.class); // Create a new object
        alumno16.setNombre("Andrei");
        alumno16.setPrimerApellido("Huluta");

        Alumno alumno17 = realm.createObject(Alumno.class); // Create a new object
        alumno17.setNombre("Maria");
        alumno17.setPrimerApellido("Sancho");

        Alumno alumno18 = realm.createObject(Alumno.class); // Create a new object
        alumno18.setNombre("Saul");
        alumno18.setPrimerApellido("Gomez");

        Alumno alumno19 = realm.createObject(Alumno.class); // Create a new object
        alumno19.setNombre("Iciar");
        alumno19.setPrimerApellido("Erla");


        realm.commitTransaction();
    }


}
