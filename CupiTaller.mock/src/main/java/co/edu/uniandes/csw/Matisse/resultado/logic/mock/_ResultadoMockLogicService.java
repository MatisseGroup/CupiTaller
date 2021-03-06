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

package co.edu.uniandes.csw.Matisse.resultado.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.Matisse.resultado.logic.dto.ResultadoDTO;
import co.edu.uniandes.csw.Matisse.resultado.logic.dto.ResultadoPageDTO;
import co.edu.uniandes.csw.Matisse.resultado.logic.api._IResultadoLogicService;

public abstract class _ResultadoMockLogicService implements _IResultadoLogicService {

	private Long id= new Long(1);
	protected List<ResultadoDTO> data=new ArrayList<ResultadoDTO>();

	public ResultadoDTO createResultado(ResultadoDTO resultado){
		id++;
		resultado.setId(id);
		data.add(resultado);
		return resultado;
    }
    
    public List<ResultadoDTO> getResultados(){
		return data; 
	}

	public ResultadoPageDTO getResultados(Integer page, Integer maxRecords){
		ResultadoPageDTO response = new ResultadoPageDTO();
		response.setTotalRecords(Long.parseLong(data.size()+""));
		response.setRecords(data);
		return response;
	}

	public ResultadoDTO getResultado(Long id){
		for(ResultadoDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteResultado(Long id){
	    ResultadoDTO delete=null;
		for(ResultadoDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateResultado(ResultadoDTO resultado){
	    ResultadoDTO delete=null;
		for(ResultadoDTO data1:data){
			if(data1.getId().equals(resultado.getId())){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(resultado);
		} 
	}	
}