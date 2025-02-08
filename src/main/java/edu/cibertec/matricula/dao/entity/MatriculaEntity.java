package edu.cibertec.matricula.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matricula")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmatricula;
    private Date fechaMat;
    @OneToOne
    @JoinColumn(name="usuario")
    private UsuarioEntity usuario;
    @OneToOne
    @JoinColumn(name = "idcurso")
    private CursoEntity curso;
    
    private int estado;
}
