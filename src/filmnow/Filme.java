package filmnow;

import java.util.Objects;

public class Filme {
    private String nome;
    private String ano;
    private String local;
    // construtores
    public Filme(String nome, String ano, String local) {
        this.nome = nome;
        this.ano = ano;
        this.local = local;
    }
    // getters
    public String getNome() {
        return this.nome;
    }
    public String getAno(){
        return this.ano;
    }
    public String getLocal(){
        return this.local;
    }
    //toString(entregar o texto correto)
    @Override
    public String toString(){
        return this.nome + ", " + this.ano;
    }

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
