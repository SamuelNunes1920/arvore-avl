	/* Universidade Federal do Pará
	 * Instituto de Ciências Exatas e Naturais
	 * Análise e Projetos de Algoritmos
	 * Implementação da Árvore AVL - Código Base
	 * Versão 1.0 - 2016
	 * @Autores: Joahannes Costa <joahannes@ufpa.br>
	 			 Danileno Rosario <
	 			 Ericson Sarmento <
	 			 Allan Costa <
	*/
	import java.util.*;
	public class Main {
		public static void main (String args[]){
			AvlTree tree = new AvlTree();
			Scanner entrada = new Scanner(System.in);
			
			//Insercao
			System.out.print("Numero de elementos que deseja inserir: ");
			int elementos = entrada.nextInt();
			for(int i = 1; i <=elementos;i++){
			  System.out.print(i + "a Chave: ");
			  int chave = entrada.nextInt();
			  tree.insert(chave);
			System.out.println("\nÁrvore montada: \n");		  
			tree.displayTree();
			System.out.print("\n");
			}
			
			//Busca
			System.out.print("*-------- BUSCA DE CHAVE -------------*");
			System.out.print("\nBuscar a chave: ");
			int buscaChave = entrada.nextInt();
			tree.search(buscaChave);

			System.out.print("\nBuscar a chave: ");
			int buscaChave1 = entrada.nextInt();
			tree.search(buscaChave1);
			System.out.println("\nÁrvore montada: \n");		  
			tree.displayTree();
			
			//Remocao
			System.out.print("\n*-------- REMOÇÃO DE CHAVE ----------*");
			System.out.print("\nRemover a chave: ");
			int removeChave = entrada.nextInt();
			tree.remove(removeChave);
			System.out.println("\nÁrvore montada: \n");
			tree.displayTree();

			//Busca
			System.out.print("\n*-------- BUSCA DE CHAVE -------------*");
			System.out.print("\nBuscar a chave: ");
			int buscaChave2 = entrada.nextInt();
			tree.search(buscaChave2);

			//Inserção
			System.out.print("\n*-------- INSERÇÃO DE CHAVE ----------*");
			System.out.print("\nInserir chave: ");
			int chave1 = entrada.nextInt();
			tree.insert(chave1);
			System.out.println("\nÁrvore montada: \n");		  
			tree.displayTree();
			System.out.print("\n");
			
			//BuscaMaior & BuscaMenor		
			System.out.print("*-------- BUSCA MAIOR E MENOR --------*\n");		  
			tree.buscaMaior(tree.root);
			tree.buscaMenor(tree.root);
			System.out.println("\n");
		}
	}
