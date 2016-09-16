package rubixware.com.mobilepoint;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PageForm  extends Activity{

    Intent intent;
    String username;
    User user;
    DataBaseHelper dbHelper = new DataBaseHelper(this, null, null, 1);
    PageQueries pageQueries = new PageQueries();
    UserQueries userQueries = new UserQueries();
    ArrayAdapter<String> pagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_form);
        intent = getIntent();
        username = intent.getStringExtra("username");
        user = userQueries.showUser(username, dbHelper);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        setFaIconsToButtons(font);
        setPagesOnListview(getUserPages());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void setFaIconsToButtons(Typeface font){
        Button goBack = (Button) findViewById(R.id.go_back);
        goBack.setTypeface(font);
    }

    public void goBack(View view){
        Intent intent = new Intent(PageForm.this, PagesOption.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private String getUrlTextField(){
        EditText urlText = (EditText) findViewById(R.id.url);
        String url = urlText.getText().toString();
        return url.trim().replaceAll("\\s+", "");
    }


    public void createPage(View view){
        if (getUrlTextField().isEmpty()){
            Toast.makeText(this, "Introduzca una dirección", Toast.LENGTH_SHORT).show();
        }else{
            Page checkPage = pageQueries.showPage(getUrlTextField(), user.getId(), dbHelper);
            if (checkPage == null){
                Page newPage = new Page(user.getId(), getUrlTextField());
                pageQueries.createPage(newPage, dbHelper, false);
                Toast.makeText(this, "Página guardada!", Toast.LENGTH_SHORT).show();
                setPagesOnListview(getUserPages());
            }else{
                Toast.makeText(this, "El usuario ya tiene esa página", Toast.LENGTH_LONG).show();
            }
        }
    }

    private ArrayList<String> getUserPages(){
        ArrayList<Page> pagesCollection = pageQueries.getUserPages(user.getId(), dbHelper);
        ArrayList<String> pagesArray = new ArrayList<>();
        for (int i =0; i < pagesCollection  .size(); i++){
            pagesArray.add(pagesCollection.get(i).getUrl());
        }
        return pagesArray;
    }

    private void setPagesOnListview(ArrayList<String> pagesArray){
        pagesAdapter = new ArrayAdapter<String>(this, R.layout.custom_listview, android.R.id.text1, pagesArray);
        final ListView listView = (ListView) findViewById(R.id.page_list);
        listView.setAdapter(pagesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pageUrl = (String) listView.getItemAtPosition(position);
                deletePageAlert(pageUrl, position);
            }
        });
    }

    private void deletePageAlert(final String url, final int position){
        AlertDialog.Builder pageDelete = new AlertDialog.Builder(this);
        pageDelete.setTitle("Eliminar Página")
                .setMessage("Seguro que desea eliminar la página: "+url+" ?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletePage(url, position);
                    }
                });
        pageDelete.create();
        pageDelete.show();
    }

    private void deletePage(String url, int position){
        if (pageQueries.deletePage(url, user.getId(), dbHelper)){
            Toast.makeText(this, "Se ha eliminado la página: "+url, Toast.LENGTH_LONG).show();
            pagesAdapter.remove(pagesAdapter.getItem(position));
            pagesAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "NO se pudo eliminar la página", Toast.LENGTH_LONG).show();
        }
    }




}
