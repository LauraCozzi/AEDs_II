public class Questao6 {

    public static boolean isFim(String s){

        return(s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean onlyVowel(String s){

        int tamanho = s.length();
        boolean vogal = true;
        int i = 0;

        while(vogal == true && i < tamanho){
            if(s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U' || s.charAt(i) == 'a' 
            || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u' || s.charAt(i) == ' ' || s.charAt(i) == ',')
                vogal = true;
            else
                vogal = false;
            i++;
        }
        
        return vogal;
    }

    public static boolean onlyConsoant(String s){

        int tamanho = s.length();
        boolean consoante = true;
        int i = 0;

        while(consoante == true && i < tamanho){
            if(s.charAt(i) >= 'B' && s.charAt(i) <= 'D' || s.charAt(i) >= 'F' && s.charAt(i) <= 'H' || 
                s.charAt(i) >= 'J' && s.charAt(i) <= 'N' || s.charAt(i) >= 'P' && s.charAt(i) <= 'T' || 
                s.charAt(i) >= 'V' && s.charAt(i) <= 'Z' || s.charAt(i) == ' ' || s.charAt(i) == ',')
                consoante = true;
            else
                consoante = false;
            i++;
        }
        
        return consoante;
    }

    public static boolean integer(String s){

        int tamanho = s.length();
        boolean inteiro = true;
        int i = 0;

        while(inteiro == true && i < tamanho){
            if((int)s.charAt(i) >= 48 && (int)s.charAt(i) <= 57 || s.charAt(i) == ' ')
                inteiro = true;
            else
                inteiro = false;
            i++;

        }

        return inteiro;

    }

    public static boolean flutuante(String s){

        int tamanho = s.length();
        boolean real = true;
        int i = 0;
        int quantidadeVirg = 0;

        while(real == true && i < tamanho){
            if((int)s.charAt(i) >= 48 && (int)s.charAt(i) <= 57 || s.charAt(i) == ' ' || s.charAt(i) == ',' || s.charAt(i) == '.'){
                if(s.charAt(i) == ',' || s.charAt(i) == '.'){
                quantidadeVirg++;
                    if(quantidadeVirg <= 1)
                    real = true;
                    else
                    real = false;
                }
            }else{
                real = false;
            }
            i++;

        }
        return real;

    }

    public static void main(String[] args){

        String[] entrada = new String[1000];
        int ordemEntrada = 0;

        do{
            entrada[ordemEntrada] = MyIO.readLine();
        }while(isFim(entrada[ordemEntrada++]) == false);
        ordemEntrada--;

        for(int i = 0; i < ordemEntrada; i++){
            if(onlyVowel(entrada[i]))
            System.out.print("SIM ");
            else
            System.out.print("NAO ");
            if(onlyConsoant(entrada[i]))
            System.out.print("SIM ");
            else
            System.out.print("NAO ");
            if(integer(entrada[i]))
            System.out.print("SIM ");
            else
            System.out.print("NAO ");
            if(flutuante(entrada[i]))
            System.out.println("SIM");
            else
            System.out.println("NAO");
        }

    }

}