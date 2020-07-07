import java.util.Scanner;

public class lab2 {

    
    public static boolean isFim(String s){

        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String palavraNova (String entrada){

        String palavras[] = entrada.split(" ");



        char[] palavra1 = new char[palavras[0].length()];
        palavra1 = palavras[0].toCharArray();


        char[] palavra2 = new char[palavras[1].length()];
        palavra2 = palavras[1].toCharArray();


        char[] palavraNova = new char[palavra1.length + palavra2.length];

        int posicao1 = 0, posicao2 = 1;
        int j1 = 0, k2 = 0;
        int menor;

        if(palavra1.length < palavra2.length)
            menor = palavra1.length;
            else 
            menor = palavra2.length;
        

        do{
            palavraNova[posicao1] = palavra1[j1];
            palavraNova[posicao2] = palavra2[k2];
            k2++;
            posicao2 += 2;
            j1++;
            posicao1 += 2;
        }while(j1 < menor || k2 < menor);



        if(j1 + k2 < palavraNova.length){
            do{
                if(j1 == palavra1.length){
                    palavraNova[j1 + k2] = palavra2[k2];
                    k2++;
                }else{
                    palavraNova[j1 + k2] = palavra1[j1];
                    j1++;
                }
             } while(k2 < palavra2.length || j1 < palavra1.length);
        }

        String novaPalavra = "";
        for(int i = 0; i < palavraNova.length; i++){
            novaPalavra += palavraNova[i];
        }


            return novaPalavra;
 


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
            System.out.println(palavraNova(entrada[i]));
        
        }

            sc.close();

    }
}