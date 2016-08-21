package rubixware.com.mobilepoint;


import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Internal storage data
public class Storage {

    public String fileName;
    private FileOutputStream outputStream;
    private Context context;


    Storage(String fileName) {
        this.fileName = fileName;
    }

    public Storage() {
    }

    public Storage(Context context) {
        this.context = context;
    }

    public void simplyStorage(String fileName,String text){

        String txt = stringLoaded(text);

        try {
            outputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            outputStream.write(txt.getBytes());
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String stringLoaded(String text){
        String txt = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            txt = txt +c;
        }
        return txt;
    }



    public String readSimplyStorage(String filename, String text){
        int ch;
        StringBuffer fileContent = new StringBuffer("");
        FileInputStream fis;
        try{
            fis = context.openFileInput(filename);
            try{
                while ((ch = fis.read()) != -1)
                    fileContent.append((char)ch);
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String data = new String(fileContent);
        String dataComplete = stringLoaded(data);
        return dataComplete;
    }


    public void multipleStorage(String fileName,String text, ArrayList<String> arrayList){
        try{
            arrayList.add(text);
            outputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            for (String t1: arrayList){
                outputStream.write((t1 + "\n").getBytes());
            }
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void multipleStorageRefresh(String fileName, ArrayList<String> arrayList){
        try{
            outputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            for (String t1: arrayList){
                outputStream.write((t1 + "\n").getBytes());
            }
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public ArrayList<String>readMultipleStorage(String fileName){
        ArrayList<String> arrayList = new ArrayList();
        try{
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader inputReader = new InputStreamReader(fis,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line;
            while ((line = bufferedReader.readLine())!= null){
                arrayList.add(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }







}
