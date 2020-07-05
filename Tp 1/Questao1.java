import java.util.Scanner;

public class Questao1{

    public static boolean isFim(String s){

        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isPalindromo(String s){

        int esquerda = 0;
        int direita = s.length() - 1;
        boolean palindromo = true;

        while(esquerda < direita && palindromo == true){
            if(s.charAt(esquerda) == s.charAt(direita))
            palindromo = true;
            else
            palindromo = false;
            esquerda++;
            direita--;
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

        for(int i = 0; i < ordemEntrada; i++)
            if(isPalindromo(entrada[i]))
            System.out.println("SIM");
            else
            System.out.println("NAO");

            sc.close();
    }

}