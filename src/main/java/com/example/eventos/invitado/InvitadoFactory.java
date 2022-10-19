package com.example.eventos.invitado;

import static com.example.eventos.config.Constants.INVITADO_TIPO_MAYOR;
import static com.example.eventos.config.Constants.INVITADO_TIPO_NINYO;

public class InvitadoFactory {

    public static Invitado crearInvitado(String id, String eventoId, String mesaId, String nombre, String tipo, String descripcion) {
        if (INVITADO_TIPO_MAYOR.equals(tipo)) {
            return new InvitadoMayor(id, eventoId, mesaId, nombre, tipo, descripcion);
        }
        else if (INVITADO_TIPO_NINYO.equals(tipo)){
            return new InvitadoNinyo(id, eventoId, mesaId, nombre, tipo, descripcion);
        }
        else {
            return new InvitadoMayor(id, eventoId, mesaId, nombre, tipo, descripcion);
        }
    }

    public static Invitado crearInvitadoFromTextExcel(String textoExcel, String eventoId) {
        String[] myData = textoExcel.split("-");
        String nombre = "";
        String tipo = "";
        String descripcion = "";

        for (int i = 0; i < myData.length; i++) {
            if (i == 0) {
                nombre = myData[0].trim();
            }
            else{
                if (myData[i].trim().equals("x")) {
                    tipo = INVITADO_TIPO_NINYO;
                }
                else{
                    descripcion = myData[i].trim();
                }
            }
        }
        if(tipo.isEmpty()){
            tipo = INVITADO_TIPO_MAYOR;
        }

        return crearInvitado(null, eventoId, null, nombre, tipo, descripcion);
    }
}
