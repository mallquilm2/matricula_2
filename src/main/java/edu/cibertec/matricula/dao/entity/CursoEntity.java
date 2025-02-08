
package edu.cibertec.matricula.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="curso")
@NamedQuery(name = "CursoEntity.abiertoIncompleto",
        query = "SELECT c FROM CursoEntity c WHERE c.alumnosMin > c.alumnosAct and c.estado = 1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcurso")
    private Integer idCurso;
    @Column(name = "nomcurso")
    private String nomCurso;
    private Date fechaInicio;
    private Integer alumnosMin;
    private Integer alumnosAct;
    private Integer estado;
    
}
