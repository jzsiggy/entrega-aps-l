package br.pro.hashi.ensino.desagil.aps.view;

import javax.swing.*;
import java.awt.*;

public class FixedPanel extends JPanel {
    protected FixedPanel(int width, int height) {

        // É muito importante estabelecer que esse painel
        // não tem layout, pois caso contrário seu tamanho
        // vai ser definido pelo tamanho de seu conteúdo.
        setLayout(null);

        // Usamos esse método nos Desafios, vocês lembram?
        setPreferredSize(new Dimension(width, height));
    }

    // Sobrecarga do método add que permite definir posição
    // e tamanho de cada componente adicionada ao painel.
    // Fixar posição e tamanho também não é uma boa prática,
    // pois o normal é deixar o layout decidir. Novamente,
    // escolhemos fazer isso aqui para priorizar simplicidade.
    protected Component add(Component comp, int x, int y, int width, int height) {

        // Usa a implementação original para adicionar.
        super.add(comp);

        // Redefine posição e tamanho da componente.
        comp.setBounds(x, y, width, height);

        return comp;
    }
}
