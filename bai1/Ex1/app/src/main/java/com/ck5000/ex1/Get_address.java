package com.ck5000.ex1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Get_address extends AsyncTask<Void, Void, String>{

	@SuppressLint("StaticFieldLeak")
	private Activity activity;
	private ProgressDialog progressDialog;
	private double lat;
	private double lon;


	Get_address(Activity activity, double latitude, double longitude){
		this.activity = activity;
		this.lat = latitude;
		this.lon = longitude;
		progressDialog = new ProgressDialog(activity);
		progressDialog.setMessage("Loading........");
		progressDialog.setCancelable(true);
	}

	@Override
	protected void onPreExecute(){
		super.onPreExecute();
		this.progressDialog.show();
	}


	@Override
	protected String doInBackground(Void... params){
		String result = "";
		String address = null;
		JSONObject jObject = null;

		try{
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key=AIzaSyCSWOxKTQsvlWa2dSl3qFBupQRmSaegsmA");
			InputStreamReader reader = new InputStreamReader(url.openStream(),"UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			result = sb.toString();
		}catch(IOException e){
			e.printStackTrace();
		}
		try {
			jObject = new JSONObject(result);
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		String status;
		try{
			assert jObject != null;
			status = jObject.getString("status");
			if (status.equalsIgnoreCase("OK")){
				JSONArray results = jObject.getJSONArray("results");
				JSONObject zero = results.getJSONObject(0);
				address = zero.getString("formatted_address");
			}
		}catch(JSONException e){
			e.printStackTrace();
		}

		return address;
	}

	@Override
	protected void onPostExecute(String s){
		super.onPostExecute(s);

		TextView textAddress = activity.findViewById(R.id.textDiachi);
		textAddress.setText(s);

		if(progressDialog != null) progressDialog.dismiss();
	}
}