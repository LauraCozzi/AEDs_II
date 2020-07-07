import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lab6 {

    public static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

		String numerosTelefonicos;
		int quantidade = 0;
		int valor = 0;

		numerosTelefonicos = in.readLine();
        
        while(numerosTelefonicos != null){
			quantidade = Integer.parseInt(numerosTelefonicos);
			ListaDupla listaDupla = new ListaDupla();
			for(int i = 0; i < quantidade; i++){
				numerosTelefonicos = in.readLine();
				listaDupla.inserirFim(Integer.parseInt(numerosTelefonicos));
			}
			listaDupla.ordenar(quantidade);
			//listaDupla.mostrar();
			valor = listaDupla.compararListas2(quantidade);
			numerosTelefonicos = in.readLine();
			System.out.println(valor);

		}
		
		in.close();
    }
}

class CelulaDupla {

	public int elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	public CelulaDupla() {
		this(0);
	}

	public CelulaDupla(int elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}

class ListaDupla {

	private CelulaDupla primeiro;
	private CelulaDupla ultimo;

	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

	public void inserirInicio(int x) {
		CelulaDupla tmp = new CelulaDupla(x);

		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo){
			ultimo = tmp;
		}else{
			tmp.prox.ant = tmp;
		}
		tmp = null;
	}

	public void inserirFim(int x) {
		ultimo.prox = new CelulaDupla(x);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}





	public void mostrar() {
		System.out.print("[ "); // Comeca a mostrar.
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] "); // Termina de mostrar.
	}

	public int tamanho() {
		int tamanho = 0; 
		for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
		return tamanho;
	}



	public int compararListas2(int quantidade){

		int iguais = 0;
		CelulaDupla k = primeiro.prox;
		CelulaDupla j = k.prox;
		int tamanho = Integer.toString(k.elemento).length();

		for(int i = 0; i < quantidade - 1; i++){
			for(int pos = 0; pos < tamanho; pos++){
				if(Integer.toString(k.elemento).charAt(pos) == Integer.toString(j.elemento).charAt(pos)){
					iguais++;
				}else{
					pos = tamanho;
					if(pos == 0)iguais = 0;
				}
			}
			
			k = j;
			j = j.prox;
		}




		return iguais;
	}

	public void ordenar(int quantidade){

		CelulaDupla temporaria = primeiro.prox;
		CelulaDupla inicio = primeiro.prox;
		CelulaDupla pMenor = inicio;
		int menor;
		int aux;

		while(inicio.prox != null){
			menor = inicio.elemento;
			temporaria = inicio.prox;
			pMenor = inicio;
			while(temporaria.prox != null){
				if(temporaria.elemento < menor){
					pMenor = temporaria;
					menor = temporaria.elemento;
				}
				temporaria = temporaria.prox;
			}
			aux = pMenor.elemento;
			pMenor.elemento = inicio.elemento;
			inicio.elemento = aux;

			inicio = inicio.prox;

			

		}




	}


}