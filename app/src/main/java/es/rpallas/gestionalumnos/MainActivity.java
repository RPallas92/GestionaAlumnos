package es.rpallas.gestionalumnos;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;

public class MainActivity extends Activity {

	
	private CalendarView mCalendar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mCalendar = (CalendarView) findViewById(R.id.calendarView);
        Button todayBtn = (Button) findViewById(R.id.todayBtn);
        todayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				selectCurrentDay();
			}
		});
        
        Button accessBtn = (Button) findViewById(R.id.accessBtn);
        accessBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				access();
			}
		});

    }
    
    
    private void access(){
    	long selectecDateMili = mCalendar.getDate();
    	Intent intent = new Intent(MainActivity.this, GestorActivity.class);
    	intent.putExtra(GestionAlumnosApplication.DATE_ARG, selectecDateMili);
    	startActivity(intent);
    }
    
    private void selectCurrentDay(){

        Date date = new Date();
        mCalendar.setDate(date.getTime());
    }
}
