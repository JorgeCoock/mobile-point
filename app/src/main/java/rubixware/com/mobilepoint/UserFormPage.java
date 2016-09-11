package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserFormPage extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_form_page);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private String usernameField(){
        final EditText usernameField = (EditText) findViewById(R.id.username);
        return usernameField.getText().toString();
    }

    private String passwordField(){
        final EditText passwordField = (EditText) findViewById(R.id.password);
        return passwordField.getText().toString();
    }

    private String passwordConfirmationField(){
        final EditText checkPasswordField = (EditText) findViewById(R.id.password_confirmation);
        return checkPasswordField.getText().toString();
    }

    public void saveUser(View view){
        formValidations(usernameField(), passwordField(), passwordConfirmationField());
    }

    private void formValidations(String username, String password, String passwordConfirmation){
        if ( username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if (password.equals(passwordConfirmation)){
                tryUserCreate(username, password);
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void tryUserCreate(String username, String password){
        DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
        UserQueries userQueries = new UserQueries();
        User user = userQueries.showUser(username, dbHelper);
        if(user == null){
            User newUser = new User(username, password);
            userQueries.createUser(newUser, dbHelper);
            Toast.makeText(this, "Administrador creado!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserFormPage.this, PanelUsers.class));

        }else{
            Toast.makeText(this, "Ya existe un usuario con ese nombre", Toast.LENGTH_LONG).show();
        }

    }




}
