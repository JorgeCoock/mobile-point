package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AdminFormUpdate extends Activity {

    String adminUsername;
    EditText usernameField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_form_update);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
        Intent intent = getIntent();
        adminUsername = intent.getStringExtra("getAdminUsername");
        usernameField = (EditText) findViewById(R.id.editAdminUsername);
        usernameField.setText(adminUsername, TextView.BufferType.EDITABLE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFaIconsToButtons(Typeface font){
        Button create_admin_button = (Button)findViewById( R.id.deleteAdmin);
        create_admin_button.setTypeface(font);
    }



}
