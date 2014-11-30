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
package co.edu.uniandes.csw.Matisse.respuesta.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.Matisse.respuesta.logic.dto.RespuestaDTO;
import co.edu.uniandes.csw.Matisse.respuesta.logic.dto.RespuestaPageDTO;
import co.edu.uniandes.csw.Matisse.respuesta.persistence.api._IRespuestaPersistence;
import co.edu.uniandes.csw.Matisse.respuesta.persistence.converter.RespuestaConverter;
import co.edu.uniandes.csw.Matisse.respuesta.persistence.entity.RespuestaEntity;

public abstract class _RespuestaPersistence implements _IRespuestaPersistence {

    @PersistenceContext(unitName = "CupiTallerPU")

    protected EntityManager entityManager;

    public RespuestaDTO createRespuesta(RespuestaDTO respuesta) {
        RespuestaEntity entity = RespuestaConverter.persistenceDTO2Entity(respuesta);
        entityManager.persist(entity);
        return RespuestaConverter.entity2PersistenceDTO(entity);
    }

    @SuppressWarnings("unchecked")
    public List<RespuestaDTO> getRespuestas() {
        Query q = entityManager.createQuery("select u from RespuestaEntity u");
        return RespuestaConverter.entity2PersistenceDTOList(q.getResultList());
    }

    @SuppressWarnings("unchecked")
    public RespuestaPageDTO getRespuestas(Integer page, Integer maxRecords) {
        Query count = entityManager.createQuery("select count(u) from RespuestaEntity u");
        Long regCount = 0L;
        regCount = Long.parseLong(count.getSingleResult().toString());
        Query q = entityManager.createQuery("select u from RespuestaEntity u");
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        RespuestaPageDTO response = new RespuestaPageDTO();
        response.setTotalRecords(regCount);
        response.setRecords(RespuestaConverter.entity2PersistenceDTOList(q.getResultList()));
        return response;
    }

    public RespuestaDTO getRespuesta(Long id) {
        return RespuestaConverter.entity2PersistenceDTO(entityManager.find(RespuestaEntity.class, id));
    }

    public void deleteRespuesta(Long id) {
        RespuestaEntity entity = entityManager.find(RespuestaEntity.class, id);
        entityManager.remove(entity);
    }

    public void updateRespuesta(RespuestaDTO detail) {
        RespuestaEntity entity = entityManager.merge(RespuestaConverter.persistenceDTO2Entity(detail));
        RespuestaConverter.entity2PersistenceDTO(entity);
    }

}
