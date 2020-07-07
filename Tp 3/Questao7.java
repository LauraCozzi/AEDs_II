//importando bibliotecas
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Questao7 {

    //Inicializando um Scanner global
    public static Scanner sc = new Scanner(System.in);

    public static void main(String [] args) throws Exception{

        ListaDupla listadupla = new ListaDupla();

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            Personagem personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line);           
            listadupla.inserirFim(personagem);
        }

        listadupla.quicksort();
        listadupla.ordenarCordoCabelo();

        listadupla.mostrar();

    }
}

    /**
     * Classe Personagens
     * @author Laura Cozzi Ribeiro
     * @version 1.0 2020
     */
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

        //
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

    class CelulaDupla {

        public Personagem elemento;
        public CelulaDupla ant;
        public CelulaDupla prox;
    
        /**
         * Construtor da classe.
         */
        public CelulaDupla() {

        }
    
    
        /**
         * Construtor da classe.
         * @param elemento int inserido na celula.
         */
        public CelulaDupla(Personagem elemento) {
            this.elemento = elemento;
            this.ant = this.prox = null;
        }
    }

    class ListaDupla {

        private CelulaDupla primeiro;
        private CelulaDupla ultimo;
    

        public ListaDupla() {
            primeiro = new CelulaDupla();
            ultimo = primeiro;
        }
    

        public void inserirInicio(Personagem x) {
            
            CelulaDupla tmp = new CelulaDupla(x);
    
            tmp.ant = primeiro;
            tmp.prox = primeiro.prox;
            primeiro.prox = tmp;
            if(primeiro == ultimo){
                ultimo = tmp;
            }else{
                tmp.prox.ant = tmp;
            }
            tmp = null;
        }
    

        public void inserirFim(Personagem x) {
            ultimo.prox = new CelulaDupla(x);
            ultimo.prox.ant = ultimo;
            ultimo = ultimo.prox;
        }
    
        public Personagem removerInicio() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover (vazia)!");
            }
    
            CelulaDupla tmp = primeiro;
            primeiro = primeiro.prox;
            Personagem resp = primeiro.elemento;
            tmp.prox = primeiro.ant = null;
            tmp = null;
            return resp;
        }
    
        public Personagem removerFim() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover (vazia)!");
            } 
            Personagem resp = ultimo.elemento;
            ultimo = ultimo.ant;
            ultimo.prox.ant = null;
            ultimo.prox = null;
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
                CelulaDupla i = primeiro;
                for(int j = 0; j < pos; j++, i = i.prox);
    
                CelulaDupla tmp = new CelulaDupla(x);
                tmp.ant = i;
                tmp.prox = i.prox;
                tmp.ant.prox = tmp.prox.ant = tmp;
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
                // Caminhar ate a posicao anterior a insercao
                CelulaDupla i = primeiro.prox;
                for(int j = 0; j < pos; j++, i = i.prox);
    
                i.ant.prox = i.prox;
                i.prox.ant = i.ant;
                resp = i.elemento;
                i.prox = i.ant = null;
                i = null;
            }
    
            return resp;
        }
    
        public void mostrar() {
            for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
                i.elemento.imprimir();
            }
        }
    
        public void mostrarInverso() {
            System.out.print("[ ");
            for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
                System.out.print(i.elemento + " ");
            }
            System.out.println("] "); // Termina de mostrar.
        }
    
        public int tamanho() {
            int tamanho = 0; 
            for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
            return tamanho;
        }

        void quicksort() {
            quicksort(1, tamanho()-1);
        }
        
        void quicksort(int esq, int dir) {

            int i = esq, j = dir;
            CelulaDupla esque = primeiro;
            CelulaDupla direi = primeiro;
            CelulaDupla pivo = primeiro;

            for(int pos = 0; pos < ((i+j)/2); pos++, pivo = pivo.prox);
            for(int pos = 0; pos < esq; pos++, esque = esque.prox);
            for(int pos = 0; pos < dir; pos++, direi = direi.prox);

            while(i <= j){
                while(esque.elemento.getCorDoCabelo().compareTo(pivo.elemento.getCorDoCabelo()) < 0){
                    esque = esque.prox;
                    i++;
                }

                while(direi.elemento.getCorDoCabelo().compareTo(pivo.elemento.getCorDoCabelo()) > 0){
                    direi = direi.ant;
                    j--;
                }

                if (i <= j) {
                    swap(esque, direi);
                    i++;
                    j--;
                    esque = esque.prox;
                    direi = direi.ant;
                }

                if (esq < j)  
                    quicksort(esq, j);
                if (i < dir)  
                    quicksort(i, dir);
            }
        }

        void swap(CelulaDupla i, CelulaDupla j) {
            Personagem tmp = i.elemento;
            i.elemento = j.elemento;
            j.elemento = tmp;

        }

        void ordenarCordoCabelo(){

            CelulaDupla temporaria = primeiro.prox;
            CelulaDupla inicio = primeiro.prox;
            CelulaDupla pMenor = inicio;
            Personagem menor;
            Personagem aux;
    
            while(inicio.prox != null){
                menor = inicio.elemento;
                temporaria = inicio.prox;
                pMenor = inicio;
                while(temporaria.prox != null){
                    if(temporaria.elemento.getCorDoCabelo().equals(menor.getCorDoCabelo()) && temporaria.elemento.getNome().compareTo(menor.getNome()) < 0){ 
                        pMenor = temporaria;
                        menor = temporaria.elemento;
                    }
                    temporaria = temporaria.prox;
                }
                aux = pMenor.elemento;
                pMenor.elemento = inicio.elemento;
                inicio.elemento = aux;
    
                inicio = inicio.prox;

        }
        }
    }
       
