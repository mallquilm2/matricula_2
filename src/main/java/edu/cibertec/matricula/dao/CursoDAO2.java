/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.cibertec.matricula.dao;

import edu.cibertec.matricula.dao.entity.CursoEntity;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public interface CursoDAO2 extends JpaRepository<CursoEntity, Integer>{
    
    /*
    public RestTemplate restTemplate;
    
    @Value("${uri.rest.cursos}")
    private String urlServidor;
    
    public CursoDAOImpl(){
        restTemplate = new RestTemplate();
    }
    
    public List<CursoEntity> listarTodos(){
        List<CursoEntity> rpta = null;
        CursoEntity[] lista = restTemplate.getForObject(urlServidor, CursoEntity[].class);
        rpta = Arrays.asList(lista);
        return rpta;
    }
    
    public CursoEntity obtenerUno(int codigo){
        return restTemplate.getForObject(urlServidor+"/"+codigo, CursoEntity.class);
    }
    
    public void insertar(CursoEntity ce){
        restTemplate.postForEntity(urlServidor, ce, CursoEntity.class);
    }
    
    public void modificar(CursoEntity ce){
        restTemplate.put(urlServidor+"/"+ce.getIdCurso(), ce);
    }
    
    public void eliminar(int codigo){
        restTemplate.delete(urlServidor+"/"+codigo);
    }
*/
}
