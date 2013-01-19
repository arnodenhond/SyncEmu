package it.imwatch.syncemu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ConfigInput extends Activity {
	private static final String sampleInput = "{\"notifier\": false,\"descriptions\": {\"values\": {   \"it\": \"Visualizza le previsioni atmosferiche delle tue localit\u00e0 preferite. Inserisci inomi delle citt\u00e0 scelte nei campi sottostanti.\",\"en\": \"See the weather forecasts of your favourite locations. Enter the names of thecities of your choice in the fields below.\"}},\"configurationEntries\": {\"KEY_COUNTRY\": {\"type\": \"string\",\"isPassword\": false,\"index\": 1,\"key\": \"KEY_COUNTRY\",\"names\": {\"values\": {\"it\": \"Paese 1\",\"en\": \"Country 1\"}},\"descriptions\": {\"values\": {\"it\": \"Inserisci il nome del Paese (opzionale)\",\"en\": \"Enter the country name (optional)\"}},\"required\": false,\"defaultValue\": \"Italy\"},\"KEY_DEGREES\": {\"displayNames\": [{\"values\": {\"en\": \"Fahrenheit (F)\"}},{\"values\": {\"en\": \"Celsius (C)\"}}],\"type\": \"dropdown\",\"index\": 5,\"key\": \"KEY_DEGREES\",\"names\": {\"values\": {\"it\": \"Gradi 2\",\"en\": \"Degrees 2\"}},\"descriptions\": {\"values\": {\"it\": \"Scegli l\\u0027unit\u00e0 di misura\",\"en\": \"Choose the units of temperature\"}},\"required\": true,\"defaultValue\": \"1\"}}}";

	EditText config;
	public static final String JSON_EXTRA = "JSON";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.jsonviewer);
		config = (EditText) findViewById(R.id.configinput);
		config.setText(sampleInput);
	}
	
	public void okButtonClick(View v) {
		Intent intent = new Intent(this, CloudEmu.class);
		intent.putExtra(JSON_EXTRA, config.getText().toString());
		startActivity(intent);
	}
	
}
