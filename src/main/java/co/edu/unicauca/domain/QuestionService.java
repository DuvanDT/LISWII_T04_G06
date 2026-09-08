package co.edu.unicauca.domain;

import co.edu.unicauca.infra.Subject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio del dominio para la gestión del banco de preguntas.
 * Extiende de Subject para notificar a los observadores cuando cambie el estado de alguna pregunta.
 */
public class QuestionService extends Subject {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("El repositorio no puede ser nulo.");
        }
        this.repository = repository;
    }

    public List<Question> getAllQuestions() {
        return repository.findAll();
    }

    public Question getQuestionById(String id) {
        return repository.findById(id);
    }

    /**
     * Actualiza el estado de una pregunta y notifica a todos los observadores registrados.
     */
    public boolean updateQuestionState(String id, String newState) {
        boolean updated = repository.updateState(id, newState);
        if (updated) {
            notifyAllObservers();
        }
        return updated;
    }

    /**
     * Guarda una nueva pregunta y notifica a los observadores.
     */
    public boolean saveQuestion(Question question) {
        boolean saved = repository.save(question);
        if (saved) {
            notifyAllObservers();
        }
        return saved;
    }

    /**
     * Obtiene el conteo total de preguntas por cada estado.
     */
    public Map<String, Integer> getQuestionCountByState() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put(Question.STATE_BORRADOR, 0);
        counts.put(Question.STATE_PENDIENTE_REVISION, 0);
        counts.put(Question.STATE_ELIMINADA, 0);

        List<Question> questions = repository.findAll();
        for (Question q : questions) {
            String state = q.getEstado();
            if (counts.containsKey(state)) {
                counts.put(state, counts.get(state) + 1);
            } else {
                counts.put(state, 1);
            }
        }
        return counts;
    }

    /**
     * Obtiene el porcentaje de preguntas por cada estado.
     */
    public Map<String, Double> getQuestionPercentageByState() {
        Map<String, Integer> counts = getQuestionCountByState();
        Map<String, Double> percentages = new LinkedHashMap<>();
        int total = repository.findAll().size();

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            double pct = (total == 0) ? 0.0 : (entry.getValue() * 100.0) / total;
            percentages.put(entry.getKey(), Math.round(pct * 10.0) / 10.0);
        }
        return percentages;
    }

    public int getTotalQuestionsCount() {
        return repository.findAll().size();
    }
}
