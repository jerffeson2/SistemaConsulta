package Classes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Medico {
    private String nome;
    private String crm;
    private String telefone;
    private String email;
    private String especialidade;

    public Medico(String nome, String crm, String telefone, String email, String especialidade) {
        this.nome = nome;
        this.crm = crm;
        this.telefone = telefone;
        this.email = email;
        this.especialidade = especialidade;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return crm;
    }

    public void setCpf(String crm) {
        this.crm = crm;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}

class Consulta {
    private String data;
    private String hora;
    private Medico medico;
    private Usuario usuario;

    public Consulta(String data, String hora, Medico medico, Usuario usuario) {
        this.data = data;
        this.hora = hora;
        this.medico = medico;
        this.usuario = usuario;
    }

    

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}

class Usuario {
    private String nomeUsuario;
    private String senha;
    private String cpf;
    private String telefone;
    private String sexo;
    private String email;

    public Usuario(String nomeUsuario, String senha, String cpf, String telefone, String sexo, String email) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.email = email;
    }


    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

public class SistemaConsultaMedica {
    private List<Usuario> usuarios;
    private List<Medico> medicos;
    private List<Consulta> consultas;
    private Usuario usuarioLogado;
    private Scanner scanner;

    public SistemaConsultaMedica() {
        usuarios = new ArrayList<>();
        medicos = new ArrayList<>();
        consultas = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void cadastrarUsuario() {
        System.out.println("=== Cadastre-se ===");
        System.out.print("Nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nomeUsuario, senha, cpf, telefone, sexo, email);
        usuarios.add(novoUsuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void cadastrarMedico() {
        System.out.println("=== Cadastro de Médico ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("crm: ");
        String crm = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        Medico novoMedico = new Medico(nome, crm, telefone, email, especialidade);
        medicos.add(novoMedico);

        System.out.println("Médico cadastrado com sucesso!");
    }

    public Usuario fazerLogin() {
        System.out.println("=== Login ===");
        System.out.print("Nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getNomeUsuario().equals(nomeUsuario) && usuario.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso!");
                return usuario;
            }
        }

        return null;
    }

    public void agendarConsulta(Usuario usuario) {
        System.out.println("=== Agendar Consulta ===");
        System.out.print("Data: ");
        String data = scanner.nextLine();
        System.out.print("Hora: ");
        String hora = scanner.nextLine();

        System.out.println("Médicos disponíveis:");
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println((i + 1) + ". " + medicos.get(i).getNome() + " - " + medicos.get(i).getEspecialidade());
        }

        System.out.print("Escolha o número do médico desejado: ");
        int escolha = Integer.parseInt(scanner.nextLine());
        if (escolha < 1 || escolha > medicos.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Medico medicoSelecionado = medicos.get(escolha - 1);
        Consulta novaConsulta = new Consulta(data, hora, medicoSelecionado, usuario);
        consultas.add(novaConsulta);

        System.out.println("Consulta agendada com sucesso!");
    }

    public void cancelarConsulta(Usuario usuario) {
        System.out.println("=== Cancelar Consulta ===");
        System.out.println("Consultas agendadas:");

        List<Consulta> consultasUsuario = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getUsuario().equals(usuario)) {
                consultasUsuario.add(consulta);
            }
        }

        if (consultasUsuario.isEmpty()) {
            System.out.println("Você não possui consultas agendadas.");
            return;
        }

        for (int i = 0; i < consultasUsuario.size(); i++) {
            Consulta consulta = consultasUsuario.get(i);
            System.out.println((i + 1) + ". " + consulta.getData() + " - " + consulta.getHora() +
                    " - " + consulta.getMedico().getNome() + " - " + consulta.getMedico().getEspecialidade());
        }

        System.out.print("Escolha o número da consulta que deseja cancelar: ");
        int escolha = Integer.parseInt(scanner.nextLine());
        if (escolha < 1 || escolha > consultasUsuario.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Consulta consultaCancelada = consultasUsuario.get(escolha - 1);
        consultas.remove(consultaCancelada);

        System.out.println("Consulta cancelada com sucesso!");
    }

    public void visualizarConsultas(Usuario usuario) {
        System.out.println("=== Consultas Agendadas ===");

        List<Consulta> consultasUsuario = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getUsuario().equals(usuario)) {
                consultasUsuario.add(consulta);
            }
        }

        if (consultasUsuario.isEmpty()) {
            System.out.println("Você não possui consultas agendadas.");
            return;
        }

        for (Consulta consulta : consultasUsuario) {
            System.out.println("Data: " + consulta.getData());
            System.out.println("Hora: " + consulta.getHora());
            System.out.println("Médico: " + consulta.getMedico().getNome());
            System.out.println("Especialidade: " + consulta.getMedico().getEspecialidade());
            System.out.println("---------------------------------");
        }
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("=== Sistema de Consulta Médica ===");
            System.out.println("1. Cadastra-se");
            System.out.println("2. login");
            System.out.println("3. Administrador");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    usuarioLogado = fazerLogin();
                    if (usuarioLogado != null) {
                        int opcaoUsuario;
                        do {
                            System.out.println("=== Menu do Usuário ===");
                            System.out.println("1. Agendar Consulta");
                            System.out.println("2. Cancelar Consulta");
                            System.out.println("3. Visualizar Consultas Agendadas");
                            System.out.println("0. Sair");
                            System.out.print("Escolha uma opção: ");
                            opcaoUsuario = Integer.parseInt(scanner.nextLine());

                            switch (opcaoUsuario) {
                                case 1:
                                    agendarConsulta(usuarioLogado);
                                    break;
                                case 2:
                                    cancelarConsulta(usuarioLogado);
                                    break;
                                case 3:
                                    visualizarConsultas(usuarioLogado);
                                    break;
                                case 0:
                                    System.out.println("Saindo...");
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                                    break;
                            }
                        } while (opcaoUsuario != 0);
                    } else {
                        System.out.println("Usuário ou senha inválidos!");
                    }
                    break;
                case 3:
                    System.out.print("Nome de usuário: ");
                    String nomeUsuario = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    if (nomeUsuario.equals("admin") && senha.equals("admin")) {
                        int opcaoAdministrador;
                        do {
                            System.out.println("=== Menu do Administrador ===");
                            System.out.println("1. Cadastrar Médico");
                            System.out.println("0. Sair");
                            System.out.print("Escolha uma opção: ");
                            opcaoAdministrador = Integer.parseInt(scanner.nextLine());

                            switch (opcaoAdministrador) {
                                case 1:
                                    cadastrarMedico();
                                    break;
                                case 0:
                                    System.out.println("Saindo...");
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                                    break;
                            }
                        } while (opcaoAdministrador != 0);
                    } else {
                        System.out.println("Acesso não autorizado!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void main(String[] args) throws Exception {
        SistemaConsultaMedica sistema = new SistemaConsultaMedica();
        sistema.executar();

      
    }
}
