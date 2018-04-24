package batmanrobin;

public class FilaDin {

    private No inicio, fim;
    private boolean operacao = false;//indica se houve saída por operação de I/O
    private boolean quantum = false;//indica se houve saída por quantum
    private boolean duracao = false;//indica se houve saída po término de duração
    private int fatia;//tempo de quantum
    private int tam;//tam da fila
    private String diagrama = "|";//armazena a tabelinha
    private String espera = "";//armazena String com o tempo de espera de cada processo
    private int media = 0;//conta o numero de processos finalizados
    private double tempoEspera;//armazena o tempo de espera total

    public int getFatia() {
        return fatia;
    }

    public void setFatia(int fatia) {
        this.fatia = fatia;
    }

    public String getDiagrama() {
        return diagrama;
    }

    public void setDiagrama(String diagrama) {
        this.diagrama = diagrama;
    }

    public String getEspera() {
        return espera;
    }

    public void setEspera(String espera) {
        this.espera = espera;
    }
    
    

    public FilaDin() {
        inicio = fim = null;
        tam = 0;
    }

    public boolean isEmpty() {
        return (inicio == null);
    }
    
    /**
     * Método que insere um processo quando chegar na fila pela primeira vez
     * @param no
     * @return String com o processo que chegou
     */
    public String enqueue(No no) {
        //cria um novo nó
        if (no != null) {
            No novo = new No(no);

            if (inicio == null) { //se a fila estiver vazia
                inicio = novo; //aponta o inicio para o novo nó
            } else {
                //aponta o próx do ultimo nó para o novo nó
                fim.setProximo(novo);
            }
            //aponta o fim para o novo nó inserido
            fim = novo;
            tam++;
            return "chegada de processo " + no.getProcesso() + " ";
        }
        return "";
    }
    
    /**
     * Método que insere um processo na fila caso realize operação de I/O
     * @param processo
     * @param duracao
     * @param chegada
     * @param operacao
     * @param instante
     * @param tempo
     * @param quantum 
     */
    public void enqueue(String processo, int duracao, int chegada, String operacao, int[] instante, int tempo, int quantum) {
        //cria um novo nó
        No novo = new No(processo, duracao, chegada, operacao, instante, tempo, quantum);

        if (inicio == null) { //se a fila estiver vazia
            inicio = novo; //aponta o inicio para o novo nó
        } else {
            //aponta o próx do ultimo nó para o novo nó
            fim.setProximo(novo);
        }
        //aponta o fim para o novo nó inserido
        fim = novo;
        tam++;
    }
    
    /**
     * Método que insere um processo na fila caso não realize operação de I/O
     * @param processo
     * @param duracao
     * @param chegada
     * @param operacao
     * @param tempo
     * @param quantum 
     */
    public void enqueue(String processo, int duracao, int chegada, String operacao, int tempo, int quantum) {
        //cria um novo nó
        No novo = new No(processo, duracao, chegada, operacao, tempo, quantum);

        if (inicio == null) { //se a fila estiver vazia
            inicio = novo; //aponta o inicio para o novo nó
        } else {
            //aponta o próx do ultimo nó para o novo nó
            fim.setProximo(novo);
        }
        //aponta o fim para o novo nó inserido
        fim = novo;
        tam++;
    }
    
    /**
     * Método que pega o processo na cpu
     * @return processo que esta na cpu
     */
    public String cpu() {
        No aux = inicio;
        if (isEmpty()) {
            return "Vazio";
        }
        return aux.getProcesso() + "(" + aux.getDuracao() + ")";
    }
    
    /**
     * Método que incrementa o tempo do próprio processo
     */
    public void incrementaTempo() {
        No aux = inicio;
        aux.setTempo(aux.getTempo() + 1);
    }
    
    /**
     * Método que decrementa a duração do próprio processo
     */
    public void decrementaDuracao() {
        No aux = inicio;
        aux.setDuracao(aux.getDuracao() - 1);
    }
    
    /**
     * Método que decrementa o quantum do próprio processo
     */
    public void decrementaQuantum() {
        No aux = inicio;
        aux.setQuantum(aux.getQuantum() - 1);
    }
    
    /**
     * Método que verifica se o processo realiza operação de I/O
     * caso ele realize chama o método da classe e nó e retorna para a variavel
     * se houve operação de I/O
     * @return true se houve operação de I/O e false se não houve
     */
    public boolean verificaOperacao() {
        No aux = inicio;
        if (aux.getOperacao().equalsIgnoreCase("sim")) {
            operacao = aux.operacaoRealizada();
            return operacao;
        }
        return false;
    }
    
    /**
     * Método que verifica se o processo chegou ao final do quantum
     * e retorna para a variavel se chegou ao final de quantum
     * @return true se chegou ao final do quantum e false se não chegou
     */
    public boolean verificaQuantum() {
        No aux = inicio;
        if (aux.getQuantum() == 0) {
            quantum = true;
            return true;
        }
        return false;
    }
    
    /**
     * Método que verifica a duração restante do processo
     * e retorna para a variavel se processo chegou ao fim
     * @return true se processo chegou ao final e false se não chegou
     */
    public boolean verificaDuracao() {
        No aux = inicio;
        if (aux.getDuracao() == 0) {
            duracao = true;
            return true;
        }
        return false;
    }
    
    /**
     * Se houve operação de I/O chama o método reenqueue
     * e reinicia a variavel de controle como false
     * @return String com processo que realizou a operação de I/O ou String vazia
     */
    public String atualizaOperacao() {
        No aux = inicio;
        if (operacao == true) {
            reenqueue();
            operacao = false;
            return "operacao de I/O " + aux.getProcesso() + " ";
        }
        return "";
    }
    
    /**
     * Se houve fim de quantum chama o método reenqueue
     * e reinicia a variavel de controle como false
     * @return String com o processo que teve fim de quantum ou String vazia
     */
    public String atualizaQuantum() {
        No aux = inicio;
        if (this.quantum == true) {
            reenqueue();
            this.quantum = false;
            return "fim de quantum " + aux.getProcesso() + " ";
        }
        return "";
    }
    
    /**
     * Se houve fim de processo chama o método dequeue
     * @return String com o processo que teve fim ou String vazia
     */
    public String atualizaDuracao() {
        No aux = inicio;
        if (duracao == true) {
            dequeue();
            duracao = false;
            return "fim de processo " + aux.getProcesso();
        }
        return "";
    }
    
    /**
     * Método que percorre a fila a partir da segunda posição,
     * pois a primeira é o que esta na cpu
     * @return String com o que há na fila
     */
    public String mostrarFila() {
        No aux = inicio.getProximo();
        String saida = "";
        if (isEmpty()) {
            return "Nao ha processos na fila";
        }
        if (aux == null) {
            return "Nao ha processos na fila";
        }
        if (tam == 1) {
            return "Nao ha processos na fila";
        }
        while (aux != null) {
            saida = saida + aux.getProcesso() + "(" + aux.getDuracao() + ") ";
            aux = aux.getProximo();
        }
        return saida;
    }
    
    /**
     * Método que retira o processo da primeira posição e insere no final da fila
     */
    public void reenqueue() {
        No aux = inicio;
        if (tam == 1) {
            return;
        } else {
            if (aux.getOperacao().equalsIgnoreCase("sim")) {//se realiza operação de I/O
                enqueue(aux.getProcesso(), aux.getDuracao(), aux.getChegada(), aux.getOperacao(), aux.getInstante(), aux.getTempo(), fatia);
                inicio = inicio.getProximo();
            } else {
                enqueue(aux.getProcesso(), aux.getDuracao(), aux.getChegada(), aux.getOperacao(), aux.getTempo(), fatia);
                inicio = inicio.getProximo();
            }
        }
    }
    
    /**
     * Método que cria a tabelinha com cada processo que esteve na cpu
     */
    public void diagrama() {
        No atual = inicio;
        diagrama = diagrama + atual.getProcesso() + "|";
    }
    
    /**
     * Método que calcula o tempo de espera assim que o processo finaliza,
     * armazena numa String e incrementa variavel media
     * @param i tempo no laço
     */
    public void espera(int i) {
        No atual = inicio;
        int tempo;
        tempoEspera = tempoEspera + ((i+1) - (atual.getChegada() + atual.getTempo()));
        tempo = (i+1) - (atual.getChegada() + atual.getTempo());
        media++;
        espera = espera + "\nTempo de espera "+atual.getProcesso()+": "+tempo;
    }
    
    /**
     * Método que calcula a espera media
     * @return tempo de espera medio
     */
    public double media(){
        return tempoEspera/media;
    }
    
    /**
     * Método que retira o processo da fila
     */
    public void dequeue() {
        if (isEmpty()) {
            return;
        }
        inicio = inicio.getProximo();
        if (inicio == null) {
            fim = null;
        }
        tam--;
    }
}
