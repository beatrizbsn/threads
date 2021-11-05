package HighLife;


	 public class MThread  implements Runnable {
		
		private int fimlinha;
		private int iniciolinha;
		private int linha;
		private int coluna;
		private int[][] matAtual;
		private int[][] matNova;
		

		public MThread (int iniciolinha , int fimlinha ,int linha, int coluna, int[][] matAtual, int[][] matNova) {
		this.fimlinha =  fimlinha;
		this.iniciolinha =  iniciolinha;
		this.linha =  linha;
		this.coluna = coluna;
		this.matAtual = matAtual;
		this.matNova = matNova;
		}
		
		
		
		public int qtdVizinhos (int l , int c){
			  int soma = 0 , i ,j, aux1, aux2;
			  
			  for (i = l - 1 ; i < l + 2 ;  i++){
			    for (j = c - 1; j < c + 2 ; j++){
			      if ( (i != l) || (j != c)){
			         aux1 = i;
			         aux2 = j;
			        if ( i < 0)     aux1 = linha - 1;
			        if ( i == linha) aux1 = 0 ;
			        if (j < 0 )     aux2 = coluna - 1;
			        if (j == coluna)  aux2 = 0;
			        soma = soma + matAtual[aux1][aux2];
			      }
			    }
			  }
			return soma ; 
			}
		
		
		public void nGeracao(int i , int j) {
			int qdtV = 0 ;
			  qdtV = qtdVizinhos(i, j);
			  // vivas
			  if (matAtual[i][j] == 1){
			    if (qdtV == 2 || qdtV == 3) {
			    	matNova[i][j] = 1 ;
			    }else{
			    	matNova[i][j] = 0 ;
			    }
			  }else { // morta
			    if (qdtV == 3 || qdtV == 6 ) {
			    	matNova[i][j]= 1;}
			  }
		}
		
		
		@Override
		public void run() {
			
			for (int i = iniciolinha ; i < fimlinha ;i++) {
				for (int j = 0; j < coluna ; j++) {
					nGeracao(i ,j);
				}
			}
			/*for (int i = 0; i < linha; i++) {
				for (int j = 0; j < coluna; j++) {
					System.out.print(matAtual[i][j]+" ");
				}
				System.out.println(" ");*/
				
			
		}

	}
