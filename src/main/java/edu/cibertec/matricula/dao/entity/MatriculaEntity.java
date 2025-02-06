package edu.cibertec.matricula.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "matricula")
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

    public MatriculaEntity() {
    }

    public MatriculaEntity(int idmatricula, Date fechaMat, UsuarioEntity usuario, CursoEntity curso, int estado) {
        this.idmatricula = idmatricula;
        this.fechaMat = fechaMat;
        this.usuario = usuario;
        this.curso = curso;
        this.estado = estado;
    }
    

    public int getIdmatricula() {
        return idmatricula;
    }

    public void setIdmatricula(int idmatricula) {
        this.idmatricula = idmatricula;
    }

    public Date getFechaMat() {
        return fechaMat;
    }

    public void setFechaMat(Date fechaMat) {
        this.fechaMat = fechaMat;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public CursoEntity getCurso() {
        return curso;
    }

    public void setCurso(CursoEntity curso) {
        this.curso = curso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
   

}
