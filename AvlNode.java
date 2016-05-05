/* Universidade Federal do Pará
 * Instituto de Ciências Exatas e Naturais
 * Análise e Projetos de Algoritmos
 * Implementação da Árvore AVL - Código Base
 * Versão 1.0 - 05/05/2016
 * @Autores:
 * Joahannes Costa <joahannes@gmail.br>
 * Danileno Rosario <lenomeireles@gmail.com>
 * Ericson Sarmento <ericsonsarmento@gmail.com>
 * Allan Costa <allandoug@gmail.com>
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
