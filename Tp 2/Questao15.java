
//importando bibliotecas
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Questao15 {

    //Inicializando um Scanner global
    public static Scanner sc = new Scanner(System.in);

    /**
     * Classe Personagens
     * @author Laura Cozzi Ribeiro
     * @version 1.0 2020
     */
    public static class Personagem {

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

    /**
     * Lista estatica
     * @author Max do Val Machado
     * @version 2 01/2015
     */
        public static class Lista{

            private Personagem[] array;
            private int n;

            /*
            * Construtor da classe.
            */
             Lista () {	
                this(100);
            }
    
            /*
            * Construtor da classe.
            * @param tamanho Tamanho da lista.
            */
             Lista (int tamanho){
                array = new Personagem[tamanho];
                n = 0;
            }
    

    
             void inserirFim(Personagem x) throws Exception {
    
                //validar insercao
                if(n >= array.length){
                    throw new Exception("Erro ao inserir!");
                }
    
                array[n] = x.clone();
                n++;
            }
    
             Personagem removerFim() throws Exception {
    
                //validar remocao
                if (n == 0) {
                    throw new Exception("Erro ao remover!");
                }
    
                return array[--n].clone();
            }
    
             void mostrar (){
                for(int i = 0; i < n; i++){
                    array[i].imprimir();
                }
            }
    
             boolean pesquisar(String x) {
                boolean retorno = false;
                for (int i = 0; i < n && retorno == false; i++) {
                    retorno = (array[i].nome.equals(x));
                }
                return retorno;
            }
             void swap(int i, int j) {
                Personagem temp = array[i].clone();
                array[i] = array[j].clone();
                array[j] = temp.clone();
            }

            public Personagem getMaior() {

                Personagem maior = array[0].clone();
                for (int i = 0; i < n; i++) {
                    if(maior.getPeso() < array[i].getPeso()){
                        maior = array[i].clone();
                    }
                }
                return maior;    
            }
 
 
     /**
      * Algoritmo de ordenacao Countingsort.
      */
      void countingsort() {
       //Array para contar o numero de ocorrencias de cada elemento
       int[] count = new int[(int) (getMaior().getPeso() + 1)];
       Personagem[] ordenado = new Personagem[n];
 
       //Inicializar cada posicao do array de contagem 
         for (int i = 0; i < count.length; count[i] = 0, i++);
 
       //Agora, o count[i] contem o numero de elemento iguais a i
       for (int i = 0; i < n; count[(int) array[i].getPeso()]++, i++);
 
       //Agora, o count[i] contem o numero de elemento menores ou iguais a i
       for(int i = 1; i < count.length; count[i] += count[i-1], i++);
 
       //Ordenando
       for(int i = n-1; i >= 0; ordenado[count[(int) array[i].getPeso()] - 1] = array[i].clone(), count[(int) array[i].getPeso()]--, i--);
 
       //Copiando para o array original
       for(int i = 0; i < n; array[i] = ordenado[i].clone(), i++);
    }
                    
                
            
    }
       

        
        

    public static void main(String [] args) throws Exception{

        //MyIO.setCharset("UTF-8");

        Lista lista = new Lista();

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            Personagem personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line);           
            lista.inserirFim(personagem);
        }

        lista.countingsort();
        lista.mostrar();





    }
}
