package edu.cibertec.matricula.dao;

import edu.cibertec.matricula.dao.entity.CursoEntity;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author miguel
 */
public abstract class CursoDAOImpl implements CursoDAO {
    
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
}
