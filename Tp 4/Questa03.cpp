#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <err.h>
#include <math.h>


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

typedef struct No {
	Personagem* elemento;       
	struct No* esq; 
	struct No* dir;
	int nivel;
} No;

typedef struct AVL
{

	No* raiz;

}AVL;


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

//Arvore AVL
int max(int num1, int num2);
AVL* new_AVL();
No* new_No(Personagem* elemento);
int getNivel(No* no);
No* setNivel(No* no);
bool pesquisar(char* x,AVL* a);
bool pesquisarRec(char* x, No* i);
void inserir(Personagem* x,AVL* a);
No* inserirRec(No* no, Personagem* x);
No* rotacionarDir(No* no);
No* rotacionarEsq(No* no);
No* balancear(No* no);





int main(){

    char* diretorio = (char*)malloc(sizeof(char)*100);
    scanf("%s", diretorio);
    Personagem *personagem;
    Personagem *removido;
    char* texto = (char*)malloc(sizeof(char)*100);   
    char* comando = (char*)malloc(sizeof(char)*100);
    int posicao = 0;
    int posicaoEspaco = 0;
    int quantidade;
    AVL* a = new_AVL();
    char* nome = (char*)malloc(sizeof(char)*100);
    bool resp = false;



    while(isFim(diretorio) == false){
        personagem = new_Personagem();
        setPersonagem(diretorio, personagem);
        inserir(personagem,a);
        scanf("\n%s", diretorio);
    }

        scanf("\n");
        fgets(nome, 1000, stdin);
        nome[strlen(nome) - 1] = '\0';
        
        while (isFim(nome) == false){
            printf("%s ", nome);
            resp = pesquisar(nome, a);   
            if(resp == true)printf("SIM\n");
            else printf("NÃO\n");
            fgets(nome, 1000, stdin);
            nome[strlen(nome) - 1] = '\0';
    }
        




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

AVL* new_AVL()
{
	AVL* novo = (AVL*)malloc(sizeof(AVL));
    novo->raiz = NULL;
	return novo;
}

No* new_No(Personagem* elemento) {
   No* nova = (No*) malloc(sizeof(No));
   nova->elemento = clone(elemento);
   nova->dir = NULL;
   nova->esq = NULL;
   nova->nivel = 1;
   return nova;
}

No* setNivel(No* no) {
   no->nivel = 1 + max(getNivel(no->esq),getNivel(no->dir));
   return no;
}

int getNivel(No* no) {
   return (no == NULL) ? 0 : no->nivel;
}

bool pesquisar(char* x,AVL* a) {
    printf("raiz ");
	return pesquisarRec(x, a->raiz);
}

bool pesquisarRec(char* x, No* i) {
	bool resp;
    if (i == NULL) {
       resp = false;
    } else if (strcmp(x,i->elemento->nome) == 0 ) {
       resp = true;
    } else if (strcmp(x,i->elemento->nome) < 0) {
		 printf("esq ");
         resp = pesquisarRec(x, i->esq);
    } else{
		 printf("dir ");
         resp = pesquisarRec(x, i->dir);
    }
    return resp;
}

void inserir(Personagem* x,AVL* a) {
	a->raiz = inserirRec(a->raiz, x);
}

No* inserirRec(No* no, Personagem* x) {
	if (no == NULL) { 
        no = new_No(x); 
      
    } else if (strcmp(x->nome,no->elemento->nome) < 0) { 
        no->esq = inserirRec(no->esq, x); 

    } else if (strcmp(x->nome,no->elemento->nome) > 0) { 
        no->dir = inserirRec(no->dir, x); 
      
    } else { 
    	printf("Erro ao inserir elemento!\n");
		exit(0); 
    }

    no = balancear(no);
	return no;
}

int max(int num1, int num2)
{
    return (num1 > num2 ) ? num1 : num2;
}


No* balancear(No* no) {
	if(no != NULL){
    int fator = getNivel(no->dir) - getNivel(no->esq);

    //Se balanceada
    if (abs(fator) <= 1){
       no = setNivel(no);

    //Se desbalanceada para a direita
    }else if (fator == 2){

    	int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);

        //Se o filho a direita tambem estiver desbalanceado
        if (fatorFilhoDir == -1) {
   	        no->dir = rotacionarDir(no->dir);
        }
        no = rotacionarEsq(no);

        //Se desbalanceada para a esquerda
        }else if (fator == -2){

            int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);

            //Se o filho a esquerda tambem estiver desbalanceado
            if (fatorFilhoEsq == 1) {
               no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);

         }else{
            printf("Erro fator de balanceamento, fator invalido!\n"); 
			exit(0);
         }
      }

  	return no;
}
No* rotacionarDir(No* no) {
	No* noEsq = no->esq;
    No* noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;

    no = setNivel(no);
    noEsq = setNivel(noEsq);

    return noEsq;
}

No* rotacionarEsq(No* no) {
    No* noDir = no->dir;
    No* noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    no = setNivel(no);
    noDir = setNivel(noDir);

    return noDir;
}

