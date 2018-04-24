package batmanrobin;

public class No {
    private String processo;
    private int duracao;
    private int chegada;
    private String operacao;
    private int[] instante;
    private int tempo = 0;
    private int quantum = 0;
    private No proximo;
    
    /**
     * Construtor para a fila
     * @param no 
     */
    public No(No no){
        this.processo = no.getProcesso();
        this.duracao = no.getDuracao();
        this.chegada = no.getChegada();
        this.operacao = no.getOperacao();
        if (operacao.equalsIgnoreCase("sim")) {
            this.instante = no.getInstante();
        }
        this.tempo = no.getTempo();
        this.quantum = no.getQuantum();
        proximo = null;
    }
    
    /**
     * Construtor para a lista
     * @param processo
     * @param duracao
     * @param chegada
     * @param operacao 
     */
    public No(String processo, int duracao, int chegada, String operacao){
        this.processo = processo;
        this.duracao = duracao;
        this.chegada = chegada;
        this.operacao = operacao;
        proximo = null;
    }
    
    /**
     * Construtor para a lista
     * @param processo
     * @param duracao
     * @param chegada
     * @param operacao
     * @param instante 
     */
    public No(String processo, int duracao, int chegada, String operacao,int[] instante){
        this.processo = processo;
        this.duracao = duracao;
        this.chegada = chegada;
        this.operacao = operacao;
        this.instante = instante;
        proximo = null;
    }
    
    /**
     * Construtor para a fila
     * @param processo
     * @param duracao
     * @param chegada
     * @param operacao
     * @param tempo
     * @param quantum 
     */
    public No(String processo, int duracao, int chegada, String operacao, int tempo, int quantum){
        this.processo = processo;
        this.duracao = duracao;
        this.chegada = chegada;
        this.operacao = operacao;
        this.tempo = tempo;
        this.quantum = quantum;
        proximo = null;
    }
    
    /**
     * Construtor para a fila
     * @param processo
     * @param duracao
     * @param chegada
     * @param operacao
     * @param instante
     * @param tempo
     * @param quantum 
     */
    public No(String processo, int duracao, int chegada, String operacao, int[] instante, int tempo, int quantum){
        this.processo = processo;
        this.duracao = duracao;
        this.chegada = chegada;
        this.operacao = operacao;
        this.instante = instante;
        this.tempo = tempo;
        this.quantum = quantum;
        proximo = null;
    }
    
    /**
     * Construtor para a lista
     * @param processo
     * @param duracao
     * @param chegada
     * @param operacao
     * @param no 
     */
    public No(String processo, int duracao, int chegada, String operacao, No no){
        this.processo = processo;
        this.duracao = duracao;
        this.chegada = chegada;
        this.operacao = operacao;
        proximo = no;
    }
    
    /**
     * Construtor para a lista
     * @param processo
     * @param duracao
     * @param chegada
     * @param operacao
     * @param instante
     * @param no 
     */
    public No(String processo, int duracao, int chegada, String operacao, int[] instante, No no){
        this.processo = processo;
        this.duracao = duracao;
        this.chegada = chegada;
        this.operacao = operacao;
        this.instante = instante;
        proximo = no;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getChegada() {
        return chegada;
    }

    public void setChegada(int chegada) {
        this.chegada = chegada;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public int[] getInstante() {
        return instante;
    }

    public void setInstante(int[] instante) {
        this.instante = instante;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
    
    /**
     * Método que verifica se realiza operação de I/O no tempo de próprio processo
     * @return true se realiza operação de I/O e false se não realizar
     */
    public boolean operacaoRealizada(){
        int time = tempo;
        int[] vetor = instante;
        for (int i = 0; i < vetor.length; i++) {
            if (time==vetor[i]) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "\nProcesso: " + processo +
                "\nDuração: " + duracao +
                "\nChegada: " + chegada +
                "\nOperação: " + operacao;
    }
}
