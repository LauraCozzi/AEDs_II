import java.util.*;

public class Questao5{

    public static void main(String [] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int casos = sc.nextInt();
        int linha, coluna;

        for(int i = 0; i < casos;i++){
            linha = sc.nextInt();
            coluna = sc.nextInt();
            Matriz matriz1 = new Matriz(linha, coluna);
            matriz1.inserir(linha, coluna, sc);
            linha = sc.nextInt();
            coluna = sc.nextInt();
            Matriz matriz2 = new Matriz(linha, coluna);
            matriz2.inserir(linha, coluna, sc);
            matriz1.mostrarDiagonalPrincipal();
            matriz1.mostrarDiagonalSecundaria();
            matriz1.soma(matriz2);
            Matriz multi = new Matriz(linha, coluna);
            multi = matriz1.multiplicacao(matriz2);
            multi.mostrarCerto();
        }

    }
}

class CelulaMatriz{

    public int elemento;
    public CelulaMatriz inferior, superior, esquerda, direita;

    public CelulaMatriz(){
        this(0, null, null, null, null);
     }
  
     public CelulaMatriz(int elemento){
        this(elemento, null, null, null, null);
     }
  
     public CelulaMatriz(int elemento, CelulaMatriz inf, CelulaMatriz sup, CelulaMatriz esq, CelulaMatriz dir){
        this.elemento = elemento;
        this.inferior = inf;
        this.superior = sup;
        this.esquerda = esq;
        this.direita = dir;
     }

}class Matriz{

    private CelulaMatriz inicio;
    private int linha; 
    private int coluna;

    Matriz(){
        this(3,3);
    }

    Matriz(int linha, int coluna){

        this.linha = linha;
        this.coluna = coluna;
        this.inicio = new CelulaMatriz(0);
        CelulaMatriz ponteiro = inicio;
        CelulaMatriz ponteiroAux = inicio;
        CelulaMatriz ponteiroAuxColuna = inicio;

        int j = 1;

        //Primeiro criação das colunas
        for(int i = 1; i < coluna; i++){ // i inicia-se com o valor 1, uma vez que já temos nossa primeira celula
            ponteiro.direita = new CelulaMatriz(0);
            ponteiro.direita.esquerda = ponteiro;
            ponteiro = ponteiro.direita;
        }

        //Criação das linhas
        ponteiro = inicio;
        for(int i = 1; i < linha; i++){
            ponteiro.inferior = new CelulaMatriz(0);
            ponteiro.inferior.superior = ponteiro;
            ponteiro = ponteiro.inferior;
        }

        //Preencher a matriz
        CelulaMatriz ponteiroColuna = inicio.direita;
        CelulaMatriz ponteiroLinha = inicio.inferior;
        ponteiroAux = ponteiroLinha;
        ponteiroAuxColuna = ponteiroColuna;
        for(int i = 1; i < linha; i++){
            for(int k = 1; k < coluna; k++){
                ponteiroLinha.direita = new CelulaMatriz(0);
                ponteiroColuna.inferior = ponteiroLinha.direita;
                ponteiroLinha.direita.esquerda = ponteiroLinha;
                ponteiroLinha.direita.superior = ponteiroColuna;
                ponteiroLinha = ponteiroLinha.direita;
                if(ponteiroColuna.direita != null)
                ponteiroColuna = ponteiroColuna.direita;
            }
            ponteiroColuna = ponteiroAuxColuna;
            ponteiroLinha = ponteiroAux;
            if(i + 1 < linha){
                ponteiroLinha = ponteiroLinha.inferior;
                ponteiroColuna = ponteiroAuxColuna.inferior;
                ponteiroAux = ponteiroAux.inferior;
                ponteiroAuxColuna = ponteiroAuxColuna.inferior;
            }
        }
        
    }//Fim construtor

    void mostrarCerto(){

        CelulaMatriz ponteiro = inicio;
        CelulaMatriz ponteiroAux = inicio;

        while(ponteiro != null){
            ponteiroAux = ponteiro;
            for(int i = 0; i < coluna; i++){
                System.out.print(ponteiro.elemento +" ");
                ponteiro = ponteiro.direita;
            }
            System.out.println("");
            ponteiro = ponteiroAux;
            ponteiro = ponteiro.inferior;
        }

    }

    void inserirElemento(int linha, int coluna, int elemento){

        CelulaMatriz ponteiro = inicio;

        for(int i = 1; i < linha; i++){
            ponteiro = ponteiro.inferior;
        }

        for(int i = 1; i < coluna; i++){
            ponteiro = ponteiro.direita;
        }

        ponteiro.elemento = elemento;
    }

    void inserir(int linha, int coluna, Scanner sc){

        String valores = "";
        int k = 1;

        sc.nextLine();

        for(int i = 1; i <= linha; i++){
            valores = sc.nextLine();
            for(int j = 1; j <= coluna; j++){
                String [] numeros = valores.split(" ");
                inserirElemento(i, j, Integer.parseInt(numeros[j-1]));
            }
        }
        //mostrarCerto();
    }

    public boolean isQuadrada(){
        return (this.linha == this.coluna);
     }

    void mostrarDiagonalPrincipal(){

        CelulaMatriz ponteiro = inicio;

        for(int i = 0; i < coluna; i++){
            System.out.print(ponteiro.elemento +" ");
            if(ponteiro.direita != null)
            ponteiro = ponteiro.direita.inferior;
        }
        System.out.println(" ");
    }

    void mostrarDiagonalSecundaria(){

        CelulaMatriz ponteiro = inicio;

        for(int i = 0; i < coluna; i++){
            if(ponteiro.direita != null)
            ponteiro = ponteiro.direita;
        }

        for(int i = 0; i < coluna; i++){
            System.out.print(ponteiro.elemento +" ");
            if(ponteiro.esquerda != null)
            ponteiro = ponteiro.esquerda.inferior;
        }

        System.out.println(" ");
    }

    void soma(Matriz matriz2){

        CelulaMatriz ponteiro1 = inicio;
        CelulaMatriz ponteiro2 = matriz2.inicio;
        CelulaMatriz ponteiro1Aux = ponteiro1;
        CelulaMatriz ponteiro2Aux = ponteiro2;
        Matriz soma = new Matriz();
        int valor = 0;

        if(this.linha == matriz2.linha && this.coluna == matriz2.coluna){
            soma = new Matriz(this.linha, this.coluna);
            for(int k = 1; k <= linha; k++){
                for(int i = 1; i <= coluna; i++){
                    valor = ponteiro1.elemento + ponteiro2.elemento;
                    soma.inserirElemento(k, i, valor);
                    if(ponteiro1.direita != null && ponteiro2.direita != null){
                        ponteiro1 = ponteiro1.direita;
                        ponteiro2 = ponteiro2.direita;
                    }
                }
                //completar segunda linha
                ponteiro1 = ponteiro1Aux;
                ponteiro2 = ponteiro2Aux;
                if(k + 1 <= linha){
                    ponteiro1 = ponteiro1.inferior;
                    ponteiro2 = ponteiro2.inferior;
                    ponteiro1Aux = ponteiro1;
                    ponteiro2Aux = ponteiro2;
                }
            }
        }
        soma.mostrarCerto();

    }//Fim soma

        
        void multi(Matriz matriz2){

            CelulaMatriz ponteiro1 = inicio;
            CelulaMatriz ponteiro2 = matriz2.inicio;
            CelulaMatriz ponteiro1Aux = ponteiro1;
            CelulaMatriz ponteiro2Aux = ponteiro2;
            Matriz multiplicacao = new Matriz();
            int valor1 = 0;
            int valor2 = 0;
            int col = 1;

            if(this.linha == matriz2.linha && this.coluna == matriz2.coluna){
                multiplicacao = new Matriz(this.linha, this.coluna);
                for(int k = 1; k <= linha; k++){
                    valor1 = 0;
                    for(int i = 1; i <= coluna; i++){
                        valor1 += ponteiro1.elemento * ponteiro2.elemento;
                        if(ponteiro1.direita != null && ponteiro2.inferior != null){
                            ponteiro1 = ponteiro1.direita;
                            ponteiro2 = ponteiro2.inferior;
                        }
                        multiplicacao.inserirElemento(k, col, valor1);
                    }//fim fo for coluna
                    col++;
                    ponteiro2 = ponteiro2Aux;
                    if(ponteiro2.direita != null){
                        ponteiro2 = ponteiro2.direita;
                    }
                }//fim do for linha

            }//fim do if

            multiplicacao.mostrarCerto();

        }

        public Matriz multiplicacao(Matriz matriz2) throws Exception{

            Matriz multiplicacao = new Matriz(this.linha, this.coluna);

            if(this.linha == matriz2.linha && this.coluna == matriz2.coluna){
            for(CelulaMatriz i = inicio, x = multiplicacao.inicio; x != null ; i = i.inferior, x = x.inferior){
                for (CelulaMatriz lin = matriz2.inicio, y = x ; y != null ; lin = lin.direita, y = y.direita) {
                    CelulaMatriz jin = lin;
                    CelulaMatriz hpri = i;

                    while(jin != null) {
                        y.elemento += jin.elemento * hpri.elemento;
                        jin = jin.inferior;
                        hpri = hpri.direita;
                    }
                }
            }
        }
            return multiplicacao;
        }


}