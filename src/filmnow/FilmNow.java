package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes.
 *
 * @author eliane
 *
 */
public class FilmNow {

    private static final int TAMANHO = 100;

    private Filme[] filmes; //uma representacao simploria da lista de filmes

    /**
     * Cria o FilmNow.
     */
    public FilmNow() {
        this.filmes = new Filme[TAMANHO];
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
        int ind = posicao - 1;
        return filmes[ind];
    }

    /**
     * Adiciona um filme em uma posição. Se já existir filme na posição, sobrescreve o anterior.
     * @param posicao Posição do filme.
     * @param nome Nome do filme.
     * @param ano Ano de lançamento do filme.
     * @param local Local onde o filme pode ser assitido.
     */
    public String cadastraFilme(int posicao, String nome, String ano, String local){
        int ind = posicao - 1;
       if (!posicaoEhValida(posicao)) {
           return "POSIÇÃO INVÁLIDA";
       }
       if (!verificaString(nome, local)) {
            return "FILME INVALIDO";
       }
        Filme outroFilme = new Filme(nome, ano, local);
        if(verificaFilme(outroFilme)){
            this.filmes[ind] = outroFilme;
            return "FILME ADICIONADO";
        }
        return "FILME JA ADICIONADO";
    }

    private boolean posicaoEhValida(int posicao){
        if (posicao >= 1 && posicao <= TAMANHO){
            return true;
            }
        return false;
    }

    private boolean verificaString(String nome, String local){
        // isBlank() devolve true quando o texto é vazio ou só tem espaços
        if (nome.isBlank() || local.isBlank()){
            return false;
        }
        return true;
    }


    private boolean verificaFilme(Filme outro){
        for (int i = 0; i < this.filmes.length; i++){
            Filme f = this.filmes[i];
            if (f != null) {
                if (f.equals(outro)) {
                    return false;
                }
            }
        }
        return true;
aa
    }

}