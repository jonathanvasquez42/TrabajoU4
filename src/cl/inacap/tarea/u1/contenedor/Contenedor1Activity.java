package cl.inacap.tarea.u1.contenedor;


import cl.inacap.tarea.u1.R;

import cl.inacap.tarea.u1.lista.ListarActivity;
import cl.inacap.tarea.u1.mantenedorcliente.Cliente2Activity;
import cl.inacap.tarea.u1.mapa.MapaActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Contenedor1Activity extends TabActivity {
	TabHost mTabhost;
	String login_enviar,rut_cliente;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contenedor);

		
		login_enviar = getIntent().getStringExtra("login"); // obtener login 
		rut_cliente = getIntent().getStringExtra("cliente"); // obtener login 
		
		mTabhost=getTabHost();
		// creamos tab
		TabSpec Spec = mTabhost.newTabSpec("Clientes");
		Spec.setIndicator("Clientes",null);
		Intent firstIntent = new Intent(Contenedor1Activity.this,ListarActivity.class);
		firstIntent.putExtra("login",login_enviar); // enviamos login
		firstIntent.putExtra("cliente",rut_cliente); // enviamos cliente
		Spec.setContent(firstIntent);
		
		// creamos tab
		TabSpec Spec2 = mTabhost.newTabSpec("Ingreso Clientes");
		Spec2.setIndicator("Ingreso Clientes",null);
		Intent Intent2 = new Intent(Contenedor1Activity.this,Cliente2Activity.class);
		Intent2.putExtra("login",login_enviar); // enviamos login
		Intent2.putExtra("cliente",rut_cliente); // enviamos login
		Spec2.setContent(Intent2);
		
		
		// se agrega tab para mostrar mapa
		TabSpec Spec3 = mTabhost.newTabSpec("Ubicación Clientes");
		Spec3.setIndicator("Ubicación Clientes",null);
		Intent Intent3 = new Intent(Contenedor1Activity.this,MapaActivity.class);
		//Intent3.putExtra("login",login_enviar); // enviamos login
		//Intent3.putExtra("cliente",rut_cliente); // enviamos login
		Spec3.setContent(Intent3);
		
		
	
		
		mTabhost.addTab(Spec);
		mTabhost.addTab(Spec2);
		mTabhost.addTab(Spec3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contenedor, menu);
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

