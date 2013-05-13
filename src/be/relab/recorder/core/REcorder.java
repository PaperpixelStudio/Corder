package be.relab.recorder.core;

import be.relab.recorder.gui.GUI;
import be.relab.recorder.Generator;
import be.relab.recorder.Spectrum;
import processing.core.PApplet;

public class REcorder extends PApplet {

    private GUI gui;
    private Spectrum spectrum;
    private Generator generator;


    static public void main(String args[]) {
        PApplet.main(new String[] { "be.relab.recorder.core.REcorder" });
    }

    public void setup() {
        size(800, 600, P3D);
        smooth();
        background(255);

        REapplet.setupApplet(this);

        gui = new GUI();
        spectrum = new Spectrum()
                .setPosition(Constants.MARGIN, Constants.SPECTRUM_POS_Y)
                .setSize(width - (Constants.MARGIN * 2), Constants.SPECTRUM_HEIGHT);

        generator = new Generator()
                .setPosition(Constants.MARGIN, 220)
                .setSize(400, 400);
    }

    public void draw() {
        background(255);

        spectrum.draw();
        generator.draw();
        gui.getCp5().draw();
    }

    public void stop() {
        spectrum.stopAudio();

        super.stop();
    }



    /*GETTERS / SETTERS*/


    public GUI getGui() {
        return gui;
    }

    public Spectrum getSpectrum() {
        return spectrum;
    }

    public Generator getGenerator() {
        return generator;
    }
}
