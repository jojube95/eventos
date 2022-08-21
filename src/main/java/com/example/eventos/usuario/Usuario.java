package com.example.eventos.usuario;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document("usuario")
public class Usuario {
    @Id
    private String id;

    @Indexed(unique = true)
    private String nombre;

    private String contrasenya;
    private String rol;

    public Usuario(){

    }

    public Usuario(String nombre, String contrasenya, String rol) {
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.rol = rol;
    }

    public Usuario(String id, String nombre, String contrasenya, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nombre, usuario.nombre) && Objects.equals(contrasenya, usuario.contrasenya) && Objects.equals(rol, usuario.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, contrasenya, rol);
    }
}
