package Questão5;

public class Main {

    public static void main(String[] args) {
        try {
            Pessoa p1 = new Pessoa(25);
            System.out.print("Pessoa criada com idade: " + p1.getIdade());

            Pessoa p2 = new Pessoa(200); // vai lançar a exceção
            System.out.println("Pessoa criada com idade: " + p2.getIdade());
        } catch (IdadeInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
