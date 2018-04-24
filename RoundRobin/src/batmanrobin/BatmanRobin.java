package batmanrobin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BatmanRobin {

    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);
        String processo = "", operacao = "";
        int chegada = 0, duracao = 0, quantum;
        int cont = 0, programa;
        boolean verifica = false;
        Lista lista = new Lista();
        FilaDin fila = new FilaDin();
        BufferedReader br = new BufferedReader(new FileReader("arquivo.txt"));
        String linha;
        while ((linha = br.readLine()) != null) {
            if (linha.isEmpty()) {//se linha vazia cont permanece 0
                cont = 0;
            } else {
                switch (cont) {
                    case 0:
                        processo = linha;//recebe o nome do processo
                        break;
                    case 1:
                        duracao = Integer.parseInt(linha);//recebe o tempo de duração
                        break;
                    case 2:
                        chegada = Integer.parseInt(linha);//recebe o tempo de chegada
                        break;
                    case 3:
                        operacao = linha;//recebe se há ou não operação
                        if (linha.equalsIgnoreCase("nao")) {
                            //se não houver operação as informações são setadas e cont é reiniciado
                            lista.inserInOrder(processo, duracao, chegada, operacao);
                            cont = 0;
                        }
                        break;
                    case 4:
                        //se há operação cont chega em 4
                        String[] operacoes = linha.split(" ");
                        //recebe vetor de String com os instante de I/O
                        int[] instante = new int[operacoes.length];
                        for (int i = 0; i < instante.length; i++) {
                            instante[i] = Integer.parseInt(operacoes[i]);
                            //passa tudo para um vetor de inteiros com os instantes de I/O
                        }//informações são setadas
                        lista.inserInOrder(processo, duracao, chegada, operacao, instante);
                        verifica = true;//sinaliza que as informações foram setadas
                        break;
                    default:
                        break;
                }
                cont++;
                if (verifica == true) {//se informações setadas o cont é reiniciado
                    verifica = false;
                    cont = 0;
                }
            }
        }
        br.close();

        System.out.println("Insira o valor de quantum:");
        quantum = entrada.nextInt();//recebe quantum
        lista.fatia(quantum);//seta quantum em cada processo
        fila.setFatia(quantum);//passa o quantum para a classe fila

        programa = lista.duracaoTotal();//calcula o tempo de programa
        System.out.println("*************************************");
        System.out.println("*******Escalonador Round Robin*******");
        System.out.println("*************************************");
        System.out.println("");

        for (int i = 0; i <= programa; i++) {
            System.out.println("Tempo " + i + " " + fila.enqueue(lista.chegada(i))
                    + fila.atualizaOperacao() + fila.atualizaQuantum()
                    + fila.atualizaDuracao());//chama os métodos para imprimir o que acontece em dado instante
            if (i == programa) {//quando programa chega no ultimo instante não há processos na fila e no cpu
                System.out.println("Fila: nao ha processos");
            } else {
                System.out.println("Fila: " + fila.mostrarFila());//mostra a fila
                System.out.println("CPU: " + fila.cpu());//mostra o cpu

                fila.incrementaTempo();
                fila.decrementaDuracao();
                fila.decrementaQuantum();
                fila.diagrama();

                if (fila.verificaOperacao() == false) {
                    if (fila.verificaQuantum() == false) {
                        if (fila.verificaDuracao()) {
                            fila.espera(i);
                        }
                    }
                }
            }
        }
        System.out.println("");
        System.out.println(fila.getDiagrama());
        System.out.println("");
        System.out.println(fila.getEspera());
        System.out.println("");
        System.out.println("Tempo de espera medio: " + fila.media());
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("Encerrando simulacao de escalonamento");
        System.out.println("*************************************");

    }

}
