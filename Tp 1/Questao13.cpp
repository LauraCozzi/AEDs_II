#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define NUMENTRADA 1000
#define TAMLINHA   1000


    bool isFim(char* s){
        return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
    }


    void _alteracaoAleatoria(char* entrada, int letra1, int letra2, int pos){ //função recursiva

    if(entrada[pos] == letra1) 
        entrada[pos] = letra2;
    if(pos > -1) 
        _alteracaoAleatoria(entrada, letra1, letra2, pos-1);

}


    void alteracaoAleatoria(char* entrada, int letra1, int letra2){ 
         
    int tam = 0;

    while(entrada[tam] != '\0') //descobre o tamanho da entrada
        tam++;

    _alteracaoAleatoria(entrada, letra1, letra2, tam);
}





    int main(int argc, char** argv){

        srand(4);
        char entrada[1000];
        fgets(entrada, 1000, stdin);//efetuar a leitura do teclado, conteúdo guardado na entrada de tamanho max 1000
        
        char letra1;
        char letra2;
        while (isFim(entrada) == false){

            letra1 = (char) ((int) 'a' + (rand() % 26)); 
            letra2 = (char) ((int) 'a' + (rand() % 26));

            alteracaoAleatoria(entrada, letra1, letra2);
            
            printf("%s", entrada);

            fgets(entrada, 1000, stdin);
        }
    }
    



    
