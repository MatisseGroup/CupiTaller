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
package co.edu.uniandes.csw.Matisse.sesion.persistence;

import co.edu.uniandes.csw.Matisse.Semana.logic.dto.SemanaDTO;
import co.edu.uniandes.csw.Matisse.entradas.logic.dto.EntradasDTO;
import co.edu.uniandes.csw.Matisse.entradas.persistence.converter.EntradasConverter;
import co.edu.uniandes.csw.Matisse.entradas.persistence.converter._EntradasConverter;
import co.edu.uniandes.csw.Matisse.entradas.persistence.entity.EntradasEntity;
import co.edu.uniandes.csw.Matisse.monitor.persistence.MonitorPersistence;
import co.edu.uniandes.csw.Matisse.sesion.logic.dto.SesionDTO;
import co.edu.uniandes.csw.Matisse.sesion.logic.dto.SesionPageDTO;
import co.edu.uniandes.csw.Matisse.sesion.persistence.api.ISesionPersistence;
import co.edu.uniandes.csw.Matisse.sesion.persistence.converter.SesionConverter;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.Parameter;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Default
@Stateless
@LocalBean

public class SesionPersistence extends _SesionPersistence implements ISesionPersistence {

    public SemanaDTO darEstadisticaPorSemana(int semana) {
        String query = "select dis.label, COALESCE(co.value,0) as value from ((select distinct(estado) as label from SESIONENTITY)dis left join (select estado as label, count(estado) as value from SESIONENTITY where semanaAnual = ? group by estado order by estado)co on dis.label=co.label)";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter(1,semana);
        List<Object[]> lista = q.getResultList();
        SemanaDTO sem = new SemanaDTO();
        String[] labels = new String[lista.size()];
        Integer[] values = new Integer[lista.size()];
        String name = "Semana " + semana;
        for(int i=0;i<lista.size();i++){
            Object actual[] = lista.get(i);
            labels[i] = (String)actual[0];
            values[i] = (Integer)actual[1];
        }
        sem.setName(name);
        sem.setLabel(labels);
        sem.setValue(values);
        return sem;
    }

    public SesionPageDTO darSesionesPorSemana(Integer page, Integer maxRecords, Integer semana) {
        Query count = entityManager.createQuery("select count(u) from SesionEntity u where u.semanaAnual = ?1");
        count.setParameter(1, semana);
        Long regCount = 0L;
        regCount = Long.parseLong(count.getSingleResult().toString());
        Query q = entityManager.createQuery("select u from SesionEntity u where u.semanaAnual = ?1");
        q.setParameter(1, semana);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        SesionPageDTO response = new SesionPageDTO();
        response.setTotalRecords(regCount);
        response.setRecords(SesionConverter.entity2PersistenceDTOList(q.getResultList()));
        return response;
    }

    public Integer darUltimaSemana() {
        Query count = entityManager.createNativeQuery("select max(u.semanaAnual) from SesionEntity u");
	Integer regCount = Integer.parseInt(count.getSingleResult().toString());
        return regCount;
    }
    
    public List<EntradasDTO>darSemanas(){
        ArrayList<EntradasDTO>respuesta = new ArrayList<EntradasDTO>();
        Query count = entityManager.createNativeQuery("select distinct(semanaAnual) from sesionEntity");
        List<Object[]> lista = count.getResultList();
        Object[] arreglo = lista.toArray();
        for(int i = 0; i<arreglo.length;i++){
            EntradasDTO nueva = new EntradasDTO();
            nueva.setValue((Integer)arreglo[i]);
            respuesta.add(nueva);
        }
        return respuesta;
    }

    public SemanaDTO estadisticasMonitor(Integer monitor) {
        String query = "select dis.label, COALESCE(co.value,0) as value from ((select distinct(estado) as label from SESIONENTITY)dis left join (select estado as label, count(estado) as value from SESIONENTITY where monitorid = ? group by estado order by estado)co on dis.label=co.label)";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter(1,monitor);
        List<Object[]> lista = q.getResultList();
        SemanaDTO sem = new SemanaDTO();
        String[] labels = new String[lista.size()];
        Integer[] values = new Integer[lista.size()];
        for(int i=0;i<lista.size();i++){
            Object actual[] = lista.get(i);
            labels[i] = (String)actual[0];
            values[i] = (Integer)actual[1];
        }
        sem.setLabel(labels);
        sem.setValue(values);
        return sem;
    }

    public byte[] getReport() {
    try {
            Map parameters = new HashMap();
            JasperReport report = JasperCompileManager.compileReport(
                    "/Users/sfrsebastian/Desktop/CupiTaller/CupiTaller.logic/reporte1.jrxml");
            Connection conn = entityManager.unwrap(java.sql.Connection.class);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
            // Exporta el informe a PDF
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, baos);
            return baos.toByteArray();
        } catch (JRException ex) {
            Logger.getLogger(SesionPersistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
