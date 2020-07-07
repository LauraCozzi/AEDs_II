//importando bibliotecas
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Questao3 {

    //Inicializando um Scanner global
    public static Scanner sc = new Scanner(System.in);
    public static void main(String [] args) throws Exception{

        int quantidadeComando = 0;
        String expressao = "";
        Fila fila = new Fila();

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            Personagem personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line);    
            fila.inserir(personagem);
            fila.media();       

        }

        quantidadeComando = sc.nextInt();
        sc.nextLine();//limpar o buffer

        for(int i = 0; i < quantidadeComando; i++){
            expressao = sc.nextLine();
            fila.comandoLista(expressao);
        }

        fila.mostrar();

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

    class Fila {

        private Celula primeiro;
        private Celula ultimo;
        private int first;
        private int last;
    
        public Fila() {
            primeiro = new Celula();
            ultimo = primeiro;
        }
    
    
        public void inserir(Personagem x) throws Exception {

            Personagem removido = new Personagem();

            if((last + 1) % 6 == first){
                removido = remover();
            }
            ultimo.prox = new Celula(x);
            ultimo = ultimo.prox;
            last = (last + 1) % 6;
        }
    
    
        public Personagem remover() throws Exception {

            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover!");
            }
     
            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            Personagem elemento = primeiro.elemento;
            tmp.prox = null;
            tmp = null;
            first = (first + 1) % 6;
            return elemento;
        }
    
    
        /**
         * Mostra os elementos separados por espacos.
         */
        public void mostrar() {
            
            for(Celula i = primeiro.prox; i != null; i = i.prox) {
                i.elemento.imprimir();          
            }
            
        }

        public void comandoLista(String linhaInteira) throws Exception {

            Personagem personagem = new Personagem();
            Personagem removido = new Personagem();
            String []persona = linhaInteira.split(" ");
            String line = null;

            if(persona[0].equals("I")){
                line = personagem.ler(persona[1]);
                personagem.inicio(line);    
                inserir(personagem);
                media();
            } else {
                removido = remover();
                System.out.println("(R) "+removido.getNome());
            }
        }

        public void media()throws Exception {

            int i = first;
            double soma = 0;
            int cont = 0;
            double media;
            Celula tmp = primeiro.prox;

            while(i != last ){
                int idade = tmp.elemento.getAltura();
                soma = soma + idade;
                cont++;
                i = ((i + 1) % 6);
                tmp = tmp.prox;
            }
                media = soma/cont;

                System.out.println(Math.round(media));
            }

    }