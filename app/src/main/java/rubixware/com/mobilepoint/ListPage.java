package rubixware.com.mobilepoint;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListPage extends Activity {

    public static String filename = "directionsStorage";
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    private Storage s1;
    public ArrayList<String>readDirections;
    final Context context = this;
    private EditText directionFiled;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);

        //creamos un objeto del tipo storage con el contexto de est actividad
        s1 = new Storage(getApplicationContext());
        //Creamos el arraylist con los datos del archivo
        readDirections = s1.readMultipleStorage(filename);
        fillListView(readDirections);
    }


    @Override
    protected void onResume() {

        final SwipeListViewTouchListener touchListener =new SwipeListViewTouchListener(listView,new SwipeListViewTouchListener.OnSwipeCallback() {
            @Override
            public void onSwipeLeft(ListView listView, final int [] reverseSortedPositions) {
                //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
                dialogAlert(reverseSortedPositions);
            }


            @Override
            public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
                //Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
                dialogAlert(reverseSortedPositions);
            }
        },true, false);

        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener(touchListener.makeScrollListener());
        super.onResume();

    }



    private String direction(){
        directionFiled = (EditText) findViewById(R.id.url);
        String direction = directionFiled.getText().toString();
        return direction.replace(String.valueOf((char) 32), "");
    }

    private boolean checkBlankSpace(String direction){
        if(direction.equals("")){
            Toast.makeText(this, "Introduzca una pagina",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            return false;
        }
    }

    private String directionComplete(String direction,boolean checkBlankSpace){
        if(checkBlankSpace){ return "";}
        else {
            return "http://"+direction.toLowerCase();}
    }

    private boolean checkPageExists(String directionComplete, boolean checkBlankSpace){
        if(checkBlankSpace){ return true; }
        else if(readDirections.contains(directionComplete)){
            Toast.makeText(this, "Página ya guardada, intente con otra.",Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }
    }

    private boolean savePage(boolean checkPageExists, String directionComplete){
        if (checkPageExists){ return true; }
        else{
            saveOnFile(directionComplete);
            return false;
        }
    }

    private void saveOnFile(String directionComplete){
        readDirections = s1.readMultipleStorage(filename);
        s1.multipleStorage(filename, directionComplete, readDirections);
        Toast.makeText(this, "Pagina guardada:  " + directionComplete,Toast.LENGTH_SHORT).show();
        fillListView(readDirections);
    }

    public void sendDirection(View button){
        String direction = direction();
        boolean checkBlankSpace = checkBlankSpace(direction);
        String directionComplete = directionComplete(direction,checkBlankSpace);
        boolean checkPageExists= checkPageExists(directionComplete,checkBlankSpace);
        boolean savePage = savePage(checkPageExists, directionComplete);
    }


    public void fillListView(ArrayList arrayList){
        listView = (ListView) findViewById(R.id.dirListView);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }


    private void dialogAlert(final int [] reverseSortedPositions){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Confirmacion de borrado");
        alertDialogBuilder
                .setMessage("Seguro que desea borrar esta pagina?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        confirmDelete(reverseSortedPositions);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    private void confirmDelete(int [] reverseSortedPositions) {
        readDirections.remove(reverseSortedPositions[0]);
        arrayAdapter.notifyDataSetChanged();
        s1.multipleStorageRefresh(filename, readDirections);
    }


}