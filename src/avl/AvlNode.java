/* Universidade Federal do Pará
 * Instituto de Ciências Exatas e Naturais
 * Análise e Projetos de Algoritmos
 * Implementação da Árvore AVL - Código Base (Nome do cara)
 * Versão 1.0
*/
public class AvlNode {
    	protected int height;       // Height = Balanco
    	protected int key;
        protected AvlNode left, right;
        public AvlNode ( int theElement ) {
            this( theElement, null, null );
        }
        public AvlNode ( int theElement, AvlNode lt, AvlNode rt ) {
            key = theElement;
            left = lt;
            right = rt;
            height   = 0;
        }
    }
