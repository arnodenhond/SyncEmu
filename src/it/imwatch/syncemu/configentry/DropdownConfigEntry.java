package it.imwatch.syncemu.configentry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class DropdownConfigEntry extends ConfigEntry {

	private String[] displayNames;

	public DropdownConfigEntry(JSONObject object) throws JSONException {
		super(object);
		defaultValue = object.getInt("defaultValue");
		JSONArray names = object.getJSONArray("displayNames");
		displayNames = new String[names.length()];
		for (int i = 0; i < names.length(); i++) {
			JSONObject name = names.getJSONObject(i);
			JSONObject values = name.getJSONObject("values");
			if (!values.keys().hasNext()) {
				continue;
			}
			displayNames[i]=values.getString((String) values.keys().next());
		}
	}

	public ViewGroup getView(Context context) {
		LinearLayout ll = (LinearLayout) super.getView(context);
		Spinner dropdown = new Spinner(context);
		
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(context,android.R.layout.simple_spinner_item,displayNames);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dropdown.setAdapter(adapter);
		dropdown.setSelection((Integer) defaultValue);
		dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				//value = Integer.valueOf(arg2);
				value = String.valueOf(arg2);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		dropdown.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		ll.addView(dropdown);
		return ll;
	}
	
}
