package edu.cibertec.matricula.dao;

import edu.cibertec.matricula.dao.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaDAO extends JpaRepository<MatriculaEntity, Integer>{
    
}
