/**
 * Representação de um estudante, especificamente de computação, matriculado da * UFCG. Todo aluno precisa ter uma matrícula e é identificado unicamente
 * por esta matrícula: 20260014800
 *
 * @author Guilherme Soares Nogueira
 */

package filmnow;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular o sistema FilmNow.
 *
 * @author eliane
 *
 */
public class MainFilmNow {

    public static void main(String[] args) {
        FilmNow fn = new FilmNow();

        System.out.println("Carregando filmes ...");
        try {
            /*
             * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
             */
            carregaFilmes("filmes_inicial.csv", fn);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, fn, scanner);
        }

    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param scanner Para captura da opção do usuário/a.
     * @return O comando escolhido.
     */
    private static String menu(Scanner scanner) {
        System.out.print(
                "\n(A)Adicionar filme\n" +
                        "(M)Mostrar todos\n" +
                        "(D)Detalhar filme\n" +
                        "(E)Exibir HotList\n" +
                        "(H)Atribuir Hot\n" +
                        "(R)Remover Hot\n" +
                        "(L)Adicionar local\n" +
                        "(V)Remover local\n" +
                        "(S)Sair\n" +
                        "\n" +
                        "Opção> ");
        return scanner.nextLine().trim().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao   Opção digitada.
     * @param fn  O sistema FilmNow que estamos manipulando.
     * @param scanner Objeto scanner para o caso do comando precisar de mais input.
     */
    private static void comando(String opcao, FilmNow fn, Scanner scanner) {
        switch (opcao) {
            case "A":
                adicionaFilme(fn, scanner);
                break;
            case "M":
                mostrarFilmes(fn);
                break;
            case "D":
                detalharFilme(fn, scanner);
                break;
            case "E":
                exibirHot(fn);
                break;
            case "H":
                atribuirHot(fn, scanner);
                break;
            case "R":
                removerHot(fn, scanner);
                break;
            case "L":
                adicionarLocal(fn, scanner);
                break;
            case "V":
                removerLocal(fn, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    /**
     * Imprime lista de filmes.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void mostrarFilmes(FilmNow fn) {
        System.out.println("\n" + fn.mostrarFilmes());
    }

    /**
     * Imprime os detalhes de um dos filmes.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void detalharFilme(FilmNow fn, Scanner scanner) {
        System.out.print("Filme> ");
        int posicao = lerNumero(scanner);

        String detalhes = fn.detalharFilme(posicao);
        if (detalhes.isEmpty()) {
            detalhes = "POSIÇÃO INVÁLIDA!";
        }
        System.out.println("\n" + detalhes);
    }

    /**
     * Cadastra um filme no sistema.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir informações do contato.
     */
    private static void adicionaFilme(FilmNow fn, Scanner scanner) {
        System.out.print("\nPosição> ");
        int posicao = lerNumero(scanner);
        System.out.print("Nome> ");
        String nome = scanner.nextLine();
        System.out.print("Ano> ");
        String ano = scanner.nextLine();
        System.out.print("Local> ");
        String local = scanner.nextLine();
        System.out.println(fn.cadastraFilme(posicao, nome, ano, local));
    }

    /**
     * Imprime a HotList.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     */
    private static void exibirHot(FilmNow fn) {
        System.out.println("\n" + fn.exibirHot());
    }

    /**
     * Coloca um filme na HotList.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir o filme e a posição na HotList.
     */
    private static void atribuirHot(FilmNow fn, Scanner scanner) {
        System.out.print("Filme> ");
        int posicaoFilme = lerNumero(scanner);
        System.out.print("Posicao> ");
        int posicaoHot = lerNumero(scanner);
        System.out.println(fn.atribuirHot(posicaoFilme, posicaoHot));
    }

    /**
     * Retira um filme da HotList.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir a posição na HotList.
     */
    private static void removerHot(FilmNow fn, Scanner scanner) {
        System.out.print("Posicao> ");
        int posicaoHot = lerNumero(scanner);
        String resposta = fn.removerHot(posicaoHot);
        if (!resposta.isEmpty()) {
            System.out.println(resposta);
        }
    }

    /**
     * Adiciona um local a um filme.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir o filme e o local.
     */
    private static void adicionarLocal(FilmNow fn, Scanner scanner) {
        System.out.print("Filme> ");
        int posicaoFilme = lerNumero(scanner);
        System.out.print("Local> ");
        String local = scanner.nextLine();
        System.out.println(fn.adicionarLocal(posicaoFilme, local));
    }

    /**
     * Remove um local de um filme.
     *
     * @param fn O sistema FilmNow a ser manipulado.
     * @param scanner Scanner para pedir o filme e o local.
     */
    private static void removerLocal(FilmNow fn, Scanner scanner) {
        System.out.print("Filme> ");
        int posicaoFilme = lerNumero(scanner);
        System.out.print("Local> ");
        String local = scanner.nextLine();
        System.out.println(fn.removerLocal(posicaoFilme, local));
    }

    /**
     * Lê um número digitado. Se o texto não for um número, devolve 0,
     * que é uma posição inválida em qualquer lista do sistema.
     *
     * @param scanner Scanner para ler a linha digitada.
     * @return O número digitado, ou 0 se não era um número.
     */
    private static int lerNumero(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Sai da aplicação.
     */
    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    /**
     * Lê carga de filmes de um arquivo csv.
     *
     * @param arquivoFilmes O caminho para o arquivo.
     * @param fn O sistema FilmNow a ser populado com os dados.
     * @throws IOException Caso o arquivo não exista ou não possa ser lido.
     */
    private static void carregaFilmes(String arquivoFilmes, FilmNow fn) throws FileNotFoundException, IOException {
        LeitorFilmNow leitor = new LeitorFilmNow();

        int carregados = leitor.carregaContatos(arquivoFilmes, fn);
        System.out.println("Carregamos " + carregados + " registros.");
    }
}