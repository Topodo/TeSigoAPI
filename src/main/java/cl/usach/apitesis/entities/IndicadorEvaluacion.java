package cl.usach.apitesis.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="indicador_evaluacion")
public class IndicadorEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_INDICADOR")
    private long idIndicador;
    @Column(name = "DESCRIPCION_INDICADOR")
    private String descripcionIndicador;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_OBJETIVO")
    @JsonBackReference
    private ObjetivoAprendizaje objetivoAprendizaje;
    @OneToMany(mappedBy = "indicadorEvaluacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<ACompletado> completados;

    public long getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(long idIndicador) {
        this.idIndicador = idIndicador;
    }

    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public ObjetivoAprendizaje getObjetivoAprendizaje() {
        return objetivoAprendizaje;
    }

    public void setObjetivoAprendizaje(ObjetivoAprendizaje objetivoAprendizaje) {
        this.objetivoAprendizaje = objetivoAprendizaje;
    }

    public Set<ACompletado> getCompletados() {
        return completados;
    }

    public void setCompletados(Set<ACompletado> completados) {
        this.completados = completados;
    }
}
