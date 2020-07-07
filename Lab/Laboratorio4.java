import java.util.Scanner;

public class Laboratorio4 {

    //Quantidade de ( é iguala de ) e primeiro deve abrir 

    public static boolean isFim(String s){

        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) =='M');
    }

    public static boolean contandoParenteses(String entrada){

        int contadorPA = 0, contadorPF = 0;
        boolean ehValida = false;

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == '('){
                contadorPA++;
            }
            else if(entrada.charAt(i) == ')'){
                contadorPF++;
            }

            if(contadorPF > contadorPA){
                i = entrada.length();
                ehValida = false;
            }
        }
            //verificando se a quantidade de parenteses abertos e igual ao de parenteses fechado
        if(contadorPA == contadorPF){
            ehValida = true;
        } else {
            ehValida = false;
        }
        

            return ehValida;
    }

    public static boolean verificaFecha(String entrada){

        boolean abriu = false, fechou = true;
        boolean ehValida = false;

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == '(')
                abriu = true;
            if(entrada.charAt(i) == ')' && abriu == false){
                ehValida = false;
                i = entrada.length();
            } else
                ehValida = true;
        }

        return ehValida;
    }





    public static void main(String [] args){

        String[] entrada = new String[1000];
        int ordemEntrada = 0;

        do{
            entrada[ordemEntrada] = MyIO.readLine();
        } while(isFim(entrada[ordemEntrada++]) == false);
        ordemEntrada--;

        for(int i = 0; i < ordemEntrada; i++){
            if(contandoParenteses(entrada[i]) && verificaFecha(entrada[i])){
                System.out.println("correto");
            } 
            else {
                System.out.println("incorreto");

            }
        }
    }






}