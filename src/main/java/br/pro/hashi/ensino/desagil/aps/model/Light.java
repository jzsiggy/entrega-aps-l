package br.pro.hashi.ensino.desagil.aps.model;

import java.awt.*;

public class Light implements Receiver {
    private Color color;
    private Color offColor;
    private Emitter emitter;

    public Light(int r, int g, int b, int offR, int offG, int offB) {
        color = new Color(r, g, b);
        offColor = new Color(offR, offG, offB);

        emitter = null;
    }

    public Color getColor() {
        if (emitter != null && emitter.read()) {
            return color;
        }
        return offColor;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setOffColor(Color color) {
        this.offColor = color;
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex != 0) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        this.emitter = emitter;
    }
}
