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
package co.edu.uniandes.csw.Matisse.resultado.logic.ejb;

import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.Matisse.resultado.logic.dto.ResultadoDTO;
import co.edu.uniandes.csw.Matisse.resultado.logic.dto.ResultadoPageDTO;
import co.edu.uniandes.csw.Matisse.resultado.logic.api._IResultadoLogicService;
import co.edu.uniandes.csw.Matisse.resultado.persistence.api.IResultadoPersistence;

public abstract class _ResultadoLogicService implements _IResultadoLogicService {

    @Inject
    protected IResultadoPersistence persistance;

    public ResultadoDTO createResultado(ResultadoDTO resultado) {
        return persistance.createResultado(resultado);
    }

    public List<ResultadoDTO> getResultados() {
        return persistance.getResultados();
    }

    public ResultadoPageDTO getResultados(Integer page, Integer maxRecords) {
        return persistance.getResultados(page, maxRecords);
    }

    public ResultadoDTO getResultado(Long id) {
        return persistance.getResultado(id);
    }

    public void deleteResultado(Long id) {
        persistance.deleteResultado(id);
    }

    public void updateResultado(ResultadoDTO resultado) {
        persistance.updateResultado(resultado);
    }
}
