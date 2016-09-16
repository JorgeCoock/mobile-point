package rubixware.com.mobilepoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class RecommendedPagesForm extends Activity {

    Intent intent;
    String username;
    User user;
    TextView title;
    String textViewTitle;
    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    UserQueries userQueries = new UserQueries();
    RecommendedPageQueries recommendedPageQueries = new RecommendedPageQueries();
    PageQueries pageQueries = new PageQueries();
    public ArrayAdapter<String> pagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommended_pages_form);
        intent = getIntent();
        username = intent.getStringExtra("username");
        user = userQueries.showUser(username, dbHelper);
        title = (TextView) findViewById(R.id.title);
        title.setText(title(user));
        setPagesOnListView();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private String title(User user){
        String ageOption;
        if(user.getAge() == 1){
            ageOption = "niños";
        }else if(user.getAge() == 2){
            ageOption = "adolescentes";
        }else{
            ageOption = "adultos";
        }
        return "Páginas para "+ageOption;
    }

    private void setPagesOnListView(){
        pagesAdapter = new ArrayAdapter<String>(this, R.layout.custom_listview, android.R.id.text1, getRecommendedPages());
        final ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(pagesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pageUrl = (String) listView.getItemAtPosition(position);
                deletePageAlert(pageUrl);
            }
        });
    }

    private ArrayList<String> getRecommendedPages(){
        ArrayList <Page> userPages = pageQueries.getUserPages(user.getId(), dbHelper);
        ArrayList <String> agePages = recommendedPageQueries.getRecommendedPagesUrls(user.getAge(), dbHelper);
        for (int i =0; i < userPages.size(); i++){
            String currentPage = userPages.get(i).getUrl();
            if (agePages.contains(currentPage)){
                agePages.remove(currentPage);
            }
        }
        return agePages;
    }

    private void deletePageAlert(final String pageUrl){
        AlertDialog.Builder pageDelete = new AlertDialog.Builder(this);
        pageDelete.setTitle("Agregar página")
                .setMessage("Desea agregar la página: "+pageUrl+" al usuario " +user.getUsername()+" ?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addRecommendedPageToUser(pageUrl);
                    }
                });
        pageDelete.create();
        pageDelete.show();
    }

    private void addRecommendedPageToUser(String url){
        Page page = new Page(user.getId(), url);
        pageQueries.createPage(page, dbHelper, true);
        Toast.makeText(this, "Página guardada!", Toast.LENGTH_SHORT).show();
        setPagesOnListView();
    }




}
