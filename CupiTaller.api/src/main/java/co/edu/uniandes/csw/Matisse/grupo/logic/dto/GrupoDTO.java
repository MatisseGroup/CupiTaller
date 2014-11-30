/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.Matisse.grupo.logic.dto;

import co.edu.uniandes.csw.Matisse.pregunta.logic.dto.PreguntaDTO;
import java.util.List;

/**
 *
 * @author Jairo
 */
public class GrupoDTO {

    private List<PreguntaDTO> preguntas;

    private String name;

    private Integer id;

    public List<PreguntaDTO> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaDTO> preguntas) {
        this.preguntas = preguntas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
