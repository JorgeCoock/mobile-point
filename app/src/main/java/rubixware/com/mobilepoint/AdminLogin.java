package rubixware.com.mobilepoint;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AdminLogin extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    public void adminLogin(View button){

    }

    private String usernameField(){
        final EditText username = (EditText) findViewById(R.id.username);
        return username.getText().toString();
    }

    private String



}
