import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class CadastroVendas {
    public static final String ARQUIVO_VENDAS = "dadosDeVenda.txt";
    public static final String ARQUIVO_RECEITA = "dadosReceita.txt";

    public static void main(String[] args) {
        String[] produtos = new String[50];
        int[] quantidadeProdutos = new int[50];
        double[] precoProdutos = new double[50];

        lerProdutos(produtos, quantidadeProdutos, precoProdutos);
        escreverArquivo(produtos, quantidadeProdutos, precoProdutos);
    }

    private static void lerProdutos(String[] produtos, int[] quantidadeVendas, double[] precoProdutos) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 50; i++) {
            System.out.println("Nome do Produto: ");
            produtos[i] = scanner.nextLine();
            if (produtos[i].equals("")) break;

            System.out.println("Quantidade de Vendas do Produto: ");
            quantidadeVendas[i] = Integer.parseInt(scanner.nextLine());

            System.out.println("Preço do Produto: ");
            precoProdutos[i] = Float.parseFloat(scanner.nextLine());
        }
    }

    private static void escreverArquivo(String[] produtos, int[] quantidadeVendas, double[] precoProdutos) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ARQUIVO_VENDAS))) {
            for (int i = 0; i < produtos.length; i++) {
                if (produtos[i].equals("")) break;
                bufferedWriter.write(produtos[i] + "\n");
                bufferedWriter.write(quantidadeVendas[i] + "\n");
                bufferedWriter.write(precoProdutos[i] + "\n");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}