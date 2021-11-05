#include <stdio.h>
#include <stdlib.h>
#include <time.h>


typedef struct {
  int geracaoAtual;
  int geracaoNova;
} Tabuleiro;

void inicializarTab (Tabuleiro **tabuleiro ,  int Tcol, int Tlin){
 int i, j ;

  for (i = 0; i < Tlin ; i ++){
    for (j = 0 ; j < Tcol ; j++){

      tabuleiro[i][j].geracaoAtual = 0;
      tabuleiro[i][j].geracaoNova  = 0;

    }
  }
}

int  atualizarTab (Tabuleiro **tabuleiro,int Tcol, int Tlin){
 int i,j,soma = 0;
   for (i = 0; i < Tlin ; i++){
    for (j = 0 ; j < Tcol ; j++){
      // mostrar tabuleiro novo
       //printf ("%d ",tabuleiro[i][j].geracaoNova);
      // atualizar ataual para novo
      tabuleiro[i][j].geracaoAtual  = 0;
      tabuleiro[i][j].geracaoAtual = tabuleiro[i][j].geracaoNova;

      if (tabuleiro[i][j].geracaoNova == 1){
        soma = soma + 1;
      }
      tabuleiro[i][j].geracaoNova  = 0;
    }
     //printf ("\n");
  }
return soma;
}

int qtdVizinhos (Tabuleiro **tabuleiro,int l , int c ,int Tcol, int Tlin){
  int soma = 0 , i ,j, aux1, aux2;

  for (i = l - 1 ; i < l + 2 ;  i++){
    for (j = c - 1; j < c + 2 ; j++){
      if ( (i != l) || (j != c)){
         aux1 = i;
         aux2 = j;
        if ( i < 0)     aux1 = Tlin - 1;
        if ( i == Tlin) aux1 = 0 ;
        if (j < 0 )     aux2 = Tcol - 1;
        if (j == Tcol)  aux2 = 0;
        soma = soma + tabuleiro[aux1][aux2].geracaoAtual;
      }
    }
  }
return soma ;
}

void  nGeracao(Tabuleiro **tabuleiro,int i , int j,int Tcol, int Tlin){
  int qdtV = 0 ;
  qdtV = qtdVizinhos(tabuleiro,i, j,Tcol,Tlin);
  // vivas
  if (tabuleiro[i][j].geracaoAtual == 1){
    if (qdtV == 2 || qdtV == 3) {
    tabuleiro[i][j].geracaoNova = 1;
    }else{
    tabuleiro[i][j].geracaoNova = 0;
    }
  }else { // morta
    if (qdtV == 3) {tabuleiro[i][j].geracaoNova = 1;}
  }

}


int main(void) {
  Tabuleiro **tabuleiro;
  int i, j,t, linha =2048 , coluna=2048,qtdgeracao=2000,somageracao;
  // leitura da quantidade de gera��es
  /*scanf("%d", &qtdgeracao);
  scanf("%d", &coluna);
  scanf("%d", &linha);*/



  //

  clock_t tempo;


  // aloca��o do tabuleiro

  tabuleiro =(Tabuleiro**) malloc(coluna * sizeof(Tabuleiro*));
  for (i=0 ; i < coluna ; i ++){
      tabuleiro[i] =(Tabuleiro*) malloc(linha * sizeof(Tabuleiro));
  }



  // inicializar tabuleiro
  inicializarTab (tabuleiro,coluna,linha);

  //GLIDER

  tabuleiro[1][1].geracaoAtual  = 1;
  tabuleiro[2][2].geracaoAtual  = 1;
  tabuleiro[3][0].geracaoAtual  = 1;
  tabuleiro[3][1].geracaoAtual  = 1;
  tabuleiro[3][2].geracaoAtual  = 1;

  //R-pentomino

  tabuleiro[10][31].geracaoAtual = 1;
  tabuleiro[10][32].geracaoAtual = 1;
  tabuleiro[11][30].geracaoAtual = 1;
  tabuleiro[11][31].geracaoAtual = 1;
  tabuleiro[12][31].geracaoAtual = 1;




printf ("Condi��o inicial 10 \n");

tempo = clock();
for (t = 0 ; t < qtdgeracao; t++ ){
  for (i = 0; i < linha ; i++){
    for (j = 0 ; j < coluna ; j++){
      nGeracao(tabuleiro,i,j,coluna,linha);
    }
  }
  // atualizar tabuleiro e estado do tabuleiro


  somageracao = atualizarTab (tabuleiro,coluna,linha);
  //printf ("Gera��o %d : ",t+1);
 // printf ("%d\n",somageracao);

}
printf ("somageracao: %d\n",somageracao);
tempo = clock() - tempo;
printf ("Tempo %f : s ",(tempo)/(double)CLOCKS_PER_SEC);

  return 0;
}
