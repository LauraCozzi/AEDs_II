#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


#define NUMENTRADA 1000
#define TAMLINHA   1000

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
}personagem;

bool isFim(char* s){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
    }

    char* string_substring(char str[], int start, int end) {

    int i, j;
    char *sub; 
     
    // Verifica valores incompatíveis e 
    // retorna NULL
    if(start >= end || end > strlen(str)) {
        return NULL;
    }
     
    // Aloca memória para a substring
    sub = (char *) malloc(sizeof(char) * (end - start + 1));
     
    // Copia a substring para a variável
    for(i = start, j = 0; i < end; i++, j++) {
        sub[j] = str[i];
    }
     
    // Terminador de string
    sub[j] = '\0';
     
    return sub;
}

 void imprimirPersonagem(personagem * personagens){

    printf(" ## %s", personagens->nome);
    printf(" ## %d", personagens->altura);
    printf(" ## %g", personagens->peso);
    printf(" ## %s", personagens->corDoCabelo);
    printf(" ## %s", personagens->corDaPele);
    printf(" ## %s", personagens->corDosOlhos);
    printf(" ## %s", personagens->anoNascimento);
    printf(" ## %s", personagens->genero);
    printf(" ## %s ##", personagens->homeworld);


 }        


    void tratarNovaEntrada(char entrada[], personagem* personagens){

    char* nome1 = (char*)malloc(sizeof(char)*100);
    char* altura1 = (char*)malloc(sizeof(char)*100);
    char* peso1 = (char*)malloc(sizeof(char)*100);
    char* corDoCabelo1 = (char*)malloc(sizeof(char)*100);
    char* corDaPele1 = (char*)malloc(sizeof(char)*100);
    char* corDosOlhos1 = (char*)malloc(sizeof(char)*100);
    char* anoNascimento1 = (char*)malloc(sizeof(char)*100);
    char* genero1 = (char*)malloc(sizeof(char)*100);
    char* homeworld1 = (char*)malloc(sizeof(char)*100);

    int posicaoSplit[8];
    int posicao2pontos[9];
    int j = 0;

        for(int K = 0; K < strlen(entrada); K++){
            if(entrada[K] == '\'' && entrada[K+1] == ','){
                posicaoSplit[j] = K;
                j++;
            }
        }

        j = 0;

        for(int i = 0; i < strlen(entrada); i++){
            if(entrada[i] == ':' && entrada[i+1] == ' '){
                posicao2pontos[j] = i;
                j++;
            }
        }


        nome1 = string_substring(entrada, 1, posicaoSplit[0]);
        nome1 = string_substring(entrada, posicao2pontos[0]+3, strlen(nome1)+1);


        altura1 = string_substring(entrada, posicaoSplit[0]+3, posicaoSplit[1]);
        altura1 = string_substring(entrada, posicao2pontos[1]+3, posicaoSplit[1]);

        peso1 = string_substring(entrada, posicaoSplit[1]+3, posicaoSplit[2]);
        peso1 = string_substring(entrada, posicao2pontos[2]+3, posicaoSplit[2]);

        corDoCabelo1 = string_substring(entrada, posicaoSplit[2]+3, posicaoSplit[3]);
        corDoCabelo1 = string_substring(entrada, posicao2pontos[3]+3, posicaoSplit[3]);

        corDaPele1 = string_substring(entrada, posicaoSplit[3]+3, posicaoSplit[4]);
        corDaPele1 = string_substring(entrada, posicao2pontos[4]+3, posicaoSplit[4]);

        corDosOlhos1 = string_substring(entrada, posicaoSplit[4]+3, posicaoSplit[5]);
        corDosOlhos1 = string_substring(entrada, posicao2pontos[5]+3, posicaoSplit[5]);

        anoNascimento1 = string_substring(entrada, posicaoSplit[5]+3, posicaoSplit[6]);
        anoNascimento1 = string_substring(entrada, posicao2pontos[6]+3, posicaoSplit[6]);

        genero1 = string_substring(entrada, posicaoSplit[6]+3, posicaoSplit[7]);
        genero1 = string_substring(entrada, posicao2pontos[7]+3, posicaoSplit[7]);

        homeworld1 = string_substring(entrada, posicaoSplit[7]+3, strlen(entrada));
        homeworld1 = string_substring(entrada, posicao2pontos[8]+3, strlen(entrada)-1);



    int altura2 = atoi(altura1);


        double peso2;

        if(strcmp(peso1, "unkown") == 0){
            peso1 = "0";
        }

        if(strcmp(peso1, "1,358") == 0){
            peso1 = "1358";
        }


        peso2 = atof(peso1);

        personagens->nome = nome1;
        personagens->altura = altura2;
        personagens->peso = peso2;
        personagens->corDoCabelo = corDoCabelo1;
        personagens->corDaPele = corDaPele1;
        personagens->corDosOlhos = corDosOlhos1;
        personagens->anoNascimento = anoNascimento1;
        personagens->genero = genero1;
        personagens->homeworld = homeworld1;

        imprimirPersonagem(personagens);




}

    void cortarString(char* entrada, personagem *personagens){

    char novaEntrada[1000];
    int posicaoF;

    for(int i = 0; i < strlen(entrada); i++){
        if(entrada[i] == 'f' && entrada[i+1] == 'i' && entrada[i+2] == 'l' && entrada[i+3] == 'm' && entrada[i+4] == 's' && entrada[i+5] == '\'')
            posicaoF = i;
    }
    
        strncpy(novaEntrada, entrada, posicaoF);
        novaEntrada[posicaoF-3] = '\0'; //elimina os 3 ultimos char
    

    tratarNovaEntrada(novaEntrada, personagens);

}


int main(int argc, char** argv){

        char* diretorio = (char*)malloc(sizeof(char)*100);
        char* texto = (char*)malloc(sizeof(char)*1000);  
        personagem *personagens = (personagem*) malloc(sizeof(personagem));
 

    do{
        personagem *personagens = (personagem*) malloc(sizeof(personagem));
        diretorio = (char*)malloc(sizeof(char)*100);
        texto = (char*)malloc(sizeof(char)*1000);   
        scanf("%s", diretorio);
            if(isFim(diretorio) == false){
                FILE* arq=fopen(diretorio, "r");
                fgets(texto,10000,arq);
                texto[strlen(texto)-1]='\0';
                cortarString(texto, personagens);
                printf("\n");
                fflush(stdin);
                free(personagens);

            }
        } while (isFim(diretorio) == false);


    }

