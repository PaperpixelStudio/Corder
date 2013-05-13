package be.relab.recorder;

import be.relab.recorder.core.Constants;
import be.relab.recorder.core.REapplet;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.Date;

public class Generator extends REapplet {

    private PVector position = new PVector();
    private float width;
    private float height;
    private boolean isGenerate;
    private boolean isDrawing;

    public void generate() {
        isGenerate = true;
        isDrawing = true;
    }

    public void clear() {
        isGenerate = false;
        isDrawing = false;
    }

    public void draw() {
        if(isDrawing) {
            p5.pushMatrix();
            p5.translate(position.x + Constants.EARRING_BORDER, position.y + (height / 2));


            if (isGenerate) {
                Date d = new Date();
                long current = d.getTime()/1000;

                p5.beginRaw(PConstants.DXF, "output_" + current + ".dxf");
            }

            p5.noFill();
            p5.stroke(0);
//            p5.strokeWeight(0.25f);

            /*Outer circle*/
            p5.ellipse(width / 2, 0,
                    width + (Constants.EARRING_BORDER * 2), height + (Constants.EARRING_BORDER * 2));

            /*Support hole*/
            p5.ellipse(width / 2, -(height / 2) - Constants.EARRING_BORDER / 2,
                    Constants.EARRING_HOLE_SIZE, Constants.EARRING_HOLE_SIZE);

            /*Inner half circle*/
            p5.arc(width / 2, 0, width, height, -PApplet.PI, 0);  // upper half of circle

            /*Spectrum*/
            p5.getSpectrum().drawSpectrumCircle(width / 2);

            if (isGenerate) {
                p5.endRaw();
                isGenerate = false;

                PApplet.println("DXF GENERATED");
            }

            p5.popMatrix();
        }
    }




    /*GETTERS / SETTERS*/

    public Generator setPosition(float theX, float theY) {
        position.x = theX;
        position.y = theY;

        return this;
    }

    public Generator setSize(float theWidth, float theHeight) {
        width = theWidth;
        height = theHeight;

        return this;
    }
}
