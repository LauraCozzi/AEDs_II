#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


#define NUMENTRADA 1000
#define TAMLINHA   1000

bool isFim (char *s);
bool isPalindromo (char* s, int esquerda, int direita);

    bool isFim(char* s){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
    }


    bool isPalindromo (char* s, int esquerda, int direita){


        bool palindromo = true;

        if(esquerda >= direita){
            palindromo = true;
        } else {
            if(s[esquerda] == s[direita])
                palindromo = true && isPalindromo(s, ++esquerda, --direita);
            else
                palindromo = false;
        }

        
        return palindromo;
    }



    int main(int argc, char** argv){
        char entrada[NUMENTRADA][TAMLINHA];
        int numEntrada = 0;

        //Leitura da entrada padrao
        do {
            fgets(entrada[numEntrada], TAMLINHA, stdin);
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

        //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
        for(int i = 0; i < numEntrada; i++){
             if(isPalindromo(entrada[i], 0, strlen(entrada[i]) - 2))
            printf("SIM\n");
            else
            printf("NAO\n");
        }
    }






















