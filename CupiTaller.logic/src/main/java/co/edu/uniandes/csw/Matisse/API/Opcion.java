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
public class Opcion {
    private int cantidad;
    private String codigo;
    private String respuesta;
    /**
     * @return the cantidad
     */
    public int getCantidad() {
            return cantidad;
    }
    /**
     * @return the codigo
     */
    public String getCodigo() {
            return codigo;
    }
    /**
     * @return the respuesta
     */
    public String getRespuesta() {
            return respuesta;
    }
    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
    }
    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
            this.codigo = codigo;
    }
    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
            this.respuesta = respuesta;
    }    
}
