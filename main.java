// Main.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static SistemaEventos sistema;
    private static Scanner scanner;
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        sistema = new SistemaEventos();
        scanner = new Scanner(System.in);

        menuPrincipal();
    }

    private static void menuPrincipal() {
        System.out.println("\n--- Bem-vindo ao Sistema de Eventos ---");

        if (usuarioLogado == null) {
            System.out.println("1. Cadastrar novo usuário");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    System.out.println("Obrigado por usar o sistema. Até mais!");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        while (usuarioLogado != null) {
            System.out.println("\n--- Menu do Usuário ---");
            System.out.println("Olá, " + usuarioLogado.getNome() + "!");
            System.out.println("1. Cadastrar um novo evento");
            System.out.println("2. Ver todos os eventos");
            System.out.println("3. Ver meus eventos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarEvento();
                    break;
                case 2:
                    listarEventos(false);
                    break;
                case 3:
                    listarEventos(true);
                    break;
                case 4:
                    usuarioLogado = null;
                    System.out.println("Sessão encerrada.");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        main(new String[]{}); // Retorna ao menu de login/cadastro
    }

    private static void cadastrarUsuario() {
        System.out.println("\n--- Cadastro de Usuário ---");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        usuarioLogado = new Usuario(nome, email, cidade);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void cadastrarEvento() {
        System.out.println("\n--- Cadastro de Evento ---");
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.println("Categorias disponíveis: " + java.util.Arrays.toString(Categoria.values()));
        System.out.print("Categoria (digite o nome): ");
        Categoria categoria = Categoria.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Horário do evento (formato dd/MM/yyyy HH:mm): ");
        String horarioStr = scanner.nextLine();
        LocalDateTime horario = null;
        try {
            horario = LocalDateTime.parse(horarioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data/hora inválido. Evento não cadastrado.");
            return;
        }
        System.out.print("Descrição do evento: ");
        String descricao = scanner.nextLine();

        Evento novoEvento = new Evento(nome, endereco, categoria, horario, descricao);
        sistema.cadastrarEvento(novoEvento);
        System.out.println("Evento '" + nome + "' cadastrado com sucesso!");
    }

    private static void listarEventos(boolean meusEventos) {
        List<Evento> eventos;
        if (meusEventos) {
            eventos = sistema.listarEventosPorUsuario(usuarioLogado);
            System.out.println("\n--- Meus Eventos ---");
        } else {
            eventos = sistema.listarTodosEventos();
            sistema.ordenarEventosPorHorario();
            System.out.println("\n--- Todos os Eventos ---");
        }

        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado.");
            return;
        }

        for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            System.out.println("----------------------------------------");
            System.out.println("ID: " + i);
            System.out.println(evento.toString());
            System.out.println("Status: " + sistema.statusEvento(evento));
            System.out.println("----------------------------------------");
        }
        
        System.out.println("\nOpções:");
        System.out.println("1. Participar de um evento");
        System.out.println("2. Cancelar participação em um evento");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case 1:
                participarEvento();
                break;
            case 2:
                cancelarParticipacao();
                break;
            case 3:
                // Volta ao menu anterior
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void participarEvento() {
        System.out.print("Digite o ID do evento que deseja participar: ");
        int id = Integer.parseInt(scanner.nextLine());
        sistema.participarEvento(usuarioLogado, id);
        System.out.println("Participação confirmada!");
    }

    private static void cancelarParticipacao() {
        System.out.print("Digite o ID do evento que deseja cancelar a participação: ");
        int id = Integer.parseInt(scanner.nextLine());
        sistema.cancelarParticipacao(usuarioLogado, id);
        System.out.println("Participação cancelada!");
    }
}