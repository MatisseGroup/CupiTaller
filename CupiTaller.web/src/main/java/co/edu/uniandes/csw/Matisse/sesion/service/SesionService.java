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

package co.edu.uniandes.csw.Matisse.sesion.service;

import co.edu.uniandes.csw.Matisse.Semana.logic.dto.SemanaDTO;
import co.edu.uniandes.csw.Matisse.sesion.logic.dto.SesionPageDTO;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("/Sesion")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SesionService extends _SesionService {
    @Context
    UriInfo uriInfo;

    @GET
    @Path("estadistica")
    public SemanaDTO darEstadisticasPorFechas(@QueryParam("inicial")String inicial, @QueryParam("final")String fin) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date fInicial = df.parse(inicial);
            Date fFinal = df.parse(fin);
            return sesionLogicService.darEstadisticaPorFechas(fInicial,fFinal);
        } 
        catch (ParseException e) {
            return sesionLogicService.darEstadisticaPorFechas(null,null);
        }
    }
    
    @GET
    @Path("compararFechas")
    public List<SemanaDTO> compararEstadisticasPorSemana() {
        ArrayList<SemanaDTO> respuesta = new ArrayList<SemanaDTO>();
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            for (int i=0;i<queryParams.values().size()/2;i++) {
                String inicio = URLDecoder.decode(queryParams.getFirst("semanas["+i+"][inicial]"), "UTF-8");
                String fin = URLDecoder.decode(queryParams.getFirst("semanas["+i+"][fin]"), "UTF-8");
                SemanaDTO dto = darEstadisticasPorFechas(inicio,fin);
                respuesta.add(dto);
            }
        }
        catch (UnsupportedEncodingException ex) {
            System.out.println("Error leyendo fechas");
        }
        return respuesta; 
    }
    
     @GET
    @Path("sesionesFecha")
    public SesionPageDTO getSesionesFecha(@QueryParam("inicial")String inicial, @QueryParam("final")String fin,@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords){
	try {
           DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date fInicial = df.parse(inicial);
           Date fFinal = df.parse(fin);
           return sesionLogicService.getSesionesFecha(page, maxRecords, fInicial,fFinal);
        } 
        catch (ParseException e) {
            System.out.println("Error leyendo fecha");
        }
        return new SesionPageDTO();
    }
    
    @GET
    @Path("sesionesPorMonitor")
    public SesionPageDTO getSesionesMonitor(@QueryParam("monitorId")Integer monitorId,@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords){
        return sesionLogicService.getSesionesMonitor(page, maxRecords, monitorId);
    }
    
    @GET
    @Path("sesionesEstudiante")
    public SesionPageDTO getSesionesEstudiante(@QueryParam("estudiante")String estudiante,@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords){
        estudiante = estudiante.toUpperCase();
        return sesionLogicService.getSesionesEstudiante(page, maxRecords, estudiante);
    }
    
    @GET
    @Path("sesionesEstado")
    public SesionPageDTO getSesionesEstado(@QueryParam("estado")String estado,@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords){
        estado = estado.toUpperCase();
        return sesionLogicService.getSesionesEstado(page, maxRecords, estado);
    }
    
    @GET
    @Path("sesionesMonitor")
    public SemanaDTO darEstadisticasPorMonitor(@QueryParam("idMonitor")Integer monitor,@QueryParam("inicial")String inicial, @QueryParam("final")String fin) {
         try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date fInicial = df.parse(inicial);
            Date fFinal = df.parse(fin);
            return sesionLogicService.darEstadisticaPorMonitor(monitor,fInicial,fFinal);
        } 
        catch (ParseException e) {
            System.out.println("Error leyendo fechas");
        }
        return new SemanaDTO();
    } 
    
    @GET
    @Path("/report")
    @Produces("application/pdf")
    public byte[] getReport(){
        return sesionLogicService.getReport();
    }
    
}