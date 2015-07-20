package cl.inacap.tarea.u1.basededatos;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBmanager {

		// creamos nombre de la tablas y la almacenamos en una variable
	   public static  String DATABASE_TABLA1="vendedor";
	   public static  String DATABASE_TABLA2="cliente";
	   public static  String DATABASE_TABLA3="relacion";
	   public static  String DATABASE_TABLA4="gps";
	   
	   // columnas de la tabla vendedor
	   public static String ID_VENDEDOR="id_vendedor";
	   public static String NOMBRE_VENDEDOR="nombre_vendedor";
	   public static String CONTRASENA_VENDEDOR="contrasena_vendedor";
	   
	   // columnas de la tabla cliente
	   public static String ID_CLIENTE="id_cliente";
	   public static String NOMBRE_CLIENTE="nombre_cliente";
	   public static String DIRECCION_CLIENTE="direccion_cliente";
	   public static String TELEFONO_CLIENTE="telefono_cliente";
	   
	 // columna tabla relacion
	   public static String PRODUCTO="producto";
	   public static String CANTIDAD="cantidad";
	   public static String NUM_COMPRA="num_compra";
	   public static String FECHA="fecha";
	   public static String PRECIO="precio";
	   public static String ESTADO="estado";
	   
	   
	   //columnas tabla base de datos
	  
	   public static String LATITUD="latitud";
	   public static String LONGITUD="longitud";
	   public static String IP="ip";
	
	   
	   // se crean las tablas con las variables que declaramos
	   
	   
	   
	   public static String CREATE_TABLE1 ="CREATE TABLE "+ DATABASE_TABLA1 +"( "+
	   		ID_VENDEDOR + " TEXT PRIMARY KEY NOT NULL, " +
	   		NOMBRE_VENDEDOR + " TEXT  NOT NULL, " +
	   		//LOGIN_VENDEDOR + " TEXT  NOT NULL, " +
	   		CONTRASENA_VENDEDOR + " TEXT  NOT NULL);";
	   
	   public static String CREATE_TABLE2 ="CREATE TABLE "+ DATABASE_TABLA2 +"( "+
		   		ID_CLIENTE + " TEXT PRIMARY KEY NOT NULL, " +
		   		NOMBRE_CLIENTE + " TEXT, " +
		   		DIRECCION_CLIENTE + " TEXT , " +
		   		TELEFONO_CLIENTE + " TEXT , " +
		   		LATITUD + " TEXT , " +
		   		LONGITUD + " TEXT );";
	   
	   public static String CREATE_TABLE3 ="CREATE TABLE "+ DATABASE_TABLA3 +"( "+
		   		ID_VENDEDOR + " TEXT , " +
		   		ID_CLIENTE + " TEXT, " +
		   		NUM_COMPRA + " TEXT , " +
		   		PRODUCTO + " TEXT , " +
		   		CANTIDAD + " INTEGER , " +
		   		FECHA + " TEXT , " +
		   		PRECIO + " INTEGER , " +
		   		ESTADO + " TEXT , FOREIGN KEY("+ID_VENDEDOR+") references "+DATABASE_TABLA1+", FOREIGN KEY("+ID_CLIENTE+") references "+DATABASE_TABLA2+");";
	   
       public static String CREATE_TABLE4 ="CREATE TABLE "+ DATABASE_TABLA4 +"( "+
		   		ID_VENDEDOR + " TEXT  , " +
		   		LATITUD + " TEXT  , " +
		   		LONGITUD + " TEXT , " +
		   		IP + " TEXT , FOREIGN KEY("+ID_VENDEDOR+") references "+DATABASE_TABLA1+");";
	   
	   // fin creacion de tablas
	
	   private DBHelper helper;
	   private SQLiteDatabase db;
	   public DBmanager(Context context)
	   {
		   helper= new DBHelper(context);
		   db= helper.getWritableDatabase();
		  
		   /* estos datos se deben descomentar para que funcione nuestra aplicacion
		    * la primera vez que se echa a correr luego de logearse se caera la aplicacion por 
		    * las pk y debe volver a comentarse
		    *
	
		    db.execSQL("insert into "+ DATABASE_TABLA1 +" values('juan','juan','juan')");
		   	db.execSQL("insert into "+ DATABASE_TABLA1 +" values('test','test','test')");
		   	db.execSQL("insert into "+ DATABASE_TABLA2 +" values('10000999-9','DANIEL FUENTES','PICARTE 1092','(63) 2 343434','-39.8168999','-73.2346903')");
		   	db.execSQL("insert into "+ DATABASE_TABLA2 +" values('11000999-8','IGNACIO PEREZ','PICARTE 3000','(63) 2 989898','-39.8166666','-73.2367475')");
			  */
	   }
	   
	   
	// funcion que datos gqs
	   public void insertar_gps(String id_vendedor, String latitude, String longitude , String ip )
	   {
		   	// consulta que almacena los datos
		   	db.execSQL("insert into " + DATABASE_TABLA4 + " values('" +id_vendedor+ "','" +latitude+ "','" +longitude+ "','" +ip+ "')");
		   
	   } // fin funcion insertar_cliente
	   





		// funcion para validar login de usuario
	   // la cual sevuelve el login y comprara con el campo de texto ingresado por el usuario
		public String validarLogin(String userName)
		{
		
		
			db= helper.getWritableDatabase();
			String[]columnas=new String[]{ID_VENDEDOR};
			Cursor cursor= db.query(DATABASE_TABLA1, columnas, ID_VENDEDOR + "='" + userName+ "'",null,null,null,null);

			if(cursor.getCount()<1){
				return "NOT EXIST";
				}else
		
					cursor.moveToFirst();
					String password =cursor.getString(0);
					cursor.close();
					db.close();
					return password;
			
		
		}
	   
		
		
		// funcion que inserta los datos del cliente 
	   public void insertar_cliente(String id_cliente, String nombre_cliente, String direccion, String telefono , String latitud, String longitud  )
	   {
		   	// consulta que almacena los datos
		   	db.execSQL("insert into " + DATABASE_TABLA2 + " values('"+ id_cliente +"','"+ nombre_cliente +"','"+ direccion +"','"+ telefono +"','"+ latitud +"','"+ longitud +"')");
		    
	   } // fin funcion insertar_cliente
	   
	   
	   public List<String> cargar_cliente_rut()
	   {
		   
					/// muestra todos los clientes independiente de cual sea el vendedor logeado
					List<String> lista = new ArrayList<String>();
					Cursor cursor=db.rawQuery("select "+ ID_CLIENTE +" from "+DATABASE_TABLA2 +" ",null);
					   
					while(cursor.moveToNext())
					   	{
					   		
					   		lista.add(cursor.getString(0));
					   	}
					   	cursor.close();
					   	db.close();
					   	return(lista);
		   
	   }// fin funcion carga_cliente_rut
	   
	   //cargar clientes con compras segun vendedor
	   public List<String> cargar_cliente(String id_vendedor)
	   {
		   
		
		List<String> lista = new ArrayList<String>();
		Cursor cursor=db.rawQuery("select distinct "+ ID_CLIENTE +" from "+DATABASE_TABLA3 + " where id_vendedor ='" + id_vendedor + "'", null);
		   
		while(cursor.moveToNext())
		   	{
		   		
		   		lista.add(cursor.getString(0));
		   	}
		   	cursor.close();
		   	db.close();
		   	return(lista);
		   
	   } // fin carga_cliente
	   
	   public List<String> cargar_todos_cliente()
	   {
		   
		
		List<String> lista = new ArrayList<String>();
		Cursor cursor=db.rawQuery("select  "+ ID_CLIENTE +" from "+ DATABASE_TABLA2 + " ", null);
		   
		while(cursor.moveToNext())
		   	{
		   		
		   		lista.add(cursor.getString(0));
		   	}
		   	cursor.close();
		   	db.close();
		   	return(lista);
		   
	   } // fin carga_cliente
	   
	
	   //  obtebemos latitud cliente 
	   public List<String> obtener_latitud()
	   {
		   
				

			List<String> lista = new ArrayList<String>();
			Cursor cursor=db.rawQuery("select  "+ LATITUD +" from "+ DATABASE_TABLA2 + " ", null);
			   
			while(cursor.moveToNext())
			   	{
			   		
			   		lista.add(cursor.getString(0));
			   	}
			   	cursor.close();
			   	db.close();
			   	return(lista);
			   
	 
	   } // fin obtener cliente
	   
	   
	   //  obtebemos longitud cliente 
	    
	   public List<String>  obtener_longitud()
	   {
		   

			List<String> lista = new ArrayList<String>();
			Cursor cursor=db.rawQuery("select  "+ LONGITUD +" from "+ DATABASE_TABLA2 + " ", null);
			   
			while(cursor.moveToNext())
			   	{
			   		
			   		lista.add(cursor.getString(0));
			   	}
			   	cursor.close();
			   	db.close();
			   	return(lista);
			   
	 
	   } // fin obtener cliente
	   
	   
	
	   
	   //  obtebemos nombre cliente 
	   public String obtener_cliente(String b)
	   {
		   
				db= helper.getWritableDatabase();
				String[]columnas=new String[]{NOMBRE_CLIENTE};

				Cursor c= db.query(DATABASE_TABLA2, columnas,ID_CLIENTE + "='" + b + "'",null,null,null,null);
				if(c !=null){
					c.moveToFirst();
					String nm =c.getString(0);
					c.close();
					db.close();
					return nm;
			}
				return null;
	 
	   } // fin obtener cliente
	   
	   
	   
	    // obteiene direccion de cliente
	   public String obtener_direccion(String b)
	   {
		   
		  
				
				db= helper.getWritableDatabase();
							String[]columnas=new String[]{DIRECCION_CLIENTE};

				Cursor c= db.query(DATABASE_TABLA2, columnas,ID_CLIENTE + "='" + b + "'",null,null,null,null);
				if(c !=null){
					c.moveToFirst();
					String nm =c.getString(0);
					c.close();
					db.close();
					return nm;
			}
				return null;
		    
		    
		   
	   }// fin obteber direccion
	   
	   
	   // obtener telefono cliente
	   
	   public String obtener_telefono(String b)
	   {
		   
		  
				
				db= helper.getWritableDatabase();
				String[]columnas=new String[]{TELEFONO_CLIENTE};

				Cursor c= db.query(DATABASE_TABLA2, columnas,ID_CLIENTE + "='" + b + "'",null,null,null,null);
				if(c !=null){
					c.moveToFirst();
					String nm =c.getString(0);
					c.close();
					db.close();
					return nm;
			}
				return null;
		    
		    
		   
	   }// fin obtener telefono
	   
	   
	   public void modificar_cliente(String id_cliente,String nombre,String direccion,String telefono)
		{
			
			// TODO Auto-generated method stub 
			ContentValues valores = new ContentValues();
			
			valores.put(NOMBRE_CLIENTE, nombre);
			valores.put(DIRECCION_CLIENTE, direccion);
			valores.put(TELEFONO_CLIENTE, telefono);
		
			db.update(DATABASE_TABLA2, valores, ID_CLIENTE+ "='" + id_cliente + "'",null);
			db.close();
		}
	 
	   
	    
	   // funcion eliminar cliente
		public void eliminar_cliente(String id_cliente3) {
		
			db.delete(DATABASE_TABLA2,ID_CLIENTE + "='" + id_cliente3 + "'",null);
			db.close();
			
			
		}// fin eliminar cliente

		
		// insertamos daots en tabla relacion 
		/*
		 * asociamos n cliente a una compra 
		 * */
		public void insertar_relacion2(String login, String rut_cliente,
				String num_compra1, String producto1, Integer cantidad1,
				String fecha1, Integer precio1) {
			
		   	db.execSQL("insert into "+ DATABASE_TABLA3 +" values('"+login+"','"+rut_cliente+"','"+num_compra1 +"','"+ producto1 +"',"+ cantidad1 +",'" + fecha1 + "',"+ precio1+",'NO ENTREGADO')");

			
		}// fin tabla relacion
		
		
		

		public List<String> obtener_boleta(String cliente,String id_vendedor) {
			List<String> lista = new ArrayList<String>();
		    Cursor cursor = db.rawQuery("SELECT DISTINCT " + NUM_COMPRA + " FROM " + DATABASE_TABLA3 + " where id_cliente ='" + cliente + "' and id_vendedor ='" + id_vendedor +"'", null);

			while(cursor.moveToNext())
			   	{
			   		
			   		lista.add(cursor.getString(0));
			   	}
			   	cursor.close();
			   	db.close();
			   	return(lista);
			
		}// fin obtener_boleta
		
		
		
		
		public List<String> producto_no_entregados(String cliente,String id_vendedor,String num_boleta) {
			List<String> lista = new ArrayList<String>();
		    Cursor cursor = db.rawQuery("SELECT DISTINCT " + NUM_COMPRA + " FROM " + DATABASE_TABLA3 + " where id_cliente ='" + cliente + "'", null);

			while(cursor.moveToNext())
			   	{
			   		
			   		lista.add(cursor.getString(0));
			   	}
			   	cursor.close();
			   	db.close();
			   	return(lista);
			
		}// lista de produtos no entregados

		

		public List<String> cargar_producto_noentregados(String login,
				String num_boleta, String id_cliente2) {
			List<String> lista = new ArrayList<String>();
		    Cursor cursor = db.rawQuery("SELECT " + PRODUCTO +" , "+ CANTIDAD + " FROM " + DATABASE_TABLA3 + " where id_vendedor ='" + login + "' and id_cliente = '" + id_cliente2 + "'  and num_compra = '" + num_boleta + "'  ", null);

			while(cursor.moveToNext())
			   	{
			   		
			   		lista.add(cursor.getString(0));
			   	}
			   	cursor.close();
			   	db.close();
			   	return(lista);
		} // FIN CARGAR PRODUCTO NO ENTREGADOS

		
		
		public void entregar_producto(String login, String id_cliente,
				String num_boleta, String producto_seleccionado,
				String estado) {
			
			
			db.execSQL("update "+ DATABASE_TABLA3 +" set " + ESTADO + " = '" + estado +  "' where id_vendedor = '" + login + "' and id_cliente = '" + id_cliente + "' and producto = '" + producto_seleccionado + "' and num_compra = '" + num_boleta + "' ");

			
			
		}

		public List<String> cargar_all_compras_cliente(String login,
				String id_cliente) {
			
			List<String> lista = new ArrayList<String>();
		    Cursor cursor = db.rawQuery("SELECT DISTINCT " + NUM_COMPRA + " FROM " + DATABASE_TABLA3 + " where id_cliente = '" + id_cliente + "' and id_vendedor = '" + login + "'", null);

			while(cursor.moveToNext())
			   	{
			   		
			   		lista.add(cursor.getString(0));
			   	}
			   	cursor.close();
			   	db.close();
			   	return(lista);
			
		}

		public List<String> mostrar_productos_entregados(String login,
				String id_cliente2, String obtener_compra) {
			
					List<String> lista = new ArrayList<String>();
				    Cursor cursor = db.rawQuery("SELECT DISTINCT " + PRODUCTO + " FROM " + DATABASE_TABLA3 + " where id_cliente = '" + id_cliente2 + "' and id_vendedor = '" + login + "' and num_compra = '" + obtener_compra + "' and estado = 'PRODUCTO ENTREGADO'", null);

			while(cursor.moveToNext())
			   	{
			   		
			   		lista.add(cursor.getString(0));
			   	}
			   	cursor.close();
			   	db.close();
			   	return(lista);
			
		}

		public List<String> mostrar_productos_no_entregados(String login,
				String id_cliente2, String obtener_compra) {
			
			List<String> lista = new ArrayList<String>();
		    Cursor cursor = db.rawQuery("SELECT DISTINCT " + PRODUCTO + " FROM " + DATABASE_TABLA3 + " where id_cliente = '" + id_cliente2 + "' and id_vendedor = '" + login + "' and num_compra = '" + obtener_compra + "' and estado = 'NO ENTREGADO'", null);

			while(cursor.moveToNext())
			   	{
			   		
			   		lista.add(cursor.getString(0));
			   	}
			   	cursor.close();
			   	db.close();
			   	return(lista);
			
		}

		public String total_pedidos_x_cliente(String login, String id_cliente2) {
			
		    Cursor cursor = db.rawQuery("SELECT  (" + PRODUCTO + ") FROM " + DATABASE_TABLA3 + " where id_cliente = '" + id_cliente2 + "' and id_vendedor = '" + login + "'", null);

		    int total = cursor.getCount();
			String total2 = String.valueOf(total);
			return(total2);
		}

		public String total_pedidos_no_entregados(String login,
				String id_cliente2) {
		    Cursor cursor = db.rawQuery("SELECT  (" + PRODUCTO + ") FROM " + DATABASE_TABLA3 + " where id_cliente = '" + id_cliente2 + "' and id_vendedor = '" + login + "' and estado = 'NO ENTREGADO'", null);

			int tot = cursor.getCount();
			String tot2 = String.valueOf(tot);
			   	return(tot2);
		}
	
}
