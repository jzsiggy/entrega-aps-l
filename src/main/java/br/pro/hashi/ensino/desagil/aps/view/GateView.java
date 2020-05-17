package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {
    private final Gate gate;

    private final JCheckBox checkBox1;
    private final JCheckBox checkBox2;

    private final Image image;
    private Color color;

    private Light light = new Light(255, 0, 0);


    public GateView(Gate gate) {
        super(345, 246);

        this.gate = gate;
        checkBox1 = new JCheckBox("");
        checkBox2 = new JCheckBox("");

        if (gate.getInputSize() == 2) {
            add(checkBox1, 30, 70, 150, 25);
            add(checkBox2, 30, 150, 150, 25);
        } else {
            add(checkBox1, 30, 110, 150, 25);
        }
        ;

//        add(checkBoxresult, 273, 110, 120, 25);

        color = light.getColor();

        // Usamos esse carregamento nos Desafios, vocês lembram?
        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        checkBox1.addActionListener(this);
        checkBox2.addActionListener(this);

        addMouseListener(this);

        update();
    }

    private void update() {
        Boolean entrada1;
        Boolean entrada2;

        entrada1 = checkBox1.isSelected();
        entrada2 = checkBox2.isSelected();

        System.out.println(entrada1);
        System.out.println(entrada2);

        Switch switch1 = new Switch();
        Switch switch2 = new Switch();

        if (entrada1) {
            switch1.turnOn();
        }
        ;
        this.gate.connect(0, switch1);

        if (this.gate.getInputSize() > 1) {
            if (entrada2) {
                switch2.turnOn();
            }
            ;
            this.gate.connect(1, switch2);
        }
        ;


        Boolean result = this.gate.read();

        light.connect(0, this.gate);

        color = light.getColor();
        repaint();
    }

    ;

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        System.out.println(x);
        System.out.println(y);

        // Se o clique foi dentro do quadrado colorido...
        //if (x >= 0 && x < 25 && y >= 0 && y < 25) {
        if (Math.pow((x - 285.5), 2) + Math.pow((y - 122.5), 2) <= 156.25) {
            System.out.println("INSIDE");

            // ...então abrimos a janela seletora de cor...
            light.setColor(JColorChooser.showDialog(this, null, color));

            // ...e chamamos repaint para atualizar a tela.
            update();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 50, 40, 230, 160, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        g.fillOval(273, 110, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
