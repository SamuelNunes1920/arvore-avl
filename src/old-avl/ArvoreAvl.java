package avl;

//-------------------------------------------------------------------------------------//

/* ArvoreAvl.java
 * Arvore AVL
 * Objetivo: Implementacao de uma estrutura de dados de uma arvore balanceada
 * com metodos de exemplificacao e testes de execucao
 * Desenvolvedor : Raul Joaquim Camara dos Santos
 * Local: Dimap/UFRN - Natal/RN -Brasil
 * Data: 01/04/2008
 * Ultima Modificacao: 04/04/2008
 */
//-------------------------------------------------------------------------------------//

public class ArvoreAvl extends ArvoreBinaria{
//************ATRIBUTOS***************/
	private boolean t; //controlador do balanceamento da arvore
	private int local; //localiza se o pai do no removido vai ter um novo
	  				   //filho a direita ou a esquerda
//**************METODOS***************/
	//construtor padrao
	public ArvoreAvl() {
		super();
		
	}
/*========================================================================================*/
	//Metodo de busca de um elemento com chave x na arvore
	public Node buscarElemento(int x){
		return super.buscarElemento(x);
	}
	/*========================================================================================*/
	private void caso1(Node p,boolean h){
		Node ptu = new Node();
		boolean testeraiz = false; //testa se o no e a raiz
		
		if (p == raiz) testeraiz = true;
		ptu = p.esq;
		if (ptu.balanco == -1){ 	  //rotacao direita
			p.esq = ptu.dir;  //seu filho a esquerda sera o filho a esquerda de seu filho
			if (ptu.dir != null){ //so entra se o filho a esquerda existir
				ptu.dir.pai = p;  //novo pai
			}
			if (p !=raiz){ p.pai.esq = ptu; }	  //o pai de p tera o filho a direita de p como seu novo filho a direita
			ptu.pai = p.pai;
			ptu.dir = p;
			p.pai = ptu;
			p.balanco = 0;
			p = ptu;
			if (testeraiz) raiz = ptu;
			System.out.println("Rotação para direita!");
		}
		else{ //rotacao dupla direita
			Node ptv = new Node();
			ptv = ptu.dir;
			ptu.dir = ptv.esq;
			if (ptv.esq != null){//so entra se o filho a esquerda existir
				ptv.esq.pai= ptu;
			}
			ptv.esq = ptu;
			ptu.pai = ptv;
			p.esq = ptv.dir;
			if (ptv.dir !=null){
			ptv.dir.pai = p;
			}
			ptv.dir = p;
			if (p.pai !=null){p.pai.dir = ptv;} //o pai de p tera ptv como seu novo filho a direita
			ptv.pai = p.pai;
			p.pai =ptv;
			if (ptv.balanco == -1)p.balanco = 1;
			else p.balanco = 0;
			if (ptv.balanco == 1) ptu.balanco = -1;
			else ptu.balanco = 0;
			if (testeraiz)raiz = ptv; //se e a raiz
			p = ptv;
			System.out.println("Rotação dupla para direita!");
		}
		p.balanco = 0;
		t = false;
		
	}
/*========================================================================================*/
	/*Metodo que executa a rotacao esquerda ou 
	a rotacao dupla esquerda para balancear a Arvore*/
	private void caso2(Node p,boolean h){
		Node ptu = new Node();
		boolean testeraiz = false;
		
		if (p == raiz) testeraiz = true;
		ptu = p.dir;
		if (ptu.balanco == 1){ 	  //rotacao esquerda
			p.dir = ptu.esq;  //seu filho a direita sera o filho a esquerda de seu filho
			if (ptu.esq != null){ //so entra se o filho a esquerda existir
				ptu.esq.pai = p;  //novo pai
			}
			if (p !=raiz) {p.pai.dir = ptu;}  //o pai de p tera o filho a direita de p como seu novo filho a direita
			ptu.pai = p.pai;
			ptu.esq = p;
			p.pai = ptu;
			p.balanco = 0;
			p = ptu;
			if (testeraiz) raiz = ptu;
			System.out.println("Rotação para esquerda!");
		}
		else{ //rotacao dupla esquerda
			Node ptv = new Node();
			ptv = ptu.esq;
			ptu.esq = ptv.dir;
			if (ptv.dir !=null){	//so entra se o filho a direita existir
				ptv.dir.pai= ptu;
			}
			ptv.dir = ptu;
			ptu.pai = ptv;
			p.dir = ptv.esq;
			if (ptv.esq !=null){
				ptv.esq.pai = p;
			}
			ptv.esq = p;
			if (p.pai !=null){p.pai.esq = ptv;} //o pai de p tera ptv como seu novo filho a esq
			p.pai =ptv;
			if (ptv.balanco == 1)p.balanco = -1;
			else p.balanco = 0;
			if (ptv.balanco == -1) ptu.balanco = 1;
			else ptu.balanco = 0;
			if (testeraiz)raiz = ptv;
			p = ptv;
			System.out.println("Rotação dupla para esquerda!");
		}
		p.balanco = 0;
		t = false;
	}
	
/*========================================================================================*/
	//Metodo de insercao de um elemento com chave x na arvore
	/*Este metodo faz a busca sem usar a motodo buscarElemento() pois a organizacao
	 * e o balanceamento sao testados e organizados durante a busca e insercao num mesmo momento.
	 * para que todo o processo seja efetuado em O(log(n)) passos. Algoritmo demonstrado no livro: 
	 * SZWARCFITER,Jaime Luiz.Estruturas de Dados e seus Algoritmos.(1994).LTC, 2 Ed.*/
	public boolean inserirElemento(int x){
		pt = new Node(x);
		if (raiz == null){			
			raiz = pt;}//se arvore vazia, insere na raiz
		else{
			pt = raiz; //pt recebe raiz (para percurso na arvore)
			insAVL(x,pt,false);
		}
		//System.out.println(exibirArvorePreOrdem());
		return true;
	}
	//Metodo privado recursivo que auxilia o metodo inserirElemento() para insercao e balanceamnto
	private boolean insAVL(int x,Node pt,boolean h){
		t = h; 
		if (pt == null){//No alocado no espaco vazio
			pt = new Node(x);
			if(pos==1){aux.esq= pt;} 
			else if (pos == 2){aux.dir = pt;}
			pt.pai = aux;//pai do no
			t=true;
			pos=0;
		}
		else{
			if (x == pt.info){ //ja existe element na arvore
				pt = null;
				System.out.println("Elemento já presente na Árvore!");
				return false;
			}
			if (x < pt.info){//chave menor que o no
				aux = pt;	 //aux sera o pai do no inserido
				pos = 1;	 // filho a esquerda de aux
				insAVL(x,pt.esq,t);
				//depois da insercao, a verificacao
				if (t){
					if (pt.balanco == 1){
						pt.balanco = 0;
						t = false;
					}
					else if (pt.balanco == 0) pt.balanco = -1;
					else if (pt.balanco == -1) caso1(pt,t); //rebalanceamento (rotacao direita)
				}
			}
			else{//chave maior que o no
					aux = pt; //aux sera o pai do no inserido
					pos = 2;  // filho a direita de aux
					insAVL(x,pt.dir,t);
				
					//depois da insercao, a verificacao
				if (t){
					
					if (pt.balanco == -1){
						pt.balanco = 0;
						t = false;
					}
					else if (pt.balanco == 0) pt.balanco = 1;
					else if (pt.balanco == 1) caso2(pt,t); //rebalanceamento (rotacao esquerda)
				}
			}
		}
		return true;
	}
	
	
/*========================================================================================*/	
	//Metodo de remocao de um elemento com chave x na arvore
	public boolean removerElemento(int x){
		if (raiz == null){return false;}//arvore vazia
		else{
			pt = raiz;//no de percurso
			remAVL(x,pt,false);
		}
		//System.out.println(exibirArvorePreOrdem());
		return true;
	}
	//Metodo auxiliar para remocao do no x e balanceamento da arvore
	public boolean remAVL(int x,Node pt,boolean h){
		t=h;

		if (pt == null){return false;}//nao achou
		else{
			if (x == pt.info ){//achou
				//*******REMOCAO*******/
				if (pt.esq == null && pt.dir == null){//no sem filhos
					if (pt == raiz) {raiz = null;}
					else{
						if (pos == 1)//este no e' o filho da esquerda do seu pai
							pt.pai.esq = null;
						else if(pos == 2)//este no e' o filho da direita do seu pai
							pt.pai.dir = null;
					}
					pt =null;
					t = true;
				}
				else {//tem filhos
					aux = pt;
					if (pt.esq == null){ //pode ter apenas o filho da direita
						pt = pt.dir;
						local = 1; //pai de pt tera novo filho a direita
					}
					else{
						if (pt.dir == null){//tem apenas o filho da esquerda
							pt = pt.esq;
							local = 2; //pai de pt tera novo filho a esquerda
						}
						else{//tem dois filhos

							pt = minDir(aux.dir);	//achar o menor a direita
							pt.esq = aux.esq;	//reordenacao de ponteiros
							if (aux == raiz){raiz = pt;}//se o no a ser removido era a raiz, pt e' a nova raiz
							else { 
								pt.pai = aux.pai;
								if (pos == 1)//este no e' o filho da esquerda do seu pai
									aux.pai.esq =pt;
								else if(pos == 2)//este no e' o filho da direita do seu pai
									aux.pai.dir =pt;
							}
							aux = null;
							return true;
						}	
					}
				}
				if (aux == raiz){raiz = pt;}//se o no a ser removido era a raiz, pt e' a nova raiz
				else{//nao e a raiz(tem pai)
					if (local == 1){aux.pai.dir = pt;} //pai tem filho a direita
					else if (local == 2) {aux.pai.esq = pt; }//pai tem filho a esquerda
				}
				aux = null;
			}
			//Continua a pesquisa
			else{
				if (x < pt.info){//procura na esquerda
					aux = pt;
					pos=1;
					remAVL(x,pt.esq,t);
					//depois da remocao, a verificacao
					if (t){
						if(pt.balanco == -1)
							pt.balanco = 0;
						t = false;
					}
					else if (pt.balanco == 0) pt.balanco = 1;
					else if (pt.balanco == 1)caso2(pt,t);//rebalanceamento (rotacao esquerda)
				}
				else{ //procura na direita
					aux = pt;
					pos=2;
					remAVL(x,pt.dir,t);
					//depois da remocao, a verificacao
					if (t){
						if (pt.balanco == 1){
							pt.balanco = 0;
							t = false;
						}
						else if (pt.balanco == 0) pt.balanco = -1;
						else if (pt.balanco == -1) caso1(pt,t); //rebalanceamento (rotacao direita)
					}
				}
			}
		}
		local=0; //sem valor, ao final da execucao
		return true;

	}
/*========================================================================================*/	
	/*Metodo que exibe a arvore em pre ordem
	na representacao de parenteses aninhados*/
	public String exibirArvorePreOrdem(){
		return super.exibirArvorePreOrdem();
	}
	
/*========================================================================================*/	
	/*Metodo que exibe o balanco da arvore em pre ordem
	na representacao de parenteses aninhados*/
	public String balanco(){
		if (raiz == null)return ""; 
		Node p = this.raiz; // recebe o no raiz
		print = print + Integer.toString(p.balanco)+ " ";
		if (p.esq != null){print = print + "(" + balanco(p.esq)+")";}
		if (p.dir != null){print = print + "(" + balanco(p.dir)+ ")";}
		String pr =print;
		print = " ";
		return pr;
	}
	/*========================================================================================*/
	/*Metodo que exibe o balanco da arvore em pre ordem (auxiliar para 
	  recursao na representacao de parenteses aninhados*/
	private String balanco(Node p){
		print = Integer.toString(p.balanco)+ " ";
		if (p.esq != null){print = print + "( " + balanco(p.esq) + ")";}
		if (p.dir != null){print = print + "(" + balanco(p.dir)+ ")";}
		String pr = print;
		print = " ";
		return pr;
	}
}
