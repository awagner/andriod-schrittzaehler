package com.example.admin.schrittzaehler;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Dieser Listener wertet den Sensorevent "onSensorchanged" des
 * Acceleratorsensor aus.
 */
public class AcceleratorListener implements SensorEventListener {

    private AcceleratorHandler acceleratorHandler;
    private float schwellwert;
    // Zustandsvariable zur unterschiedlichen Auswertung eines Events.
    private int zustand = 0; // 0 = Schritt noch nicht begonnen, 1 = Schritt begonnen.

    /**
     * erzeugt ein Object der Klasse und setzt die Attributwerte.
     *
     * @param acceleratorHandler Referenz auf den Handler, der die Schritterhöhung anstößt.
     */
    public AcceleratorListener(AcceleratorHandler acceleratorHandler, float schwellwert) {
        this.acceleratorHandler = acceleratorHandler;
        this.schwellwert = schwellwert;
    }

    /**
     * die Methode onSensoChanged wird vom Sensor bei einem Event aufgerufen
     *
     * @param event enthält die Eventdaten
     */
    public void onSensorChanged(SensorEvent event) {

        float x = (float) event.values[0];
        float y = (float) event.values[1];
        float z = (float) event.values[2];

        switch (zustand) {

            case 0: // Schritt beendet, prüfe auf Schrittanfang
                if (x > schwellwert) {// Schritt begonnen.
                    zustand = 1;
                }
                break;

            case 1: // Schritt begonnen, prüfe auf Schrittende.

                if (x < -schwellwert) {// Schritt erfolgt.
                    acceleratorHandler.sendEmptyMessage(0);
                    zustand = 0;
                }
                break;
        }
    }

    /**
     * diese Methode muss implementiert werden, obwohl man sie für diese Anwendung nicht braucht
     */
    public void onAccuracyChanged(Sensor s, int number) {

    }

}
