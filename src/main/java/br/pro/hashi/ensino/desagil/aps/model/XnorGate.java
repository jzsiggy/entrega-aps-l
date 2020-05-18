package br.pro.hashi.ensino.desagil.aps.model;

public class XnorGate extends Gate {
    private final NandGate nand1;
    private final NandGate nandTop;
    private final NandGate nandBottom;
    private final NandGate nand2;
    private final NandGate nand3;


    public XnorGate() {
        super("XNOR", 2);

        nand1 = new NandGate();
        nandTop = new NandGate();
        nandBottom = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();


        nandTop.connect(1, nand1);
        nandBottom.connect(0, nand1);
        nand2.connect(1, nandBottom);
        nand2.connect(0, nandTop);
        nand3.connect(1, nand2);
        nand3.connect(0, nand2);
    }

    @Override
    public boolean read() {
        return nand3.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        switch (inputIndex) {
            case 0:
                nand1.connect(inputIndex, emitter);
                nandTop.connect(inputIndex, emitter);
                break;
            case 1:
                nand1.connect(inputIndex, emitter);
                nandBottom.connect(inputIndex, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputIndex);
        }
    }
}