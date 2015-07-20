package cl.inacap.tarea.u1.mapa;


import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import cl.inacap.tarea.u1.R;
import cl.inacap.tarea.u1.basededatos.DBmanager;


	public class MapaActivity extends FragmentActivity {

		String longitud,latitud;
		private List<String> uno,dos,tres;
		// FIN DECLARACION VARIABLES
		
		
	    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_mapa);
	        setUpMapIfNeeded();
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();
	        setUpMapIfNeeded();
	    }

	    /**
	     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
	     * installed) and the map has not already been instantiated.. This will ensure that we only ever
	     * call {@link #setUpMap()} once when {@link #mMap} is not null.
	     * <p/>
	     * If it isn't installed {@link SupportMapFragment} (and
	     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
	     * install/update the Google Play services APK on their device.
	     * <p/>
	     * A user can return to this FragmentActivity after following the prompt and correctly
	     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
	     * have been completely destroyed during this process (it is likely that it would only be
	     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
	     * method in {@link #onResume()} to guarantee that it will be called.
	     */
	    private void setUpMapIfNeeded() {
	        // Do a null check to confirm that we have not already instantiated the map.
	        if (mMap == null) {
	            // Try to obtain the map from the SupportMapFragment.
	            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
	                    .getMap();
	            // Check if we were successful in obtaining the map.
	            if (mMap != null) {
	                setUpMap();
	            }
	        }
	    }

	    /**
	     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
	     * just add a marker near Africa.
	     * <p/>
	     * This should only be called once and when we are sure that {@link #mMap} is not null.
	     */
	    private void setUpMap() {
	    
	        //mMap.addMarker(new MarkerOptions().position(new LatLng(-39.8377018,-73.2192968)).title("Marker"));
	    	uno = new ArrayList<String>();
	    	dos = new ArrayList<String>();
	    	tres = new ArrayList<String>();
	    	try
	    	{
	    	
	    	//TRAEMOS LOS DATOS DE NUESTRA BD
      		DBmanager manager = new DBmanager(this);
      		uno = manager.obtener_longitud();
   
      		
      		DBmanager manager2 = new DBmanager(this);
      		dos =manager2.obtener_latitud();
      		
      		DBmanager manager3 = new DBmanager(this);
      		tres =manager3.cargar_todos_cliente();
      		
      		
      		
      		
      					
      				/*validado para que funcione con los dos clientes comentados en la BD 
      				 * SI SE INGRESA UN NUEVO CLIENTE ESTE NO ALMACENA LATITUD NI LONGITUD LO QUE GENERARÍA UN ERROR.
      		*/
      				for(int i=0; i<2;i++)
      				{
      					Double l11 = Double.parseDouble(dos.get(i));
				    	Double l22 = Double.parseDouble(uno.get(i));
      					String cliente=tres.get(i);
      				
			   		  
				    	
				    	// agregamos marcadores 
				    	 LatLng Location = new LatLng(l11,l22);
				    			  mMap.addMarker(new MarkerOptions().position(Location)
				    			 .title(cliente)
				    			 .snippet("Ubicación del Cliente"));
				    			/*  mMap.addMarker(new MarkerOptions().position(KIEL)
							    			 .title("base de datos")
							    			 .snippet("uno"));
				    			 */
				    			// Move the camera instantly to hamburg with a zoom of 15.
				    			 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Location, 15));
				    			 // Zoom in, animating the camera.
				    			 mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
      				}
			   	
	    	}catch(Exception e)
			  {
					Toast.makeText(getBaseContext(), "error"+e+" ", Toast.LENGTH_LONG).show();

			  }
			   	
	    	
	 		
	 		
	 		 
	    }
	}