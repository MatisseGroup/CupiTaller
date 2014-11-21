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

package co.edu.uniandes.csw.Matisse.entradas.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.Matisse.entradas.logic.dto.EntradasDTO;
import co.edu.uniandes.csw.Matisse.entradas.logic.dto.EntradasPageDTO;
import co.edu.uniandes.csw.Matisse.entradas.persistence.api._IEntradasPersistence;
import co.edu.uniandes.csw.Matisse.entradas.persistence.converter.EntradasConverter;
import co.edu.uniandes.csw.Matisse.entradas.persistence.entity.EntradasEntity;

public abstract class _EntradasPersistence implements _IEntradasPersistence {

  	@PersistenceContext(unitName="CupiTallerPU")
 
	protected EntityManager entityManager;
	
	public EntradasDTO createEntradas(EntradasDTO entradas) {
		EntradasEntity entity=EntradasConverter.persistenceDTO2Entity(entradas);
		entityManager.persist(entity);
		return EntradasConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<EntradasDTO> getEntradass() {
		Query q = entityManager.createQuery("select u from EntradasEntity u");
		return EntradasConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public EntradasPageDTO getEntradass(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from EntradasEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from EntradasEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		EntradasPageDTO response = new EntradasPageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(EntradasConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public EntradasDTO getEntradas(Long id) {
		return EntradasConverter.entity2PersistenceDTO(entityManager.find(EntradasEntity.class, id));
	}

	public void deleteEntradas(Long id) {
		EntradasEntity entity=entityManager.find(EntradasEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateEntradas(EntradasDTO detail) {
		EntradasEntity entity=entityManager.merge(EntradasConverter.persistenceDTO2Entity(detail));
		EntradasConverter.entity2PersistenceDTO(entity);
	}

}