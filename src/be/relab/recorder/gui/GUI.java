package be.relab.recorder.gui;

import controlP5.*;
import be.relab.recorder.core.Constants;
import be.relab.recorder.core.REapplet;

public class GUI extends REapplet {
    private ControlP5 cp5;

    private final Button recordButton;

    private static final String RECORD_START = "record_start";
    private static final String RECORD_STOP = "record_stop";

    public GUI() {
        cp5 = new ControlP5(p5);

        float pos_x = Constants.MARGIN;
        float pos_y = Constants.MARGIN;

        recordButton = cp5.addButton("record_start")
                .setPosition(pos_x, pos_y)
                .setCaptionLabel(Constants.STRING_START_RECORDING)
                .setStringValue(RECORD_START)
                .setWidth(150)
                .setHeight(30)
                .addListener(new RecordListener());


        cp5.addButton("generate")
                .setPosition(Constants.MARGIN, Constants.SPECTRUM_POS_Y + Constants.SPECTRUM_HEIGHT + 20)
                .setHeight(30)
                .setCaptionLabel(Constants.STRING_GENERATE)
                .addListener(new GenerateListener());
    }


    /*GETTERS / SETTERS*/

    public ControlP5 getCp5() {
        return cp5;
    }

    /*LISTENERS*/

    private class RecordListener implements ControlListener {
        public void controlEvent(ControlEvent theEvent) {
            if (theEvent.getStringValue().equals(RECORD_START)) {

                recordButton.setStringValue(RECORD_STOP);
                recordButton.setCaptionLabel(Constants.STRING_STOP_RECORDING);

                p5.getSpectrum().startRecording();
                p5.getGenerator().clear();

            } else if (theEvent.getStringValue().equals(RECORD_STOP)) {

                recordButton.setStringValue(RECORD_START);
                recordButton.setCaptionLabel(Constants.STRING_START_RECORDING);

                p5.getSpectrum().stopRecording();

            }
        }
    }

    private class GenerateListener implements ControlListener {
        public void controlEvent(ControlEvent theEvent) {
            if (recordButton.getStringValue().equals(RECORD_START)) {
                p5.getGenerator().generate();
            }
        }
    }
}
