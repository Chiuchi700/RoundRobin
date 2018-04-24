package batmanrobin;

public class Lista {

    private No firstNo;
    private No lastNo;
    private int tam;

    public Lista() {
        firstNo = lastNo = null;
        tam = 0;
    }

    public boolean isEmpty() {
        return (firstNo == null);
    }
    
    public void insertAtFront(String processo, int duracao, int chegada, String operacao) {
        if (isEmpty()) { // firstNode e lastNode são nulos
            firstNo = lastNo = new No(processo, duracao, chegada, operacao);
        } else { // firstNode se refere ao novo No
            firstNo = new No(processo, duracao, chegada, operacao, firstNo);
        }
        tam++;
    }
    
    public void insertAtFront(String processo, int duracao, int chegada, String operacao, int[] instante) {
        if (isEmpty()) { // firstNode e lastNode são nulos
            firstNo = lastNo = new No(processo, duracao, chegada, operacao, instante);
        } else { // firstNode se refere ao novo No
            firstNo = new No(processo, duracao, chegada, operacao, instante, firstNo);
        }
        tam++;
    }
    
    public void insertAtBack(String processo, int duracao, int chegada, String operacao) {
        if (isEmpty()) {
            firstNo = lastNo = new No(processo, duracao, chegada, operacao);
        } else {
            lastNo.setProximo(new No(processo, duracao, chegada, operacao));
            lastNo = lastNo.getProximo();
        }
        tam++;
    }
    
    public void insertAtBack(String processo, int duracao, int chegada, String operacao, int[] instante) {
        if (isEmpty()) {
            firstNo = lastNo = new No(processo, duracao, chegada, operacao, instante);
        } else {
            lastNo.setProximo(new No(processo, duracao, chegada, operacao, instante));
            lastNo = lastNo.getProximo();
        }
        tam++;
    }

    public int removeFromFront() {
        if (isEmpty()) {
            return -1;  //flag para indicar Lista vazia
        }
        int removItem = firstNo.getChegada();

        // atualiza referencias firstNode and lastNode 
        if (firstNo == lastNo){ //último nó será removido
            firstNo = lastNo = null;
        } else {
            firstNo = firstNo.getProximo();
        }
        tam--;
        return removItem;
    }

    public int removeFromBack() {
        if (isEmpty()) {
            return -1;
        }

        int removedItem = lastNo.getChegada();

        if (firstNo == lastNo) {
            firstNo = lastNo = null;
        } else {
            No atual = firstNo;
            while (atual.getProximo() != lastNo) {
                atual = atual.getProximo();
            }
            lastNo = atual;
            atual.setProximo(null);
        }
        tam--;
        return removedItem;
    }

    public int getTam() {
        return tam;
    }
    
    /**
     * Método que calcula a duração total
     * @return int com tempo total de duração
     */
    public int duracaoTotal() {
        No atual = firstNo;
        int saida = 0;
        while (atual != null) {
            saida = saida + atual.getDuracao();
            atual = atual.getProximo();
        }
        return saida;
    }
    
    /**
     * Método que insere passa o quantum para os processos
     * @param quantum 
     */
    public void fatia(int quantum) {
        No atual = firstNo;
        while (atual != null) {
            atual.setQuantum(quantum);
            atual = atual.getProximo();
        }
    }
    
    /**
     * Verifica se processo chegou em tal instante
     * @param tempo
     * @return No em que processo chegou ou null
     */
    public No chegada(int tempo) {
        No atual = firstNo;
        while (atual != null) {
            if (atual.getChegada() == tempo) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }
    
    public void inserInOrder(String processo, int duracao, int chegada, String operacao) {
        if (isEmpty()) {
            insertAtFront(processo, duracao, chegada, operacao);
            return;
        }
        No atual = firstNo;
        No anterior = null;
        while (atual != null && atual.getChegada() <= chegada) {
            anterior = atual;
            atual = atual.getProximo();
        }
        if (atual == firstNo) {
            insertAtFront(processo, duracao, chegada, operacao);
            return;
        }
        if (atual == null) {
            insertAtBack(processo, duracao, chegada, operacao);
            return;
        }
        anterior.setProximo(new No(processo, duracao, chegada, operacao, atual));
        tam++;
    }
    
    public void inserInOrder(String processo, int duracao, int chegada, String operacao, int[] instante) {
        if (isEmpty()) {
            insertAtFront(processo, duracao, chegada, operacao, instante);
            return;
        }
        No atual = firstNo;
        No anterior = null;
        while (atual != null && atual.getChegada() <= chegada) {
            anterior = atual;
            atual = atual.getProximo();
        }
        if (atual == firstNo) {
            insertAtFront(processo, duracao, chegada, operacao, instante);
            return;
        }
        if (atual == null) {
            insertAtBack(processo, duracao, chegada, operacao, instante);
            return;
        }
        anterior.setProximo(new No(processo, duracao, chegada, operacao, instante, atual));
        tam++;
    }
    
}//class Lista
