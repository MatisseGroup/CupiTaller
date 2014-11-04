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

package co.edu.uniandes.csw.Matisse.entradas.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.Matisse.entradas.logic.dto.EntradasPageDTO;
import co.edu.uniandes.csw.Matisse.entradas.logic.dto.EntradasDTO;
import co.edu.uniandes.csw.Matisse.entradas.logic.api.IEntradasLogicService;
import co.edu.uniandes.csw.Matisse.entradas.persistence.EntradasPersistence;
import co.edu.uniandes.csw.Matisse.entradas.persistence.api.IEntradasPersistence;
import co.edu.uniandes.csw.Matisse.entradas.persistence.entity.EntradasEntity;
import co.edu.uniandes.csw.Matisse.entradas.persistence.converter.EntradasConverter;
import static co.edu.uniandes.csw.Matisse.util._TestUtil.*;

@RunWith(Arquillian.class)
public class EntradasLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(EntradasLogicService.class.getPackage())
				.addPackage(IEntradasLogicService.class.getPackage())
				.addPackage(EntradasPersistence.class.getPackage())
				.addPackage(EntradasEntity.class.getPackage())
				.addPackage(IEntradasPersistence.class.getPackage())
                .addPackage(EntradasDTO.class.getPackage())
                .addPackage(EntradasConverter.class.getPackage())
                .addPackage(EntradasEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IEntradasLogicService entradasLogicService;
	
	@Inject
	private IEntradasPersistence entradasPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<EntradasDTO> dtos=entradasPersistence.getEntradass();
		for(EntradasDTO dto:dtos){
			entradasPersistence.deleteEntradas(dto.getId());
		}
	}

	private List<EntradasDTO> data=new ArrayList<EntradasDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			EntradasDTO pdto=new EntradasDTO();
			pdto.setLabel(generateRandom(String.class));
			pdto.setValue(generateRandom(Integer.class));
			pdto.setName(generateRandom(String.class));
			pdto=entradasPersistence.createEntradas(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createEntradasTest(){
		EntradasDTO ldto=new EntradasDTO();
		ldto.setLabel(generateRandom(String.class));
		ldto.setValue(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		
		
		EntradasDTO result=entradasLogicService.createEntradas(ldto);
		
		Assert.assertNotNull(result);
		
		EntradasDTO pdto=entradasPersistence.getEntradas(result.getId());
		
		Assert.assertEquals(ldto.getLabel(), pdto.getLabel());	
		Assert.assertEquals(ldto.getValue(), pdto.getValue());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
	}
	
	@Test
	public void getEntradassTest(){
		List<EntradasDTO> list=entradasLogicService.getEntradass();
		Assert.assertEquals(list.size(), data.size());
        for(EntradasDTO ldto:list){
        	boolean found=false;
            for(EntradasDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getEntradasTest(){
		EntradasDTO pdto=data.get(0);
		EntradasDTO ldto=entradasLogicService.getEntradas(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getLabel(), ldto.getLabel());
		Assert.assertEquals(pdto.getValue(), ldto.getValue());
		Assert.assertEquals(pdto.getName(), ldto.getName());
        
	}
	
	@Test
	public void deleteEntradasTest(){
		EntradasDTO pdto=data.get(0);
		entradasLogicService.deleteEntradas(pdto.getId());
        EntradasDTO deleted=entradasPersistence.getEntradas(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateEntradasTest(){
		EntradasDTO pdto=data.get(0);
		
		EntradasDTO ldto=new EntradasDTO();
		ldto.setId(pdto.getId());
		ldto.setLabel(generateRandom(String.class));
		ldto.setValue(generateRandom(Integer.class));
		ldto.setName(generateRandom(String.class));
		
		
		entradasLogicService.updateEntradas(ldto);
		
		
		EntradasDTO resp=entradasPersistence.getEntradas(pdto.getId());
		
		Assert.assertEquals(ldto.getLabel(), resp.getLabel());	
		Assert.assertEquals(ldto.getValue(), resp.getValue());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
	}
	
	@Test
	public void getEntradasPaginationTest(){
		
		EntradasPageDTO dto1=entradasLogicService.getEntradass(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		EntradasPageDTO dto2=entradasLogicService.getEntradass(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(EntradasDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(EntradasDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(EntradasDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(EntradasDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        EntradasPageDTO dto3=entradasLogicService.getEntradass(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(EntradasDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(EntradasDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}