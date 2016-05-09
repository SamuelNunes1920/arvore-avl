package avl;

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
		  System.out.print(i + "° Elemento: ");
		  int chave = entrada.nextInt();
		  tree.insert(chave);
		System.out.println("\nÁrvore montada: \n");		  
		tree.displayTree();
		System.out.print("\n");
		}

		//Busca
		System.out.print("\n*---------- BUSCA DE CHAVE ----------*");
		System.out.print("\nBuscar a chave: ");
		int buscaChave = entrada.nextInt();
		tree.search(buscaChave);

		System.out.print("\nBuscar a chave: ");
		int buscaChave1 = entrada.nextInt();
		tree.search(buscaChave1);
		
		//Remocao
		System.out.print("\n*-------- REMOVENDO CHAVE --------*\n");
		System.out.print("\nRemover a chave: ");
		int removeChave = entrada.nextInt();
		tree.remove(removeChave);
		tree.displayTree();
	}
}
