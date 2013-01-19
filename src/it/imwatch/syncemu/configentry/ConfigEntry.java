package it.imwatch.syncemu.configentry;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConfigEntry {

	protected String key;
	protected Object value;
	protected Object defaultValue;
	protected boolean required;
	
	
	public ConfigEntry(JSONObject object) throws JSONException {
		key = object.getString("key");
		required = object.getBoolean("required");
	}

	public void jsonString(JSONStringer stringer) throws JSONException {
		stringer.key(key);
		stringer.value(value);
	}

	public ViewGroup getView(Context context) {
		LinearLayout ll = new LinearLayout(context);
		TextView tv = new TextView(context);
		tv.setText(key);
		ll.addView(tv);
		return ll;
	}

	
}
