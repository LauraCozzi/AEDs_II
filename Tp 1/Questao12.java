public class Questao12{

    public static boolean isFim(String s){

        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) =='M');
    }

    public static String ciframentoCesar(String entrada, int i){

        int letra;
        char letraNova;
        String entradaNova = "";

        if(i == entrada.length()){
           entradaNova = "";
        } else {
            letra = entrada.charAt(i) + 3;
            letraNova = (char)letra;
            entradaNova = letraNova + ciframentoCesar(entrada, ++i);
        }


        return entradaNova;

    }

    public static void main(String[] args){

        String[] entrada = new String[1000];
        int ordemEntrada = 0;

        do{
            entrada[ordemEntrada] = MyIO.readLine();
        } while(isFim(entrada[ordemEntrada++]) == false);
        ordemEntrada--;

        for(int i = 0; i < ordemEntrada; i++){
            MyIO.println(ciframentoCesar(entrada[i], 0));
        }

    }


}
