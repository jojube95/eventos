package com.example.eventos.distribucion;

import com.example.eventos.mesa.Mesa;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.Objects;

public class Distribucion {
    private String distribucion;

    public Distribucion() {

    }

    public void updateMesa(Mesa mesa) throws JSONException {
        JSONObject distribucionJson = new JSONObject(this.distribucion);

        JSONArray objects = distribucionJson.getJSONArray("objects");
        int mesaIndex = -1;

        for (int i = 0; i < objects.length(); i++) {
            if(objects.getJSONObject(i).getString("mesaId").equals(mesa.getId())){
                mesaIndex = i;
            }
        }
        
        if (mesaIndex != -1) {
            distribucionJson.getJSONArray("objects").getJSONObject(mesaIndex).put("numero", mesa.getNumero());
            distribucionJson.getJSONArray("objects").getJSONObject(mesaIndex).put("personas", mesa.getPersonas());

            distribucionJson.getJSONArray("objects").getJSONObject(mesaIndex).getJSONArray("objects").getJSONObject(1).put("text", "T-" + mesa.getNumero() + "\n" + mesa.getPersonas() + "p");
        }

        this.distribucion = distribucionJson.toString();
    }

    public void deleteMesa(Mesa mesa) throws JSONException {
        JSONObject distribucionJson = new JSONObject(this.distribucion);

        JSONArray objects = distribucionJson.getJSONArray("objects");
        int mesaIndex = -1;

        for (int i = 0; i < objects.length(); i++) {
            if(objects.getJSONObject(i).getString("mesaId").equals(mesa.getId())){
                mesaIndex = i;
            }
        }

        if (mesaIndex != -1) {
            distribucionJson.getJSONArray("objects").remove(mesaIndex);
        }

        this.distribucion = distribucionJson.toString();
    }

    public Distribucion(String distribucion) {
        this.distribucion = distribucion;
    }

    public String getDistribucion() {
        return distribucion;
    }

    public void setDistribucion(String distribucion) {
        this.distribucion = distribucion;
    }

    @Override
    public String toString() {
        return "Distribucion{" +
                "distribucion='" + distribucion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distribucion that = (Distribucion) o;
        return Objects.equals(distribucion, that.distribucion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distribucion);
    }
}
