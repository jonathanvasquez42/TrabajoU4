package cl.inacap.tarea.u1.mantenedorcliente;






import cl.inacap.tarea.u1.R;
import cl.inacap.tarea.u1.basededatos.DBmanager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ClienteActivity extends Activity implements  OnClickListener{
  
	/*
	  DECLARAMOS LAS VARIABLES NECESARIAS PARA R
	  CARGAR LISTA, MODIFICAR ELIMINAR E INSERTAR 
	 */
	Button btn_modificar,btn_eliminar;
	EditText  id_cliente,nombre,direccion,telefono;

	String rut_cliente,nombre1,telefono1,direccion1;
	
	 
	 /*
	  * FIN DECLARACION DE VARIABLES
	  * 
	  * */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente);
		
		rut_cliente = getIntent().getStringExtra("cliente"); // obtenemos nombre de usuario de vendedor
		//rut_cliente = getIntent().getStringExtra("login"); // obtenemos nombre de usuario de vendedor
		
	
		btn_modificar = (Button)findViewById(R.id.btn_modificar);
		btn_modificar.setOnClickListener(this);
		
		btn_eliminar = (Button)findViewById(R.id.btn_eliminar);
		btn_eliminar.setOnClickListener(this);
		
		//FIN DECLARAR BOTONES
		 
		// DECLARAMOS CAMPOS DE TEXTO
		 id_cliente =(EditText)findViewById(R.id.txt_id);
		 nombre =(EditText)findViewById(R.id.txt_nombre);
		 
		 direccion =(EditText)findViewById(R.id.txt_direccion);
		 telefono =(EditText)findViewById(R.id.txt_telefono);
		 
		 //FIN CAMPOS DE TEXTO
         
 
		// al presionar y obtener el valor recargamos los datos del cliente
		
			DBmanager manager_cliente = new DBmanager(this);
			 nombre1= manager_cliente.obtener_cliente(rut_cliente);
			 direccion1= manager_cliente.obtener_direccion(rut_cliente);
			 telefono1 = manager_cliente.obtener_telefono(rut_cliente);

			id_cliente.setText(rut_cliente);
			nombre.setText(nombre1);
			direccion.setText(direccion1);
			telefono.setText(telefono1);
			
		 // SE DECLARA SPINNER Y SE REALIZA LA CARGA DE LOS ID_VENDEDOR INGRESADOS
		 // A TRAVES DE LA FUNCION CARGAR_CLIENTE_RUT()
				

			      
	      // FIN CARGA DE LISTA
	}// CIERRA ONCREATE


	

	
		
	// FUNCION CLICK DE LOS BOTONES DEL ACTIVITY
	@Override
	public void onClick(View v) {
		
		switch(v.getId()){  	
			  case R.id.btn_modificar:
				  
				  try{
					  

					  DBmanager manager = new DBmanager(this);
						
						String id_cliente2 = id_cliente.getText().toString();
						String nombre2 = nombre.getText().toString();
						String direccion2 = direccion.getText().toString();
						String telefono2 = telefono.getText().toString();
						
						// validamos campos no esten vacios 
						if(id_cliente2.equals("") || nombre2.equals("") || direccion2.equals("") || telefono2.equals("") )
						{
							
							Toast.makeText(getBaseContext(), "No deje campos vacios ", Toast.LENGTH_LONG).show();

						}else
						{
							
							manager.modificar_cliente(id_cliente2, nombre2, direccion2, telefono2);
							Toast.makeText(getBaseContext(), "modificando usuario ", Toast.LENGTH_LONG).show();
						}
					    
					  
					  
				  }
				  catch(Exception e)
				  {
						Toast.makeText(getBaseContext(), "error "+e+"", Toast.LENGTH_LONG).show();

				  }
				  
					break;
				  	
			  case R.id.btn_eliminar:
				  
				  try{
					  
					  	// se crea instancia de la bd
					  	DBmanager manager1 = new DBmanager(this);
						// obtienen valores del txt_rut
					  	
						String id_cliente3 = id_cliente.getText().toString();
						
						//validacion de txt_id_cliente no vacio para eliminar
						if(id_cliente3.equals(""))
						{
							Toast.makeText(getBaseContext(), "no dejar el campo rut vacio para eliminar ", Toast.LENGTH_LONG).show();

						}
						else
							{
									 // se envia dato a funcion eliminar
									 manager1.eliminar_cliente(id_cliente3);
									 Toast.makeText(getBaseContext(), "Usuario Eliminado ", Toast.LENGTH_LONG).show();
									// se recarga nuevamente la lista
		
							
							}// fin else
						
						 
						  
				  }
				  catch(Exception e)
				  {
						Toast.makeText(getBaseContext(), "error eliminar"+e+"", Toast.LENGTH_LONG).show();

				  }
				  
	break;
				  	
			
		}// fin switch
	} // fin funcion
	
	
		
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cliente, menu);
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