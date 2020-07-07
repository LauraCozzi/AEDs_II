import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Questao5 { // hash direto com área de reserva

    public static void main(String [] args) throws Exception {

        MyIO.setCharset("UTF-8");

        HashLaura hash = new HashLaura();
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


class HashLaura{

    Personagem [] tabelaHash;
    int tam_hash, tam_reserva, tam_total, quant_elem_reserva;

    public HashLaura(){
        this(21, 9);
     }
  
    public HashLaura (int tam_hash, int tam_reserva){
        this.tam_hash = tam_hash;
        this.tam_reserva =  tam_reserva;
        this.tam_total = tam_hash + tam_reserva;
        this.tabelaHash = new Personagem [this.tam_total];
        for(int i = 0; i < tam_total; i++){
           tabelaHash[i] = null;
        }
        quant_elem_reserva  = 0;
    }

    public int posicao(Personagem elemento){
        return elemento.getAltura() % 15;
    }

    public void inserir(Personagem elemento) {

        int posicao = posicao(elemento);

        if(tabelaHash[posicao] == null){// verifica se cabe direto na tabela hasg
            tabelaHash[posicao] = elemento;
        } else if(quant_elem_reserva < tam_reserva){ //verifica se cabe na área de reserva
            tabelaHash[tam_hash + quant_elem_reserva] = elemento;
            quant_elem_reserva++;
        }
    }

    public boolean pesquisar(String nome) {
        boolean existeTabela = false;

        for(int i = 0; i < this.tam_reserva + this.tam_hash; i++) {
            if(this.tabelaHash[i] != null && this.tabelaHash[i].getNome().compareTo(nome) == 0) {
                existeTabela = true;
                i = this.tam_reserva + this.tam_hash;
            }
        }

        return existeTabela;
    }


    public boolean remover(Personagem elemento){

        boolean possivel = false;

        int posicao = posicao(elemento);

        if(tabelaHash[posicao] == elemento){
            possivel = true;
            tabelaHash[posicao] = arrumarReserva(posicao);
        } else {
            for(int i = 0; i < tam_reserva; i++){ //não achou na hash principal, foi para reserva
                if(tabelaHash[tam_hash + i] == elemento){
                    possivel = true;
                    arrumarReserva2(tam_hash + i);
                    i = tam_reserva; //acabar com o for
                }
            }
        }        

        return possivel;
    }

    //foi removido um elemento da principal, tenho que verificar se possui algum da reserva que pode se encaixar la
    public Personagem arrumarReserva(int posicao){

        Personagem elemento = null;

        for(int i = 0; i < tam_reserva; i++){
            if(posicao(tabelaHash[tam_hash + i]) == posicao){
                elemento = tabelaHash[tam_hash + i];
                arrumarReserva2(tam_hash + i);
                i = tam_reserva;
            }
        }

        return elemento;
    }

    //foi retirado um elemento da reserva, temos que reorganizar para não ficar buraco
    public void arrumarReserva2(int posicao){

        int aux;

        for(int i = posicao + 1; i < tam_hash + tam_reserva; i++){
            tabelaHash[i -1] = tabelaHash[i];
        }
        quant_elem_reserva--;

    }

    public void mostrar(){

        for(int i = 0; i < tabelaHash.length; i++){
            System.out.print(tabelaHash[i]+" ");
        }

        System.out.println();
    }


}