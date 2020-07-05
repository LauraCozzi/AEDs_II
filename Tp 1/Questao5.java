import java.util.Scanner;

/**
 * Algebra Booleana - Questão 5
 * @author Laura Cozzi Ribeiro
 * @version 2020
 */
public class Quest5Final {

    public static Scanner sc = new Scanner(System.in);

    /**
    * Verifica se chegou ao fim a entrada de dados
    * @param s String conteúdo que o usuário digitou.
    */
    public static boolean isFim(String s){

        return (s.charAt(0) == '0');
    }

    /**
    * Indica qual a operação mais de fora, a que inclui todos os outros
    * @param s String somente a expressão booleana.
    */
    public static int identifier(String entrada){

        int identificador = 0;

        //identificando NOT = 1
        for(int i = 0; i < entrada.indexOf("("); i++){
            if(entrada.charAt(i) == 'n' && entrada.charAt(i+1) == 'o' && entrada.charAt(i+2) == 't')
            identificador = 1;
            //identificando AND = 2
            else if(entrada.charAt(i) == 'a' && entrada.charAt(i+1) == 'n' && entrada.charAt(i+2) == 'd')
            identificador = 2;
            //identificando OR = 3
            else if(entrada.charAt(i) == 'o' && entrada.charAt(i+1) == 'r') 
            identificador = 3;
        }

        return identificador;
    }

    /**
    * Substitui os valores pelas variáveis A, B e C
    * @param s String a quantidade de variáveis com seus valores e String sentença booleana.
    * @return sentença booleana com as variáveis com seus valores
    */
    public static String substituirValores2(String parte1, String fraseBooleana){

        String valorA = String.valueOf(parte1.charAt(2));
        String valorB = String.valueOf(parte1.charAt(4));

        fraseBooleana = fraseBooleana.replaceAll("A", valorA);
        fraseBooleana = fraseBooleana.replaceAll("B", valorB);

        return fraseBooleana;
    }

    /**
    * Substitui os valores pelas variáveis A, B e C
    * @param s String a quantidade de variáveis com seus valores e String sentença booleana.
    * @return sentença booleana com as variáveis com seus valores
    */
    public static String substituirValores3(String parte1, String fraseBooleana){

        String valorA = String.valueOf(parte1.charAt(2));
        String valorB = String.valueOf(parte1.charAt(4));
        String valorC = String.valueOf(parte1.charAt(6));

        fraseBooleana = fraseBooleana.replaceAll("A", valorA);
        fraseBooleana = fraseBooleana.replaceAll("B", valorB);
        fraseBooleana = fraseBooleana.replaceAll("C", valorC);

        return fraseBooleana;
    }

    /**
    * FUnção substitui os valores das variáveis e manda somente a sentenca booleana
    * @param entrada String entrada do usuário completa.
    * @return fraseBool String sentença com os valores trocados e sem a primeira parte
    */
    public static String sentencaInteira(String entrada){

        String [] entradaDupla = new String[2];
        String fraseBool = "";

        if(entrada.charAt(0) == '2'){
            entradaDupla[0] = entrada.substring(0,5);
            entradaDupla[1] = entrada.substring(6, entrada.length());
            //fraseBool com os valores
            fraseBool = substituirValores2(entradaDupla[0], entradaDupla[1]);
        } 
        else if(entrada.charAt(0) == '3'){
            entradaDupla[0] = entrada.substring(0,7);
            entradaDupla[1] = entrada.substring(8, entrada.length());
            //frase com os valores
            fraseBool = substituirValores3(entradaDupla[0], entradaDupla[1]);
        }


        return fraseBool;
    }

    /**
    * Função corta a primeira expressão que deve ser resolvida
    * @param entrada sentença booleana
    * @return String simplificada
    */
    public static String cortandoExpressao(String entrada){

        int contadorPA = 0, contadorPF = 0, posicaoUltimo = 0;
        String simplificada = "";
        boolean fim = false;

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == '('){
                contadorPA++;
            }
            else if(entrada.charAt(i) == ')'){
                contadorPF++;
            }
            //verificando se a quantidade de parenteses abertos e igual ao de parenteses fechado
            if(contadorPA != 0 && contadorPF != 0){
                if(contadorPA == contadorPF){
                    posicaoUltimo = i;
                    i = entrada.length();
                }
            }
        }

        simplificada = entrada.substring(entrada.indexOf("(")+1, posicaoUltimo);//tiramos os parenteses da expressao global

        return simplificada;

    }

    /**
    * Corta a primeira expressao booleana que aparecer
    * @param entrada 
    * @return sentence String
    */
    public static String cortando(String entrada){

        boolean abriu = false;
        boolean fechou = false;
        int posicao = 0;
        String sentenca = "";
        int posicaoI = 0;

        for(int i = 0; fechou == false && i < entrada.length(); i++){
            if(entrada.charAt(i) == '(')
            abriu = true;
            else if(entrada.charAt(i) == ')')
            fechou = true;
            posicao = i;
        }

        for(int j = 0; j < entrada.length(); j++){
            if(entrada.charAt(j) == 'n' || entrada.charAt(j) == 'a' || entrada.charAt(j) == 'o'){
                posicaoI = j;
                j = entrada.length();
            }

        }

        sentenca = entrada.substring(posicaoI, ++posicao); //colocar quando aparecer a primeira letra

        return sentenca;

    }

    /**
    * Resolve a expressao NOT
    * @param s String Expressão com not
    * @return String correspondente a 1 ou 0
    */
    public static String resolveNot(String entrada){

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == '0'){
                entrada = entrada.replace('0', '1');
                entrada = entrada.substring(4, 5);
                //System.out.println(entrada);
            }
            else if(entrada.charAt(i) == '1'){
                entrada = entrada.replace('1', '0');
                entrada = entrada.substring(4, 5);
            }
        }

        return entrada;
    }
    /**
    * Função resolve a operação AND
    * @param entrada String somente com a operação and
    * @return result String com a resposta da operação and, 1 ou 0
    */
    public static String resolveAnd(String entrada){

        String result = "0";

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == '0'){
                result = "0";
                i = entrada.length();
            }
            else {
                result = "1";
            }
        }
    
        return result;

    }

    /**
    * Função resolve a operação OR
    * @param s String somente a operação or com seus respectivos valores
    * @return result String 1 ou 0 resolução da operação or
    */
    public static String resolveOr(String entrada){

        String result = "0";

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == '1'){
                result = "1";
                i = entrada.length();
            }
            else {
                result = "0";
            }
        }
    
        return result;

    }

    /**
    * 
    * @param s 
    * @return 
    */
    public static String interpretando(String entrada){

        String sentence = cortando(entrada);
        int porta = 0;

        if (sentence.charAt(0) != '0' && sentence.charAt(0) != '1'){
        porta = identifier(sentence);
        }

        String resposta = "";
        String frase = "";


        if(porta == 1){
            resposta = resolveNot(sentence);
            frase = entrada.replace(sentence, resposta);
        } else if(porta == 2){
            resposta = resolveAnd(sentence);
            frase = entrada.replace(sentence, resposta);
        } else if(porta == 3){
            resposta = resolveOr(sentence);
            frase = entrada.replace(sentence, resposta);
        } else if (sentence.charAt(0) == '0' || sentence.charAt(0) == '1'){
            frase = entrada;
        }

        return frase;
        
    }


    /**
    * Função resolve uma expressão
    * @param entrada expressão para ser resolvida
    * @return resposta String da resolução da porta
    */
    public static String respostaFinal(String entrada, int identificador){

        String resposta = "";

        if(identificador == 1){
            resposta = resolveNot(entrada);
        } else if(identificador == 2){
            resposta = resolveAnd(entrada);
        } else if(identificador == 3){
            resposta = resolveOr(entrada);
        }

        return resposta;

    }

    /**
    * Função verifica se tem mais portas lógicas para serem resolvidas na expressão
    * @param entrada String expressão para ser resolvida
    * @return MaisExpressao true se possui mais expressão, false se não possuir
    */
    public static boolean verificarMaisExpressoes(String entrada){

        boolean maisExpressao = false;

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) != '1' && entrada.charAt(i) != '0' && entrada.charAt(i) != ' ' && entrada.charAt(i) != ',')
                maisExpressao = true;            
        }

        return maisExpressao;

    }

    /**
    * 
    * @param s 
    * @return 
    */
    public static String continuarResolvendo(String entrada){

        String novaEntrada = "";
        String resolvida = "";
        boolean maisExpressoes = false;
        String expressao = "";

        novaEntrada = acharProximaExpressao(entrada);

        if(novaEntrada.length() > 10){ //verificar se tem um ()
            resolvida = resolucao(novaEntrada);
            maisExpressoes = verificarMaisExpressoes(resolvida);
                if(maisExpressoes == true){
                    if(expressaoPronta(resolvida)){
                        resolvida = interpretando(resolvida);
                    } else { //possuo mais expressoes porem nao posso ser resolvida

                        resolvida = entrada.replace(novaEntrada, resolvida);
                        novaEntrada = resolvida;
                    }

                } else {
                }
                  //fim sefundo
        } else{
            resolvida = interpretando(novaEntrada);
        }//fim do primeiro if

        resolvida = entrada.replace(novaEntrada, resolvida);

        return resolvida;
    }

    public static String acharProximaExpressao(String expressao){

        int inicio = 0;
        String novaExpressao = "";

        for(int i = 0; i < expressao.length(); i++){
            if(expressao.charAt(i) == 'n' || expressao.charAt(i) == 'o' || expressao.charAt(i) == 'a'){
                inicio = i;
                i = expressao.length();
            }
        }
        novaExpressao = expressao.substring(inicio, expressao.length());

        return novaExpressao;
    }

    /**
    * 
    * @param s 
    * @return 
    */
    public static String resolucao(String entrada){

        String simplificada = "";
        String sentencaMain = "";
        String comReplace = "";
        int identificador = 0;

        //identificando a operação global NOT1, AND2, OR3
        identificador = identifier(entrada);

        //cortando a expressao booleana para resolver por partes
        simplificada = cortandoExpressao(entrada);

        if(expressaoPronta(simplificada))
        sentencaMain = interpretando(simplificada);
        else 
        sentencaMain = simplificada;

        comReplace = entrada.replace(simplificada, sentencaMain);

        return comReplace;

    }

    public static boolean expressaoPronta(String booleana){
        
        int parentesesA = 0, parentesesF = 0;
        boolean pronto = false;

        for(int i = 0; i < booleana.length(); i++){
            if(booleana.charAt(i) == '(')
                parentesesA++;
            else if(booleana.charAt(i) == ')')
                parentesesF++;
        }

        if(parentesesA == 1 && parentesesF == 1) pronto = true;
        else pronto = false;

        return pronto;

    }

    public static String resolverGrandes(String entrada){

        String novaEntrada = "";
        String primeiraExpressao = "";
        boolean podeSerResolvida = false;
        String expression = "";
        String interpretada = "";
        String entrada2 = "";
        String respostaFinal = "";
        String fraseAserSubstituida = "";
        String entrada3 = "";
        String entrada4 = "";

        novaEntrada = cortandoExpressao(entrada);
        primeiraExpressao = acharProximaExpressao(novaEntrada);
        fraseAserSubstituida = primeiraExpressao;
        podeSerResolvida = consegueResolver(primeiraExpressao);
        if(podeSerResolvida){
            expression = primeiraExpressao(primeiraExpressao);
            interpretada = interpretando(expression);
            primeiraExpressao = primeiraExpressao.replace(expression, interpretada);
            entrada2 = acharProximaExpressao(primeiraExpressao);
            expression = primeiraExpressao(primeiraExpressao);
            interpretada = interpretando(expression);
            primeiraExpressao = primeiraExpressao.replace(expression, interpretada);
            if(verificarMaisExpressoes(primeiraExpressao) == false){
                respostaFinal = entrada.replace(fraseAserSubstituida, primeiraExpressao);
            } else {
                entrada3 = acharProximaExpressao(primeiraExpressao);
                expression = primeiraExpressao(primeiraExpressao);
                interpretada = interpretando(expression);
                primeiraExpressao = primeiraExpressao.replace(expression, interpretada);
                respostaFinal = entrada.replace(fraseAserSubstituida, primeiraExpressao);
            }

        } else {
            if(novaEntrada.equals("0") || novaEntrada.equals("1")){
                respostaFinal = entrada;
            } else {
        entrada2 = cortandoExpressao(primeiraExpressao);
        primeiraExpressao = acharProximaExpressao(entrada2);
        entrada3 = resolucao(primeiraExpressao);
        interpretada = interpretando(entrada3);
        entrada3 = entrada2.replace(primeiraExpressao, interpretada);
        expression = fraseAserSubstituida.replace(entrada2, entrada3);
        interpretada = interpretando(expression);
        entrada4 = novaEntrada.replace(fraseAserSubstituida, interpretada);
        respostaFinal = entrada.replace(novaEntrada, entrada4);
            }


        }

        return respostaFinal;
        

    }

        /**
    * 
    * @param s 
    * @return 
    */
    public static String primeiraExpressao(String CortandoExpressao){

        int posicaoI = 0, posicaoF = 0, contadorPA = 0, contadorPF = 0;
        String primeiraExpressao = "";

        for(int i = 0; i < CortandoExpressao.length(); i++){
            if(CortandoExpressao.charAt(i) == 'n' || CortandoExpressao.charAt(i) == 'a' || CortandoExpressao.charAt(i) == 'o' ){
                posicaoI = i;
                i = CortandoExpressao.length();
            }
        }

        for(int i = 0; i < CortandoExpressao.length(); i++){
            if(CortandoExpressao.charAt(i) == '('){
                contadorPA++;
            }
            else if(CortandoExpressao.charAt(i) == ')'){
                contadorPF++;
            }
            //verificando se a quantidade de parenteses abertos e igual ao de parenteses fechado
            if(contadorPA != 0 && contadorPF != 0){
                if(contadorPA == contadorPF){
                    posicaoF = i;
                    i = CortandoExpressao.length();
                }
            }
        }

        primeiraExpressao = CortandoExpressao.substring(posicaoI, ++posicaoF);

        return primeiraExpressao;
    }

    public static boolean consegueResolver(String entrada){

        int PA = 0, PF = 0;
        boolean consegue = false;

        for(int i = 0; i < entrada.length(); i++){
            if(entrada.charAt(i) == '(') 
                PA++;
            if(entrada.charAt(i) == ')'){
                PF++;
                i = entrada.length();
            }
        }

        if(PA == 1 && PF == 1) consegue = true;
        else consegue = false;

        return consegue;
    }


    /**
    * Função que inicializa a resolução do que o usuário digitou
    * @param s String entrada do usuário
    */
    public static void inicio(String entrada){

        String booleana = "";
        int identificador = 0;
        String simplificada = "";
        String sentencaMain = "";
        String respostaFinal = "";
        boolean maisExpressoes = false;
        String resolvida = "";

        //pegar a sentença para ser resolvida
        booleana = sentencaInteira(entrada);
        booleana = booleana.replace("not(0)", "1");
        booleana = booleana.replace("not(1)", "0");

        //identificando a operação global NOT1, AND2, OR3
        identificador = identifier(booleana);

        //cortando a expressao booleana para resolver por partes
        simplificada = cortandoExpressao(booleana);

        //le a primeira parte e resolve
        sentencaMain = interpretando(simplificada);

        maisExpressoes = verificarMaisExpressoes(sentencaMain);
        if(maisExpressoes == true){
           resolvida = continuarResolvendo(sentencaMain);
           sentencaMain = booleana.replace(simplificada, resolvida);
           if(expressaoPronta(sentencaMain) == false){
               sentencaMain = resolverGrandes(sentencaMain);
           }
        } else {
            sentencaMain = booleana.replace(simplificada, sentencaMain);
        }

        respostaFinal = respostaFinal(sentencaMain, identificador);
        System.out.println(respostaFinal);


    }


    public static void main(String [] args){

        Scanner sc = new Scanner(System.in);

        String[] entrada = new String[1000];
        int ordemEntrada = 0;

        do{
            entrada[ordemEntrada] = MyIO.readLine();
        } while(isFim(entrada[ordemEntrada++]) == false);
        ordemEntrada--;

        for(int i = 0; i < ordemEntrada; i++){
            inicio(entrada[i]);
        }

    }
}