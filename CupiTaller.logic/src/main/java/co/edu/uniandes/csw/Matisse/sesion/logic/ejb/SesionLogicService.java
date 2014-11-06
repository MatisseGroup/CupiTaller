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

package co.edu.uniandes.csw.Matisse.sesion.logic.ejb;

import co.edu.uniandes.csw.Matisse.Semana.logic.dto.SemanaDTO;
import co.edu.uniandes.csw.Matisse.entradas.logic.dto.EntradasDTO;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.Matisse.sesion.logic.api.ISesionLogicService;
import co.edu.uniandes.csw.Matisse.sesion.logic.dto.SesionPageDTO;
import java.util.List;

@Default
@Stateless
@LocalBean
public class SesionLogicService extends _SesionLogicService implements ISesionLogicService {

    public SemanaDTO darEstadisticaPorSemana(int semana) {
        return persistance.darEstadisticaPorSemana(semana);
    }

    public SesionPageDTO getSesionesSemana(Integer page, Integer maxRecords, Integer semana) {
        return persistance.darSesionesPorSemana(page,maxRecords,semana);
    }

    public Integer darUltimaSemana() {
        return persistance.darUltimaSemana();
    }
    
    public List<EntradasDTO>darSemanas(){
        return persistance.darSemanas();
    }

}