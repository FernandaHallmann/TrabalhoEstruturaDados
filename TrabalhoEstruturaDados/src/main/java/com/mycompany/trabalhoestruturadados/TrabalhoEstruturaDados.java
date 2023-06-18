package com.mycompany.trabalhoestruturadados;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Ferna
 */
public class TrabalhoEstruturaDados {
    
    public static void main(String[] args) {
        int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Informe quantos números deseja inserir no vetor:"));
        int[] vetorNumeros = new int[tamanho];
        
        Random gerador = new Random();
        for (int i = 0; i < vetorNumeros.length; i++) {
            vetorNumeros[i] = gerador.nextInt(999999);
            //vetorNumeros[i] = Integer.parseInt(JOptionPane.showInputDialog("Digite o " + (i + 1) + "º número:"));
        }
        
        int[] antigoVetorNumeros = vetorNumeros.clone();
        int opcao = 1;
        long tempoExecucao = 0;
        
        while (opcao != 0) {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da opção que deseja:\n"
                    + "1 - Ordenação por inserção\n"
                    + "2 - Ordenação por seleção\n"
                    + "3 - Ordenação bolha\n"
                    + "0 - Sair"));

            switch (opcao) {
                case 1:
                    tempoExecucao = ordenacaoInsercao(vetorNumeros);
                    opcao = 0;
                    break;
                case 2:
                    tempoExecucao = ordenacaoSelecao(vetorNumeros);
                    opcao = 0;
                    break; 
                case 3:
                    tempoExecucao = ordenacaoBolha(vetorNumeros);
                    opcao = 0;
                    break;
                default:
                    break;
            }
        }
        
        exibeResultados(antigoVetorNumeros, vetorNumeros, tempoExecucao);
        
        opcao = 1;
        int posicao = 0;
        int chave = Integer.parseInt(JOptionPane.showInputDialog("Informe o número a ser pesquisado:"));
        while (opcao != 0) {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da opção que deseja:\n"
                    + "1 - Pesquisa Linear\n"
                    + "2 - Pesquisa Binária\n"
                    + "0 - Sair"));
            
            switch (opcao) {
                case 1:
                    posicao = pesquisaLinear(vetorNumeros, chave);
                    opcao = 0;
                    break;
                case 2:
                    posicao = pesquisaBinaria(vetorNumeros, chave);
                    opcao = 0;
                    break;
                default:
                    break;
            }
        }
        
        if (posicao >= 0) {
            JOptionPane.showMessageDialog(null, "O elemento " + chave + " está localizado na posição: " + posicao);
        } else {
            JOptionPane.showMessageDialog(null, "O elemento " + chave + " não foi localizado na lista");
        }
    }
    
    
    public static long ordenacaoInsercao(int[] numeros) {
        long inicio = System.currentTimeMillis();
        int chave, j;
        
        for(int i = 1; i < numeros.length; i++){
            chave = numeros[i];
            
            for(j = i - 1;  (j >= 0 && numeros[j] > chave) ; j-- ){
                
                numeros[j+1] = numeros[j];
                
            }
            numeros[j+1] = chave;
        }
        
        long fim = System.currentTimeMillis();
        return fim - inicio;
    }

    
    public static long ordenacaoSelecao(int[] numeros) {
        long inicio = System.currentTimeMillis();
        
        for (int i = 0; i < numeros.length - 1; i++) {
            int posicaoMenor = i;
            for (int j = i + 1; j < numeros.length; j++) {
                if (numeros[j] < numeros[posicaoMenor]) {
                    posicaoMenor = j;
                }
            }
            if (posicaoMenor != i) {
                int aux = numeros[i];
                numeros[i] = numeros[posicaoMenor];
                numeros[posicaoMenor] = aux;
            }
        }
        
        long fim = System.currentTimeMillis();
        return fim - inicio;
    }

    
    public static long ordenacaoBolha(int[] numeros) {
        long inicio = System.currentTimeMillis();
        boolean houveTroca = true;

        while (houveTroca) {
            houveTroca = false;

            for (int i = 0; i < numeros.length - 1; i++) {
                if (numeros[i] > numeros[i + 1]) {
                    int aux = numeros[i];
                    numeros[i] = numeros[i + 1];
                    numeros[i + 1] = aux;
                    houveTroca = true;
                }
            }
        }
        
        long fim = System.currentTimeMillis();
        return fim - inicio;
    }
    
    
    public static void exibeResultados(int[] vetorOriginal, int[] vetorNovo, long tempo) {
        String mensagem = "Vetor Original:  |  ";
        for (int i = 0; i < vetorOriginal.length; i++) {
            mensagem += vetorOriginal[i] + "  |  ";
        }
        
        mensagem += "\nVetor Ordenado:  |  ";
        for (int i = 0; i < vetorNovo.length; i++) {
            mensagem += vetorNovo[i] + "  |  ";
        }
        
        mensagem += "\nTempo de execução do método de ordenação escolhido: " + tempo;
        
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    
    public static int pesquisaLinear(int[] numeros, int chave) {   
        for (int i = 0; i < numeros.length; i++) {
           if (chave == numeros[i]) {
               return i;
           }         
       }
       
       return -1;
    }
    
    
    public static int pesquisaBinaria(int[] numeros, int chave) {
        int esquerda, meio, direita;
        esquerda = 0;
        direita = numeros.length - 1;
        
        while (esquerda <= direita) {
            meio = (esquerda + direita) / 2;
            if (chave == numeros[meio]) {
                return meio;
            }
            
            if (chave >  numeros[meio]) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        
        return -1;
    }
    
}
