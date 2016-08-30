package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by jorge on 30/08/16.
 */
public class AdminPanel extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
