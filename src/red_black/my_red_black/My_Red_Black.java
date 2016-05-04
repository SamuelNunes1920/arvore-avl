/*
   Implementação de uma Árvore Rubro-Negra
  
Disciplina: Análise e Projeto de algoritmos
Professor: Prof. Dr. Nelson Neto
Alunos: Ericson Sarmento, Danileno Meireles, Joahannes Costa, Allan Costa
Data: 01/05/2016
Update: 04/05/2016
*/
package red_black.my_red_black;

/**
 *
 * @author Ericson Sarmento
 */
public class My_Red_Black {
 
    private My_Red_Black_Node raiz = null;    
       
    public My_Red_Black(){
     /*Não faz nada, os métodos de inserção e deleção cuidarão de definir
      dados, raiz, filhos, balanceamento, coloração;  
     */    
    }  

    /*Get e Set para a Raiz da árvore*/
    
    /**
     * Faz um nós qualquer dado virar a raiz da árvore.
     **/
    public void setRaiz(My_Red_Black_Node no) {
        if (no != null) {
            no.removeNo();
        }
        raiz = no;
    }

    /**
    *  Retorna Raiz da Árvore
    **/
    public My_Red_Black_Node getRaiz() {
        return raiz;
    }
    
    

    /**
     * Classes utilitárias privadas
     **/
    
    /**
     * Returns whether or not the tree contains an object with
     * the given value.
     */
    private boolean contem(int dado) {
        return noContendo(dado) != null;
    }
   
     /**
     * A special helper method that returns the node containing
     * an object that compares equal to the given object.  This
     * is used in both contains and remove.
     */
    private My_Red_Black_Node noContendo(int dado) {
        for (My_Red_Black_Node n = raiz; n != null;) {
            int comparisonResult = compara(dado, n.getDado());
            if (comparisonResult == 0) {
                return n;
            } else if (comparisonResult < 0) {
                n = n.getFilho_esquerda();
            } else {
                n = n.getFilho_direita();
            }
        }
        return null;
    }
   
    /**
    * Best to put the comparison code in a single place so that we don't have
    *to check for comparators and cast all over the place.
    **/
    private int compara(int x, int y) {
//        if (comparator == null) {
//            return ((Comparable<E>)x).compareTo(y);
//        } else {
//            return comparator.compare(x, y);
//        }
    return 0; //concertar
    }
     
    /**
     * Rotates left around the given node.
     */
    private void rotacaoEsquerda(My_Red_Black_Node n) {
        if (n.getFilho_direita() == null) {
            return;
        }
        My_Red_Black_Node oldRight = n.getFilho_direita();
        n.setFilho_direita(oldRight.getFilho_esquerda());
        if (n.getPai() == null) {
            raiz = oldRight;
        } else if (n.getPai().getFilho_esquerda() == n) {
            n.getPai().setFilho_esquerda(oldRight);
        } else {
            n.getPai().setFilho_direita(oldRight);
        }
        oldRight.setFilho_esquerda(n);
    }

    /**
     * Rotates right around the given node.
     */
    private void rotacaoDireita(My_Red_Black_Node n) {
        if (n.getFilho_esquerda() == null) {
            return;
        }
        My_Red_Black_Node oldLeft = n.getFilho_esquerda();
        n.setFilho_esquerda(oldLeft.getFilho_direita());
        if (n.getPai() == null) {
            raiz = oldLeft;
        } else if (n.getPai().getFilho_esquerda() == n) {
            n.getPai().setFilho_esquerda(oldLeft);
        } else {
            n.getPai().setFilho_direita(oldLeft);
        }
        oldLeft.setFilho_direita(n);
    }

    /**
     * Returns the rightmost node in the left subtree.
     */
    private My_Red_Black_Node predecessor(My_Red_Black_Node node) {
        My_Red_Black_Node n = node.getFilho_esquerda();
        if (n != null) {
            while (n.getFilho_direita() != null) {
                n = n.getFilho_direita();
            }
        }
        return n;
    }

   
 
 
 
}
