/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.seguridad;

/**
 *
 * @author visitante
 */
public class Bodega {

    public Bodega(String id, String idTipobodega, String nombre, String direccion, String estado) {
        this.id = id;
        this.idTipobodega = idTipobodega;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getIdTipobodega() {
        return idTipobodega;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdTipobodega(String idTipobodega) {
        this.idTipobodega = idTipobodega;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Bodega{" + "id=" + id + ", idTipobodega=" + idTipobodega + ", nombre=" + nombre + ", direccion=" + direccion + ", estado=" + estado + '}';
    }
    
    public Bodega() {
    }
    
    String id;
    String idTipobodega;
    String nombre;
    String direccion;
    String estado;

}
