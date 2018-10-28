package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="curso")
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CURSO")
    private long idCurso;
    @Column(name = "GRADO_CURSO")
    private String gradoCurso;
    @Column(name = "CANTIDAD_ALUMNOS")
    private long cantidadAlumnos;
    @Column(name = "ID_PROFESOR")
    private long idProfesor;
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Alumno> alumnos;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PROFESOR")
    @JsonBackReference
    private Profesor profesor;
    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Unidad> unidades;

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    public String getGradoCurso() {
        return gradoCurso;
    }

    public void setGradoCurso(String gradoCurso) {
        this.gradoCurso = gradoCurso;
    }

    public long getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(long cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Set<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(Set<Unidad> unidades) {
        this.unidades = unidades;
    }
}
