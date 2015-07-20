package cl.inacap.tarea.u1.registropedidos;


import java.util.ArrayList;
import java.util.List;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RelacionActivity extends Activity implements OnItemSelectedListener, OnClickListener {
String login,nom_producto,rut_cliente;
Spinner lista_producto,lista_cliente;
private List<String> lista;

/*
 VARIABLES PARA OBTENER LOS DATOS  
 */
 EditText num_compra, cantidad, precio;
Button btn_ingresar;

DatePicker fecha;
// lista de productos
String [] producto={"Coca Cola","Pan","Cerveza","Vino","Ron"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relacion);
		
		/*
		 RECIBIR VARIABLES PARA TABLA RELACION
		 * */
		
		// 
		num_compra = (EditText) findViewById(R.id.txt_numero);
		cantidad = (EditText) findViewById(R.id.txt_cantidad);
		precio = (EditText) findViewById(R.id.txt_precio);
		
		
		btn_ingresar= (Button)findViewById(R.id.btn_ingresar);
		btn_ingresar.setOnClickListener(this);
		
		fecha= (DatePicker)findViewById(R.id.datePicker1);
		login = getIntent().getStringExtra("login"); // obtenemos nombre de usuario de vendedor
		rut_cliente = getIntent().getStringExtra("cliente"); // obtenemos nombre de usuario de vendedor
		
		
		
		//cargamos lista de productos que cargamos del vector declarado al inicio
		lista_producto = (Spinner) findViewById(R.id.ls_verproducto);
        lista_producto.setOnItemSelectedListener(this);
        ArrayAdapter<String> adaptador3= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, producto);
		adaptador3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lista_producto.setAdapter(adaptador3);
		lista_producto.setOnItemSelectedListener(this);
	
		
		/*
		//cargamos lista de clientes
		lista_cliente =  (Spinner)findViewById(R.id.ls_cliente);
		lista_cliente.setOnItemSelectedListener(this);
		lista = new ArrayList<String>();
		
		DBmanager managerspin = new DBmanager(this);
		lista = managerspin.cargar_cliente_rut();
		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lista);
	    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
	    lista_cliente.setAdapter(adaptador);
	    lista_cliente.setOnItemSelectedListener(this);
	    lista_cliente.setAdapter(adaptador);
		adaptador.notifyDataSetChanged();
	    */
	}

	@Override
	public void onClick(View v) {
		
		
		DBmanager manager = new DBmanager(this);
		
		String num_compra1 = num_compra.getText().toString();
		Integer cantidad1 = Integer.parseInt(cantidad.getText().toString());
		Integer precio1 =  Integer.parseInt(precio.getText().toString());
		
		
		//obtenemos valor de decha y almacenamos en un string
		final int mont=fecha.getMonth();
		final int uno=1;
		final int mes=mont+uno;
		final int dia=fecha.getDayOfMonth();
		final int anio=fecha.getYear();
		String fecha1=""+dia+"/"+mes+"/"+anio+"";
		
		if(login.equals("") || rut_cliente.equals("") || num_compra1.equals("") || nom_producto.equals("") || cantidad1.equals("") || fecha1.equals("") || precio1.equals(""))
		{
			Toast.makeText(getBaseContext(), "no deje campo vacios", Toast.LENGTH_LONG).show();

		}else
		{
			//enviamos datos para ser ingresados
			manager.insertar_relacion2(login,rut_cliente,num_compra1,nom_producto, cantidad1,fecha1,precio1);
			
			Toast.makeText(getBaseContext(), "producto añadido a compra", Toast.LENGTH_LONG).show();

			
		}
	
		
	}// fin oncreate
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int arg2,
			long arg3) {
		 int id = parent.getId();
	    
        
		 switch (id) 
	        {
	        	case R.id.ls_verproducto:
	                 nom_producto= lista_producto.getItemAtPosition(arg2).toString();
					break;
	      
	          
	        }
		
	} // fin public void onItemSelected
	



	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		//LISTA VACIA
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.relacion, menu);
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
