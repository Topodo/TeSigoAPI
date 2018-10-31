package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Alumno> alumnos;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PROFESOR")
    @JsonBackReference(value = "profesor-curso")
    private Profesor profesor;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "curso-unidad")
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
