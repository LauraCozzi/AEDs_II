public class Questao15 {

    public static boolean isFim(String s){

        return(s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean onlyVowel(String s, int i){

        boolean vogal = true;

        if(i == s.length()){
            vogal = true;
        }
        else {
            if(s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U' || s.charAt(i) == 'a' 
            || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u' || s.charAt(i) == ' ' || s.charAt(i) == ',')
                vogal = onlyVowel(s, ++i);
            else
                vogal = false;
        }

        return vogal;

    }

    public static boolean onlyConsoant(String s, int i){

        boolean consoante = true;

        if(i == s.length()){
            consoante = true;
        }
        else {
            if(s.charAt(i) >= 'B' && s.charAt(i) <= 'D' || s.charAt(i) >= 'F' && s.charAt(i) <= 'H' || 
            s.charAt(i) >= 'J' && s.charAt(i) <= 'N' || s.charAt(i) >= 'P' && s.charAt(i) <= 'T' || 
            s.charAt(i) >= 'V' && s.charAt(i) <= 'Z' || s.charAt(i) == ' ' || s.charAt(i) == ',')
                consoante = onlyConsoant(s, ++i);
            else
                consoante = false;
        }
        return consoante;
    }

    public static boolean integer(String s, int i){

        boolean inteiro = true;

        if(i == s.length()){
            inteiro = true;
        }
        else {
            if((int)s.charAt(i) >= 48 && (int)s.charAt(i) <= 57 || s.charAt(i) == ' ')
                inteiro = integer(s, ++i);
            else
                inteiro = false;
        }


        return inteiro;

    }

    public static boolean flutuante(String s, int i, int quantidadeVirg){

        boolean real = true;

        if(i == s.length()){
            real = true;
        }
        else {
            if((int)s.charAt(i) >= 48 && (int)s.charAt(i) <= 57 || s.charAt(i) == ' ' || s.charAt(i) == ',' || s.charAt(i) == '.'){
                if(s.charAt(i) == ',' || s.charAt(i) == '.')
                quantidadeVirg++;
                    if(quantidadeVirg <= 1)
                    real = flutuante(s, ++i, quantidadeVirg);
                    else
                    real = false;
            
            }
            else
            real = false;
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
            if(onlyVowel(entrada[i], 0))
            System.out.print("SIM ");
            else
            System.out.print("NAO ");
            if(onlyConsoant(entrada[i], 0))
            System.out.print("SIM ");
            else
            System.out.print("NAO ");
            if(integer(entrada[i], 0))
            System.out.print("SIM ");
            else
            System.out.print("NAO ");
            if(flutuante(entrada[i], 0, 0))
            System.out.println("SIM");
            else
            System.out.println("NAO");
        }

    }

}