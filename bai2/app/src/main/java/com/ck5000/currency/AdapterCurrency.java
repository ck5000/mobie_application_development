package com.ck5000.currency;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterCurrency extends BaseAdapter{

	Context context;
	List<Currency> list;

	public AdapterCurrency(@NonNull Context context, @NonNull List<Currency> objects){
		this.context = context;
		this.list = objects;
	}

	@Override
	public int getCount(){
		return (list == null)?0:  list.size();
	}

	@Nullable
	@Override
	public Currency getItem(int position){
		return list.get(position);
	}

	@Override
	public long getItemId(int position){
		return 0;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			assert inflater != null;
			convertView = inflater.inflate(R.layout.custom_spinner_item,parent,false);
		}
		Currency currency = list.get(position);

		TextView txtTitle = convertView.findViewById(R.id.textView);
		txtTitle.setText(currency.getName());
		return convertView;
	}
}
