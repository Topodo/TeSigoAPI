package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="unidad")
public class Unidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_UNIDAD")
    private long idUnidad;

    @Column(name = "NOMBRE_UNIDAD")
    private String nombreUnidad;

    @Column(name = "OBJETIVO_TRANSVERSAL")
    private String objetivoTransversal;

    @Column(name = "EJES")
    private String ejes;

    @Column(name = "BIMESTRE")
    private String bimestre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CURSO")
    @JsonBackReference(value = "curso-unidad")
    private Curso curso;

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "unidad-oa")
    private Set<ObjetivoAprendizaje> objetivosAprendizaje;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "profesor_unidades",
            joinColumns = {
                    @JoinColumn(name = "unidad_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "profesor_id")
            })
    @JsonIgnore
    private Set<Profesor> profesores = new HashSet<>();

    public long getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(long idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public Set<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getObjetivoTransversal() {
        return objetivoTransversal;
    }

    public void setObjetivoTransversal(String objetivoTransversal) {
        this.objetivoTransversal = objetivoTransversal;
    }

    public String getEjes() {
        return ejes;
    }

    public void setEjes(String ejes) {
        this.ejes = ejes;
    }

    public String getBimestre() {
        return bimestre;
    }

    public void setBimestre(String bimestre) {
        this.bimestre = bimestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Set<ObjetivoAprendizaje> getObjetivosAprendizaje() {
        return objetivosAprendizaje;
    }

    public void setObjetivosAprendizaje(Set<ObjetivoAprendizaje> objetivosAprendizaje) {
        this.objetivosAprendizaje = objetivosAprendizaje;
    }
}
