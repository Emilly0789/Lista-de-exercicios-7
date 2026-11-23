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

Questão 3: O recurso de multi-catch (introduzido no Java 7) permite capturar múltiplos tipos de exceções em um único bloco catch, separando-os pelo operador pipe (|), o que elimina a duplicação de código quando o tratamento é idêntico.

Exemplo de sintaxe

java
try {
    // Código que pode lançar IOException ou SQLException
} catch (IOException | SQLException ex) {
    // Tratamento unificado para ambas as exceções
    logger.log(ex);
}
Use o código com cuidado.

Principais cuidados ao utilizar

O cuidado mais importante envolve a hierarquia de herança entre as exceções:
• Proibição de relação pai-filho: As exceções listadas no mesmo bloco catch não podem ter relação de herança entre si (superclasse e subclasse). Por exemplo, catch (IOException | Exception ex) gera um erro de compilação, pois IOException já é filha de Exception e a captura da classe mais específica seria redundante.
• Variável implicitamente final: A variável que recebe a exceção (como ex no exemplo acima) é considerada implicitamente final. Você não pode reatribuir um novo objeto a ela dentro do bloco catch.
• Tipo da variável capturada: Em tempo de compilação, o tipo da variável será a superclasse comum mais próxima entre as exceções listadas (muitas vezes Exception ou Throwable), limitando os métodos específicos que você pode chamar diretamente sem fazer um cast.
