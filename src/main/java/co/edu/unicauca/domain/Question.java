package co.edu.unicauca.domain;

/**
 * Entidad principal del dominio que representa una pregunta en el Banco de Preguntas.
 */
public class Question {
    public static final String STATE_BORRADOR = "Borrador";
    public static final String STATE_PENDIENTE_REVISION = "Pendiente de revisión";
    public static final String STATE_ELIMINADA = "Eliminada";

    private String id;
    private String nombre;
    private String pregunta;
    private QuestionDistractors distractors;
    private String respuestaCorrecta;
    private String estado;

    public Question() {
    }

    public Question(String id, String nombre, String pregunta, QuestionDistractors distractors, String respuestaCorrecta, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.pregunta = pregunta;
        this.distractors = distractors;
        this.respuestaCorrecta = respuestaCorrecta;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public QuestionDistractors getDistractors() {
        return distractors;
    }

    public void setDistractors(QuestionDistractors distractors) {
        this.distractors = distractors;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
