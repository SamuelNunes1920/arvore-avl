/*
   Implementação de uma Árvore Rubro-Negra
  
Disciplina: Análise e Projeto de algoritmos
Professor: Prof. Dr. Nelson Neto
Alunos: Ericson Sarmento, Danileno Meireles, Joahannes Costa, Allan Costa
Dado: 01/05/2016
Update: 04/05/2016
*/
package red_black.my_red_black;

import java.awt.Color;

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
    *  Retorna Raiz da Árvore.
    **/
    public My_Red_Black_Node getRaiz() {
        return raiz;
    }
    
    

    /**
     * Classes utilitárias privadas
     **/
    
    /**
     * Confirma se um valor dado está presente na árvore.
     */
    public boolean contem(int dado) {
        return noContendo(dado) != null;
    }
   
     /**
     * Um método que retorna o nó que contém um valor dado.  
     * É utilizando como método auxiliar pelo método de remoção (remove)
     * e pelo método de verificação (contem).
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
    * Realiza a comparação entre dois valores dados.
    * É utilizado como método auxiliar para a inserção de novos nós.
    **/
    private int compara(int x, int y) {
      return Integer.compare(x, y);
    }
     
    /**
     * Realiza uma rotação simples à esquerda de um dado nó.
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
     * Realiza uma rotação simples à direita de um dado nó.
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
     * Retorna o nó com maior valor da sub-árvore da esquerda de um dado nó.
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
   
     /**
     * Adds a single dado item to the tree. If there is already an item in the
     * tree that compares equal to the item being inserted, it is "overwritten"
     * by the new item. Overrides BinarySearchTree.add because the tree needs to
     * be adjusted after insertion.
     */
    public void adiciona(int dado) {
        if (raiz == null) {
            raiz = new My_Red_Black_Node(dado);
        }
        My_Red_Black_Node n = raiz;
        while (true) {
            int comparisonResult = compara(dado, n.getDado());
            if (comparisonResult == 0) {
                n.setDado(dado);
                return;
            } else if (comparisonResult < 0) {
                if (n.getFilho_esquerda() == null) {
                    n.setFilho_esquerda(new My_Red_Black_Node(dado));
                    balanceamentoDeInsercao((My_Red_Black_Node) n.getFilho_esquerda());
                    break;
                }
                n = n.getFilho_esquerda();
            } else { // comparisonResult > 0
                if (n.getFilho_direita() == null) {
                    n.setFilho_direita(new My_Red_Black_Node(dado));
                    balanceamentoDeInsercao((My_Red_Black_Node) n.getFilho_direita());
                    break;
                }
                n = n.getFilho_direita();
            }
        }
    }
  
     /**
     * Removes the node containing the given value. Does nothing if there is no
     * such node.
     */
    public void remove(int dado) {
        My_Red_Black_Node no = (My_Red_Black_Node) noContendo(dado);
        if (no == null) {
            // No such object, do nothing.
            return;
        } else if (no.getFilho_esquerda() != null && no.getFilho_direita() != null) {
            // Node has two children, Copy predecessor dado in.
            My_Red_Black_Node predecessor = predecessor(no);
            no.setDado(predecessor.getDado());
            no = (My_Red_Black_Node) predecessor;
        }
        // At this point node has zero or one child
        My_Red_Black_Node pullUp;
        
        if(filhoEsquerdoDe(no) == null)
            pullUp = filhoDireitoDe(no);
        else
            pullUp = filhoEsquerdoDe(no);
         
        if (pullUp != null) {
            // Splice out node, and adjust if pullUp is a double black.
            if (no == raiz) {
                setRaiz(pullUp);
            } else if (no.getPai().getFilho_esquerda() == no) {
                no.getPai().setFilho_esquerda(pullUp);
            } else {
                no.getPai().setFilho_direita(pullUp);
            }
            if (ePreto(no)) {
                balanceamentoDeRemocao(pullUp);
            }
        } else if (no == raiz) {
            // Nothing to pull up when deleting a raiz means we emptied the tree
            setRaiz(null);
        } else {
            // The node being deleted acts as a double black sentinel
            if (ePreto(no)) {
                balanceamentoDeRemocao(no);
            }
            no.removeNo();
        }
    }
    
     /**
     * Classic algorithm for fixing up a tree after inserting a node.
     */
    private void balanceamentoDeInsercao(My_Red_Black_Node n) {
        // Step 1: color the node red
        setCor(n, Color.red);

        // Step 2: Correct double red problems, if they exist
        if (n != null && n != raiz && eVermelho(paiDe(n))) {

            // Step 2a (simplest): Recolor, and move up to see if more work
            // needed
            if (eVermelho(irmaoDe(paiDe(n)))) {
                setCor(paiDe(n), Color.black);
                setCor(irmaoDe(paiDe(n)), Color.black);
                setCor(avoDe(n), Color.red);
                balanceamentoDeInsercao(avoDe(n));
            }

            // Step 2b: Restructure for a parent who is the left child of the
            // grandparent. This will require a single right rotation if n is
            // also
            // a left child, or a left-right rotation otherwise.
            else if (paiDe(n) == filhoEsquerdoDe(avoDe(n))) {
                if (n == filhoDireitoDe(paiDe(n))) {
                    rotacaoEsquerda(n = paiDe(n));
                }
                setCor(paiDe(n), Color.black);
                setCor(avoDe(n), Color.red);
                rotacaoDireita(avoDe(n));
            }

            // Step 2c: Restructure for a parent who is the right child of the
            // grandparent. This will require a single left rotation if n is
            // also
            // a right child, or a right-left rotation otherwise.
            else if (paiDe(n) == filhoEsquerdoDe(avoDe(n))) {
                if (n == filhoEsquerdoDe(paiDe(n))) {
                    rotacaoDireita(n = paiDe(n));
                }
                setCor(paiDe(n), Color.black);
                setCor(avoDe(n), Color.red);
                rotacaoEsquerda(avoDe(n));
            }
        }

        // Step 3: Color the raiz black
        setCor((My_Red_Black_Node) raiz, Color.black);
    }
    
     /**
     * Classic algorithm for fixing up a tree after removing a node; the
     * parameter to this method is the node that was pulled up to where the
     * removed node was.
     */
    private void balanceamentoDeRemocao(My_Red_Black_Node n) {
        while (n != raiz && ePreto(n)) {
            if (n == filhoEsquerdoDe(paiDe(n))) {
                // Pulled up node is a left child
                My_Red_Black_Node irmao = filhoDireitoDe(paiDe(n));
                if (eVermelho(irmao)) {
                    setCor(irmao, Color.black);
                    setCor(paiDe(n), Color.red);
                    rotacaoEsquerda(paiDe(n));
                    irmao = filhoDireitoDe(paiDe(n));
                }
                if (ePreto(filhoEsquerdoDe(irmao)) && ePreto(filhoDireitoDe(irmao))) {
                    setCor(irmao, Color.red);
                    n = paiDe(n);
                } else {
                    if (ePreto(filhoDireitoDe(irmao))) {
                        setCor(filhoEsquerdoDe(irmao), Color.black);
                        setCor(irmao, Color.red);
                        rotacaoDireita(irmao);
                        irmao = filhoDireitoDe(paiDe(n));
                    }
                    setCor(irmao, corDe(paiDe(n)));
                    setCor(paiDe(n), Color.black);
                    setCor(filhoDireitoDe(irmao), Color.black);
                    rotacaoEsquerda(paiDe(n));
                    n = (My_Red_Black_Node) raiz;
                }
            } else {
                // pulled up node is a right child
                My_Red_Black_Node sibling = filhoEsquerdoDe(paiDe(n));
                if (eVermelho(sibling)) {
                    setCor(sibling, Color.black);
                    setCor(paiDe(n), Color.red);
                    rotacaoDireita(paiDe(n));
                    sibling = filhoEsquerdoDe(paiDe(n));
                }
                if (ePreto(filhoEsquerdoDe(sibling)) && ePreto(filhoDireitoDe(sibling))) {
                    setCor(sibling, Color.red);
                    n = paiDe(n);
                } else {
                    if (ePreto(filhoEsquerdoDe(sibling))) {
                        setCor(filhoDireitoDe(sibling), Color.black);
                        setCor(sibling, Color.red);
                        rotacaoEsquerda(sibling);
                        sibling = filhoEsquerdoDe(paiDe(n));
                    }
                    setCor(sibling, corDe(paiDe(n)));
                    setCor(paiDe(n), Color.black);
                    setCor(filhoEsquerdoDe(sibling), Color.black);
                    rotacaoDireita(paiDe(n));
                    n = (My_Red_Black_Node) raiz;
                }
            }
        }
        setCor(n, Color.black);
    }
      
    private Color corDe(My_Red_Black_Node n) {
        
        if (n == null) 
            return Color.black;
        else 
            return n.getCor();    
    }

    private boolean eVermelho(My_Red_Black_Node n) {
    
        if (n != null && corDe(n) == Color.red)
            return true;
        else
            return false;
      
    }

    private boolean ePreto(My_Red_Black_Node n) {
       
        if (n != null && corDe(n) == Color.black)
            return true;
        else
            return false;
        
    }

    private void setCor(My_Red_Black_Node n, Color c) {
        if (n != null)
            n.setCor(c);
    }

    private My_Red_Black_Node paiDe(My_Red_Black_Node n) {
       
        if( n == null)
          return null;
        else
          return (My_Red_Black_Node) n.getPai();
        
    }

    private My_Red_Black_Node avoDe(My_Red_Black_Node n) {
        
        if( n == null || n.getPai() == null)
            return null;
        else
            return (My_Red_Black_Node) n.getPai().getPai();
    }

    private My_Red_Black_Node irmaoDe(My_Red_Black_Node n) {
        
        if( n == null || n.getPai() == null)
            return null;
        else
            if( n == n.getPai().getFilho_esquerda())
                return (My_Red_Black_Node) n.getPai().getFilho_direita();
            else
                return (My_Red_Black_Node) n.getPai().getFilho_esquerda();
    }

    private My_Red_Black_Node filhoEsquerdoDe(My_Red_Black_Node n) {
        
        if( n == null)
            return null;
        else
            return (My_Red_Black_Node) n.getFilho_esquerda();
    }

    private My_Red_Black_Node filhoDireitoDe(My_Red_Black_Node n) {
        
        if( n == null)
            return null;
        else
            return (My_Red_Black_Node) n.getFilho_direita();
    }

  
    public static void main(String[] args) {
        My_Red_Black tree = new My_Red_Black();
        
    }



}
