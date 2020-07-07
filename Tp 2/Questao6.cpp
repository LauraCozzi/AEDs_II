#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>

#define NUMENTRADA 1000
#define TAMLINHA   1000
#define MAXTAM     50
#define TAMFILA    6

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

typedef struct {
    Personagem *array[TAMFILA + 1];   // Elementos da pilha 
    int primeiro;          // Remove do indice "primeiro".
    int ultimo;             // Insere no indice "ultimo".     
} Fila;  


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
double somaAltura(Fila *fila);

//fila
void start(Fila *fila);
void inserir(Personagem *x, Fila *fila);
Personagem* remover(Fila *fila);
void mostrar (Fila *fila);
bool isVazia(Fila *fila);
Personagem* clone(Personagem* x);



int main(){

    char* diretorio = (char*)malloc(sizeof(char)*100);
    scanf("%s", diretorio);
    Personagem *personagem;
    Personagem *removido;
    Fila *fila = (Fila*)malloc(sizeof(Fila));
    char* texto = (char*)malloc(sizeof(char)*100);   
    char* comando = (char*)malloc(sizeof(char)*100);
    int posicao = 0;
    int posicaoEspaco = 0;
    int quantidade;
    int altura = 0;
    int quantidadePersonagens = 0;
    double mediaAltura = 0;


    while(isFim(diretorio) == false){
        personagem = new_Personagem();
        setPersonagem(diretorio, personagem);
        inserir(personagem, fila);
        somaAltura(fila);
        scanf("%s", diretorio);
    }

    scanf("%d", &quantidade);
    fgets(texto, 10, stdin);

    for(int i = 0; i < quantidade; i++){
        char* endereco = (char*)malloc(sizeof(char)*100);
        fgets(texto, 1000, stdin);
        texto[strlen(texto)-1] = '\0';
        posicaoEspaco = index_of(texto, ' ');
        comando = subString(texto, 0, posicaoEspaco);
        personagem = new_Personagem();
        removido = new_Personagem();

        if(posicaoEspaco == 0) comando = texto;

        if(strcmp(comando, "I") == 0){
            endereco = pegandoEndereco(comando, texto, posicaoEspaco);
            setPersonagem(endereco, personagem);
            inserir(personagem, fila);
            somaAltura(fila);
        } else if(strcmp(comando, "R") == 0){
            posicao = posicaoDoComando(texto, posicaoEspaco);
            removido = remover(fila);
            printf("(R) %s\n", removido->nome);
        }

        fflush(stdin);
    }


}

double somaAltura(Fila *fila){

    float media = 0;
    double soma = 0;
    int quantidade = 0;
    int i = fila->primeiro;

    while (i != fila->ultimo)
    {
     quantidade++;
     soma += fila->array[i]->altura;  
     i = (i + 1) % TAMFILA;  
    }

    //printf("Quantidade: %d\n", quantidade);
    
    media = soma/quantidade;

    printf("%g\n", round(media));

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

/**
 * Inicializacoes
 */
void start(Fila *fila){
   fila->primeiro = fila->ultimo = 0;
}


/**
 * Insere um elemento na ultima posicao da 
 * @param x int elemento a ser inserido.
 * @Se a fila estiver cheia.
 */
void inserir(Personagem *x, Fila *fila) {

   //validar insercao
   if (((fila->ultimo + 1) % TAMFILA) == fila->primeiro) {
      remover(fila);
   }

   fila->array[fila->ultimo] = x;
   fila->ultimo = (fila->ultimo + 1) % TAMFILA;
}


/**
 * Remove um elemento da primeira posicao da fila e movimenta 
 * os demais elementos para o primeiro da mesma.
 * @return resp int elemento a ser removido.
 * @Se a fila estiver vazia.
 */
Personagem* remover(Fila *fila) {

    Personagem *resp = (Personagem*)malloc(sizeof(Personagem));

   //validar remocao
   if (fila->primeiro == fila->ultimo) {
      //printf("Erro ao remover!");
      exit(1);
   }

   resp = fila->array[fila->primeiro];
   fila->primeiro = (fila->primeiro + 1) % TAMFILA;
   return resp;
}


/**
 * Mostra os array separados por espacos.
 */
void mostrar (Fila *fila){
   int i;

   for(i = fila->primeiro; i != fila->ultimo; i = ((i + 1) % TAMFILA)) {
       imprimir(fila->array[i]);
   }

}


/**
 * Retorna um bool indicando se a fila esta vazia
 * @return bool indicando se a fila esta vazia
 */
bool isVazia(Fila *fila) {
   return (fila->primeiro == fila->ultimo); 
}


Personagem* clone(Personagem* x){

    Personagem* tmp;
    tmp = x;

    return tmp;
}