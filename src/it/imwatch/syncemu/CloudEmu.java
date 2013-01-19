package it.imwatch.syncemu;

import it.imwatch.syncemu.configentry.ConfigEntry;
import it.imwatch.syncemu.configentry.DropdownConfigEntry;
import it.imwatch.syncemu.configentry.StringConfigEntry;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 
 * @author arnodenhond
 * 
 */
public class CloudEmu extends Activity implements OnClickListener {

	ConfigEntry[] configEntries = new ConfigEntry[0];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String json = getIntent().getExtras().getString(ConfigInput.JSON_EXTRA);
		try {
			configEntries = parseJSON(json);
		} catch (JSONException e) {
		}
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		for (ConfigEntry entry : configEntries) {
			ll.addView(entry.getView(this));
		}
		
		Button ok = new Button(this);
		ok.setText("ok");
		ok.setOnClickListener(this);
		ll.addView(ok);
		
		setContentView(ll);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, SendBroadcast.class);
		intent.putExtra(ConfigInput.JSON_EXTRA, stringJSON(configEntries));
		startActivity(intent);
	}
	
	private ConfigEntry[] parseJSON(String JSON) throws JSONException {
		JSONTokener tokener = new JSONTokener(JSON);
		JSONObject config = (JSONObject) tokener.nextValue();
		JSONObject entries = config.getJSONObject("configurationEntries");
		Iterator keys = entries.keys();
		ArrayList<ConfigEntry> configEntries = new ArrayList<ConfigEntry>();
		
		while (keys.hasNext()) {
			String key = (String) keys.next();
			JSONObject entry = entries.getJSONObject(key);
			String type = entry.getString("type");
			ConfigEntry configentry;

			switch (ConfigEntryType.valueOf(type)) {
			case string:
				configentry = new StringConfigEntry(entry);
				break;
			case dropdown:
				configentry = new DropdownConfigEntry(entry);
				break;
			default: configentry = new ConfigEntry(entry); 
			}
			configEntries.add(configentry);
		}
		return configEntries.toArray(new ConfigEntry[configEntries.size()]);
	}

	private String stringJSON(ConfigEntry[] configEntries) {
		JSONStringer stringer = new JSONStringer();
		try {
			stringer.object();
			stringer.key("installed").value(true);
			stringer.key("notificationActive").value(false);
			stringer.key("configurationValues");
			stringer.object();
			for (ConfigEntry configEntry : configEntries) {
				configEntry.jsonString(stringer);
			}
			stringer.endObject();
			stringer.endObject();
			return stringer.toString();
		} catch (JSONException e) {
			return e.toString();
		}
	}
}
