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
import android.widget.Toast;


public class UserFormPageUpdate  extends Activity{

    String USERNAME;
    Intent intent;
    TextView title;
    EditText username_field;
    EditText password_field;
    EditText password_confirmation_field;
    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    UserQueries userQueries = new UserQueries();
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_form_page_update);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
        intent = getIntent();
        USERNAME = intent.getStringExtra("username");
        user = userQueries.showUser(USERNAME, dbHelper);
        setTextOnLayout();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFaIconsToButtons(Typeface font){
        Button goBack = (Button) findViewById(R.id.goBack);
        goBack.setTypeface(font);
    }

    private void setTextOnLayout(){
        title = (TextView) findViewById(R.id.textView9);
        String titleUsername = "Editar Usuario: "+USERNAME;
        title.setText(titleUsername);
        username_field = (EditText) findViewById(R.id.username);
        username_field.setText(USERNAME);
        password_field = (EditText) findViewById(R.id.password);
        password_field.setText(user.getPassword());
        password_confirmation_field = (EditText) findViewById(R.id.password_confirmation);
        password_confirmation_field.setText(user.getPassword());
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

    public void updateUser(View view){
        if (usernameField().isEmpty() || passwordField().isEmpty() || passwordConfirmationField().isEmpty()){
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if(usernameField().equals(user.getUsername())){
                if(passwordField().equals(passwordConfirmationField())){
                    tryUpdateUser(usernameField(), passwordField());
                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }else{
                User new_username = userQueries.showUser(usernameField(), dbHelper);
                if (new_username == null){
                    if(passwordField().equals(passwordConfirmationField())){
                        tryUpdateUser(usernameField(), passwordField());
                    }else{
                        Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Ya existe un usuario con ese nombre", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void tryUpdateUser(String username, String password){
        User new_user = new User(username, password);
        if(userQueries.updateAdmin(user, dbHelper, new_user)){
            Toast.makeText(this, "Se actualizo el usuario", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "No se pudo actualizar el usuario", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(UserFormPageUpdate.this, PanelUsers.class);
        startActivity(intent);
        finish();
    }

    public void goBack(View view){
        startActivity(new Intent(UserFormPageUpdate.this, PanelUsersOption.class));
        finish();
    }
}
