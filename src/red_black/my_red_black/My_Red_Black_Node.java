/*
   Classe que representa um nó da árvore
   Características:
    1 - Possui um pai, um filho à esquerda, um filho à direita (Árvore binária)
    2 - Neste caso específico (Rubro-Negra) possui coloração
    3 - Possui dados armazenados (que não se repetem - Ávore binária)
 
    Observações:

    1 - Para visualização da árvore foi escolhida a biblioteca gráfica AWT do Java
*/
package red_black.my_red_black;

import java.awt.Color;


/**
 *  @author Ericson Sarmento
 * 
 */
public class My_Red_Black_Node {
    
    private My_Red_Black_Node pai = null, 
                              filho_esquerda = null, 
                              filho_direita = null;
    private int dado;
    private Color cor = null;
    
    /*
       Contrutor padrão:
        - Apenas inicializa o dado do nó
        - A tarefa de definir o pai, e os filhos, será feita 
          através das operações de inserção de filhos a esquerda ou direita;
    */
    public My_Red_Black_Node(int dado){
        this.dado = dado;
    }

    
    /*Interfaces públicas Getters*/
    
    public Color getCor() {
        return cor;
    }

    public int getDado() {
        return dado;
    }

    public My_Red_Black_Node getFilho_direita() {
        return filho_direita;
    }

    public My_Red_Black_Node getFilho_esquerda() {
        return filho_esquerda;
    }

    
    /* Interface pública setters */
    
    public void setCor(Color cor) {
        this.cor = cor;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }

    /**
     * Insere um filho à direita do nó atual.
     * Remove o filho à direita de seu pai (se for necessário).
     * Se o nó atual possuir filho a direita, este será perdido.
     * @exception IllegalArgumentException dispara uma excessão de argumento 
     * inválido se o filho à direita for um nó ancestral do nó atual, 
     * o que poderia tornar a árvore em um grafo cíclico.
     */
    public void setFilho_direita(My_Red_Black_Node filho_direita) {
        // Ensure the filho_direita is not an ancestor.
        for (My_Red_Black_Node n = this; n != null; n = n.pai) {
            if (n == filho_direita) {
                throw new IllegalArgumentException();
            }
        }

        // Ensure that the filho_direita is an instance of LinkedBinaryTreeNode.
        My_Red_Black_Node filho = (My_Red_Black_Node)filho_direita;

        // Break old links, then reconnect properly.
        if (this.filho_direita != null) {
            this.filho_direita.pai = null;
        }
        if (filho != null) {
            filho.removeFilho();
            filho.pai = this;
        }
        this.filho_direita = filho;
    }
    
     /**
     * Insere este filho como o filho esquerdo do nó atual. 
     * Remove o filho dos seu pai atual (se for necessário).
     * Se o nó atual tive um filho esquerdo, este será perdido.
     * @exception IllegalArgumentException dispara um excessão de argumento 
     * ilegal caso tente inserir um filho
     * esquerdo que já é ancestral do nó atual, o que causaria um grafo cíclico
     * na árvore
     */
    public void setFilho_esquerda(My_Red_Black_Node filho_esquerda) {
        // Verifica se o filho da esquerda não é o próprio pai
        for (My_Red_Black_Node n = this; n != null; n = n.pai) {
            if (n == filho_esquerda) {
                throw new IllegalArgumentException();
            }
        }
        
       // Verifica se o filho da esquerda é uma instância da classe nó.
       //Caso contrário não será possível realizar as operações e deleção
        My_Red_Black_Node filho = (My_Red_Black_Node)filho_esquerda;

        // Limpa as conexões anteriores e seta como filho da esquerda
        if (this.filho_esquerda != null) {
            this.filho_esquerda.pai = null;
        }
        if (filho != null) {
            filho.removeFilho();
            filho.pai = this;
        }
        this.filho_esquerda = filho; 
    }
    
   
    
    /*Métodos utilitários*/
        
    /**
     * Remove (limpa) todas as relações de um nó.
     * Porém se ele for a raiz não faz nada.
     */
    public void removeFilho() {
        if (pai != null) { //Só será se não tiver pai
            if (pai.filho_esquerda == this) {
                pai.filho_esquerda = null;
            } else if (pai.filho_direita == this) {
                pai.filho_direita = null;
            }
            this.pai = null;
        }
    }
    
    
    
    
}
