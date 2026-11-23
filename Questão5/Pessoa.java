package Questão5;

public class Pessoa {

    private int idade;

    public Pessoa(int idade) throws IdadeInvalidaException {
        setIdade(idade);
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) throws IdadeInvalidaException {
        if (idade < 0 || idade > 150) {
            throw new IdadeInvalidaException(
                    "Idade inválida: " + idade + ". A idade deve estar entre 0 e 150.");
        }
        this.idade = idade;
    }
}
