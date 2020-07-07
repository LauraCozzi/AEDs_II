
public class Laboratorio3 {

    public static boolean isFim(String s){

        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) =='M');
    }

    public static void espelho(String entrada){

        String [] valores = entrada.split(" ");

        int inicio = Integer.parseInt(valores[0]);
        int fim = Integer.parseInt(valores[1]);

        int tamanho = (fim - inicio) + 1;

        int [] espelho = new int[tamanho];
        int j = 0;

        for(int i = inicio; i <= fim; i++){
            espelho[j] = i;
            j++;

        }
        String numeros = "";
        for(int i = 0; i < tamanho; i++){
            numeros += espelho[i];

        }

        System.out.print(numeros);

        for(int t = numeros.length() - 1; t >= 0; t--){
            System.out.print(numeros.charAt(t));
        }
        


        System.out.println("");


    }

    public static void main(String [] args){

        String[] entrada = new String[1000];
        int ordemEntrada = 0;

        do{
            entrada[ordemEntrada] = MyIO.readLine();
        } while(isFim(entrada[ordemEntrada++]) == false);
        ordemEntrada--;

        for(int i = 0; i < ordemEntrada; i++){
            espelho(entrada[i]);
        }
    }
}