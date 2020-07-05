public class Questao3{

    public static boolean isFim(String s){

        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) =='M');
    }

    public static String ciframentoCesar(String entrada){

        int letra;
        char letraNova;
        String entradaNova = "";
        
        for(int i = 0; i < entrada.length(); i++){
            letra = entrada.charAt(i) + 3;
            letraNova = (char)letra;
            entradaNova += letraNova;
        }

        return entradaNova;

    }

    public static void main(String[] args){

	//MyIO.setCharset("UTF-8");

        String[] entrada = new String[1000];
        int ordemEntrada = 0;

        do{
            entrada[ordemEntrada] = MyIO.readLine();
        } while(isFim(entrada[ordemEntrada++]) == false);
        ordemEntrada--;

        for(int i = 0; i < ordemEntrada; i++){
            MyIO.println(ciframentoCesar(entrada[i]));
        }

    }


}
