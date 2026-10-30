/**
 * Representação de um estudante, especificamente de computação, matriculado da * UFCG. Todo aluno precisa ter uma matrícula e é identificado unicamente
 * por esta matrícula: 20260014800
 *
 * @author Guilherme Soares Nogueira
 */






package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes.
 * A HotList guarda até 10 deles.
 *
 * @author eliane
 *
 */
public class FilmNow {

    private static final int TAMANHO = 100;
    private static final int TAMANHO_HOT = 10;
    private static final String SINAL_HOT = "🔥 ";

    private Filme[] filmes; //uma representacao simploria da lista de filmes
    private Filme[] filmeHot;

    /**
     * Cria o FilmNow.
     */
    public FilmNow() {
        this.filmes = new Filme[TAMANHO];
        this.filmeHot = new Filme[TAMANHO_HOT];
    }

    /**
     * Acessa a lista de filmes mantida.
     * @return O array de filmes.
     */
    public Filme[] getFilmes() {
        return this.filmes.clone();
    }

    /**
     * Acessa os dados de um filme específico.
     * @param posicao Posição do filme no sistema.
     * @return Dados do filme. Null se não há filme na posição.
     */

    public Filme getFilme(int posicao) {
        return buscaFilme(posicao);
    }

    /**
     * Adiciona um filme em uma posição. Se já existir filme na posição, sobrescreve o anterior.
     * @param posicao Posição do filme.
     * @param nome Nome do filme.
     * @param ano Ano de lançamento do filme.
     * @param local Local onde o filme pode ser assitido.
     * @return A mensagem do resultado da operação.
     */
    public String cadastraFilme(int posicao, String nome, String ano, String local) {
        if (!posicaoEhValida(posicao)) {
            return "POSIÇÃO INVÁLIDA";
        }
        Filme novoFilme;
        try {
            novoFilme = new Filme(nome, ano, local);
        } catch (IllegalArgumentException e) {
            return "FILME INVALIDO";
        }
        if (jaExiste(novoFilme)) {
            return "FILME JA ADICIONADO";
        }
        this.filmes[posicao - 1] = novoFilme;
        return "FILME ADICIONADO";
    }

    /**
     * Monta a lista de todos os filmes, uma linha por filme: posição e nome.
     *
     * @return O texto da lista. Vazio se não há filmes.
     */
    public String mostrarFilmes() {
        String texto = "";
        for (int i = 0; i < this.filmes.length; i++) {
            if (this.filmes[i] != null) {
                if (!texto.isEmpty()) {
                    texto += "\n";
                }
                texto += formataFilme(i + 1, this.filmes[i]);
            }
        }
        return texto;
    }

    /**
     * Monta os detalhes de um filme: nome e ano na primeira linha, locais na segunda.
     * Filmes da HotList começam com o sinal de hot.
     *
     * @param posicao Posição do filme (de 1 a 100).
     * @return Os detalhes, "POSIÇÃO INVÁLIDA!" se a posição não existe, ou texto vazio se não há filme na posição.
     */
    public String detalharFilme(int posicao) {
        if (!posicaoEhValida(posicao)) {
            return "POSIÇÃO INVÁLIDA!";
        }
        Filme filme = buscaFilme(posicao);
        if (filme == null) {
            return "";
        }
        String detalhes = filme.toString() + "\n" + filme.getLocais();
        if (estaNaHot(filme)) {
            detalhes = SINAL_HOT + detalhes;
        }
        return detalhes;
    }

    /**
     * Coloca um filme da lista na HotList. Se a posição da HotList já tinha um filme, ele sai da HotList.
     *
     * @param posicaoFilme Posição do filme na lista de filmes (de 1 a 100).
     * @param posicaoHot Posição na HotList (de 1 a 10).
     * @return A mensagem do resultado da operação.
     */
    public String atribuirHot(int posicaoFilme, int posicaoHot) {
        Filme filme = buscaFilme(posicaoFilme);
        if (filme == null || !posicaoHotEhValida(posicaoHot)) {
            return "POSIÇÃO INVÁLIDA!";
        }
        if (estaNaHot(filme)) {
            return "FILME JA ESTA NA HOTLIST!";
        }
        this.filmeHot[posicaoHot - 1] = filme;
        return "ADICIONADO À HOTLIST NA POSIÇÃO " + posicaoHot + "!";
    }

    /**
     * Retira o filme de uma posição da HotList.
     *
     * @param posicaoHot Posição na HotList (de 1 a 10).
     * @return Texto vazio se deu certo, ou a mensagem de erro.
     */
    public String removerHot(int posicaoHot) {
        if (!posicaoHotEhValida(posicaoHot)) {
            return "POSIÇÃO INVÁLIDA!";
        }
        this.filmeHot[posicaoHot - 1] = null;
        return "";
    }

    /**
     * Monta a HotList, uma linha por filme: posição na HotList, nome e ano.
     *
     * @return O texto da HotList. Vazio se não há filmes nela.
     */
    public String exibirHot() {
        String texto = "";
        for (int i = 0; i < this.filmeHot.length; i++) {
            if (this.filmeHot[i] != null) {
                if (!texto.isEmpty()) {
                    texto += "\n";
                }
                texto += (i + 1) + " - " + this.filmeHot[i];
            }
        }
        return texto;
    }

    /**
     * Adiciona um local a um filme.
     *
     * @param posicaoFilme Posição do filme (de 1 a 100).
     * @param local O novo local.
     * @return A mensagem do resultado da operação.
     */
    public String adicionarLocal(int posicaoFilme, String local) {
        Filme filme = buscaFilme(posicaoFilme);
        if (filme == null) {
            return "POSIÇÃO INVÁLIDA!";
        }
        if (filme.adicionarLocal(local)) {
            return "LOCAL ADICIONADO";
        }
        return "LOCAL NAO ADICIONADO";
    }

    /**
     * Remove um local de um filme.
     *
     * @param posicaoFilme Posição do filme (de 1 a 100).
     * @param local O local a remover.
     * @return A mensagem do resultado da operação.
     */
    public String removerLocal(int posicaoFilme, String local) {
        Filme filme = buscaFilme(posicaoFilme);
        if (filme == null) {
            return "POSIÇÃO INVÁLIDA!";
        }
        if (filme.removerLocal(local)) {
            return "LOCAL REMOVIDO";
        }
        return "LOCAL NAO REMOVIDO";
    }

    /**
     * Formata um filme para impressão.
     *
     * @param posicao A posição do filme (que é exibida)/
     * @param filme O filme a ser impresso.
     * @return A String formatada.
     */
    private String formataFilme(int posicao, Filme filme) {
        return posicao + " - " + filme.getNome();
    }

    private Filme buscaFilme(int posicao) {
        if (!posicaoEhValida(posicao)) {
            return null;
        }
        return this.filmes[posicao - 1];
    }

    private boolean posicaoEhValida(int posicao) {
        return posicao >= 1 && posicao <= TAMANHO;
    }

    private boolean posicaoHotEhValida(int posicao) {
        return posicao >= 1 && posicao <= TAMANHO_HOT;
    }

    private boolean jaExiste(Filme outro) {
        for (int i = 0; i < this.filmes.length; i++) {
            if (this.filmes[i] != null && this.filmes[i].equals(outro)) {
                return true;
            }
        }
        return false;
    }

    private boolean estaNaHot(Filme filme) {
        for (int i = 0; i < this.filmeHot.length; i++) {
            if (this.filmeHot[i] != null && this.filmeHot[i].equals(filme)) {
                return true;
            }
        }
        return false;
    }

}