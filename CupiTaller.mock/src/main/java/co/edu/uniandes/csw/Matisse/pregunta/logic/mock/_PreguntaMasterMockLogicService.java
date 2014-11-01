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

package co.edu.uniandes.csw.Matisse.pregunta.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.Matisse.pregunta.logic.dto.PreguntaDTO;
import co.edu.uniandes.csw.Matisse.pregunta.logic.api.IPreguntaLogicService;
import co.edu.uniandes.csw.Matisse.pregunta.master.logic.api._IPreguntaMasterLogicService;
import co.edu.uniandes.csw.Matisse.pregunta.master.logic.dto.PreguntaMasterDTO;
import co.edu.uniandes.csw.Matisse.respuesta.logic.api.IRespuestaLogicService;
import co.edu.uniandes.csw.Matisse.respuesta.logic.dto.RespuestaDTO;
import javax.inject.Inject;


public abstract class _PreguntaMasterMockLogicService implements _IPreguntaMasterLogicService {

    protected static ArrayList<PreguntaMasterDTO> preguntaMasterDtosList = new ArrayList<PreguntaMasterDTO>() ;
    @Inject
    protected IRespuestaLogicService respuestaPersistance;
    @Inject
    protected IPreguntaLogicService preguntaPersistance;

    public PreguntaMasterDTO createMasterPregunta(PreguntaMasterDTO pregunta) {

        preguntaPersistance.createPregunta(pregunta.getPreguntaEntity());
        for (RespuestaDTO dto : pregunta.getCreaterespuesta()) {
            pregunta.getListrespuesta().add(respuestaPersistance.createRespuesta(dto));
        }
        preguntaMasterDtosList.add(pregunta);
        return pregunta;
    }

    public PreguntaMasterDTO getMasterPregunta(Long id) {
        for (PreguntaMasterDTO preguntaMasterDTO : preguntaMasterDtosList) {
            if (preguntaMasterDTO.getPreguntaEntity().getId() == id) {
                return preguntaMasterDTO;
            }
        }

        return null;
    }

    public void deleteMasterPregunta(Long id) {
        for (PreguntaMasterDTO preguntaMasterDTO : preguntaMasterDtosList) {
            if (preguntaMasterDTO.getPreguntaEntity().getId() == id) {

                for (RespuestaDTO dto : preguntaMasterDTO.getCreaterespuesta()) {
                    respuestaPersistance.deleteRespuesta(dto.getId());
                }
                preguntaPersistance.deletePregunta(preguntaMasterDTO.getId());
                preguntaMasterDtosList.remove(preguntaMasterDTO);
            }
        }

    }

    public void updateMasterPregunta(PreguntaMasterDTO pregunta) {

		PreguntaMasterDTO currentPregunta = getMasterPregunta(pregunta.getPreguntaEntity().getId());
		if (currentPregunta == null) {
			currentPregunta = pregunta;
		}else{
			preguntaMasterDtosList.remove(currentPregunta);
		}

        // update Respuesta
        if (pregunta.getUpdaterespuesta() != null) {
            for (RespuestaDTO dto : pregunta.getUpdaterespuesta()) {
                respuestaPersistance.updateRespuesta(dto);
                for (RespuestaDTO respuestadto : currentPregunta.getListrespuesta()) {
					if (respuestadto.getId() == dto.getId()) {
						currentPregunta.getListrespuesta().remove(respuestadto);
						currentPregunta.getListrespuesta().add(dto);
					}
				}
            }
        }
        // persist new Respuesta
        if (pregunta.getCreaterespuesta() != null) {
            for (RespuestaDTO dto : pregunta.getCreaterespuesta()) {
                RespuestaDTO persistedRespuestaDTO = respuestaPersistance.createRespuesta(dto);
                dto = persistedRespuestaDTO;
                currentPregunta.getListrespuesta().add(dto);
            }
        }
        // delete Respuesta
        if (pregunta.getDeleterespuesta() != null) {
            for (RespuestaDTO dto : pregunta.getDeleterespuesta()) {
				for (RespuestaDTO respuestadto : currentPregunta.getListrespuesta()) {
					if (respuestadto.getId() == dto.getId()) {
						currentPregunta.getListrespuesta().remove(respuestadto);
					}
				}
                respuestaPersistance.deleteRespuesta(dto.getId());
            }
        }
        preguntaMasterDtosList.add(currentPregunta);
    }
}