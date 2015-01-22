package com.example.admin.schrittzaehler;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SchrittZaehler extends ActionBarActivity {

    // Referenez auf den SensorManager
    private SensorManager sMgr;
    // Referenz auf den Beschleunigungssensor
    private Sensor schrittsensor;
    // Listener für den Beschleunigungs-Event
    private AcceleratorListener acceleratorListener;
    // Ereignisbehandlung für den Beschleugigungs-Evet
    private AcceleratorHandler acceleratorHandler;
    // Speichert die Anzahl der Schritte
    private int schritte;
    // View für die Schrittanzahl
    private TextView labelschritte;

    @Override
    /**
     * alle Arbeiten, die beim Erzeugen der App erledigt werden müssen
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schritt_zaehler);

        // Referenz auf die Textview zur Anzeige der Schritte holen.
        labelschritte = (TextView) this.findViewById(R.id.labelschritte);

        // hole den Sensor Manager.
        sMgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        schrittsensor = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Handler und Listener erzeugen.
        acceleratorHandler = new AcceleratorHandler(this);
        acceleratorListener = new AcceleratorListener(acceleratorHandler, 1.0f);

        reset();
    }

    /** erhöht die Anzahl der Schritte und aktualisiere die View*/
    public void erhoeheSchrittAnzahl() {
        schritte++;
        updateView();
    }

    /** die Anzahl der schritte auf 0 setzen und die View aktualisieren */
    private void reset() {
        schritte = 0;
        updateView();
    }

    /** aktualisiere die View */
    private void updateView() {
        labelschritte.setText(Integer.toString(schritte));
    }

    /** Eventhandling beim Aufwecken:
     *  regisriert die Listener wenn die Activity in den Vordergrund rückt
     **/
    public void onResume() {

        super.onResume();
        sMgr.registerListener(acceleratorListener, schrittsensor, SensorManager.SENSOR_DELAY_GAME);

    }

    /** Eventhandling beim Pausieren:
     * löscht die Listenerregistrierung, wenn die Activity in den Hintergrund fällt
     **/
    public void onPause() {
        super.onPause();
        sMgr.unregisterListener(acceleratorListener);
    }

    /** Eventhandling für den Zurücksetzen Button
     *
     * @param v Referenz auf die View.
     */
    public void onClickReset(View v) {
        reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schritt_zaehler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
