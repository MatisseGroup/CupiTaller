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
  
  
  Source generated by CrudMaker version 1.0.0.201408112050*/

package co.edu.uniandes.csw.Matisse.encuesta.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.Matisse.encuesta.logic.dto.EncuestaDTO;
import co.edu.uniandes.csw.Matisse.encuesta.persistence.api._IEncuestaPersistence;
import co.edu.uniandes.csw.Matisse.encuesta.persistence.converter.EncuestaConverter;
import co.edu.uniandes.csw.Matisse.encuesta.persistence.entity.EncuestaEntity;

public abstract class _EncuestaPersistence implements _IEncuestaPersistence {

  	@PersistenceContext(unitName="CupiTallerPU")
 
	protected EntityManager entityManager;
	
	public EncuestaDTO createEncuesta(EncuestaDTO encuesta) {
		EncuestaEntity entity=EncuestaConverter.persistenceDTO2Entity(encuesta);
		entityManager.persist(entity);
		return EncuestaConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<EncuestaDTO> getEncuestas() {
		Query q = entityManager.createQuery("select u from EncuestaEntity u");
		return EncuestaConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public EncuestaDTO getEncuesta(Long id) {
		return EncuestaConverter.entity2PersistenceDTO(entityManager.find(EncuestaEntity.class, id));
	}

	public void deleteEncuesta(Long id) {
		EncuestaEntity entity=entityManager.find(EncuestaEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateEncuesta(EncuestaDTO detail) {
		EncuestaEntity entity=entityManager.merge(EncuestaConverter.persistenceDTO2Entity(detail));
		EncuestaConverter.entity2PersistenceDTO(entity);
	}

}