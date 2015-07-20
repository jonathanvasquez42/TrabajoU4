package cl.inacap.tarea.u1.mantenedorcliente;



import cl.inacap.tarea.u1.R;

import cl.inacap.tarea.u1.basededatos.DBmanager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Cliente2Activity extends Activity  implements OnClickListener{
	/*
	  DECLARAMOS LAS VARIABLES NECESARIAS PARA R
	  CARGAR LISTA, MODIFICAR ELIMINAR E INSERTAR 
	 */
	Button  btn_insertar,btn_modificar,btn_eliminar;
	EditText  id_cliente,nombre,direccion,telefono,latitud,longitud;
	Spinner spin;
	
	String b;
	 ArrayAdapter<String> adaptador;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente2);
		// SE DECLARAN LOS BOTONES Y SE AGREGA METODO ONCLICK
		btn_insertar = (Button)findViewById(R.id.btn_ingresar2);
		btn_insertar.setOnClickListener(this);
		
		
		//FIN DECLARAR BOTONES
		
		// DECLARAMOS CAMPOS DE TEXTO
		 id_cliente =(EditText)findViewById(R.id.txt_id2);
		 nombre =(EditText)findViewById(R.id.txt_nombre2);
		 
		 direccion =(EditText)findViewById(R.id.txt_direccion2);
		 telefono =(EditText)findViewById(R.id.txt_telefono2);
		 latitud =(EditText)findViewById(R.id.latitud);
		 longitud =(EditText)findViewById(R.id.longitud);
		 
		 //FIN CAMPOS DE TEXTO
		 
		
			      
	      // FIN CARGA DE LISTA
	}// CIERRA ONCREATE


	

	
		
	// FUNCION CLICK DE LOS BOTONES DEL ACTIVITY
	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		  case R.id.btn_ingresar2:
			  
			  try{
				  

					DBmanager manager = new DBmanager(this); // INSTANCIA DE LA BD
					// ASIGNAMOS EL VALOR DE LOS CAPOS DE TEXTO A NUESTRAS VARIABLES 
					String id_cliente1 = id_cliente.getText().toString();
					String nombre1 = nombre.getText().toString();
					String direccion1 = direccion.getText().toString();
					String telefono1 = telefono.getText().toString();
					String latitud1 = latitud.getText().toString();
					String longitud1 = latitud.getText().toString();
					//
					
					//VALIDAMOS QUE NO EXISTAN CAMPOS VACIOS
					if(id_cliente1.equals("") || nombre1.equals("") || direccion1.equals("") || telefono1.equals("") || longitud1.equals("") || latitud1.equals("")  )
					{
						Toast.makeText(getBaseContext(), "no deje campos vacios", Toast.LENGTH_LONG).show();

					}else
					{
						// SE ENVIA LA INFORMACION A LA FUNCION INSERTAR_CLIENTE
						 manager.insertar_cliente(id_cliente1, nombre1, direccion1, telefono1,latitud1,longitud1);
						 Toast.makeText(getBaseContext(), "Insertando usuario", Toast.LENGTH_LONG).show();
						

					}
						  
			  }
			  catch(Exception e)
			  {
					Toast.makeText(getBaseContext(), "error"+e+" ", Toast.LENGTH_LONG).show();

			  }
	
			  	break;
			  	
			
			
		}// fin switch
	} // fin funcion
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cliente2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
