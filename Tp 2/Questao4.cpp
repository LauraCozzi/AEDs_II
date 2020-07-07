#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

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

typedef struct {
    Personagem* array[MAXTAM];   // Elementos da pilha 
    int n;       
} Lista;  


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
void start(Lista * lista);
void inserirInicio(Personagem * x, Lista * lista);
void inserirFim(Personagem * x, Lista * lista);
void inserir(Personagem * x, int pos, Lista * lista);
Personagem* removerInicio(Lista * lista);
Personagem* removerFim(Lista * lista);
Personagem* remover(int pos, Lista * lista);
void mostrar (Lista *lista);
bool pesquisar(Personagem * x, Lista * lista);
Personagem* clone(Personagem* x);



int main(){

    char* diretorio = (char*)malloc(sizeof(char)*100);
    scanf("%s", diretorio);
    Personagem *personagem;
    Personagem *removido;
    Lista *lista = (Lista*)malloc(sizeof(Lista));
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

        if(strcmp(comando, "II") == 0){
            endereco = pegandoEndereco(comando, texto, posicaoEspaco);
            setPersonagem(endereco, personagem);
            inserirInicio(personagem, lista);
        } else if(strcmp(comando, "R*") == 0){
            posicao = posicaoDoComando(texto, posicaoEspaco);
            removido = remover(posicao, lista);
            printf("(R) %s\n", removido->nome);
        }  else if(strcmp(comando, "I*") == 0){
            posicao = posicaoDoComando(texto, posicaoEspaco);
            endereco = pegandoEndereco(comando, texto, 5);
            setPersonagem(endereco, personagem);
            inserir(personagem, posicao, lista);
        } else if(strcmp(comando, "RI") == 0){
            removido = removerInicio(lista);
            printf("(R) %s\n", removido->nome);
        } else if(strcmp(comando, "IF") == 0){
            endereco = pegandoEndereco(comando, texto, posicaoEspaco);
            setPersonagem(endereco, personagem);
            inserirFim(personagem, lista);
        } else if(strcmp(comando, "RF") == 0){
            removido = removerFim(lista);
            printf("(R) %s\n", removido->nome);
        }


        fflush(stdin);
    }

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
void start(Lista * lista){
    lista->n = 0;
}


/**
 * Insere um elemento na primeira posicao da lista e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
 */
void inserirInicio(Personagem * x, Lista * lista) {
    int i;

    //validar insercao
    if(lista->n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    } 

    //levar elementos para o fim do array
    for(i = lista->n; i > 0; i--){
        lista->array[i] = clone(lista->array[i-1]);
    }
    

    lista->array[0] = clone(x);
    lista->n++;
}


/**
 * Insere um elemento na ultima posicao da 
 * @param x int elemento a ser inserido.
 */
void inserirFim(Personagem * x, Lista * lista) {

    //validar insercao
    if(lista->n >= MAXTAM){
        printf("Erro ao inserir!");
        exit(1);
    }

    lista->array[lista->n] = clone(x);
    lista->n++;
}


/**
 * Insere um elemento em uma posicao especifica e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
 * @param pos Posicao de insercao.
 */
void inserir(Personagem * x, int pos, Lista * lista) {
    int i;

    //validar insercao
    if(lista->n >= MAXTAM || pos < 0 || pos > lista->n){
        printf("Erro ao inserir!");
        exit(1);
    }

    //levar elementos para o fim do array
    for(i = lista->n; i > pos; i--){
        lista->array[i] = clone(lista->array[i-1]);}
    

    lista->array[pos] = clone(x);
    lista->n++;
}


/**
 * Remove um elemento da primeira posicao da lista e movimenta 
 * os demais elementos para o inicio da mesma.
 * @return resp int elemento a ser removido.
 */
Personagem* removerInicio(Lista * lista) {

    int i;
    Personagem *resp = (Personagem*)malloc(sizeof(Personagem));

    //validar remocao
    if (lista->n == 0) {
        printf("Erro ao remover!");
        exit(1);
    }

    resp = clone(lista->array[0]);
    lista->n--;

    for(i = 0; i < lista->n; i++)
        lista->array[i] = clone(lista->array[i+1]);
    

    return resp;
}


/**
 * Remove um elemento da ultima posicao da 
 * @return resp int elemento a ser removido.
 */
Personagem* removerFim(Lista * lista) {

    //validar remocao
    if (lista->n == 0) {
        printf("Erro ao remover!");
        exit(1);
    }

    return lista->array[--lista->n];
}


/**
 * Remove um elemento de uma posicao especifica da lista e 
 * movimenta os demais elementos para o inicio da mesma.
 * @param pos Posicao de remocao.
 * @return resp int elemento a ser removido.
 */
Personagem* remover(int pos, Lista * lista) {
    int i;
    Personagem *resp = (Personagem*)malloc(sizeof(Personagem));

    //validar remocao
    if (lista->n == 0 || pos < 0 || pos >= lista->n) {
        printf("Erro ao remover!");
        exit(1);
    }

    resp = clone(lista->array[pos]);
    lista->n--;

    for(i = pos; i < lista->n; i++)
        lista->array[i] = clone(lista->array[i+1]);
    

    return resp;
}


/**
 * Mostra os array separados por espacos.
 */
void mostrar (Lista *lista){
    int i;

// lista->array[i]
    for(i = 0; i < lista->n; i++){
        printf("[%d]", i);
        imprimir(lista->array[i]);
    }
    

}


/**
 * Procura um array e retorna se ele existe.
 * @param x int elemento a ser pesquisado.
 * @return &lt;code&gt;true&lt;/code&gt; se o array existir,
 * &lt;code&gt;false&lt;/code&gt; em caso contrario.
 */
bool pesquisar(Personagem * x, Lista * lista) {
    bool retorno = false;
    int i;

        for (int i = 0; i < lista->n && retorno == false; i++) 
            retorno = (lista->array[i] == x);
    
    return retorno;
}


Personagem* clone(Personagem* x){

    Personagem* tmp;
    tmp = x;

    return tmp;
}