package com.ck5000.ex1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
	EditText editLatitude ;
	EditText editLongitude ;
	Button 	 buttonGetAddress ;
	TextView textAddress ;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editLatitude = findViewById(R.id.editLatitude);
		editLongitude = findViewById(R.id.editLongitude);
		buttonGetAddress = findViewById(R.id.button);
		textAddress = findViewById(R.id.textDiachi);
		buttonGetAddress.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				getAddress();
			}
		});
	}

	public void getAddress(){
		Double lat;
		Double lon;
		try
		{
			lat = Double.valueOf(String.valueOf(editLatitude.getText()));
			lon = Double.valueOf(String.valueOf(editLongitude.getText()));
		}
		catch(NumberFormatException nfe)
		{
			Toast.makeText(this,"nhap location",Toast.LENGTH_LONG).show();
			return;
		}
		new Get_address(this, lat, lon).execute();
	}
}

