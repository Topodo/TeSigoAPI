package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="alumno")
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ALUMNO")
    private Long idAlumno;

    @Column(name = "NOMBRE_ALUMNO")
    private String nombreAlumno;

    @Column(name = "APELLIDO_PATERNO_ALUMNO")
    private String apellidoPaternoAlumno;

    @Column(name = "APELLIDO_MATERNO_ALUMNO")
    private String apellidoMaternoAlumno;

    @Column(name = "EDAD_ALUMNO")
    private Long edadAlumno;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CURSO")
    @JsonBackReference
    private Curso curso;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "alumno-a-completado")
    private Set<ACompletado> completados;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "alumno-reporte")
    private Set<Reporte> reportes;

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoPaternoAlumno() {
        return apellidoPaternoAlumno;
    }

    public void setApellidoPaternoAlumno(String apellidoPaternoAlumno) {
        this.apellidoPaternoAlumno = apellidoPaternoAlumno;
    }

    public String getApellidoMaternoAlumno() {
        return apellidoMaternoAlumno;
    }

    public void setApellidoMaternoAlumno(String apellidoMaternoAlumno) {
        this.apellidoMaternoAlumno = apellidoMaternoAlumno;
    }

    public Long getEdadAlumno() {
        return edadAlumno;
    }

    public void setEdadAlumno(Long edadAlumno) {
        this.edadAlumno = edadAlumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Set<ACompletado> getCompletados() {
        return completados;
    }

    public void setCompletados(Set<ACompletado> completados) {
        this.completados = completados;
    }

    public Set<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(Set<Reporte> reportes) {
        this.reportes = reportes;
    }
}
