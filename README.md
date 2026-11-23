Questão 1: Em Java, a hierarquia de exceções começa na classe Throwable (que herda de Object) e se divide em duas subclasses diretas: Error e Exception, sendo RuntimeException uma subclasse de Exception.

Diferenças principais

• Throwable: É a classe raiz de tudo o que pode ser lançado (throw) ou capturado (catch) no mecanismo de exceções do Java. Fornece métodos úteis como getMessage(), getStackTrace() e printStackTrace().
• Error: Representa falhas graves da JVM ou de recursos do sistema, geralmente irrecuperáveis pela aplicação. Não exige tratamento em tempo de compilação (unchecked) e não deve ser capturado no código normal. Exemplos: OutOfMemoryError e StackOverflowError.
• Exception: Representa condições excepcionais que a aplicação pode prever e tratar. Subclasses diretas de Exception (fora RuntimeException) são checked exceptions: o compilador obriga a tratar com try-catch ou declarar com throws. Exemplos: IOException e ClassNotFoundException.
• RuntimeException: É uma subclasse de Exception para erros que costumam indicar falhas de lógica ou uso incorreto de APIs durante a execução. É uma unchecked exception: o compilador não exige try-catch nem throws. Exemplos: NullPointerException, IllegalArgumentException e ArrayIndexOutOfBoundsException.

Questão 2: O bloco try-with-resources é uma estrutura de controle do Java (introduzida no Java 7) que declara um ou mais recursos e garante que cada um deles seja fechado automaticamente ao final da execução, mesmo que ocorra uma exceção.

Quando deve ser usado?

Você deve usá-lo sempre que manipular recursos externos que precisam ser encerrados manualmente após o uso para evitar vazamento de memória (memory leaks) ou travamento de arquivos/conexões. Os cenários mais comuns incluem:
• Leitura e escrita de arquivos (FileInputStream, BufferedReader, Scanner).
• Conexões com bancos de dados (Connection, PreparedStatement, ResultSet).
• Comunicação em rede (Socket, ServerSocket).

Exemplo Prático

java
// O BufferedReader é declarado dentro dos parênteses do try
try (BufferedReader br = new BufferedReader(new FileReader("dados.txt"))) {
    System.out.println(br.readLine());
} catch (IOException e) {
    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
}
// Não é necessário usar o bloco "finally" para chamar br.close()
Use o código com cuidado.

Quais interfaces são compatíveis?

Para que um objeto possa ser utilizado dentro de um try-with-resources, sua classe deve implementar obrigatoriamente uma das duas interfaces abaixo:
1. java.lang.AutoCloseable: É a interface raiz criada especificamente para esse recurso. Qualquer classe que a implemente pode ser usada no bloco. Seu método close() lança Exception genérica.
2. java.io.Closeable: É uma subinterface que estende AutoCloseable. Foi mantida por retrocompatibilidade com classes de entrada/saída (I/O) mais antigas. Seu método close() lança especificamente IOException.

Questão 3:
