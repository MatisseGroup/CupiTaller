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
package co.edu.uniandes.csw.Matisse.resultado.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.Matisse.resultado.logic.dto.ResultadoDTO;
import co.edu.uniandes.csw.Matisse.resultado.logic.dto.ResultadoPageDTO;
import co.edu.uniandes.csw.Matisse.resultado.persistence.api._IResultadoPersistence;
import co.edu.uniandes.csw.Matisse.resultado.persistence.converter.ResultadoConverter;
import co.edu.uniandes.csw.Matisse.resultado.persistence.entity.ResultadoEntity;

public abstract class _ResultadoPersistence implements _IResultadoPersistence {

    @PersistenceContext(unitName = "CupiTallerPU")

    protected EntityManager entityManager;

    public ResultadoDTO createResultado(ResultadoDTO resultado) {
        ResultadoEntity entity = ResultadoConverter.persistenceDTO2Entity(resultado);
        entityManager.persist(entity);
        return ResultadoConverter.entity2PersistenceDTO(entity);
    }

    @SuppressWarnings("unchecked")
    public List<ResultadoDTO> getResultados() {
        Query q = entityManager.createQuery("select u from ResultadoEntity u");
        return ResultadoConverter.entity2PersistenceDTOList(q.getResultList());
    }

    @SuppressWarnings("unchecked")
    public ResultadoPageDTO getResultados(Integer page, Integer maxRecords) {
        Query count = entityManager.createQuery("select count(u) from ResultadoEntity u");
        Long regCount = 0L;
        regCount = Long.parseLong(count.getSingleResult().toString());
        Query q = entityManager.createQuery("select u from ResultadoEntity u");
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        ResultadoPageDTO response = new ResultadoPageDTO();
        response.setTotalRecords(regCount);
        response.setRecords(ResultadoConverter.entity2PersistenceDTOList(q.getResultList()));
        return response;
    }

    public ResultadoDTO getResultado(Long id) {
        return ResultadoConverter.entity2PersistenceDTO(entityManager.find(ResultadoEntity.class, id));
    }

    public void deleteResultado(Long id) {
        ResultadoEntity entity = entityManager.find(ResultadoEntity.class, id);
        entityManager.remove(entity);
    }

    public void updateResultado(ResultadoDTO detail) {
        ResultadoEntity entity = entityManager.merge(ResultadoConverter.persistenceDTO2Entity(detail));
        ResultadoConverter.entity2PersistenceDTO(entity);
    }

}
