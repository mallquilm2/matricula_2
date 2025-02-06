
package edu.cibertec.matricula.dao;

import edu.cibertec.matricula.dao.entity.CursoEntity;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoDAO  extends JpaRepository<CursoEntity, Integer>{
    
    //Consulta por DSL
    @Query("SELECT c FROM CursoEntity c WHERE c.estado = ?1")
    public List<CursoEntity> consultarPorEstado(int estado);
    
    //Consulta por Query nombrado
    public List<CursoEntity> abiertoIncompleto();
    
    //Consulta JPQL
    @Query("SELECT c FROM CursoEntity c where c.fechaInicio >= :fecha")
    public List<CursoEntity> consultarPorFecha(@Param("fecha") Date fecha);
    
    //Consulta de cursos a los que falta X numeros de alumnos para llenarse.
    @Query(value = "SELECT * FROM Curso WHERE alumnosMin - alumnosAct = :cantidad",
            nativeQuery = true)
    public List<CursoEntity> consultarFaltantes(@Param("cantidad") Integer cantidad);
    
    //Consulta por SP
    @Query(nativeQuery = true, value = "call Curso_Por_Nombre(:cadena)")
    public List<CursoEntity> consultarPorNombre(@Param("cadena") String cadena);
    
}
