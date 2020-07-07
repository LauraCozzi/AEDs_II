#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <err.h>

#define bool      short
#define true      1
#define false     0

//Declarando constante MAXTAM
const int MAXTAM = 50;

int contador;

//struct String
#define MAXTAM     50

typedef struct {
	char string[MAXTAM];
}String;

typedef struct {
    char *nome;
    int altura;
    double peso;
    char *corDoCabelo;
    char *corDaPele;
    char *corDosOlhos;
    char *anoNascimento;
    char *genero;
    char *homeworld;
}Personagem;

typedef struct Celula {
	Personagem* elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

typedef struct Lista
{
	Celula* primeiro;
	Celula* ultimo;

}Lista;

typedef struct Hash
{
	Lista* tabela[25];
	int tam;

}Hash;


//Funcoes da classe PERSONAGENS
String* new_String();
void setPersonagem(char* diretorio, Personagem* personagens);
bool isFim(char* entrada);
Personagem* new_Personagem();
char* string_substring(char str[], int start, int end);
void imprimir(Personagem* temp);
int index_of(char* texto, char caracter);
char* subString(char texto[], int posI, int posF);
char* pegandoEndereco(char* comando, char* texto, int posicao);
int posicaoDoComando(char* texto, int posicaoEspaco);
Personagem* clone(Personagem* x);

//Lista e Hash
Lista* new_Lista();
Celula* new_Celula(Personagem* elemento);
Hash* new_Hash();
void inserirLista(Lista* l,Personagem* x);
void inserirHash(Hash* hash,Personagem* x);
bool pesquisarLista(Lista* l,String* x);
bool pesquisarHash(Hash* h,String* x);
int h(Personagem* elemento,Hash* h);



int main()
{

	String* entrada = new_String();
	scanf("%s", entrada->string);
	Personagem* Personagens;
	bool resp;


	Hash* hash = new_Hash();
	
	while (!isFim(entrada->string)) 
	{

		Personagens = new_Personagem();
		setPersonagem(entrada->string,Personagens);

		inserirHash(hash,Personagens);
		scanf("\n%s", entrada->string);

	}

	fgets(entrada->string, sizeof(entrada->string), stdin);
	entrada->string[strlen(entrada->string)-1] = '\0';

	while (!isFim(entrada->string)) 
	{

		if (strcmp(entrada->string,"") != 0)
		{
			printf("%s",entrada->string);
			resp = pesquisarHash(hash,entrada);
			if ( resp == true )
				printf(" SIM\n");
			else 
				printf(" NÃO\n");

		}

		fgets(entrada->string, sizeof(entrada->string), stdin);
		entrada->string[strlen(entrada->string)-1] = '\0';

	}

	return 0;

}

char* string_substring(char str[], int start, int end) {

    int i, j;
    char *sub; 
     
    if(start >= end || end > strlen(str)) {
        return NULL;
    }
    sub = (char *) malloc(sizeof(char) * (end - start + 1));
     
    for(i = start, j = 0; i < end; i++, j++) {
        sub[j] = str[i];
    }
    sub[j] = '\0';
     
    return sub;
}

void setPersonagem(char* diretorio, Personagem* personagens){

    char* altura = (char*)malloc(sizeof(char)*100);
    char* peso = (char*)malloc(sizeof(char)*100);
    char* texto = (char*)malloc(sizeof(char)*1000);  
    char* nome = (char*)malloc(sizeof(char)*100);
    char* corDoCabelo = (char*)malloc(sizeof(char)*100);
    char* corDaPele = (char*)malloc(sizeof(char)*100);
    char* corDosOlhos = (char*)malloc(sizeof(char)*100);
    char* anoNascimento = (char*)malloc(sizeof(char)*100);
    char* genero = (char*)malloc(sizeof(char)*100);
    char* homeworld = (char*)malloc(sizeof(char)*100);
    char* linha = (char*)malloc(sizeof(char)*100);
    char *novaEntrada = (char*)malloc(sizeof(char)*1000);

    //leitura do arquivo
    FILE* arq = fopen(diretorio, "r");
	fgets(texto,10000,arq);
	texto[strlen(texto)-1] = '\0';
    
    //Limpando HTML
    int posicaoF;
    for(int i = 0; i < strlen(texto); i++){
        if(texto[i] == 'f' && texto[i+1] == 'i' && texto[i+2] == 'l' && texto[i+3] == 'm' && texto[i+4] == 's' && texto[i+5] == '\'')
            posicaoF = i;
    }
        strncpy(novaEntrada, texto, posicaoF);
        novaEntrada[posicaoF-3] = '\0'; //elimina os 3 ultimos char

    int posicaoDesejada = 0;
    double pesoAux = 0;

        //Pegando nome
        linha = strstr(novaEntrada, "name");
	    linha = strstr(linha, "\'");
        memmove(&linha[0], &linha[4], strlen(linha));
        posicaoDesejada = index_of(linha, '\'');
        strncat(nome, linha, posicaoDesejada);
        personagens->nome = nome;

        //Pegando altura
        linha = strstr(novaEntrada, "height");
        linha = strstr(linha, "\'");
        memmove(&linha[0], &linha[4], strlen(linha));
        posicaoDesejada = index_of(linha, '\'');
        strncat(altura, linha, posicaoDesejada);
        personagens->altura = atoi(altura);

        //Pegando peso
        linha = strstr(novaEntrada, "mass");
        linha = strstr(linha, "\'");
        memmove(&linha[0], &linha[4], strlen(linha));
        posicaoDesejada = index_of(linha, '\'');
        strncat(peso, linha, posicaoDesejada);
        if(strcmp(peso, "unkown") == 0){
            peso = "0";
        }
        pesoAux = atof(peso);
        personagens->peso = pesoAux;

        //Pegando cor do Cabelo
        linha = strstr(novaEntrada, "hair_color");
	    linha = strstr(linha, "\'");
        memmove(&linha[0], &linha[4], strlen(linha));
        posicaoDesejada = index_of(linha, '\'');
        strncat(corDoCabelo, linha, posicaoDesejada);
        personagens->corDoCabelo = corDoCabelo;

        //Pegando cor da pele
        linha = strstr(novaEntrada, "skin_color");
	    linha = strstr(linha, "\'");
        memmove(&linha[0], &linha[4], strlen(linha));
        posicaoDesejada = index_of(linha, '\'');
        strncat(corDaPele, linha, posicaoDesejada);
        personagens->corDaPele = corDaPele;

        //Pegando cor dos olhs
        linha = strstr(novaEntrada, "eye_color");
	    linha = strstr(linha, "\'");
        memmove(&linha[0], &linha[4], strlen(linha));
        posicaoDesejada = index_of(linha, '\'');
        strncat(corDosOlhos, linha, posicaoDesejada);
        personagens->corDosOlhos = corDosOlhos;

        //Pegando ano de nascimento
        linha = strstr(novaEntrada, "birth_year");
	    linha = strstr(linha, "\'");
        memmove(&linha[0], &linha[4], strlen(linha));
        posicaoDesejada = index_of(linha, '\'');
        strncat(anoNascimento, linha, posicaoDesejada);
        personagens->anoNascimento = anoNascimento;

        //Pegando genero
        linha = strstr(novaEntrada, "gender");
	    linha = strstr(linha, "\'");
        memmove(&linha[0], &linha[4], strlen(linha));
        posicaoDesejada = index_of(linha, '\'');
        strncat(genero, linha, posicaoDesejada);
        personagens->genero = genero;

        //Pegando planeta
        linha = strstr(novaEntrada, "homeworld");
	    linha = strstr(linha, "\'");
        memmove(&linha[0], &linha[4], strlen(linha));
        posicaoDesejada = index_of(linha, '\'');
        strncat(homeworld, linha, posicaoDesejada);
        personagens->homeworld = homeworld;

       
}



void imprimir(Personagem* temp)
{
	printf(" ## %s ## %i ## %g ## %s ## %s ## %s ## %s ## %s ## %s ##\n", temp->nome, temp->altura, temp->peso, temp->corDoCabelo, temp->corDaPele, temp->corDosOlhos, temp->anoNascimento, temp->genero, temp->homeworld);

}


String* new_String(){

	return (String*) malloc(sizeof(String));
}

Personagem* new_Personagem(){

    return(Personagem*)malloc(sizeof(Personagem));
}

bool isFim(char* s){

    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int posicaoDoComando(char* texto, int posicaoEspaco){//Função pega a posicao a ser colocada ou retira

    char* novoTexto = (char*)malloc(sizeof(char)*100);  
    char* posicao = (char*)malloc(sizeof(char)*100);  
    int posEspaco;
    int posicaoInt = 0;

    //pegando o texto sem o comando
    novoTexto = subString(texto, posicaoEspaco+1, strlen(texto));
    //printf("%s\n", novoTexto);

    posEspaco = index_of(novoTexto, ' ');
    //printf("%d\n", posEspaco);

    if(posEspaco != 0){
        novoTexto = subString(novoTexto, 0, posEspaco);
            //printf("%s\n", novoTexto);
    }

    posicaoInt = atoi(novoTexto);

    return posicaoInt;


}

char* pegandoEndereco(char* comando, char* texto, int posicao){//funcao pega o endereço do char*

    char* diretorio = (char*)malloc(sizeof(char)*100);  

    diretorio = subString(texto, posicao+1, strlen(texto));

    return diretorio;
}


int index_of(char* texto, char caracter){//Encontra o char desejado e retorna sua posicao da primeira ocorrencia

    int posicao = 0;

    for(int i = 0; i < strlen(texto); i++){
        if(texto[i] == caracter) {
            posicao = i;
            i = strlen(texto);
        }
    }

    return posicao;
}

char* subString(char texto[], int posI, int posF){//funcao corta a string entre os dois parametros enviados

    char* substring;
    int i, j;


    substring = (char *) malloc(sizeof(char)*(posF - posI + 1));

    if(posF != 0){
        for (i = posI, j = 0; i < posF; i++, j++){
            substring[j] = texto[i];
        }
        substring[j] = '\0';
    } else if(strcmp(texto, "RF") == 0 || strcmp(texto, "RI") == 0){
        substring = texto;
    }

    return substring;

}

Personagem* clone(Personagem* x){

    Personagem* tmp;
    tmp = x;

    return tmp;
}

/**
 * Inicializacoes
 */
Lista* new_Lista()
{
	Lista* novo = (Lista*)malloc(sizeof(Lista));
    novo->primeiro = novo->ultimo = new_Celula(NULL);
	return novo;
}

Celula* new_Celula(Personagem* elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = clone(elemento);
   nova->prox = NULL;
   return nova;
}

Hash* new_Hash()
{
	Hash* novo = (Hash*)malloc(sizeof(Hash));
	novo->tam = 25;
	for ( int i = 0; i < novo->tam ; i++ ) {
		novo->tabela[i] = new_Lista();
	}
	return novo;
}

int h(Personagem* elemento,Hash* h) {
	return elemento->altura % h->tam;
}

bool pesquisarHash(Hash* h, String* elemento) {
	int i = 0;
	bool resp = false;
	while ( resp == false && i < 25 ) {
		resp = pesquisarLista(h->tabela[i],elemento);
		i++;
	}
	return resp;
}

void inserirHash(Hash* hash, Personagem* elemento) {
	int pos = h(elemento,hash);
	inserirLista(hash->tabela[pos],elemento);
}

bool pesquisarLista(Lista* l, String* elemento) {
	bool resp;
	for ( Celula* i = l->primeiro->prox; i != NULL; i = i->prox) {
		if(strcmp(i->elemento->nome,elemento->string) == 0 ) {
			resp = true;
			i = l->ultimo;
		}
	}
	return resp;
}

void inserirLista(Lista* l,Personagem* elemento) {
	Celula* tmp = new_Celula(elemento);
	tmp->prox = l->primeiro->prox;
	l->primeiro->prox = tmp;
	if ( l->primeiro == l->ultimo ) {
		l->ultimo = tmp;
	}
	tmp = NULL;
}


