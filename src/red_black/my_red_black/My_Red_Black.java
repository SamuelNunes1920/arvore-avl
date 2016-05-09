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
     * Faz um nó qualquer dado virar a raiz da árvore.
     **/
    public void setRaiz(My_Red_Black_Node no) {
        if (no != null) {
            no.removeNo();
        }
        this.raiz = no;
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
            this.setRaiz(oldRight);
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
            this.setRaiz(oldLeft);
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
                    balanceamentoDeInsercao(n.getFilho_esquerda());
                    break;
                }
                n = n.getFilho_esquerda();
            } else { // comparisonResult > 0
                if (n.getFilho_direita() == null) {
                    n.setFilho_direita(new My_Red_Black_Node(dado));
                    balanceamentoDeInsercao(n.getFilho_direita());
                    break;
                }
                n = n.getFilho_direita();
            }
        }
    }
  
    
    public void remove(int dado) {
        My_Red_Black_Node no = (My_Red_Black_Node) noContendo(dado);
        if (no == null) {
    
            return;
        } else if (no.getFilho_esquerda() != null && no.getFilho_direita() != null) {
    
            My_Red_Black_Node predecessor = predecessor(no);
            no.setDado(predecessor.getDado());
            no = (My_Red_Black_Node) predecessor;
        }
    
        My_Red_Black_Node pullUp;
        
        if(filhoEsquerdoDe(no) == null)
            pullUp = filhoDireitoDe(no);
        else
            pullUp = filhoEsquerdoDe(no);
         
        if (pullUp != null) {
    
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
    
            setRaiz(null);
        } else {
    
            if (ePreto(no)) {
                balanceamentoDeRemocao(no);
            }
            no.removeNo();
        }
    }
    
     /**
     * Previsão de casos de balanceamento da árvore rubro-negra após
     * uma inserção.
     */
    private void balanceamentoDeInsercao(My_Red_Black_Node n) {
        // Caso 1: Todo nó inserido tem a cor vermelha
        setCor(n, Color.red);

        // Caso 2: Pai vermelho com tio vermelho, realiza mudança de cores
        if ((n != null) && (n != this.getRaiz()) && (eVermelho(paiDe(n)))) {
              if (eVermelho(tioDe(n))) {
                setCor(paiDe(n), Color.black);
                setCor(tioDe(n), Color.black);
                setCor(avoDe(n), Color.red);
                balanceamentoDeInsercao(avoDe(n));
            }

            // Caso 3a ou 3b: Pai do lado esquerdo do Avo, 
            //com filho do lado esquerdo ou filho do lado direito.
            // Se o filho estiver do lado esquerdo faz rotação simples direita,
            // Se o filho estiver do lado direito faz rotação dupla a esquerda.
            else if (paiDe(n) == filhoEsquerdoDe(avoDe(n))) {
                if (n == filhoDireitoDe(paiDe(n))) {
                    rotacaoEsquerda(n = paiDe(n));
                }
                setCor(paiDe(n), Color.black);
                setCor(avoDe(n), Color.red);
                rotacaoDireita(avoDe(n));
            }

            // Passo 3b ou 3c: Quando o pai é filho direito do avo, 
            // com filho esquerdo ou filho direito.             
            // Se for filho direito faz rotação simples a esquerda,
            // Se for filho esquerdo faz rotação dupla a esquerda.
            else if (paiDe(n) == filhoDireitoDe(avoDe(n))) {
                if (n == filhoEsquerdoDe(paiDe(n))) {
                    rotacaoDireita(n = paiDe(n));
                }
                setCor(paiDe(n), Color.black);
                setCor(avoDe(n), Color.red);
                rotacaoEsquerda(avoDe(n));
            }
        }

        //Passo base: Pinta a Raiz de preto
        setCor((My_Red_Black_Node) raiz, Color.black);
    }
    
    /**
     *  Previsão de casos de balancemaneto da árvores rubro-negra após
     *  uma remoção.
     * @param n 
     */ 
    private void balanceamentoDeRemocao(My_Red_Black_Node n) {
         
        while((n != this.getRaiz()) && ePreto(n)) {
            if (n == filhoEsquerdoDe(paiDe(n))) {
                My_Red_Black_Node sibling = filhoDireitoDe(paiDe(n));
                if (eVermelho(sibling)) {
                    setCor(sibling, Color.black);
                    setCor(paiDe(n), Color.red);
                    rotacaoEsquerda(paiDe(n));
                    sibling = filhoDireitoDe(paiDe(n));
                }
                if (ePreto(filhoEsquerdoDe(sibling)) && ePreto(filhoDireitoDe(sibling))) {
                    setCor(sibling, Color.red);
                    n = paiDe(n);
                } else {
                    if (ePreto(filhoDireitoDe(sibling))) {
                        setCor(filhoEsquerdoDe(sibling), Color.black);
                        setCor(sibling, Color.red);
                        rotacaoDireita(sibling);
                        sibling = filhoDireitoDe(paiDe(n));
                    }
                    setCor(sibling, corDe(paiDe(n)));
                    setCor(paiDe(n), Color.black);
                    setCor(filhoEsquerdoDe(sibling), Color.black);
                    rotacaoEsquerda(paiDe(n));
                    n = this.getRaiz();
                }
            } else {
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
                    n = this.getRaiz();
                }
            }
        }
        //Regra base: pinta a raiz de preto
        setCor(n, Color.black);
    }
      
    private Color corDe(My_Red_Black_Node n) {
        
        if (n == null) 
            return Color.black;
        else 
            return n.getCor();    
    }

    private boolean eVermelho(My_Red_Black_Node n) {
    
        if ((n != null) && (corDe(n) == Color.red))
            return true;
        else
            return false;
      
    }

    private boolean ePreto(My_Red_Black_Node n) {
       
        if((n != null) && (corDe(n) == Color.black))
            return true;
        else
            return false;
        
    }

    private void setCor(My_Red_Black_Node n, Color c) {
        if (n != null)
            n.setCor(c);
    }
    
    private My_Red_Black_Node tioDe(My_Red_Black_Node n){
      
      if ((n == null) || (n.getPai() == null))  
          return null;
      else  
        return irmaoDe(paiDe(n));  
        
    }

    private My_Red_Black_Node paiDe(My_Red_Black_Node n) {
       
        if( n == null)
          return null;
        else
          return n.getPai();
        
    }

    private My_Red_Black_Node avoDe(My_Red_Black_Node n) {
        
        if( n == null || n.getPai() == null)
            return null;
        else
            return n.getPai().getPai();
    }

    private My_Red_Black_Node irmaoDe(My_Red_Black_Node n) {
        
        if( n == null || n.getPai() == null)
            return null;
        else
            if( n == n.getPai().getFilho_esquerda())
                return n.getPai().getFilho_direita();
            else
                return n.getPai().getFilho_esquerda();
    }

    private My_Red_Black_Node filhoEsquerdoDe(My_Red_Black_Node n) {
        
        if( n == null)
            return null;
        else
            return n.getFilho_esquerda();
    }

    private My_Red_Black_Node filhoDireitoDe(My_Red_Black_Node n) {
        
        if( n == null)
            return null;
        else
            return n.getFilho_direita();
    }

    private int getMaior(){
        My_Red_Black_Node n = this.getRaiz();
        while( n.getFilho_direita() != null){
            n = n.getFilho_direita();
        }
        
        return n.getDado();
    }
    
     private int getMenor(){
        My_Red_Black_Node n = this.getRaiz();
        while( n.getFilho_esquerda() != null){
            n = n.getFilho_esquerda();
        }
        
        return n.getDado();
    }
  
     
    private String transformaCor(Color cor){
        if(cor == Color.black){
            return "preto";
        }
        else
          return "vermelho";
    } 
     
     
    public void displayTree() {
        if (this.getRaiz() == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        String separator = String.valueOf("  |__");
        System.out.println(this.getRaiz().getDado() + "(" + transformaCor(this.getRaiz().getCor()) + ")");
        displaySubTree(this.getRaiz().getFilho_direita(), separator);
        displaySubTree(this.getRaiz().getFilho_esquerda(), separator);
    }

    private void displaySubTree(My_Red_Black_Node node, String separator) {
        if( node != null){
            My_Red_Black_Node pai = node.getPai();
            if (pai.getFilho_esquerda() == node) {
                System.out.println(separator + node.getDado() + "(" + transformaCor(node.getCor()) + ")" + " (ESQ)");
            } else {
                System.out.println(separator + node.getDado() + "(" + transformaCor(node.getCor()) + ")" + " (DIR)");
            }
            displaySubTree(node.getFilho_esquerda(), "     " + separator);
            displaySubTree(node.getFilho_direita(), "     " + separator);
        }
    }
      
    
    public static void main(String[] args) {
        My_Red_Black tree = new My_Red_Black();
        
        //for(int i = 0; i<= 5; i++)
          //  tree.adiciona(i);
        
        //tree.remove(3);
        //tree.remove(1);
        
        tree.adiciona(9);
        tree.adiciona(11);
        tree.adiciona(15);
               
        
        tree.displayTree();
        
        System.out.println("Maior Elemento : "+tree.getMaior());
        
        System.out.println("Menor Elemento : "+tree.getMenor());
        
        System.out.println("Busca elemento 1 : "+tree.contem(1));
        
        
    }
    
}
