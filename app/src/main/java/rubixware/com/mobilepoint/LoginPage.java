package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends Activity{

    private Storage storage;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        storage = new Storage(this);
        passwordField = (EditText) findViewById(R.id.passwordCheck);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    private String passwordField(){
        return passwordField.getText().toString();
    }

    private String originalPassword(){
        return storage.readSimplyStorage("etc", "");
    }

    private boolean checkPassword(String passwordFields,String originalPassword){
        if (originalPassword.equals(passwordFields)){
            changeLayout();
            return true;
        }else{
            Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
            passwordField.setText("");
            return false;
        }
    }

    public void passwordCheck(View button){
        String passwordField = passwordField();
        String originalPassword = originalPassword();
        boolean checkPassword = checkPassword(passwordField,originalPassword);
    }

    private void changeLayout(){
        Intent intent = new Intent(this,ListPage.class);
        startActivity(intent);
        finish();
    }

}
