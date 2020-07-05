import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class Questao7Encoding {

    //NUMERO DE VOGAIS COM E SEM ACENTO, CONSOANTES, <br> e <table>, NOME DA PAGINA

    public static boolean isFim(String s){

        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static int contadorA(String html) {

        int a = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'a')
            a++;   
        }

        return a;
    }

    public static int contadorE(String html) {
        
        int e = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'e')
            e++;
        }

        return e;
    }

    public static int contadorI(String html) {
        
        int i = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'i')
            i++;
        }

        return i;
    }

    public static int contadorO(String html) {
        
        int o = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'o')
            o++;
        }

        return o;
    }

    public static int contadorU(String html) {
        
        int u = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'u')
            u++;
        }

        return u;
    }

    public static int contadorAD(String html) {
        
        int a = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'á')
            a++;
        }

        return a;
    }

    public static int contadorED(String html) {
        
        int e = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'é')
            e++;
        }

        return e;
    }

    public static int contadorID(String html) {
        
        int i = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'í')
            i++;
        }

        return i;
    }

    public static int contadorOD(String html) {
        
        int o = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'ó')
            o++;
        }

        return o;
    }

    public static int contadorUD(String html) {
        
        int u = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'ú')
            u++;
        }

        return u;
    }

    public static int contadorAE(String html) {
        
        int a = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'à')
            a++;
        }

        return a;
    }
    
    public static int contadorEE(String html) {
        
        int e = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'è')
            e++;
        }

        return e;
    }

    public static int contadorIE(String html) {
        
        int i = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'ì')
            i++;
        }

        return i;
    }

    public static int contadorOE(String html) {
        
        int o = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'ò')
            o++;
        }

        return o;
    }

    public static int contadorUE(String html) {
        
        int u = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'ù')
            u++;
        }

        return u;
    }

    public static int contadorAtil(String html) {
        
        int a = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'ã')
            a++;
        }

        return a;
    }

    public static int contadorOtil(String html) {
        
        int o = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'õ')
            o++;
        }

        return o;
    }

    public static int contadorAchapeu(String html) {
        
        int a = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'â')
            a++;
        }

        return a;
    }

    public static int contadorEchapeu(String html) {
        
        int e = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'ê')
            e++;
        }

        return e;
    }

    public static int contadorIchapeu(String html) {
        
        int i = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'î')
            i++;
        }

        return i;
    }

    public static int contadorOchapeu(String html) {
        
        int o = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'ô')
            o++;
        }

        return o;
    }

    public static int contadorUchapeu(String html) {
        
        int u = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == 'û')
            u++;
        }

        return u;
    }

    public static int contadorConsoantes(String html){

        int consoante = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) >= 'b' && html.charAt(j) <= 'd' || html.charAt(j) >= 'f' && html.charAt(j) <= 'h' || 
                html.charAt(j) >= 'j' && html.charAt(j) <= 'n' || html.charAt(j) >= 'p' && html.charAt(j) <= 't' || 
                html.charAt(j) >= 'v' && html.charAt(j) <= 'z')
                consoante++;
        }

        return consoante;
    }

    public static int contadorBR(String html) {
        
        int br = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == '<' && html.charAt(j+1) == 'b' && html.charAt(j+2) == 'r' && html.charAt(j+3) == '>')
            br++;
        }

        return br;
    }

    public static int contadorTable(String html) {
        
        int table = 0;

        for(int j = 0; j < html.length(); j++){
            if(html.charAt(j) == '<' && html.charAt(j+1) == 't' && html.charAt(j+2) == 'a' && html.charAt(j+3) == 'b' && 
            html.charAt(j+4) == 'l' && html.charAt(j+5) == 'e' && html.charAt(j+6) == '>')
            table++;
        }

        return table;
    }



    public static String pegarTexto(String link)
	{

		String texto = "",linha = "";

		try {//tentando ler o link inserido

			URL url = new URL(link);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			linha = br.readLine();

			while (linha != null) {
				texto += linha;
				linha = br.readLine();
			}

			br.close();
		} catch (MalformedURLException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	
		return texto;

	}


    public static void main (String args[]) throws Exception { 

        Scanner sc = new Scanner(System.in);

        String serieNome = "";
        String link = "";
        String[] texto = new String[1000];
        int i = 0;
        String[] serie = new String[5];

        

        do{
            serieNome = sc.nextLine();
            serie[i] = serieNome;
            if(isFim(serieNome) == false)
                link = sc.nextLine();
                texto[i] = pegarTexto(link);
                i++;
        }while(isFim(serieNome) == false);
        i--;
        
        int valor = 0;
        int valorA = 0;
        int valorE = 0;

        for(int j = 0; j < i; j++){
            valor = contadorTable(texto[j]);
            valorA = contadorA(texto[j]) - valor;
            valorE = contadorE(texto[j]) - valor;
            System.out.print("a(" +valorA +") ");
            System.out.print("e(" +valorE +") ");
            System.out.print("i(" +contadorI(texto[j]) +") ");
            System.out.print("o(" +contadorO(texto[j]) +") ");
            System.out.print("u(" +contadorU(texto[j]) +") ");
            System.out.print("á(" +contadorAD(texto[j]) +") ");
            System.out.print("é(" +contadorED(texto[j]) +") ");
            System.out.print("í(" +contadorID(texto[j]) +") ");
            System.out.print("ó(" +contadorOD(texto[j]) +") ");
            System.out.print("ú(" +contadorUD(texto[j]) +") ");
            System.out.print("à(" +contadorAE(texto[j]) +") ");
            System.out.print("è(" +contadorEE(texto[j]) +") ");
            System.out.print("ì(" +contadorIE(texto[j]) +") ");
            System.out.print("ò(" +contadorOE(texto[j]) +") ");
            System.out.print("ù(" +contadorUE(texto[j]) +") ");
            System.out.print("ã(" +contadorAtil(texto[j]) +") ");
            System.out.print("õ(" +contadorOtil(texto[j]) +") ");
            System.out.print("â(" +contadorAchapeu(texto[j]) +") ");
            System.out.print("ê(" +contadorEchapeu(texto[j]) +") ");
            System.out.print("î(" +contadorIchapeu(texto[j]) +") ");
            System.out.print("ô(" +contadorOchapeu(texto[j]) +") ");
            System.out.print("û(" +contadorUchapeu(texto[j]) +") ");
            System.out.print("consoante(" +contadorConsoantes(texto[j]) +") ");
            System.out.print("<br>(" +contadorBR(texto[j]) +") ");
            System.out.print("<table>(" +contadorTable(texto[j]) +") ");
            System.out.println(serie[j]);
        }
        


        sc.close();
        
    }
}
