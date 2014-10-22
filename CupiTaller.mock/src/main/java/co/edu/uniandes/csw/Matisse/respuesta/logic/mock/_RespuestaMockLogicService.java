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

package co.edu.uniandes.csw.Matisse.respuesta.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.Matisse.respuesta.logic.dto.RespuestaDTO;
import co.edu.uniandes.csw.Matisse.respuesta.logic.dto.RespuestaPageDTO;
import co.edu.uniandes.csw.Matisse.respuesta.logic.api._IRespuestaLogicService;

public abstract class _RespuestaMockLogicService implements _IRespuestaLogicService {

	private Long id= new Long(1);
	protected List<RespuestaDTO> data=new ArrayList<RespuestaDTO>();

	public RespuestaDTO createRespuesta(RespuestaDTO respuesta){
		id++;
		respuesta.setId(id);
		data.add(respuesta);
		return respuesta;
    }
    
    public List<RespuestaDTO> getRespuestas(){
		return data; 
	}

	public RespuestaPageDTO getRespuestas(Integer page, Integer maxRecords){
		RespuestaPageDTO response = new RespuestaPageDTO();
		response.setTotalRecords(Long.parseLong(data.size()+""));
		response.setRecords(data);
		return response;
	}

	public RespuestaDTO getRespuesta(Long id){
		for(RespuestaDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteRespuesta(Long id){
	    RespuestaDTO delete=null;
		for(RespuestaDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateRespuesta(RespuestaDTO respuesta){
	    RespuestaDTO delete=null;
		for(RespuestaDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(respuesta);
		} 
	}	
}