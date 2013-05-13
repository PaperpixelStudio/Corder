package be.relab.recorder;

import ddf.minim.AudioInput;
import ddf.minim.Minim;
import be.relab.recorder.core.REapplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Spectrum extends REapplet {

    private PVector position = new PVector();
    private float width;
    private float height;
    private Minim minim;
    private AudioInput lineIn;
    private ArrayList<Float> spectrum = new ArrayList<Float>();
    private boolean isRecording;

    public Spectrum() {
        minim = new Minim(p5);
        lineIn = minim.getLineIn(Minim.MONO, 2);
    }

    public void draw() {
        p5.pushMatrix();
        p5.translate(position.x, position.y);

        /*draw the boundaries rectangle*/
        p5.noFill();
        p5.strokeWeight(1);
        p5.stroke(150);
        p5.rect(0, 0, width, height);


        /*save the spectrum in array*/
        if (isRecording && p5.frameCount % 2 == 0) {
            for(int i = 0; i < lineIn.bufferSize() - 1; i++) {
                spectrum.add(lineIn.left.get(i));
            }
        }


        /*Draw the spectrum in the rectangle*/
        drawSpectrum(width / spectrum.size(), height / 2);

        p5.popMatrix();
    }

    public void startRecording() {
        spectrum.clear();

        isRecording = true;
    }

    public void stopRecording() {
        isRecording = false;
    }

    public void drawSpectrum(float theWidth, float theHeight) {
        for (int i = 0; i < spectrum.size() - 1; i++) {
            float pos_x = i * theWidth;
            float pos_x_next = (i + 1) * theWidth;

            p5.line(pos_x, theHeight + spectrum.get(i) * theHeight, pos_x_next, theHeight + spectrum.get(i + 1) * theHeight);
        }
    }

    public void drawSpectrumCircle(float theRadius) {
        for(int i = 0; i < spectrum.size(); i++) {

            float dot = spectrum.get(i);
            float dot_next = i + 1 < spectrum.size() ? spectrum.get(i + 1) : 0;

            float pos_x = (i * ((theRadius * 2) / spectrum.size()));
            float pos_x_next = (i + 1) * ((theRadius * 2) / spectrum.size());

            float height = (float) (Math.sqrt(Math.pow(theRadius, 2) - Math.pow(pos_x - theRadius, 2)) - 10);
            float height_next = (float) (Math.sqrt(Math.pow(theRadius, 2) - Math.pow(pos_x_next - theRadius, 2)) - 10);

            float pos_y = height + dot * height;
            pos_y = pos_y - (height );
            float pos_y_next = height_next + dot_next * height_next;
            pos_y_next = pos_y_next - (height_next );

            p5.line(pos_x, pos_y, pos_x_next, pos_y_next);
        }
    }


    public void stopAudio() {
        lineIn.close();
        minim.stop();
    }



    /*GETTERS / SETTERS*/

    public Spectrum setPosition(float theX, float theY) {
        position.x = theX;
        position.y = theY;

        return this;
    }

    public Spectrum setSize(float theWidth, float theHeight) {
        width = theWidth;
        height = theHeight;

        return this;
    }
}
