/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.Matisse.API;

/**
 *
 * @author Jairo
 */
public class Grupo {
    private String gid;
    private String nombre;
    /**
     * @return the gid
     */
    public String getGid() {
            return gid;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
            return nombre;
    }
    /**
     * @param gid the gid to set
     */
    public void setGid(String gid) {
            this.gid = gid;
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
            this.nombre = nombre;
    }
}
