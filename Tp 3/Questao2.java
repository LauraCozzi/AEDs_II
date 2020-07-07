//importando bibliotecas
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Questao2{

    //Inicializando um Scanner global
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        int quantidadeComando = 0;
        String expressao = null;
        Pilha pilha = new Pilha();

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            Personagem personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line);           
            pilha.inserir(personagem);
        }

        quantidadeComando = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < quantidadeComando; i++){
            expressao = sc.nextLine();
            pilha.comandoLista(expressao);
        }

        //pilha.quantidadeElem();
        pilha.mostrar();

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

class Pilha {//pq não posso colocar static 

	private Celula topo;

	public Pilha() {
		topo = null;
	}

	public void inserir(Personagem x) {

		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}

	public Personagem remover() throws Exception {

		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}

		Personagem resp = topo.elemento;
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
	}


	public void mostrar() {

        int quant = quantidadeElem();

        mostrar(topo, quant);
    }

    public int quantidadeElem(){

        int quant = quantidadeElem(topo);

        return quant;
    }

    public int quantidadeElem(Celula i){

        int quant = 0;

        if(i != null){
            quant = 1 + quantidadeElem(i.prox);
        }


        return quant;
    }
    
    public void mostrar(Celula i, int quant) {

		if(i != null){
            mostrar(i.prox, --quant);
            System.out.print("["+quant+"]");
            i.elemento.imprimir();
		}
	}

    
    public void comandoLista(String linhaInteira) throws Exception {

        String path = null;
        Personagem personagem = new Personagem();
        String line = null;
        Personagem removido = new Personagem();

        if(linhaInteira.charAt(0) == 'I')
        path = linhaInteira.substring(2, linhaInteira.length());

        if(linhaInteira.charAt(0) == 'I'){
            line = personagem.ler(path);
            personagem.inicio(line); 
            inserir(personagem);
        } else if(linhaInteira.charAt(0) == 'R'){
            removido = remover();
            System.out.println("(R) "+removido.getNome());
        }

    }
}