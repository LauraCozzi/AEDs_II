import java.util.Scanner;

public class Questao10{

    public static boolean isFim(String s){

        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isPalindromo(String s, int esquerda, int direita){

        boolean palindromo = true;

        if(esquerda >= direita){
            palindromo = true;
        } else {
            if(s.charAt(esquerda) == s.charAt(direita))
                palindromo = true && isPalindromo(s, ++esquerda, --direita);
            else
                palindromo = false;
        }

        
        return palindromo;
    }

    public static void main(String [] args){

        String[] entrada = new String[1000];
        int ordemEntrada = 0;

        Scanner sc = new Scanner(System.in);

        do{
            entrada[ordemEntrada] = sc.nextLine();
        } while(isFim(entrada[ordemEntrada++]) == false);
        ordemEntrada--;

        for(int i = 0; i < ordemEntrada; i++){
            if(isPalindromo(entrada[i], 0, entrada[i].length() -1))
            System.out.println("SIM");
            else
            System.out.println("NAO");}

            sc.close();
    }

}