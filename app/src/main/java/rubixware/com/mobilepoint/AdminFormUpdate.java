package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AdminFormUpdate extends Activity {

    String adminUsername;
    EditText usernameField, passwordField, confirmPasswordField;


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
        passwordField = (EditText) findViewById(R.id.editAdminPassword);
        confirmPasswordField = (EditText) findViewById(R.id.editAdminPasswordConfirmation);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFaIconsToButtons(Typeface font){
        Button create_admin_button = (Button)findViewById( R.id.deleteAdmin);
        create_admin_button.setTypeface(font);
    }

    private String username(){
        return usernameField.getText().toString();
    }
    private String password(){
        return passwordField.getText().toString();
    }

    private String passwordConfirmation(){
        return confirmPasswordField.getText().toString();
    }


    public void editAdmin(View view){
        if (password().isEmpty() || passwordConfirmation().isEmpty() && !username().isEmpty()){

        }

    }





}
