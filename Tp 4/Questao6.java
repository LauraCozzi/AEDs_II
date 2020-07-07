import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Questao6 { // hash direto com área de reserva

    public static void main(String [] args) throws Exception {

        MyIO.setCharset("UTF-8");

        Hash_rehash hash = new Hash_rehash();
        Scanner sc = new Scanner(System.in);
        boolean pertence = false;

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            Personagem personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line);           
            hash.inserir(personagem);
        }

        for(String nome = sc.nextLine(); nome.equals("FIM") == false; nome = sc.nextLine()){
            pertence = hash.pesquisar(nome);
            System.out.print(nome);
            if(pertence){
                System.out.println(" SIM");
            } else {
                MyIO.println(" N" +(char)195+ "O");
            }
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


class Hash_rehash{

    Personagem tabela[];
    int tamanho;

    public Hash_rehash(){
        this(13);
    }

    public Hash_rehash(int tamanho){
        this.tamanho = tamanho;
        this.tabela = new Personagem[this.tamanho];

        for(int i = 0; i < tabela.length; i++){
            tabela[i] = null; //posso inserir o 0 
        }
    }

    public int hash(Personagem elemento){
        return elemento.getAltura() % tamanho;
    }

    public int rehash(Personagem elemento){
        return (elemento.getAltura() + 1) % tamanho;
    }

    public boolean inserir(Personagem elemento){

        boolean possivel = false;
        int posicao = hash(elemento);

        if(tabela[posicao] == null){
            tabela[posicao] = elemento;
            possivel = true;
        } else {
            posicao = rehash(elemento);
            if(tabela[posicao] == null){
                tabela[posicao] = elemento;
                possivel = true;
            }
        }

        return possivel;
    }

    public boolean pesquisar (String elemento){

        boolean resp = false;
  
               for(int i = 0; i < tamanho; i++){
                  if ( tabela[i] != null ) {
                      if(tabela[i].getNome().compareTo(elemento) == 0){
                         resp = true;
                         i = tamanho;
                      }
                  }
               }
  
        return resp;
      }

    public Personagem remover(Personagem elemento){

        Personagem removido = null;
        int posicao = hash(elemento);

        if(tabela[posicao] == elemento){
            removido = tabela[posicao];
            tabela[posicao] = null;
            arrumartabela(posicao);
        } else {
            posicao = rehash(elemento);
            tabela[posicao] = null;
            arrumartabela(posicao);
        }

        return removido;

    }

    public void arrumartabela(int posicao){

        int posicaoProx = posicao + 1;

        for(int i = posicao; i < tabela.length; i++){
            if(hash(tabela[posicaoProx]) == i){
                tabela[i] = tabela[posicaoProx];
                tabela[posicaoProx] = null;
                posicaoProx++;
            }
        }
    }

    public void mostrar(){

        for(int i = 0; i < tabela.length; i++){
            System.out.println(tabela[i]);
        }
        System.out.println();
    }


}