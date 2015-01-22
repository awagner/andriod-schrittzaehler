package com.example.admin.schrittzaehler;

import android.os.Handler;
import android.os.Message;

/**
 * Dieser Handler übernimmt die Ereignisbehandlung des Listeners
 * AcceleratorListener im Falle einer onSensorChanged Events.
 */
public class AcceleratorHandler extends Handler {

    private SchrittZaehler schrittZaehler;

    public AcceleratorHandler(SchrittZaehler schrittZaehler) {
        this.schrittZaehler = schrittZaehler;
    }

    @Override
    /**
     * überschreibt die Methode handleMessage um in der Ereignisbehandlung die Erhöhung der Schrittzahl
     * auszulösen.
     *
     * @param msg
     */
    public void handleMessage(Message msg) {
        schrittZaehler.erhoeheSchrittAnzahl();
    }
}
