// SistemaEventos.java
import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaEventos {
    private List<Evento> eventos;
    private static final String ARQUIVO_DADOS = "events.data";

    public SistemaEventos() {
        this.eventos = new ArrayList<>();
        carregarEventos();
    }

    public void cadastrarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
    }

    public List<Evento> listarTodosEventos() {
        return new ArrayList<>(eventos);
    }

    public List<Evento> listarEventosPorUsuario(Usuario usuario) {
        return eventos.stream()
                      .filter(e -> e.getParticipantes().contains(usuario))
                      .collect(Collectors.toList());
    }

    public void participarEvento(Usuario usuario, int indiceEvento) {
        if (indiceEvento >= 0 && indiceEvento < eventos.size()) {
            eventos.get(indiceEvento).adicionarParticipante(usuario);
            salvarEventos();
        }
    }

    public void cancelarParticipacao(Usuario usuario, int indiceEvento) {
        if (indiceEvento >= 0 && indiceEvento < eventos.size()) {
            eventos.get(indiceEvento).removerParticipante(usuario);
            salvarEventos();
        }
    }

    public void ordenarEventosPorHorario() {
        Collections.sort(eventos, (e1, e2) -> e1.getHorario().compareTo(e2.getHorario()));
    }

    public String statusEvento(Evento evento) {
        LocalDateTime agora = LocalDateTime.now();
        if (agora.isAfter(evento.getHorario().plus(1, ChronoUnit.HOURS))) {
            return "Ocorreu";
        } else if (agora.isAfter(evento.getHorario())) {
            return "Ocorrendo agora";
        } else {
            return "Futuro";
        }
    }

    private void salvarEventos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            oos.writeObject(eventos);
            System.out.println("Eventos salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarEventos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
            eventos = (List<Evento>) ois.readObject();
            System.out.println("Eventos carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de eventos não encontrado. Iniciando com lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar eventos: " + e.getMessage());
        }
    }
}