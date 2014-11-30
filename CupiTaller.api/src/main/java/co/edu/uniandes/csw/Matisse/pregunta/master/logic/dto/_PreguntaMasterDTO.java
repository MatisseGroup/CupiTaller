/* ========================================================================
 * Copyright 2014 Matisse
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 Matisse
  
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
  
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
  
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 * ========================================================================
  
  
 Source generated by CrudMaker version 1.0.0.201411201032*/
package co.edu.uniandes.csw.Matisse.pregunta.master.logic.dto;

import co.edu.uniandes.csw.Matisse.respuesta.logic.dto.RespuestaDTO;
import co.edu.uniandes.csw.Matisse.pregunta.logic.dto.PreguntaDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _PreguntaMasterDTO {

    protected PreguntaDTO preguntaEntity;
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PreguntaDTO getPreguntaEntity() {
        return preguntaEntity;
    }

    public void setPreguntaEntity(PreguntaDTO preguntaEntity) {
        this.preguntaEntity = preguntaEntity;
    }

    public List<RespuestaDTO> createrespuesta;
    public List<RespuestaDTO> updaterespuesta;
    public List<RespuestaDTO> deleterespuesta;
    public List<RespuestaDTO> listrespuesta;

    public List<RespuestaDTO> getCreaterespuesta() {
        return createrespuesta;
    }

    ;
    public void setCreaterespuesta(List<RespuestaDTO> createrespuesta) {
        this.createrespuesta = createrespuesta;
    }

    ;
    public List<RespuestaDTO> getUpdaterespuesta() {
        return updaterespuesta;
    }

    ;
    public void setUpdaterespuesta(List<RespuestaDTO> updaterespuesta) {
        this.updaterespuesta = updaterespuesta;
    }

    ;
    public List<RespuestaDTO> getDeleterespuesta() {
        return deleterespuesta;
    }

    ;
    public void setDeleterespuesta(List<RespuestaDTO> deleterespuesta) {
        this.deleterespuesta = deleterespuesta;
    }

    ;
    public List<RespuestaDTO> getListrespuesta() {
        return listrespuesta;
    }

    ;
    public void setListrespuesta(List<RespuestaDTO> listrespuesta) {
        this.listrespuesta = listrespuesta;
    }
;

}
