package it.imwatch.syncemu.configentry;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;

public class StringConfigEntry extends ConfigEntry {

	public StringConfigEntry(JSONObject object) throws JSONException {
		super(object);
		defaultValue = object.getString("defaultValue");
	}

	public ViewGroup getView(Context context) {
		LinearLayout ll = (LinearLayout) super.getView(context);
		EditText string = new EditText(context);
		string.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		string.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				value = s;
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		string.setText((String) defaultValue);

		ll.addView(string);
		return ll;
	}
	
	

}
