package avl;

import avl.ArvoreAvl;
import java.util.*;
public class TestaArvoreAVL {
	public static void main(String[] args) {
		ArvoreAvl tree = new ArvoreAvl(); //nova arvore
		Scanner entrada = new Scanner(System.in);
		//Teste de insercao e rotacoes da arvore AVL
		System.out.print("Numero de elementos que deseja inserir: ");
		int numero = entrada.nextInt();
		for(int i = 1; i <=numero;i++){
		  System.out.print(i + "° Elemento: ");
		  int valor = entrada.nextInt();
		  tree.inserirElemento(valor);
		  System.out.println("Arvore montada: " +  tree.exibirArvorePreOrdem());
		  //System.out.println("Exibindo arvore em pre ordem: " + tree.exibirArvorePreOrdem());
		}
		
		
		/*
		tree.inserirElemento(numero);
		tree.inserirElemento(7);
		tree.inserirElemento(11);
		System.out.println("\nExibindo balanco da arvore em pre ordem " + tree.balanco());
		tree.inserirElemento(3);
		//System.out.println("\ninserindo elemento repetido(7)...\n");
		//tree.inserirElemento(7);
		System.out.println("Exibindo arvore em pre ordem " + tree.exibirArvorePreOrdem());
		//teste de impressao do balanco de cada no da arvore
		System.out.println("\nExibindo balanco da arvore em pre ordem " + tree.balanco());
		//tree.inserirElemento(11);
		//tree.inserirElemento(3);
		//System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());
		tree.inserirElemento(8);
		System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());
		tree.inserirElemento(10);
		System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());
		tree.inserirElemento(15);
		System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());
		System.out.println("Exibindo arvore em pre ordem " + tree.exibirArvorePreOrdem());
		
		//teste de busca
		System.out.println("Buscando o 15...");
		if(tree.buscarElemento(15).info == 15){System.out.println("Achou o 15");}
		else {System.out.println("Nao achou");}
		System.out.println("Exibindo arvore em pre ordem " + tree.exibirArvorePreOrdem());
		//tree.inserirElemento(15);
		tree.inserirElemento(18);
		System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());
		
		//Teste de Remocao
		System.out.println("Removendo o 18");//no sem filhos
		tree.removerElemento(18);
		System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());
		System.out.println("Removendo o 5");//no com um filho (desbalanceamento da raiz)
		tree.removerElemento(5);
		System.out.println("Removendo o 10");
		tree.removerElemento(10);
		
		//insercao de novos elementos
		System.out.println("\nInserindo novos elementos");
		tree.inserirElemento(2);
		//tree.inserirElemento(30);
		tree.inserirElemento(20);
		tree.inserirElemento(14);
		System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());
		tree.inserirElemento(58);
		System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());
		tree.inserirElemento(16);
		System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());
		//tree.inserirElemento(1);

		tree.inserirElemento(4);
		tree.inserirElemento(9);
		tree.inserirElemento(10);
		tree.inserirElemento(12);

		System.out.println("Exibindo balanco da arvore em pre ordem " + tree.balanco());

		//System.out.println("\nRemovendo todos os elementos...");
		//tree.removerTodosElementos();
		
		//Teste da arvore AVL com JOptionPane
		//ArvoreAvl tree2 = new ArvoreAvl(); //nova arvore
		//tree2.start();
		*/

	}

}//;~
