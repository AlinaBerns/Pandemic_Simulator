package be.intecbrussel.PandemicSimulatorApp;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class TemperatureSorter implements Comparator <Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getTemperature() - o2.getTemperature();
    }
}
