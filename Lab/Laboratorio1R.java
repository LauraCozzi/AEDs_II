
class Lab01_Q1AquecimentoRecursivo {

	public static boolean isMaiuscula (char c){
		boolean maiuscula = false;
		if(c >= 'A' && c <= 'Z')
			maiuscula = true;
		return maiuscula;
	}

	public static boolean isFim (String s){
		      return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}

	public static int contarLetrasMaiusculas (String s, int posicao){
		int contador = 0;

        if(posicao == -1){
			
        }
            
        else{
            if(isMaiuscula(s.charAt(posicao)) == true)
			contador++;
            contador += contarLetrasMaiusculas(s, --posicao);
        }

		return contador;
	}

	public static void main(String[] args){
		String[] entrada = new String[1000];
		int ordemEntrada = 0;

		do{
			entrada[ordemEntrada] = MyIO.readLine();;
		} while(isFim(entrada[ordemEntrada++]) == false);
		ordemEntrada--;

		for(int i = 0; i < ordemEntrada; i++){
			MyIO.println(contarLetrasMaiusculas(entrada[i], entrada[i].length()-1));
		}

	}
}
