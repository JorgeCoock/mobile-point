package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    private String ageOptionField(){
        final RadioGroup ageOption = (RadioGroup) findViewById(R.id.ageOption);
        return ((RadioButton) findViewById(ageOption.getCheckedRadioButtonId())).getText().toString();
    }

    public void saveUser(View view){
        formValidations(usernameField(), passwordField(), passwordConfirmationField());
    }

    private void formValidations(String username, String password, String passwordConfirmation){
        if ( username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if (password.equals(passwordConfirmation)){
                tryUserCreate(username, password, ageOptionField());
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void tryUserCreate(String username, String password, String userAge){
        DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
        UserQueries userQueries = new UserQueries();
        User user = userQueries.showUser(username, dbHelper);
        if(user == null){
            int age = createUserAge(userAge);
            User newUser = new User(username, password, age);
            userQueries.createUser(newUser, dbHelper);
            Toast.makeText(this, "Administrador creado!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserFormPage.this, PanelUsers.class));
            finish();
        }else{
            Toast.makeText(this, "Ya existe un usuario con ese nombre", Toast.LENGTH_LONG).show();
        }

    }

    private int createUserAge(String age){
        int userAge;
        if (age.equals("Niño")){
            userAge = 1;
        }else if(age.equals("Adolescente")){
            userAge = 2;
        }else{
            userAge = 3;
        }
        return userAge;
    }



}
