 

   ALTER TABLE APP.PreguntarespuestaEntity ADD FOREIGN KEY (preguntaId) REFERENCES  APP.PreguntaEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
   ALTER TABLE APP.PreguntarespuestaEntity ADD FOREIGN KEY (respuestaId) REFERENCES  APP.RespuestaEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
		
   ALTER TABLE APP.ResultadoentradasEntity ADD FOREIGN KEY (resultadoId) REFERENCES  APP.ResultadoEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
   ALTER TABLE APP.ResultadoentradasEntity ADD FOREIGN KEY (entradasId) REFERENCES  APP.EntradasEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
		
