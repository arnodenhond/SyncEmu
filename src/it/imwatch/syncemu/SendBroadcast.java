package it.imwatch.syncemu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendBroadcast extends Activity {

	EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String json = getIntent().getExtras().getString(ConfigInput.JSON_EXTRA);
		setContentView(R.layout.jsonviewer);
		et = (EditText) findViewById(R.id.configinput);
		et.setText(json);
	}
	
	public void okButtonClick(View view) {
		Intent intent = new Intent("it.imwatch.imcloud.UPDATE");
		intent.putExtra("it.imwatch.JSON_DATA", et.getText().toString());
		sendBroadcast(intent);
		Toast.makeText(this, "Broadcast sent", Toast.LENGTH_SHORT).show();
	}
	
}