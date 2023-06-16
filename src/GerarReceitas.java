import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class GerarReceitas {
    public static void main(String[] args) {
        double receitaGerada = gerarReceita();
        System.out.println("Sua Receita Total é de : " + receitaGerada + " R$");
    }

    private static double gerarReceita() {
        try (FileReader fileReader = new FileReader(CadastroVendas.ARQUIVO_VENDAS)) {
            Scanner arquivo = new Scanner(fileReader);

            String[] nomeProduto = new String[50];
            double[] valorTotalVendas = new double[50];
            double receitaTotal = 0;
            int contador = 0;
            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine();
                if (linha.equals("")) break;
                nomeProduto[contador] = linha;
                linha = arquivo.nextLine();
                int quantidade = Integer.parseInt(linha);
                linha = arquivo.nextLine();
                double valorProduto =  Double.parseDouble(linha);
                valorTotalVendas[contador] = quantidade * valorProduto;
                receitaTotal += valorTotalVendas[contador];
                contador++;
            }

            escreverArquivo(nomeProduto, valorTotalVendas);
            return receitaTotal;
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0.0;
        }
    }

    private static void escreverArquivo(String[] nomeProduto, double[] valorTotalVendas) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dadosReceita.txt"))) {
            for (int i = 0; i < nomeProduto.length; i++) {
                if (nomeProduto[i] == null) break;
                bufferedWriter.write(nomeProduto[i] + " - " + valorTotalVendas[i] + "\n");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
