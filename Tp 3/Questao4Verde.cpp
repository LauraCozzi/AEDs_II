#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <err.h>

#define NUMENTRADA 1000
#define TAMLINHA   1000
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

typedef struct CelulaDupla {
	Personagem* elemento;        // Elemento inserido na celula.
	struct CelulaDupla* prox; // Aponta a celula prox.
    struct CelulaDupla* ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Personagem* elemento) {
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = elemento;
   nova->ant = nova->prox = NULL;
   return nova;
}

typedef struct ListaDupla{
    CelulaDupla* primeiro;
    CelulaDupla* ultimo;
} ListaDupla;


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

//lista
Personagem* clone(Personagem* x);
void ordenarCordoCabelo(ListaDupla* lista);
void swap(CelulaDupla* i, CelulaDupla* j);
void quicksortRec(int esq, int dir, ListaDupla* lista);
void quicksort(ListaDupla* lista);



ListaDupla* start() {
    ListaDupla* lista = (ListaDupla*)malloc(sizeof(ListaDupla)); 
    lista->primeiro = novaCelulaDupla(NULL);
   lista->ultimo = lista->primeiro;
   return lista;
}


void inserirInicio(Personagem* x, ListaDupla* lista) {
   CelulaDupla* tmp = novaCelulaDupla(x);

   tmp->ant = lista->primeiro;
   tmp->prox = lista->primeiro->prox;
   lista->primeiro->prox = tmp;
   if (lista->primeiro == lista->ultimo) {                    
      lista->ultimo = tmp;
   } else {
      tmp->prox->ant = tmp;
   }
   tmp = NULL;
}



void inserirFim(Personagem* x, ListaDupla* lista) {

   lista->ultimo->prox = novaCelulaDupla(x);
   lista->ultimo->prox->ant = lista->ultimo;
   lista->ultimo = lista->ultimo->prox;
}


Personagem* removerInicio(ListaDupla* lista) {
   if (lista->primeiro == lista->ultimo) {
      errx(1, "Erro ao remover (vazia)!");
   }

   CelulaDupla* tmp = lista->primeiro;
   lista->primeiro = lista->primeiro->prox;
   Personagem* resp = lista->primeiro->elemento;
   tmp->prox = lista->primeiro->ant = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
}


Personagem* removerFim(ListaDupla* lista) {
   if (lista->primeiro == lista->ultimo) {
      errx(1, "Erro ao remover (vazia)!");
   } 
   Personagem* resp = lista->ultimo->elemento;
   lista->ultimo = lista->ultimo->ant;
   lista->ultimo->prox->ant = NULL;
   free(lista->ultimo->prox);
   lista->ultimo->prox = NULL;
   return resp;
}


int tamanho(ListaDupla* lista) {
   int tamanho = 0; 
   CelulaDupla* i;
   for(i = lista->primeiro; i != lista->ultimo; i = i->prox, tamanho++);
   return tamanho;
}

void inserir(Personagem* x, int pos, ListaDupla* lista) {

   int tam = tamanho(lista);

   if(pos < 0 || pos > tam){
      errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
   } else if (pos == 0){
      inserirInicio(x, lista);
   } else if (pos == tam){
      inserirFim(x, lista);
   } else {
      // Caminhar ate a posicao anterior a insercao
      CelulaDupla* i = lista->primeiro;
      int j;
      for(j = 0; j < pos; j++, i = i->prox);

      CelulaDupla* tmp = novaCelulaDupla(x);
      tmp->ant = i;
      tmp->prox = i->prox;
      tmp->ant->prox = tmp->prox->ant = tmp;
      tmp = i = NULL;
   }
}


Personagem* remover(int pos, ListaDupla* lista) {
   Personagem* resp;
   int tam = tamanho(lista);

   if (lista->primeiro == lista->ultimo){
      errx(1, "Erro ao remover (vazia)!");
   } else if(pos < 0 || pos >= tam){
      errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
   } else if (pos == 0){
      resp = removerInicio(lista);
   } else if (pos == tam - 1){
      resp = removerFim(lista);
   } else {
      // Caminhar ate a posicao anterior a insercao
      CelulaDupla* i = lista-> primeiro->prox;
      int j;
      for(j = 0; j < pos; j++, i = i->prox);

      i->ant->prox = i->prox;
      i->prox->ant = i->ant;
      resp = i->elemento;
      i->prox = i->ant = NULL;
      free(i);
      i = NULL;
   }

   return resp;
}

void mostrar(ListaDupla* lista) {
   CelulaDupla* i;
   for (i = lista->primeiro->prox; i != NULL; i = i->prox) {
      imprimir(i->elemento);
   }
}



int main(){

    char* diretorio = (char*)malloc(sizeof(char)*100);
    scanf("%s", diretorio);
    Personagem *personagem;
    Personagem *removido;
    ListaDupla *lista = start();
    char* texto = (char*)malloc(sizeof(char)*100);   
    char* comando = (char*)malloc(sizeof(char)*100);
    int posicao = 0;
    int posicaoEspaco = 0;
    int quantidade;


    while(isFim(diretorio) == false){
        personagem = new_Personagem();
        setPersonagem(diretorio, personagem);
        inserirFim(personagem, lista);
        scanf("\n%s", diretorio);
    }

    //printf("%d\n", tamanho(lista));

    quicksort(lista);
        quicksort(lista);
    ordenarCordoCabelo(lista);



    mostrar(lista);

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
            peso = 0;
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

    void quicksort(ListaDupla* lista) {
        int tam = tamanho(lista);
        quicksortRec(0, tam-1, lista);
    }
    
    void quicksortRec(int esq, int dir, ListaDupla* lista) {

        int i = esq, j = dir;
        CelulaDupla* esque = lista->primeiro->prox;
        CelulaDupla* direi = lista->primeiro->prox;
        CelulaDupla* pivo = lista->primeiro->prox;

        for(int pos = 0; pos < ((esq+dir)/2); pos++, pivo = pivo->prox);
        for(int pos = 0; pos < esq; pos++, esque = esque->prox);
        for(int pos = 0; pos < dir; pos++, direi = direi->prox);

        while(i <= j){
            while(strcmp(esque->elemento->corDoCabelo, pivo->elemento->corDoCabelo) < 0){
                esque = esque->prox;
                i++;
            }

            while(strcmp(direi->elemento->corDoCabelo, pivo->elemento->corDoCabelo) > 0){
                direi = direi->ant;
                j--;
            }

            if (i <= j) {
                swap(esque, direi);
                i++;
                j--;
                esque = esque->prox;
                direi = direi->ant;
            }
        }
            if (esq < j)  
                quicksortRec(esq, j, lista);
            if (i < dir)  
                quicksortRec(i, dir, lista);
    }

    void swap(CelulaDupla* i, CelulaDupla* j) {
        Personagem* tmp = i->elemento;
        i->elemento = j->elemento;
        j->elemento = tmp;

    }

    void ordenarCordoCabelo(ListaDupla* lista){

        CelulaDupla* temporaria = lista->primeiro->prox;
        CelulaDupla* inicio = lista->primeiro->prox;
        CelulaDupla* pMenor = inicio;
        Personagem* menor;
        Personagem* aux;

        while(inicio->prox != NULL){
            menor = inicio->elemento;
            temporaria = inicio->prox;
            pMenor = inicio;
            while(temporaria->prox != NULL){
                if(strcmp(temporaria->elemento->corDoCabelo, menor->corDoCabelo) == 0 && strcmp(temporaria->elemento->nome, menor->nome) < 0)       {
                 pMenor = temporaria;
                    menor = temporaria->elemento;
                }
                temporaria = temporaria->prox;
            }
            aux = pMenor->elemento;
            pMenor->elemento = inicio->elemento;
            inicio->elemento = aux;

            inicio = inicio->prox;

        }
    }
