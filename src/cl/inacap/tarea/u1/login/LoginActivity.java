package cl.inacap.tarea.u1.login;




import cl.inacap.tarea.u1.R;
import cl.inacap.tarea.u1.basededatos.DBmanager;
import cl.inacap.tarea.u1.contenedor.Contenedor1Activity;
import cl.inacap.tarea.u1.gps.clasegps;
import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;


public class LoginActivity extends Activity  implements  OnClickListener{
	Button btn_ingresar;
	EditText contrasena,login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
			
	     
	     // Get The Refference Of Buttons
	     btn_ingresar=(Button)findViewById(R.id.btn_ingresar);
	     btn_ingresar.setOnClickListener(this);
	     
	     contrasena=(EditText)findViewById(R.id.txt_contrasena);
	     login=(EditText)findViewById(R.id.txt_login);
	}
	
	

				public void onClick(View v) {
					DBmanager DBmanager2 = new DBmanager(this);
					// get The User name and Password
					String userName=login.getText().toString();
					String password=contrasena.getText().toString();
					
					// fetch the Password form database for respective user name
					String logear=DBmanager2.validarLogin(userName);
					
					// check if the Stored password matches with  Password entered by user
					if(password.equals(logear))
					{
						
						
						Toast.makeText(LoginActivity.this, "Logeado", Toast.LENGTH_LONG).show();
						
					  Intent intent =  new Intent(LoginActivity.this, Contenedor1Activity.class);  // permite llamar otro activity 
			    		intent.putExtra("login",login.getText().toString()); // obtenemos login del usuario y lo utilizaremos en el activity client
			    		LoginActivity.this.startActivity(intent);  // llamamos activity
					    
					  
						 
						
					}
					else
					{
						Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_LONG).show();
					}
				}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
