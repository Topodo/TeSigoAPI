package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "profesor")
@JsonIgnoreProperties(ignoreUnknown=true, value={"hibernateLazyInitializer", "handler"})
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROFESOR")
    private Long idProfesor;

    @Column(name = "NOMBRE_PROFESOR")
    private String nombreProfesor;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "profesores")
    private Set<Curso> cursos;

    @Column(name = "FIREBASE_UID")
    private String firebaseUID;

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public String getFirebaseUID() {
        return firebaseUID;
    }

    public void setFirebaseUID(String firebaseUID) {
        this.firebaseUID = firebaseUID;
    }
}
