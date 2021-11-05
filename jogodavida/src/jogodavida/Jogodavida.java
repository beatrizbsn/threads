package jogodavida;

import java.util.Scanner;

public class Jogodavida {
	
	static public void inicializarMat(int linha, int coluna , int[][] matAtual, int [][]matNova) {
		for (int i = 0 ; i < linha; i++ ) {
			for (int j = 0 ; j < coluna ; j++) {
				matAtual[i][j]=0;
				matNova[i][j]=0;
			}
		}
	}
	
	static public int atualizarMat(int linha, int coluna , int[][] matAtual, int [][]matNova) {
		int soma = 0;
		for (int i = 0 ; i < linha; i++ ) {
			for (int j = 0 ; j < coluna ; j++) {
				matAtual[i][j] = 0;
				matAtual[i][j] = matNova[i][j];
				
				 if (matNova[i][j] == 1){
				  soma = soma + 1;
				 }
				 matNova[i][j]= 0;
			}
		}
		return soma;
	}
	
	
	
	public static void main(String args[]) throws InterruptedException {
		
		// ler linha coluna gerações e threads  
		//Scanner ler = new Scanner(System.in); 
		
		int qtdgeracao = 2000 ;//ler.nextInt();
		int MaxThreads = 8;//ler.nextInt();
		int Tlin = 2048;//ler.nextInt();
		int Tcol =2048;// ler.nextInt();
		 long startTime1 = System.currentTimeMillis();	
		
		
		Thread[] th;
		MinhaThread[] rh;
		rh = new MinhaThread[MaxThreads];
		th = new Thread[MaxThreads];
		
		
		// construir tabuleiro 
		
		int [][] matAtual = new int[Tlin][Tcol];
		int [][] matNova = new int[Tlin][Tcol];
		
		// inicializar com zeros  tabuleiro 
		inicializarMat(Tlin, Tcol,matAtual, matNova);
		
		
		
		//GLIDER
		 
		
		matAtual[1][1]= 1;
		matAtual[2][2]= 1;
		matAtual[3][0]= 1;
		matAtual[3][1]= 1;
		matAtual[3][2]= 1;  
		 
		//R-pentomino
		  
		matAtual[10][31]= 1;
		matAtual[10][32]= 1;
		matAtual[11][30]= 1;
		matAtual[11][31]= 1;
		matAtual[12][31]= 1;
		
		
		// divisão entre as threads 
		
		int linhaT = Tlin / MaxThreads;
		int resto =  Tlin % MaxThreads;
		
		// print condição inicial
		System.out.println("Condicao inicial : 10 ");
		
		int iniciolinha = 0 ;
		int fimlinha = 0;
	
	 long startTime = System.currentTimeMillis();	
	 for (int p = 0 ; p < qtdgeracao ; p++) {
			
			for(int q = 0; q<MaxThreads; q++) {
				iniciolinha = q * linhaT;
				fimlinha = iniciolinha + linhaT;
				if ( q == MaxThreads - 1) {
				rh[q] = new MinhaThread( iniciolinha, fimlinha + resto,Tlin, Tcol ,matAtual, matNova);
				th[q] = new Thread(rh[q]);
				th[q].start();
				}else {
					rh[q] = new MinhaThread(iniciolinha , fimlinha,Tlin, Tcol ,matAtual, matNova);
					th[q] = new Thread(rh[q]);
					th[q].start();	
				}
			}
			
			for(int t = 0; t < MaxThreads; t++) {
				th[t].join();	
			} 
			
		//  atualizar tabuleiro
		int soma = atualizarMat(Tlin, Tcol,matAtual, matNova);
		
		/*for (int i = 0; i < Tlin; i++) {
			for (int j = 0; j < Tcol; j++) {
				System.out.print(matAtual[i][j]+" ");
			}
			System.out.println(" ");
			
		} */
		
		System.out.println("Geração "+ (p+1 ) + ":"+ soma);
			
		}
	 
	 long calcTime = System.currentTimeMillis() - startTime;
	 System.out.println("Em " + calcTime + "ms.\n");
	 
	 long calcTime1 = System.currentTimeMillis() - startTime1;
	 System.out.println("Em " + calcTime1 + "ms.\n");
	}

}
