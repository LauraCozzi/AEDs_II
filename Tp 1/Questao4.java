import java.util.Random;

public class Questao4 {

    public static boolean isFim(String s){

        return(s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) =='M');
    }

    public static String alteracaoAleatoria(String entrada, Random gerador){

        char letra1  = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        char letra2  = (char)('a' + (Math.abs(gerador.nextInt()) % 26));

        int tamanhoEntrada = entrada.length();
        char[] entradaArray = new char[tamanhoEntrada];
        String palavraNova = "";

        for(int i = 0; i < tamanhoEntrada; i++){
            entradaArray[i] = entrada.charAt(i);
        }

        for(int i = 0; i < tamanhoEntrada; i++){
            if(entradaArray[i] == letra1)
                entradaArray[i] = letra2;        
        }

        for(int i = 0; i < tamanhoEntrada; i++){
            palavraNova += entradaArray[i];
        }

        return palavraNova;
    }



    public static void main(String[] args){

        //MyIO.setCharset("ISO-8859-1");

        Random gerador = new Random();
        gerador.setSeed(4);//4 é a semente para a geração de números aleatórios

        String[] entrada = new String[1000];
        int ordemEntrada = 0;

        do{
            entrada[ordemEntrada] = MyIO.readLine();//posso colocar o ++ aqui?
        }while(isFim(entrada[ordemEntrada++]) == false);
        ordemEntrada--;

        for(int i = 0; i < ordemEntrada; i++){
            MyIO.println(alteracaoAleatoria(entrada[i], gerador));
            
        }
        

    }


}
