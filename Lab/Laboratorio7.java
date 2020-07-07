import java.util.Scanner;

public class lab7 {
    public static void main(String [] args){

        Scanner sc = new Scanner(System.in);

        int quantidade = Integer.parseInt(sc.nextLine());
        String entrada;
        String [] casos;

        for(int i = 0; i < quantidade; i++){
            entrada = sc.nextLine();
            casos = entrada.split(" ");

            foundPos(casos[2], casos[1], Integer.parseInt(casos[0]));
            System.out.println("");

        }

    }

    public static int procurar(String infixo, char raiz, int n) { 

        int pos = 0;

		for(int i = 0; i < n; i++){
		    if (infixo.charAt(i) == raiz) 
                pos = i; 
        }
		return pos; 
	} 

    public static void foundPos(String infixo, String prefixo, int tam){

        int posRaiz = procurar(infixo, prefixo.charAt(0), tam);

        if(posRaiz != 0){
            foundPos(infixo, prefixo.substring(1, tam), posRaiz); 
        }

        if(posRaiz != tam - 1){
            foundPos(infixo.substring(posRaiz+1, tam), prefixo.substring(posRaiz+1, tam), tam - posRaiz - 1);
        }

        System.out.print(prefixo.charAt(0));
    }
    
}