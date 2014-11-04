/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.Matisse.API;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jairo
 */
public class Pregunta {
    private String identificador;
    private String pregunta;
    private List<Opcion> opciones;
    private String questionID;
    /**
     * @return the questionID
     */
    public String getQuestionID() {
            return questionID;
    }
    /**
     * @return the identificador
     */
    public String getIdentificador() {
            return identificador;
    }
    /**
     * @return the pregunta
     */
    public String getPregunta() {
            return pregunta;
    }
    /**
     * @return the opciones
     */
    public List<Opcion> getOpciones() {
            return opciones;
    }
    /**
     * @param questionID the questionID to set
     */
    public void setQuestionID(String questionID) {
            this.questionID = questionID;
    }
    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
            this.identificador = identificador;
    }
    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(String pregunta) {
            this.pregunta = pregunta;
    }
    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(ArrayList<Opcion> opciones) {
            this.opciones = opciones;
    }
}
