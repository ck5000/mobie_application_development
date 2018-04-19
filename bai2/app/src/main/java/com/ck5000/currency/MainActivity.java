package com.ck5000.currency;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

	ArrayList<Currency> list ;
	Spinner spinner1;
	Spinner spinner2;
	EditText editText1;
	EditText editText2;
	Currency currency1;
	Currency currency2;
	Boolean	aBoolean;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		createList();
		AdapterCurrency dataAdapter = new AdapterCurrency(this, list);
		spinner1.setAdapter(dataAdapter);
		spinner2.setAdapter(dataAdapter);
		spinner1.setOnItemSelectedListener(new Selected());
		spinner2.setOnItemSelectedListener(new Selected());
		editText1.setOnFocusChangeListener(new View.OnFocusChangeListener(){
			@Override
			public void onFocusChange(View view, boolean b){
				if(b) aBoolean =true;
			}
		});
		editText2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
			@Override
			public void onFocusChange(View view, boolean b){
				if(b) aBoolean =false;
			}
		});
		editText1.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){
			}
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){
			}
			@SuppressLint("SetTextI18n")
			@Override
			public void afterTextChanged(Editable editable){
				if(aBoolean){
					try{
						double d = Double.valueOf(String.valueOf(editText1.getText()));
						double res = d * currency2.rate / currency1.rate;
						editText2.setText("" + res);
					}catch(Exception e){
						editText2.setText("");
					}
				}
			}
		});
		editText2.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){
			}
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){
			}
			@SuppressLint("SetTextI18n")
			@Override
			public void afterTextChanged(Editable editable){
				if(!aBoolean){
					try{
						double d = Double.valueOf(String.valueOf(editText2.getText()));
						double res = d * currency1.rate / currency2.rate;
						editText1.setText("" + res);
					}catch(Exception e){
						editText1.setText("");
					}
				}
			}
		});
	}
	private void init(){
		spinner1 = findViewById(R.id.spinner1);
		spinner2 = findViewById(R.id.spinner2);
		editText1 = findViewById(R.id.editText1);
		editText2 = findViewById(R.id.editText2);
	}
	private void createList(){
		list = new ArrayList<>();
		list.add(new Currency("USD",1));
		list.add(new Currency("EUR",0.808));
		list.add(new Currency("GBP",0.7072));
		list.add(new Currency("Dong",22.235));
		list.add(new Currency("JPY",107.3570));
		list.add(new Currency("CHF",0.9674));
		list.add(new Currency("CAD",1.2611));
		list.add(new Currency("KRW",1063));
		list.add(new Currency("SEK",8.3847));
		list.add(new Currency("SGD",1.3091));
	}
	class Selected implements AdapterView.OnItemSelectedListener{
		@SuppressLint("SetTextI18n")
		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
			if(adapterView.getId()==R.id.spinner1){
				currency1 = list.get(i);

			}else {
				currency2 = list.get(i);
			}
			try{
				double d = Double.valueOf(String.valueOf(editText1.getText()));
				double res = d*currency2.rate/currency1.rate;
				editText2.setText(""+res);
			}catch(Exception e){
				editText2.setText("");
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> adapterView){

		}
	}
}
