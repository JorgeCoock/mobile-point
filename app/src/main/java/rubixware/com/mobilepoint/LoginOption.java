package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by jorge on 28/08/16.
 */
public class LoginOption extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_option);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    public void goToUserLogin(View button){
        Toast.makeText(this, "Go to user!", Toast.LENGTH_SHORT).show();
    }

    public void gotToAdminLogin(View button){
        Toast.makeText(this, "Admin login?", Toast.LENGTH_SHORT).show();
    }
}
