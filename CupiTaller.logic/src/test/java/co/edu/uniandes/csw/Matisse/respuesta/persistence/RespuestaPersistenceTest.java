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

package co.edu.uniandes.csw.Matisse.respuesta.persistence;

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


import co.edu.uniandes.csw.Matisse.respuesta.logic.dto.RespuestaPageDTO;
import co.edu.uniandes.csw.Matisse.respuesta.logic.dto.RespuestaDTO;
import co.edu.uniandes.csw.Matisse.respuesta.persistence.api.IRespuestaPersistence;
import co.edu.uniandes.csw.Matisse.respuesta.persistence.entity.RespuestaEntity;
import co.edu.uniandes.csw.Matisse.respuesta.persistence.converter.RespuestaConverter;
import static co.edu.uniandes.csw.Matisse.util._TestUtil.*;

@RunWith(Arquillian.class)
public class RespuestaPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(RespuestaPersistence.class.getPackage())
				.addPackage(RespuestaEntity.class.getPackage())
				.addPackage(IRespuestaPersistence.class.getPackage())
                .addPackage(RespuestaDTO.class.getPackage())
                .addPackage(RespuestaConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IRespuestaPersistence respuestaPersistence;

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
		em.createQuery("delete from RespuestaEntity").executeUpdate();
	}

	private List<RespuestaEntity> data=new ArrayList<RespuestaEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			RespuestaEntity entity=new RespuestaEntity();
			entity.setName(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createRespuestaTest(){
		RespuestaDTO dto=new RespuestaDTO();
		dto.setName(generateRandom(String.class));
		
		RespuestaDTO result=respuestaPersistence.createRespuesta(dto);
		
		Assert.assertNotNull(result);
		
		RespuestaEntity entity=em.find(RespuestaEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());
	}
	
	@Test
	public void getRespuestasTest(){
		List<RespuestaDTO> list=respuestaPersistence.getRespuestas();
		Assert.assertEquals(list.size(), data.size());
        for(RespuestaDTO dto:list){
        	boolean found=false;
            for(RespuestaEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getRespuestaTest(){
		RespuestaEntity entity=data.get(0);
		RespuestaDTO dto=respuestaPersistence.getRespuesta(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
        
	}
	
	@Test
	public void deleteRespuestaTest(){
		RespuestaEntity entity=data.get(0);
		respuestaPersistence.deleteRespuesta(entity.getId());
        RespuestaEntity deleted=em.find(RespuestaEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateRespuestaTest(){
		RespuestaEntity entity=data.get(0);
		
		RespuestaDTO dto=new RespuestaDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		
		
		respuestaPersistence.updateRespuesta(dto);
		
		
		RespuestaEntity resp=em.find(RespuestaEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
	}
	
	@Test
	public void getRespuestaPaginationTest(){
		//Page 1
		RespuestaPageDTO dto1=respuestaPersistence.getRespuestas(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        RespuestaPageDTO dto2=respuestaPersistence.getRespuestas(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(RespuestaDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(RespuestaEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(RespuestaDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(RespuestaEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
}