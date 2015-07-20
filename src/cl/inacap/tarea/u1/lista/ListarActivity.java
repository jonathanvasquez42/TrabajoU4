package cl.inacap.tarea.u1.lista;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.tarea.u1.R;
import cl.inacap.tarea.u1.basededatos.DBmanager;
import cl.inacap.tarea.u1.contenedor.ContenedorActivity;
import cl.inacap.tarea.u1.gps.clasegps;
import cl.inacap.tarea.u1.login.LoginActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
public class ListarActivity extends Activity{

	ListView lista_cliente;
	private List<String> lista;
	String login_enviar,rut_cliente;
	EditText filter_txt;
	ArrayAdapter<String> adaptador ;
	
	clasegps gps;
	final Handler handler = new Handler();
	double latitude;
	double longitude;
	String ip;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
      	
       
		
		login_enviar = getIntent().getStringExtra("login"); // obtenemos nombre de usuario de vendedor
		filter_txt=(EditText)findViewById(R.id.filter_txt);
		
		lista_cliente =  (ListView)findViewById(R.id.lv_cliente);
		lista = new ArrayList<String>();
		DBmanager managerspin = new DBmanager(this);
		lista = managerspin.cargar_todos_cliente();
        adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
	    lista_cliente.setTextFilterEnabled(true);
	    lista_cliente.setAdapter(adaptador);
		adaptador.notifyDataSetChanged();
         
		
		// corremos en background nuestro metodo
		 handler.post(runnable);
		 runnable.run();
		
		
		
        // Evento para cuando doy click en algun elemento de la lista ( ListView )
        lista_cliente.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long id) {
                // Mensaje Toast del elemento seleccionado.
            	    rut_cliente = adaptador.getItem(position);
            	    Intent intent =  new Intent(ListarActivity.this, ContenedorActivity.class);  // permite llamar otro activity 
            		intent.putExtra("login",login_enviar); // obtenemos login del usuario y lo utilizaremos en el activity client
		    		intent.putExtra("cliente",rut_cliente);
		    		ListarActivity.this.startActivity(intent);  // llamamos activity
		    	
                    Toast.makeText(getApplicationContext(), "Cargando...", Toast.LENGTH_SHORT).show();
                 
            }
             
        });
         
         
        /* Activando el filtro de busqueda */
        filter_txt.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                 
                ListarActivity.this.adaptador.getFilter().filter(arg0);
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                 
            }
        });
         
   }

    
  // Ejecutamos cada 3 min. llamamos el metodo llamargps
    Runnable runnable = new Runnable() {
        public void run() {
            LlamarGps();
            handler.postDelayed(runnable, 180000);
        }
    };
    
    public void LlamarGps(){
    	 gps = new clasegps(ListarActivity.this);

			// check if GPS enabled		
	        if(gps.canGetLocation()){
	        	
	        	latitude = gps.getLatitude(); // obtenemos latitude
	        	longitude = gps.getLongitude(); // obtener longitud
	        	
	        	
	        	// obtenemos ip
	        	WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
	        	WifiInfo wifiInfo = wifiManager.getConnectionInfo();
	        	int ipAddress = wifiInfo.getIpAddress();
	        	
	        	// transformamos ip  a string 
	        	ip = String.format("%d.%d.%d.%d",
	        			(ipAddress & 0xff),
	        			(ipAddress >> 8 & 0xff),
	        			(ipAddress >> 16 & 0xff),
	        			(ipAddress >> 24 & 0xff));
	        	
	        	// de double a string 
	        	 String lat = Double.toString(latitude);
				 String lon = Double.toString(longitude);
				
				/* 
				//lamamos funcion insertar_gps
				   DBmanager manager = new DBmanager(this); // INSTANCIA DE LA BD
	        	   manager.insertar_gps(login_enviar, lat, lon,ip);
				
	        	*/
	        	//mostramos en pantalla cada 3 minutos ubicacion y la ip
	        	Toast.makeText(getApplicationContext(), "Estas en - \nLat: " + latitude + "\nLong: " + longitude + " ip : "+ip, Toast.LENGTH_SHORT).show();
	        	//Toast.makeText(getApplicationContext(), "Estas en - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show();
	        }else{
	        	// si gps no esta habilitado mostramos menu para habilitarlo
	        	gps.showSettingsAlert();
	        }
    }

}

