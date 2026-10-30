/**
 * Representação de um estudante, especificamente de computação, matriculado da * UFCG. Todo aluno precisa ter uma matrícula e é identificado unicamente
 * por esta matrícula: 20260014800
 *
 * @author Guilherme Soares Nogueira
 */

package filmnow;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Um filme com nome, ano de lançamento e de 1 a 5 locais onde pode ser assistido.
 */
public class Filme {

    private static final int MAXIMO_LOCAIS = 5;

    private String nome;
    private String ano;
    private ArrayList<String> locais;

    /**
     * Cria um filme.
     *
     * @param nome Nome do filme.
     * @param ano Ano de lançamento (pode ser vazio).
     * @param local Primeiro local onde o filme pode ser assistido.
     * @throws NullPointerException Se algum parâmetro for nulo.
     * @throws IllegalArgumentException Se o nome ou o local forem vazios ou só espaços.
     */
    // construtor
    public Filme(String nome, String ano, String local) {
        if (nome == null || ano == null || local == null) {
            throw new NullPointerException("FILME NULO");
        }
        if (nome.isBlank() || local.isBlank()) {
            throw new IllegalArgumentException("FILME INVALIDO");
        }
        this.nome = nome;
        this.ano = ano;
        this.locais = new ArrayList<>();
        this.locais.add(local);
    }

    // getters
    public String getNome() {
        return this.nome;
    }

    public String getAno() {
        return this.ano;
    }

    /**
     * @return Os locais do filme separados por vírgula.
     */
    public String getLocais() {
        return String.join(", ", this.locais);
    }

    /**
     * Adiciona um local onde o filme pode ser assistido.
     *
     * @param local O novo local.
     * @return true se adicionou. false se o local é vazio, já existe ou o filme já tem 5 locais.
     */
    public boolean adicionarLocal(String local) {
        if (local.isBlank() || this.locais.contains(local) || this.locais.size() >= MAXIMO_LOCAIS) {
            return false;
        }
        this.locais.add(local);
        return true;
    }

    /**
     * Remove um local do filme. O filme sempre mantém pelo menos um local.
     *
     * @param local O local a remover.
     * @return true se removeu. false se o local não existe ou é o único do filme.
     */
    public boolean removerLocal(String local) {
        if (this.locais.size() == 1) {
            return false;
        }
        return this.locais.remove(local);
    }

    //toString(entregar o texto correto)
    @Override
    public String toString() {
        if (this.ano.isBlank()) {
            return this.nome;
        }
        return this.nome + ", " + this.ano;
    }

    //equals() verificar se o filme é repetido ou nao
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Filme filme = (Filme) o;
        return Objects.equals(getNome(), filme.getNome()) && Objects.equals(getAno(), filme.getAno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getAno());
    }

}