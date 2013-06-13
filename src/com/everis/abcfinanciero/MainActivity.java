package com.everis.abcfinanciero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.everis.abcfinanciero.Dominio.ItemWord;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ImageButton b=(ImageButton)findViewById(R.id.btnSearch);
        final ProgressBar pgb=(ProgressBar)findViewById(R.id.progressBar2);
        b.setOnClickListener(new OnClickListener() {
            @Override
         public void onClick(View arg0) {
            	pgb.setVisibility(View.VISIBLE);
            	TaskLoadData myTask=new TaskLoadData();
                myTask.execute();
             }
        });
    }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
  
    class TaskLoadData extends AsyncTask<Void, Void, Void> {

    	ArrayList<ItemWord> words=new ArrayList<ItemWord>() ;
    	@Override
    	protected Void doInBackground(Void... params) {
    		try
    		{
    			HttpClient hc=new DefaultHttpClient();
    			String URL="https://script.google.com/macros/s/AKfycbx3Pejp7tCts2PAIunz7WX_zllzhaKrRXBAC_oAeExJ0k_4ns_t/exec";
    			HttpGet get=new HttpGet(URL);
    			HttpResponse rp=hc.execute(get);
    			if(rp.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
    			{
    				String result=EntityUtils.toString(rp.getEntity());
    				JSONArray objects=new JSONArray(result);
    				for(int i=0;i<objects.length();i++)
    				{
    					JSONObject session=objects.getJSONObject(i);
    					ItemWord item=new ItemWord();
    					item.setTitulo(session.getString("title"));
    					item.setDescripcion(session.getString("description"));
    					words.add(item);
    				}
    			}
    		}catch(Exception e)
    		{
    			Log.e("LoadingJSON",e.getMessage(),e);
    		}
    		return null;
    	}

    	@Override
    	protected void onPostExecute(Void result) {
    		 Collections.sort(words, new Comparator<ItemWord>() {
					@Override
					public int compare(ItemWord arg0, ItemWord arg1) {
						 return arg0.getTitulo().compareToIgnoreCase(arg1.getTitulo());
					}
    		    });
    		ListView lista;
    		lista = (ListView) MainActivity.this.findViewById(R.id.listView1);
    		lista.setAdapter(new ListadoAdapter(MainActivity.this,R.layout.palabra,words){
    			@Override
    			public void onEntrada(Object entrada, View view) {
    				
    					TextView titulo = (TextView) view.findViewById(R.id.txtTitulo);
    					TextView descripcion= (TextView) view.findViewById(R.id.txtDescription);
    					if (titulo != null)
    						titulo.setText(((ItemWord)entrada).getTitulo());
    						descripcion.setText(((ItemWord)entrada).getDescripcion());
    				
    			}
    			}
    			);
    		final ProgressBar pgb=(ProgressBar)findViewById(R.id.progressBar2);
    		pgb.setVisibility(View.INVISIBLE);
    		
    	}

    }

    
}

