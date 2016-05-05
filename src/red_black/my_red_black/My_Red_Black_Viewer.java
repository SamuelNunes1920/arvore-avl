/*
   Classe que visualiza a árvore rubro-negra
   
   - Cria uma janela (JFrame) e 

*/
package red_black.my_red_black;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;




/**
 *
 * @author Ericson Sarmento
 */
public class My_Red_Black_Viewer extends JPanel{
  
    My_Red_Black tree = new My_Red_Black();
    My_Red_Black_Node no = null;
    
    private int gridwidth = 40;
    private int gridheight = 40;

    JFrame frame = new JFrame("Red Black Tree Viewer");
    JTextField valueField = new JTextField(40);
    JPanel buttonPanel = new JPanel();
    JScrollPane displayArea = new JScrollPane();
    JLabel messageLine = new JLabel();

    
    
    /**
     * Stores the pixel values for each node in the tree.
     */
    private Map<My_Red_Black_Node, Point> coordinates =
        new HashMap<My_Red_Black_Node, Point>();
    
    
    /**
     * Changes the tree rendered by this panel.
     */
    public void setTree(My_Red_Black_Node root) {
        no = root;
        repaint();
    }

    /**
     * Draws the tree in the panel.  First it computes the coordinates
     * of all the nodes with an inorder traversal, then draws them
     * with a postorder traversal.
     */
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (tree == null) {
            return;
        }
        
      // no.caminhamentoEmOrdem(gridwidth, gridheight, coordinates);
      // no.caminhamentoEmPosOrdem( g, coordinates);
    }    

       
    /**
     * An operation encapsulates a button and its action.  The constructor
     * will create a button, add it to a button panel, and register itself
     * as a listener for the button.  The listener first reads inputs from
     * a textfield, then calls a subclass-supplied method with those inputs,
     * then displays the resulting tree in the display area.
     */
    private abstract class Operation implements ActionListener {
        public Operation(String label) {
            JButton button = new JButton(label);
            buttonPanel.add(button);
            button.addActionListener(this);
        }
        public void actionPerformed(ActionEvent event) {
            String value = valueField.getText();
            messageLine.setText("");
            try {execute(value);} catch (Exception e) {e.printStackTrace();}
            // Update the picture and return the focus to the text field.  Select
            // all the text in the textfield so it can easily be overwritten.
            setTree(tree.getRaiz());
            valueField.requestFocus();
            valueField.selectAll();
        }
        protected abstract void execute(String value);
    }

    /**
     * Constructs a viewer, laying out all the components in a
     * very nice way, and constructs and registers all the
     * operation objects.
     */
    public My_Red_Black_Viewer() {
        JPanel valuePanel = new JPanel();
        valuePanel.add(new JLabel("Valor: "));
        valuePanel.add(valueField);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0, 1));
        controlPanel.add(valuePanel);
        controlPanel.add(buttonPanel);

        // NOTE: Hardcoded preferred size!  Fix this in the exercises.
        this.setPreferredSize(new Dimension(2048, 2048));
        this.setBackground(Color.white);
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        displayArea.setViewportView(this);

        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(controlPanel, "North");
        frame.getContentPane().add(displayArea, "Center");
        frame.getContentPane().add(messageLine, "South");
        frame.pack();
        frame.repaint();

        new Operation("Adicionar") {
            protected void execute(String value) {
                tree.adiciona(Integer.parseInt(value));
            }};
//        new Operation("Add All") {
//            protected void execute(String value) {
//                for (String s: value.split("\\s+")) tree.add(Integer.parseInt(s));}};
        new Operation("Pesquisar") {
            protected void execute(String value) {
                messageLine.setText("O valor \"" + value + "\" é " +
                    (tree.contem(Integer.parseInt(value)) ? "" : "não foi ") + "encontrado");}};
        new Operation("Remover") {
            protected void execute(String value) {
                tree.remove(Integer.parseInt(value));}};
    }  


     public static void main(String[] args) {
        My_Red_Black_Viewer viewer = new My_Red_Black_Viewer();
        viewer.frame.setSize(540, 480);
        viewer.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewer.frame.setVisible(true);
    }
    
    
}
