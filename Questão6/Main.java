package Questão6;

public class Main {

    static class ErroDeProcessamentoException extends Exception {
        public ErroDeProcessamentoException(String mensagem, Throwable causa) {
            super(mensagem, causa);
        }
    }

    // Método que lança uma exceção "de baixo nível"
    static int dividir(int a, int b) {
        return a / b;
    }
    static int processar(int a, int b) throws ErroDeProcessamentoException {
        try {
            return dividir(a, b);
        } catch (ArithmeticException e) {
            throw new ErroDeProcessamentoException("Falha ao processar a divisão", e);
        }
    }

    public static void main(String[] args) {
        try {
            processar(10, 0);
        } catch (ErroDeProcessamentoException e) {
            System.out.print("Exceção capturada: " + e.getMessage());

            Throwable causa = e.getCause();
            System.out.print("Causa: " + causa);
            System.out.print("Tipo da causa: " + causa.getClass().getName());
            System.out.print("Mensagem da causa: " + causa.getMessage());

            System.out.print("\nCadeia completa:");
            Throwable atual = e;
            while (atual != null) {
                System.out.print(" -> " + atual.getClass().getSimpleName() + ": " + atual.getMessage());
                atual = atual.getCause();
            }
            System.out.print("\nStack trace:");
            e.printStackTrace();
        }
    }
}
