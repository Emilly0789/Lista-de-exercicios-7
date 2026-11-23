
void main() {
    Scanner scanner = new Scanner(System.in);
    int[] numeros = new int[5];

    for (int i = 0; i < numeros.length; i++) {
        numeros[i] = lerInteiro(scanner, "Digite o número " + (i + 1) + ": ");
    }

    // Acesso por índice, repetindo até o usuário querer sair
    while (true) {
        int indice = lerInteiro(scanner, "\nDigite o índice que deseja acessar (0 a 4, ou -1 para sair): ");

        if (indice == -1) {
            IO.print("Encerrando o programa.");
            break;
        }

        try {
            IO.println("O valor no índice " + indice + " é: " + numeros[indice]);
        } catch (ArrayIndexOutOfBoundsException e) {
            IO.println("Erro: índice " + indice + " inválido! Use um valor entre 0 e " + (numeros.length - 1) + ".");
        }
    }

    scanner.close();
}

// Garante que o usuário digite um número inteiro
private static int lerInteiro(Scanner scanner, String mensagem) {
    while (true) {
        IO.print(mensagem);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            IO.print("Entrada inválida! Digite apenas números inteiros.");
            scanner.next(); // descarta a entrada incorreta
        }
    }
}
