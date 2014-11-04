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

package co.edu.uniandes.csw.Matisse.resultado.persistence;

import co.edu.uniandes.csw.Matisse.API.ServiciosAPI;
import co.edu.uniandes.csw.Matisse.grupo.logic.dto.GrupoDTO;
import co.edu.uniandes.csw.Matisse.opcion.dto.OpcionDTO;
import co.edu.uniandes.csw.Matisse.pregunta.logic.dto.PreguntaDTO;
import co.edu.uniandes.csw.Matisse.resultado.logic.dto.ResultadoDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.Matisse.resultado.persistence.api.IResultadoPersistence;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Default
@Stateless 
@LocalBean
public class ResultadoPersistence extends _ResultadoPersistence  implements IResultadoPersistence {

    public ResultadoDTO listarPreguntas(Integer semana) {
        ResultadoDTO resultado = new ResultadoDTO();
        ServiciosAPI limeSurvey = ServiciosAPI.getInstance();
        List<GrupoDTO> grupos = new ArrayList();
        JSONArray surveyGroups = limeSurvey.listGroups(68124);
        
        
        int cantG = surveyGroups.length();
        for (int i = 0; i < cantG; i++) {
            
            try {
                JSONObject groupActual = surveyGroups.getJSONObject(i);
                GrupoDTO grupo = new GrupoDTO();
                List<PreguntaDTO> pregs = new ArrayList<PreguntaDTO>();
                grupo.setName(groupActual.getString("group_name"));
                grupo.setId(groupActual.getJSONObject("id").getInt("gid"));
                JSONArray groupQuestions = limeSurvey.listQuestions(68124, groupActual.getInt("gid"));
                int cantQperG = groupQuestions.length();
                for (int j = 0; j < cantQperG; j++) {
                    
                    JSONObject qActual = groupQuestions.getJSONObject(i);
                    PreguntaDTO pregunta = new PreguntaDTO();
                    pregunta.setPregunta(qActual.getString("question"));
                    pregunta.setId(qActual.getJSONObject("id").getLong("qid"));
                    pregunta.setName(qActual.getString("title"));
                    pregs.add(pregunta);
                }
                grupo.setPreguntas(pregs);
                grupos.add(grupo);
            } catch (JSONException ex) {
            }   
        }
        return resultado;
    }

    public PreguntaDTO respuestaA(Integer id) {
        ServiciosAPI limeSurvey = ServiciosAPI.getInstance();
        JSONObject surveyQuestion = limeSurvey.getQuestionsProperties(id);
        PreguntaDTO pregunta = new PreguntaDTO();
        try {
        String titulo = surveyQuestion.getString("title");
        pregunta.setPregunta(surveyQuestion.getString("question"));
        pregunta.setName(titulo);
        pregunta.setId(id.longValue());
        OpcionDTO o1 = new OpcionDTO();
        OpcionDTO o2 = new OpcionDTO();
        OpcionDTO o3 = new OpcionDTO();
        OpcionDTO o4 = new OpcionDTO();
        JSONObject options = surveyQuestion.getJSONObject("answeroptions");
        o1.setLabel(options.getJSONObject("A1").getString("answer"));
        o1.setId("A1");
        o1.setLabel(options.getJSONObject("A2").getString("answer"));
        o1.setId("A2");
        o1.setLabel(options.getJSONObject("A3").getString("answer"));
        o1.setId("A3");
        o1.setLabel(options.getJSONObject("A4").getString("answer"));
        o1.setId("A4");
        List<OpcionDTO> opciones = new ArrayList();
        JSONObject respuestas = limeSurvey.exportResponses();
        
            JSONArray arrayRespuesta = respuestas.getJSONArray("responses");
            int longi = arrayRespuesta.length();
            int j = 463;
            for (int i = 0; i < longi; i++) {
                JSONObject actual = arrayRespuesta.getJSONObject(i);
                String opcio = actual.getJSONObject(j+"").getString(titulo);
                if (opcio.equals("A1")) {
                    o1.incCantidad();
                }
                else if(opcio.equals("A2")){
                    o2.incCantidad();
                }
                else if(opcio.equals("A3")){
                    o3.incCantidad();
                }
                else if(opcio.equals("A4")){
                    o4.incCantidad();
                }
               
            }
            opciones.add(o1);
            opciones.add(o2);
            opciones.add(o3);
            opciones.add(o4);
            pregunta.setOpciones(opciones);
        } catch (JSONException ex) {
            Logger.getLogger(ResultadoPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pregunta;   
    }
}