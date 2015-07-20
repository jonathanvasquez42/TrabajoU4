package cl.inacap.tarea.u1.resumen;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.tarea.u1.R;
import cl.inacap.tarea.u1.basededatos.DBmanager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;


public class ResumenActivity extends Activity implements OnItemSelectedListener {
	
// DECLARAMOS VARIABLES
Spinner ls_compras,ls_producto_si,ls_producto_no,lista_cliente;
EditText total_productos_no_entregados,total_pedidos;
private List<String> lista_compras,lista_productos_si,lista_productos_no;
String id_cliente,login, obtener_compra;
String resul_total_pedido,resul_total_no_entregados;
// FIN DECLARACION VARIABLES


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resumen);
		
		login = getIntent().getStringExtra("login"); // obtenemos LOGIN
		id_cliente= getIntent().getStringExtra("cliente"); // obtenemos ID_CLIENTE
		
		total_productos_no_entregados= (EditText)findViewById(R.id.txt_total_no_entregados);
		total_pedidos = (EditText)findViewById(R.id.txt_pedidos);
		
		ls_compras =  (Spinner)findViewById(R.id.ls_compras);
		ls_compras.setOnItemSelectedListener(this);
		
		
		
		

		
	    
	    ls_producto_si =  (Spinner)findViewById(R.id.ls_entregados);
		ls_producto_si.setOnItemSelectedListener(this);
		
		 ls_producto_no =  (Spinner)findViewById(R.id.ls_no_entregados);
		 ls_producto_no.setOnItemSelectedListener(this);
		
	// guardamos valor de elemento seleccionado
 		// CARGAMOS LISTA DE NUMERO DE ORDEN
 		lista_compras = new ArrayList<String>();
 		DBmanager manager_ = new DBmanager(this);
 		lista_compras = manager_.cargar_all_compras_cliente(login,id_cliente);
 		ArrayAdapter<String> adaptador_ =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lista_compras);
 	    adaptador_.setDropDownViewResource(android.R.layout.simple_spinner_item);
 	    ls_compras.setAdapter(adaptador_);
 	    ls_compras.setOnItemSelectedListener(this);
 	    ls_compras.setAdapter(adaptador_);
 		adaptador_.notifyDataSetChanged();
 	 
 	    
 		 // LISTA TOTAL PEDIDOS
 		DBmanager manager_tot_pedidos = new DBmanager(this);
 		resul_total_pedido = manager_tot_pedidos.total_pedidos_x_cliente(login,id_cliente);
 		
 		// LISTA TOTAL NO ENTREGADOS
 		DBmanager manager_tot_no_entregados = new DBmanager(this);
 		resul_total_no_entregados = manager_tot_no_entregados.total_pedidos_no_entregados(login,id_cliente);
 		//Toast.makeText(getBaseContext(), "" +resul_total_no_entregados+ " ", Toast.LENGTH_LONG).show();
 		// CARGAR CAMPOS DE TEXTO
 		total_productos_no_entregados.setText(resul_total_no_entregados);
 		total_pedidos.setText(resul_total_pedido);
	}

	


	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int arg2,
			long arg3) {
	 int id1 = parent.getId();
	    
        
	 switch (id1) 
        {
        	case R.id.ls_compras:
                 obtener_compra= ls_compras.getItemAtPosition(arg2).toString();
				//Toast.makeText(getBaseContext(), "" +nom_producto+ " ", Toast.LENGTH_LONG).show();
                 
                 // cargamos lista de productos entregados 
                lista_productos_si = new ArrayList<String>();
         		DBmanager manager = new DBmanager(this);
         		lista_productos_si = manager.mostrar_productos_entregados(login,id_cliente,obtener_compra);
         		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lista_productos_si);
         	    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
         	    ls_producto_si.setAdapter(adaptador);
         	    ls_producto_si.setOnItemSelectedListener(this);
         	    ls_producto_si.setAdapter(adaptador);
         	    adaptador.notifyDataSetChanged();
         	    
         	    
         	    // cargamos lista total no entregados
         	    lista_productos_no = new ArrayList<String>();
        		DBmanager manager2 = new DBmanager(this);
        		lista_productos_no = manager2.mostrar_productos_no_entregados(login,id_cliente,obtener_compra);
        		ArrayAdapter<String> adaptador2 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lista_productos_no);
        	    adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        	    ls_producto_no.setAdapter(adaptador2);
        	    ls_producto_no.setOnItemSelectedListener(this);
        	    ls_producto_no.setAdapter(adaptador2);
        		adaptador2.notifyDataSetChanged();
                 
                 
                 break;
                 
        
        }
	

	  
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// LISTA VACIA
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resumen, menu);
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