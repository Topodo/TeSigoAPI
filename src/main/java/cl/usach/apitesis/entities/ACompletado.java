package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="a_completado")
public class ACompletado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_A_COMPLETADO", unique = true, nullable = false)
    private Long idACompletado;
    @Column(name = "INDICADOR_COMPLETADO")
    private Boolean indicadorCompletado;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ALUMNO")
    @JsonBackReference(value = "alumno-a-completado")
    private Alumno alumno;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_INDICADOR")
    @JsonBackReference(value = "indicador-evaluacion-a-completado")
    private IndicadorEvaluacion indicadorEvaluacion;

    public Boolean getIndicadorCompletado() {
        return indicadorCompletado;
    }

    public void setIndicadorCompletado(Boolean indicadorCompletado) {
        this.indicadorCompletado = indicadorCompletado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public IndicadorEvaluacion getIndicadorEvaluacion() {
        return indicadorEvaluacion;
    }

    public void setIndicadorEvaluacion(IndicadorEvaluacion indicadorEvaluacion) {
        this.indicadorEvaluacion = indicadorEvaluacion;
    }

    public Long getIdACompletado() {
        return idACompletado;
    }

    public void setIdACompletado(Long idACompletado) {
        this.idACompletado = idACompletado;
    }
}
