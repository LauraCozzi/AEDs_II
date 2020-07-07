import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Q2{

    public static void main(String [] args) throws Exception {

        ArvoreArvore arvoreArvore = new ArvoreArvore();
        Scanner sc = new Scanner(System.in);
        boolean pertence = false;

        for(String nomeArquivo = sc.nextLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = sc.nextLine()){
            Personagem personagem = new Personagem();
            String line = personagem.ler(nomeArquivo);
            personagem.inicio(line);           
            arvoreArvore.inserir(personagem);
        }

        for(String nome = sc.nextLine(); nome.equals("FIM") == false; nome = sc.nextLine()){
            System.out.print(nome +" ");
            pertence = arvoreArvore.pesquisar(nome);
            if(pertence){
                System.out.println(" SIM");
            } else {
                System.out.println(" NÃO");
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

class No{

    public int elemento; // Conteudo do no.
	public No esq; 
	public No dir; 
    public No2 outro; //Aponta para um No2

    No(int elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
        this.outro = null;
	}

	No(int elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
        this.outro = null;
	}
}

class No2{

    public Personagem elemento; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.
	
	No2(Personagem elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
	}

	No2(Personagem elemento, No2 esq, No2 dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}


}

class ArvoreArvore{

    private No raiz; // Raiz da arvore.

	public ArvoreArvore() throws Exception {
		raiz = null;
        inserir(7);
        inserir(3);
        inserir(11);
        inserir(1);
        inserir(5);
        inserir(9);
        inserir(12);
        inserir(0);
        inserir(2);
        inserir(4);
        inserir(6);
        inserir(8);
        inserir(10);
        inserir(13);
        inserir(14);
	}

    public void inserir(int x) throws Exception{
		raiz = inserir(x, raiz);
	}

	private No inserir(int x, No i) throws Exception{
		if(i == null){
			i = new No(x);
		}else if(x < i.elemento){
			i.esq = inserir(x, i.esq);
		}else if(x > i.elemento){
			i.dir = inserir(x, i.dir);
		}else{
			throw new Exception("Erro ao inserir!");
		}

		return i;
    }

    public void inserir(Personagem x) throws Exception {
        raiz = inserir(raiz, x);
    }

    private No inserir(No i, Personagem x) throws Exception {

        if(i == null){
            throw new Exception("Erro!");
        } else if(x.getAltura() % 15 == i.elemento){
            i.outro = inserir2(i.outro, x);
        } else if(x.getAltura() % 15 < i.elemento){
            i.esq = inserir(i.esq, x);
        } else {
            i.dir = inserir(i.dir, x);
        }
        return i;
    }

    private No2 inserir2(No2 i, Personagem x) throws Exception{

        if(i == null){
            i = new No2(x);
        } else if(x.getNome().compareTo(i.elemento.getNome()) < 0){
            i.esq = inserir2(i.esq, x);
        } else if(x.getNome().compareTo(i.elemento.getNome()) > 0){
            i.dir = inserir2(i.dir, x);
        } else {
            throw new Exception("Erro!");
        }

        return i;
    }

    public boolean pesquisar(String x){
		System.out.print("raiz ");
		return pesquisar(x, raiz);
	}

	private boolean pesquisar(String x, No i){
		boolean resp;
		if(i != null){
			resp = pesquisar2(x, i.outro);
			if(resp == false){
				System.out.print("esq ");
				resp = pesquisar(x, i.esq);
			}
			if(resp == false){
				System.out.print("dir ");
				resp = pesquisar(x, i.dir);
			}
		}else{
			resp = false;
		}

		return resp;
	}

	private boolean pesquisar2(String x, No2 no){
		boolean resp;
		if(no != null){
			if(x.compareTo(no.elemento.getNome()) == 0){
				resp = true;
			}else{
				System.out.print("ESQ ");
				resp = pesquisar2(x, no.esq);
			}
			if(resp == false){
				System.out.print("DIR ");
				resp = pesquisar2(x, no.dir);
			}
		}else{
			resp = false;
		}

		return resp;
	}


    
}