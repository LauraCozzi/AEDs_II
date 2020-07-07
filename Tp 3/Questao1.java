//importando bibliotecas
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Questao1 {

    //Inicializando um Scanner global
    public static Scanner sc = new Scanner(System.in);
    public static void main(String [] args) throws Exception{

        int quantidadeComando = 0;
        String expressao = "";
        Lista lista = new Lista();

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            Personagem personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line);           
            lista.inserirFim(personagem);
        }

        quantidadeComando = sc.nextInt();
        sc.nextLine();//limpar o buffer

        for(int i = 0; i < quantidadeComando; i++){
            expressao = sc.nextLine();
            lista.comandoLista(expressao);
        }

        lista.mostrar();

    }


}

    class Personagem {

        private String nome;
        private int altura;
        private double peso;
        private String corDoCabelo;
        private String corDaPele;
        private String corDosOlhos;
        private String anoNascimento;
        private String genero;
        private String homeworld;

        //Contrutores da classe

        Personagem(){
            this.nome = null;
            this.altura = -1;
            this.peso = -1;
            this.corDoCabelo = null;
            this.corDaPele = null;
            this.corDosOlhos = null;
            this.anoNascimento = null;
            this.genero = null;
            this.homeworld = null;

        }

        Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele, 
                          String corDosOlhos, String anoNascimento, String genero, String homeworld){

            this.nome = null;
            this.altura = -1;
            this.peso = -1;
            this.corDoCabelo = null;
            this.corDaPele = null;
            this.corDosOlhos = null;
            this.anoNascimento = null;
            this.genero = null;
            this.homeworld = null;

        }

        //clone de Personagem
        protected Personagem clone() {
            Personagem novo = new Personagem();
            novo.nome = this.nome;
            novo.altura = this.altura;
            novo.peso = this.peso;
            novo.corDoCabelo = this.corDoCabelo;
            novo.corDaPele = this.corDaPele;
            novo.corDosOlhos = this.corDosOlhos;
            novo.anoNascimento = this.anoNascimento;
            novo.genero = this.genero;
            novo.homeworld = this.homeworld;
            return novo;
        }

        //Métodos set
        private void setNome(String aux){
            this.nome = aux;
        }

        private void setAltura(int aux){
            this.altura = aux;
        }

        private void setPeso(Double aux){
            this.peso = aux;
        }

        private void setCorDoCabelo(String aux){
            this.corDoCabelo = aux;
        }

        private void setCorDaPele(String aux){
            this.corDaPele = aux;
        }

        private void setCOrDosOlhos(String aux){
            this.corDosOlhos = aux;
        }

        private void setAnoNascimento(String aux){
            this.anoNascimento = aux;
        }

        private void setGenero(String aux){
            this.genero = aux;
        }

        private void setHomeworld(String aux){
            this.homeworld = aux;
        }

        //Métodos get
         String getNome(){
            return this.nome;
        }
    
         int getAltura(){
            return this.altura;
        }
    
         double getPeso(){
            return this.peso;
        }
    
         String getCorDoCabelo(){
            return this.corDoCabelo;
        }
    
         String getCorDaPele(){
            return this.corDaPele;
        }
    
         String getCorDosOlhos(){
            return this.corDosOlhos;
        }
    
         String getAnoNascimento(){
            return this.anoNascimento;
        }
    
         String getGenero(){
            return this.genero;
        }
    
         String getHomeworld(){
            return this.homeworld;
        }

        /*
        *Imprime na tela os atributos da classe Personagem
        */
        void imprimir(){
            System.out.println(" ## " +
                         this.nome + " ## " +
                         this.altura + " ## " +
                         String.valueOf(this.peso).replace(".0", "")
                         + " ## " + 
                         this.corDoCabelo + " ## " +
                         this.corDaPele + " ## " +
                         this.corDosOlhos + " ## " +
                         this.anoNascimento + " ## " +
                         this.genero + " ## " +
                         this.homeworld + " ## ");
      }


        /** 
        * Leitura do arquivo
        * @param path String endereço do arquivo a ser aberto
        * @return linha String conteúdo do arquivo
        */
         String ler(String path){

            FileReader fr = null;
            BufferedReader br = null;
            String line = null;
            String linha = null;

            try {
                fr = new FileReader(path);
                br = new BufferedReader(fr);
                line = br.readLine();

            while(line != null){
                    linha = line;
                    line = br.readLine();
                    linha += line;
                }

            } catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
            }

            return linha;
           
        }

        //Método separar o que precisa
        String cortarEntrada(String entrada){

            int posicaoI = 0, posicaoF = 0;
            String texto = null;

            posicaoI = entrada.indexOf("name");
            posicaoF = entrada.indexOf(", 'films");
            texto = entrada.substring(posicaoI, posicaoF);

            return texto;

        }

        void separandoString(String entrada){

            String [] informacoes = new String[9];
            String [] respostas = new String[9];
            int altura = 0;
            double peso = 0;

            informacoes = entrada.split("',");

            for(int i = 0; i < informacoes.length; i++){
                respostas[i] = tratarInformacoes(informacoes[i]);               
            }

            altura = mudarAltura(respostas[1]);
            peso = mudarPeso(respostas[2]);

            setNome(respostas[0]);
            setAltura(altura);
            setPeso(peso);
            setCorDoCabelo(respostas[3]);
            setCorDaPele(respostas[4]);
            setCOrDosOlhos(respostas[5]);
            setAnoNascimento(respostas[6]);
            setGenero(respostas[7]);
            setHomeworld(respostas[8]);

        }

        int mudarAltura(String entrada){

            int altura = 0;

            if(entrada.equals("unknown"))entrada = "0";
            altura = Integer.parseInt(entrada);

            return altura;

        }

        double mudarPeso(String entrada){

            double peso = 0;

            entrada = entrada.replace(",", "");
            if(entrada.equals("unknown"))entrada = "0";
            peso = Double.parseDouble(entrada);
            
            return peso;

        }
    
        String tratarInformacoes(String entrada){

            int posicaoI = 0;

            posicaoI = entrada.indexOf(": '");
            entrada = entrada.substring(posicaoI, entrada.length());
            entrada = entrada.replace(": '", "");
            entrada = entrada.replace("'", "");

        return entrada;  

        }

        public void inicio(String entrada){

            entrada = cortarEntrada(entrada);
            separandoString(entrada);
        }



    }// fim classe Personagem

    class Celula {

        public Personagem elemento; // Elemento inserido na celula.
        public Celula prox; // Aponta a celula prox.
        
        public Celula(){
            //this(elemento);
        }
    
        public Celula(Personagem elemento) {
          this.elemento = elemento;
          this.prox = null;
        }
    }

    class Lista {

        private Celula primeiro;
        private Celula ultimo;
    
    
        /**
         * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
         */
        public Lista() {
            primeiro = new Celula();
            ultimo = primeiro;
        }
    
    
        /**
        * Insere um elemento na primeira posicao da lista.
        * @param x int elemento a ser inserido.
        */
        public void inserirInicio(Personagem x) {
            Celula tmp = new Celula(x);
            tmp.prox = primeiro.prox;
            primeiro.prox = tmp;
            if (primeiro == ultimo) {                 
                ultimo = tmp;
            }
          tmp = null;
        }
    

        public void inserirFim(Personagem x) {
            ultimo.prox = new Celula(x);
            ultimo = ultimo.prox;
        }
    

        public Personagem removerInicio() throws Exception {

            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover (vazia)!");
            }
    
            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            Personagem resp = primeiro.elemento;
            tmp.prox = null;
            tmp = null;

            return resp;
        }
    

        public Personagem removerFim() throws Exception {

            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover (vazia)!");
            } 
    
            Celula i;
            for(i = primeiro; i.prox != ultimo; i = i.prox);
        
            Personagem resp = ultimo.elemento; 
            ultimo = i; 
            i = ultimo.prox = null;
          
            return resp;
        }
    
       public void inserir(Personagem x, int pos) throws Exception {
    
          int tamanho = tamanho();
    
          if(pos < 0 || pos > tamanho){
                throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
          } else if (pos == 0){
             inserirInicio(x);
          } else if (pos == tamanho){
             inserirFim(x);
          } else {
               // Caminhar ate a posicao anterior a insercao
             Celula i = primeiro;
             for(int j = 0; j < pos; j++, i = i.prox);
            
             Celula tmp = new Celula(x);
             tmp.prox = i.prox;
             i.prox = tmp;
             tmp = i = null;
          }
       }
    
        public Personagem remover(int pos) throws Exception {

          Personagem resp;
          int tamanho = tamanho();
    
            if (primeiro == ultimo){
                throw new Exception("Erro ao remover (vazia)!");
    
          } else if(pos < 0 || pos >= tamanho){
                throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
          } else if (pos == 0){
             resp = removerInicio();
          } else if (pos == tamanho - 1){
             resp = removerFim();
          } else {
             Celula i = primeiro;
             for(int j = 0; j < pos; j++, i = i.prox);
            
             Celula tmp = i.prox;
             resp = tmp.elemento;
             i.prox = tmp.prox;
             tmp.prox = null;
             i = tmp = null;
          }
    
            return resp;
        }
    
        public void mostrar() {

            int posicao = 0;

            for (Celula i = primeiro.prox; i != null; i = i.prox) {

                System.out.print("[" +posicao+ "] ");
                i.elemento.imprimir();
                posicao++;
            }
        }
    
       public int tamanho() {
          int tamanho = 0; 
          for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
          return tamanho;
       }

       public void comandoLista(String linhaInteira) throws Exception {

            String path = null;
            Personagem personagem = new Personagem();
            String line = null;
            String comando = null;
            Personagem removido = new Personagem();
            int posicao = 0;

            if(linhaInteira.charAt(0) != 'R' && linhaInteira.contains("*") == false){
                path = linhaInteira.substring(3, linhaInteira.length());
            } else if(linhaInteira.contains("I*")){
                path = linhaInteira.substring(6, linhaInteira.length());
            }

            if(path != null){
                line = personagem.ler(path);
                personagem.inicio(line);        
            }

            comando = linhaInteira.substring(0, 2);

            if(comando.equals("II")){
                inserirInicio(personagem);
            } else if(comando.equals("IF")){
                inserirFim(personagem);
            } else if(comando.equals("RI")){
                removido = removerInicio();
                System.out.println("(R) "+removido.getNome());
            } else if(comando.equals("RF")){
            removido = removerFim();
            System.out.println("(R) "+removido.getNome());
            }
            if(comando.equals("I*")){
                posicao = Integer.parseInt(linhaInteira.substring(3,5));
                path = linhaInteira.substring(7, linhaInteira.length());
                inserir(personagem, posicao);
            } else if(comando.equals("R*")){
                posicao = Integer.parseInt(linhaInteira.substring(3,5));
                removido = remover(posicao);
                System.out.println("(R) "+removido.getNome());
            }
        }
    }