import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Questao4 {

    public static void main(String [] args) throws Exception {

        MyIO.setCharset("UTF-8");

        Alvinegra alvinegra = new Alvinegra();
        Scanner sc = new Scanner(System.in);
        boolean pertence = false;

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            Personagem personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line);           
            alvinegra.inserir(personagem);
        }

        for(String nome = sc.nextLine(); nome.equals("FIM") == false; nome = sc.nextLine()){
            pertence = alvinegra.pesquisar(nome);
        }


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

class No{
    public boolean cor;
    public Personagem elemento;
    public No esq, dir;

    public No (Personagem elemento){
        this(elemento, false, null, null);
    }
    public No (Personagem elemento, boolean cor){
        this(elemento, cor, null, null);
    }
    public No (Personagem elemento, boolean cor, No esq, No dir){
      this.cor = cor;
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
    }
  }
  
  class Alvinegra {
      private No raiz; // Raiz da arvore.
  
      public Alvinegra() {
          raiz = null;
      }
  
      public boolean pesquisar(String x){
        System.out.print(x + " raiz ");
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, No i){

        boolean pertence = false;

        if(i == null){
            MyIO.println("N" +(char)195+ "O");
            pertence = false;
        }else if(i.elemento.getNome().equals(x)){
            System.out.println("SIM");
            pertence = true;
        }else if(x.compareTo(i.elemento.getNome()) > 0){
            System.out.print("dir ");
            pertence = pesquisar(x, i.dir);
        }else{
            System.out.print("esq ");
            pertence = pesquisar(x, i.esq);
        }

        return pertence;
    }
  
      public void mostrarCentral() {
          System.out.print("[ ");
          mostrarCentral(raiz);
          System.out.println("]");
      }
  
      private void mostrarCentral(No i) {
          if (i != null) {
              mostrarCentral(i.esq); // Elementos da esquerda.
              System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
              mostrarCentral(i.dir); // Elementos da direita.
          }
      }
  
      public void mostrarPre() {
          System.out.print("[ ");
          mostrarPre(raiz);
          System.out.println("]");
      }
  
      private void mostrarPre(No i) {
          if (i != null) {
              System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
              mostrarPre(i.esq); // Elementos da esquerda.
              mostrarPre(i.dir); // Elementos da direita.
          }
      }
  
      public void mostrarPos() {
          System.out.print("[ ");
          mostrarPos(raiz);
          System.out.println("]");
      }
  
      private void mostrarPos(No i) {
          if (i != null) {
              mostrarPos(i.esq); // Elementos da esquerda.
              mostrarPos(i.dir); // Elementos da direita.
              System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
          }
      }
  
      public void inserir(Personagem elemento) throws Exception {
     
        //Se a arvore estiver vazia
        if(raiz == null){
           raiz = new No(elemento, false);
           //System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento + ").");
  
        //Senao, se a arvore tiver um elemento 
        } else if (raiz.esq == null && raiz.dir == null){
           if (raiz.elemento.getNome().compareTo(elemento.getNome()) > 0){
              raiz.esq = new No(elemento, true);
              //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e esq(" + raiz.esq.elemento +").");
           } else {
              raiz.dir = new No(elemento, true);
              //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e dir(" + raiz.dir.elemento +").");
           }
  
        //Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null){
  
           if(raiz.elemento.getNome().compareTo(elemento.getNome()) > 0){
              raiz.esq = new No(elemento);
              //System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
  
           } else if (raiz.dir.elemento.getNome().compareTo(elemento.getNome()) > 0){
              raiz.esq = new No(raiz.elemento);
              raiz.elemento = elemento;
              //System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
  
           } else {
              raiz.esq = new No(raiz.elemento);
              raiz.elemento = raiz.dir.elemento;
              raiz.dir.elemento = elemento;
              //System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
           }
  
           raiz.esq.cor = raiz.dir.cor = false;
           
        //Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null){
           
           if(raiz.elemento.getNome().compareTo(elemento.getNome()) < 0){
              raiz.dir = new No(elemento);
              //System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
           } else if (raiz.esq.elemento.getNome().compareTo(elemento.getNome()) < 0){
              raiz.dir = new No(raiz.elemento);
              raiz.elemento = elemento;
              //System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
           } else {
              raiz.dir = new No(raiz.elemento);
              raiz.elemento = raiz.esq.elemento;
              raiz.esq.elemento = elemento;
              //System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
           }
  
           raiz.esq.cor = raiz.dir.cor = false;
  
        //Senao, a arvore tem tres ou mais elementos
        } else {
           //System.out.println("Arvore com tres ou mais elementos...");
             inserir(elemento, null, null, null, raiz);
        }
  
        raiz.cor = false;
     }
  
     private void balancear(No bisavo, No avo, No pai, No i){
  
        //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if(pai.cor == true){
  
           //4 tipos de reequilibrios e acoplamento
           if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0){ // rotacao a esquerda ou direita-esquerda
              if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0){
                 avo = rotacaoEsq(avo);
              } else {
                 avo = rotacaoDirEsq(avo);
              }
  
           } else { // rotacao a direita ou esquerda-direita
              if(i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
                 avo = rotacaoDir(avo);
              } else {
                 avo = rotacaoEsqDir(avo);
              }
           }
  
           if (bisavo == null){
              raiz = avo;
           } else {
              if(avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0){
                 bisavo.esq = avo;
              } else {
                 bisavo.dir = avo;
              }
           }
  
           //reestabelecer as cores apos a rotacao
           avo.cor = false;
           avo.esq.cor = avo.dir.cor = true;
           //System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e avo.esq / avo.dir(" + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
        } //if(pai.cor == true)
     }

      private void inserir(Personagem elemento, No bisavo, No avo, No pai, No i) throws Exception {
          if (i == null) {
           if(elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
              i = pai.esq = new No(elemento, true);
           } else {
              i = pai.dir = new No(elemento, true);
           }
           if(pai.cor == true){
              balancear(bisavo, avo, pai, i);
           }
        } else {
          //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
           if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
              i.cor = true;
              i.esq.cor = i.dir.cor = false;
              if(i == raiz){
                 i.cor = false;
              }else if(pai.cor == true){
                 balancear(bisavo, avo, pai, i);
              }
           }
           if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
              inserir(elemento, avo, pai, i, i.esq);
           } else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
              inserir(elemento, avo, pai, i, i.dir);
           } else {
              throw new Exception("Erro inserir (elemento repetido)!");
           }
        }
      }
  
     private No rotacaoDir(No no) {
        //System.out.println("Rotacao DIR(" + no.elemento + ")");
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;
  
        noEsq.dir = no;
        no.esq = noEsqDir;
  
        return noEsq;
     }
  
     private No rotacaoEsq(No no) {
        //System.out.println("Rotacao ESQ(" + no.elemento + ")");
        No noDir = no.dir;
        No noDirEsq = noDir.esq;
  
        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
     }
  
     private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
     }
  
     private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
     }
  }