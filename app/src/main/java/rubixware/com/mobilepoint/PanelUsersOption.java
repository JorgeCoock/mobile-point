package rubixware.com.mobilepoint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PanelUsersOption extends Activity{

    String username;
    Intent intent;
    TextView editUserTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_users_option);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
        intent = getIntent();
        username = intent.getStringExtra("username");
        editUserTitle = (TextView) findViewById(R.id.edit_user_title);
        editUserTitle.setText(username);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFaIconsToButtons(Typeface font){
        Button editUser = (Button)findViewById( R.id.edit_user);
        Button editPages = (Button) findViewById(R.id.edit_pages);
        editUser.setTypeface(font);
        editPages.setTypeface(font);
    }

}
