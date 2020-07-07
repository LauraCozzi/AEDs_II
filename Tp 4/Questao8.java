import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Questao8 {

    public static void main(String [] args) throws Exception {

        MyIO.setCharset("UTF-8");

        Scanner sc = new Scanner(System.in);

        ArvoreTrie trie1 = new ArvoreTrie();
		ArvoreTrie trie2 = new ArvoreTrie();
        ArvoreTrie trie3 = new ArvoreTrie();

        boolean pertence = false;

        Personagem personagem;
        
        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line); 
            trie1.inserir(personagem.getNome());
            //trie1.mostrar();
        }

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line); 
            trie2.inserir(personagem.getNome());
        }

        trie3.merge(trie1,trie2);	

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            pertence = trie3.pesquisar(nomeArquivo);

            System.out.print(nomeArquivo);

            if(pertence){
                System.out.println(" SIM");
            } else {
                MyIO.println(" N" +(char)195+ "O");
            }
        }

    }
}

class No {

    public char elemento;
    int tamanho = 255;
    No [] prox;
    boolean folha;

    public No(){
        this(' ');
    }

    public No(char letra){
        this.elemento = letra;
        prox = new No[tamanho];
        for(int i = 0; i < tamanho; i++){
            prox[i] = null;
        }
        folha = false;
    }

    public int hash (char x){
        return (int)x;
     }
    


}

class ArvoreTrie {

    private No raiz;

    public ArvoreTrie(){
        raiz = new No();
    }

    public void inserir(String s) throws Exception {

        inserir(s, raiz, 0);
    }

    private void inserir(String s, No no, int i) throws Exception {

        //se a letra na posição i da string s, não se encontra no array
        if(no.prox[s.charAt(i)] == null){ //tem que ser com esse prox pq a raiz no inicio não tem nada
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if(i == s.length() - 1){ //ultima letra da palavra
                no.prox[s.charAt(i)].folha = true;
            } else {
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }
        } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) {
            inserir(s, no.prox[s.charAt(i)], i + 1);
        } else {
            throw new Exception("A palavra inserida possui um prefixo já inserido!");
        }
    }
 

    public boolean pesquisar(String s) throws Exception {

        boolean resp = false; 
        resp = pesquisar(s, raiz, 0);

        return resp;
    }


    public boolean pesquisar(String s, No no, int i) throws Exception {

        boolean resp = false;

        if(no.prox[s.charAt(i)] == null){
            resp = false;
        } else if(i == s.length() - 1){
            resp = (no.prox[s.charAt(i)].folha == true);
        } else if(i < s.length() - 1 ){
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return resp;
    }


    public void mostrar(){
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if(no.folha == true){
            System.out.println("Palavra: " + (s + no.elemento));
        } else {
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }

    public void merge(No no) throws Exception{
        merge("",no);
     }
  
     private void merge(String s, No no) throws Exception {

        if(no.folha == true){
          String nome = s + no.elemento;
          //nome pronto para inserir
          inserir(nome.trim());
        } else {
           for(int j = 0; j < no.prox.length; j++){
              if(no.prox[j] != null){
                 merge(s + no.elemento, no.prox[j]);
              }
           }
        }
     }
  
      public void merge(ArvoreTrie a, ArvoreTrie b) throws Exception{
  
          merge(a.raiz);
          merge(b.raiz);
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
