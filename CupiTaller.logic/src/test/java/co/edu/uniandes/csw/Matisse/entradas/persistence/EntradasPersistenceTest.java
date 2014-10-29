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

package co.edu.uniandes.csw.Matisse.entradas.persistence;

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
import co.edu.uniandes.csw.Matisse.entradas.persistence.api.IEntradasPersistence;
import co.edu.uniandes.csw.Matisse.entradas.persistence.entity.EntradasEntity;
import co.edu.uniandes.csw.Matisse.entradas.persistence.converter.EntradasConverter;
import static co.edu.uniandes.csw.Matisse.util._TestUtil.*;

@RunWith(Arquillian.class)
public class EntradasPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(EntradasPersistence.class.getPackage())
				.addPackage(EntradasEntity.class.getPackage())
				.addPackage(IEntradasPersistence.class.getPackage())
                .addPackage(EntradasDTO.class.getPackage())
                .addPackage(EntradasConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IEntradasPersistence entradasPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from EntradasEntity").executeUpdate();
	}

	private List<EntradasEntity> data=new ArrayList<EntradasEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			EntradasEntity entity=new EntradasEntity();
			entity.setLabel(generateRandom(String.class));
			entity.setValue(generateRandom(Integer.class));
			entity.setName(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createEntradasTest(){
		EntradasDTO dto=new EntradasDTO();
		dto.setLabel(generateRandom(String.class));
		dto.setValue(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		
		EntradasDTO result=entradasPersistence.createEntradas(dto);
		
		Assert.assertNotNull(result);
		
		EntradasEntity entity=em.find(EntradasEntity.class, result.getId());
		
		Assert.assertEquals(dto.getLabel(), entity.getLabel());
		Assert.assertEquals(dto.getValue(), entity.getValue());
		Assert.assertEquals(dto.getName(), entity.getName());
	}
	
	@Test
	public void getEntradassTest(){
		List<EntradasDTO> list=entradasPersistence.getEntradass();
		Assert.assertEquals(list.size(), data.size());
        for(EntradasDTO dto:list){
        	boolean found=false;
            for(EntradasEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getEntradasTest(){
		EntradasEntity entity=data.get(0);
		EntradasDTO dto=entradasPersistence.getEntradas(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getLabel(), dto.getLabel());
		Assert.assertEquals(entity.getValue(), dto.getValue());
		Assert.assertEquals(entity.getName(), dto.getName());
        
	}
	
	@Test
	public void deleteEntradasTest(){
		EntradasEntity entity=data.get(0);
		entradasPersistence.deleteEntradas(entity.getId());
        EntradasEntity deleted=em.find(EntradasEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateEntradasTest(){
		EntradasEntity entity=data.get(0);
		
		EntradasDTO dto=new EntradasDTO();
		dto.setId(entity.getId());
		dto.setLabel(generateRandom(String.class));
		dto.setValue(generateRandom(Integer.class));
		dto.setName(generateRandom(String.class));
		
		
		entradasPersistence.updateEntradas(dto);
		
		
		EntradasEntity resp=em.find(EntradasEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getLabel(), resp.getLabel());	
		Assert.assertEquals(dto.getValue(), resp.getValue());	
		Assert.assertEquals(dto.getName(), resp.getName());	
	}
	
	@Test
	public void getEntradasPaginationTest(){
		//Page 1
		EntradasPageDTO dto1=entradasPersistence.getEntradass(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        EntradasPageDTO dto2=entradasPersistence.getEntradass(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(EntradasDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(EntradasEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(EntradasDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(EntradasEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
}