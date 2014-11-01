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
  
  
  Source generated by CrudMaker version 1.0.0.201410261249*/

package co.edu.uniandes.csw.Matisse.estudiante.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.Matisse.estudiante.logic.dto.EstudianteDTO;
import co.edu.uniandes.csw.Matisse.estudiante.logic.dto.EstudiantePageDTO;
import co.edu.uniandes.csw.Matisse.estudiante.logic.api._IEstudianteLogicService;
import co.edu.uniandes.csw.Matisse.estudiante.persistence.api.IEstudiantePersistence;

public abstract class _EstudianteLogicService implements _IEstudianteLogicService {

	@Inject
	protected IEstudiantePersistence persistance;

	public EstudianteDTO createEstudiante(EstudianteDTO estudiante){
		return persistance.createEstudiante( estudiante); 
    }

	public List<EstudianteDTO> getEstudiantes(){
		return persistance.getEstudiantes(); 
	}

	public EstudiantePageDTO getEstudiantes(Integer page, Integer maxRecords){
		return persistance.getEstudiantes(page, maxRecords); 
	}

	public EstudianteDTO getEstudiante(Long id){
		return persistance.getEstudiante(id); 
	}

	public void deleteEstudiante(Long id){
	    persistance.deleteEstudiante(id); 
	}

	public void updateEstudiante(EstudianteDTO estudiante){
	    persistance.updateEstudiante(estudiante); 
	}	
}