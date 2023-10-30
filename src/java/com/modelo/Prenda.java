package com.modelo;



public class Prenda {
    private String nombre;
    private String color;
    private String talla;
    private String descripcion;
    private String precio;
    
    public Prenda(){
    }
     public Prenda(String nombre, String precio){
         this.nombre = nombre;
         this.precio = precio;
    }

    public Prenda(String nombre, String color, String talla, String descripcion, String precio) {
        this.nombre = nombre;
        this.color = color;
        this.talla = talla;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio (String precio) {
        this.precio = precio;
    }
    
    
    
    
    
     
}
