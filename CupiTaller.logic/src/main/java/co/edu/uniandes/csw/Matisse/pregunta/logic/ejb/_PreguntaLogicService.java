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
  
  
  Source generated by CrudMaker version 1.0.0.201410152247*/

package co.edu.uniandes.csw.Matisse.pregunta.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.Matisse.pregunta.logic.dto.PreguntaDTO;
import co.edu.uniandes.csw.Matisse.pregunta.logic.dto.PreguntaPageDTO;
import co.edu.uniandes.csw.Matisse.pregunta.logic.api._IPreguntaLogicService;
import co.edu.uniandes.csw.Matisse.pregunta.persistence.api.IPreguntaPersistence;

public abstract class _PreguntaLogicService implements _IPreguntaLogicService {

	@Inject
	protected IPreguntaPersistence persistance;

	public PreguntaDTO createPregunta(PreguntaDTO pregunta){
		return persistance.createPregunta( pregunta); 
    }

	public List<PreguntaDTO> getPreguntas(){
		return persistance.getPreguntas(); 
	}

	public PreguntaPageDTO getPreguntas(Integer page, Integer maxRecords){
		return persistance.getPreguntas(page, maxRecords); 
	}

	public PreguntaDTO getPregunta(Long id){
		return persistance.getPregunta(id); 
	}

	public void deletePregunta(Long id){
	    persistance.deletePregunta(id); 
	}

	public void updatePregunta(PreguntaDTO pregunta){
	    persistance.updatePregunta(pregunta); 
	}	
}