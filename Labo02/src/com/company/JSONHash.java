package com.company;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONHash {

    private final String filename;
    private JSONObject obj;

    public JSONHash(String filename) {
        this.obj = null;
        this.filename = filename;
    }

    public boolean load() throws FileNotFoundException, JSONException, LabINF2050Exception {
        boolean resultat = false;
        try {
            String stringJson = IOUtils.toString(new FileInputStream(this.filename), "UTF-8");
            JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(stringJson);
            this.obj = jsonObj;
            resultat = true;
        }catch (FileNotFoundException E) {
            throw new LabINF2050Exception("Le fichier d'entrée n'existe pas");
        }catch (JSONException e){
            throw new LabINF2050Exception("Le fichier Json n'est pas valide");
        } catch (IOException e){
            throw new LabINF2050Exception(e.toString());
        }
        return resultat;
    }

    public int countAlbum(){
        int resultat = 0;
        JSONArray collection = (JSONArray) JSONSerializer.toJSON(obj.getString("collection"));
        for(int i = 0; i < collection.size(); i++){
            if(collection.getJSONObject(i).getString("type").equals("album")){
                resultat+=1;
            }
        }
        return resultat;
    }

    public int countSingle(){
        int resultat = 0;
        JSONArray collection = (JSONArray) JSONSerializer.toJSON(obj.getString("collection"));
        for(int i = 0; i < collection.size(); i++){
            if(collection.getJSONObject(i).getString("type").equals("single")){
                resultat+=1;
            }
        }
        return resultat;
    }

    public void getListeAlbumFrom2003(){

        ArrayList<JSONObject> liste = new ArrayList<>();

        JSONArray collection = (JSONArray) JSONSerializer.toJSON(obj.getString("collection"));
        for(int i = 0; i < collection.size(); i++){
            if(collection.getJSONObject(i).getInt("publication")>= 2003){
                accumulateJSON(liste, collection.getJSONObject(i));
            }
        }
        liste.forEach((elem) -> {
            System.out.println(elem.toString(3));
        });
    }

    public void accumulateJSON(ArrayList<JSONObject> liste, JSONObject myObj){
        JSONObject msgObj = new JSONObject();
        msgObj.put("type", myObj.getString("type"));
        msgObj.put("artist", myObj.getString("artist"));
        msgObj.put("title", myObj.getString("title"));
        msgObj.put("publication", myObj.getInt("publication"));
        msgObj.put("rating", myObj.getInt("rating"));

        liste.add(msgObj);
    }


    public void getListeAlbumPreferes() throws LabINF2050Exception{

        ArrayList<JSONObject> liste = new ArrayList<>();
        JSONArray collection = (JSONArray) JSONSerializer.toJSON(obj.getString("collection"));
        for(int i = 0; i < collection.size(); i++){

            if(collection.getJSONObject(i).getString("title").equals("Century Child")
                    || collection.getJSONObject(i).getString("title").equals("The System has Failed")
                    || collection.getJSONObject(i).getString("title").equals("Land of the Free")){

                accumulateJSON(liste, collection.getJSONObject(i));
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("preferes", liste);
        System.out.println(jsonObject.toString(3));
        save("albumPreferes", jsonObject);
    }


    public void getListeAlbumNote5() {
        JSONObject msgObj = new JSONObject();
        ArrayList<JSONObject> liste = new ArrayList<>();

        JSONArray collection = (JSONArray) JSONSerializer.toJSON(obj.getString("collection"));
        for(int i = 0; i < collection.size(); i++){
            if(collection.getJSONObject(i).getInt("rating") == 5){
                accumulateJSON(liste, collection.getJSONObject(i));
            }
        }
        liste.forEach((elem) ->{
            System.out.println(elem.toString(3));
        });
    }

    public boolean save(String filename, JSONObject jsonObj) throws LabINF2050Exception{
        boolean resultat;
        try(FileWriter f = new FileWriter(filename)){
            f.write(jsonObj.toString(3));
            f.flush();
            f.close();
            resultat = true;
        } catch(IOException e){
            throw new LabINF2050Exception(e.toString());
        }
        return resultat;
    }
}
