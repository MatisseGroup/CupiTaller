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

package co.edu.uniandes.csw.Matisse.resultado.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.Matisse.resultado.logic.api.IResultadoLogicService;
import co.edu.uniandes.csw.Matisse.resultado.logic.dto.ResultadoDTO;
import co.edu.uniandes.csw.Matisse.resultado.logic.dto.ResultadoPageDTO;


public abstract class _ResultadoService {

	@Inject
	protected IResultadoLogicService resultadoLogicService;
	
	@POST
	public ResultadoDTO createResultado(ResultadoDTO resultado){
		return resultadoLogicService.createResultado(resultado);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteResultado(@PathParam("id") Long id){
		resultadoLogicService.deleteResultado(id);
	}
	
	@GET
	public ResultadoPageDTO getResultados(@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords){
		return resultadoLogicService.getResultados(page, maxRecords);
	}
	
	@GET
	@Path("{id}")
	public ResultadoDTO getResultado(@PathParam("id") Long id){
		return resultadoLogicService.getResultado(id);
	}
	
	@PUT
	public void updateResultado(@PathParam("id") Long id, ResultadoDTO resultado){
		resultadoLogicService.updateResultado(resultado);
	}
	
}