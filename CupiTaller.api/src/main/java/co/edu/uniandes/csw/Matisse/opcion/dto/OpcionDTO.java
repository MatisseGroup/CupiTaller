/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.Matisse.opcion.dto;

/**
 *
 * @author Jairo
 */
public class OpcionDTO {

    private String id;

    private String label;

    private int cantidad;

    public OpcionDTO() {
        cantidad = 0;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void incCantidad() {
        cantidad++;
    }
}
