
package edu.cibertec.matricula.service;

import edu.cibertec.matricula.dao.CursoDAO;
import edu.cibertec.matricula.dao.CursoDAOImpl;
import edu.cibertec.matricula.dao.entity.CursoEntity;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    
    @Autowired
    private CursoDAO cursoDao;
    
    @Autowired
    private CursoDAOImpl cursoDAOImpl;
    
    public List<CursoEntity> consultarPorEstado(int estado){
        return cursoDao.consultarPorEstado(estado);
    }
    
    public List<CursoEntity> abiertoIncompleto(){
        return cursoDao.abiertoIncompleto();
    }
    
    public List<CursoEntity> consultarPorFecha(Date fecha){
        return cursoDao.consultarPorFecha(fecha);
    }
    
    public List<CursoEntity> consultarFaltantes(Integer cantidad){
        return cursoDao.consultarFaltantes(cantidad);
    }
    
    public List<CursoEntity> consultarPorNombre(String cadena){
        return cursoDao.consultarPorNombre(cadena);
    }
    
    public List<CursoEntity> listarTodosLosCursos(){
        return cursoDao.findAll();
    }
    
    public List<CursoEntity> listarTodos(){
        return cursoDAOImpl.listarTodos();
    }
    
    public CursoEntity obtenerUno(int codigo){
        return cursoDAOImpl.obtenerUno(codigo);
    }
    
    public void insertar(CursoEntity ce){
        cursoDAOImpl.insertar(ce);
    }
    
    public void modificar(CursoEntity ce){
        cursoDAOImpl.modificar(ce);
    }
    
    public void eliminar(int codigo){
        cursoDAOImpl.eliminar(codigo);
    }
    
}
