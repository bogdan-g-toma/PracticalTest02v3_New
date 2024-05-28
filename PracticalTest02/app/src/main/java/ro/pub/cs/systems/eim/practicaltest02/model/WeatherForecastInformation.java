package ro.pub.cs.systems.eim.practicaltest02.model;

import android.support.annotation.NonNull;

public class WeatherForecastInformation {

    private final String definition;

    public WeatherForecastInformation(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    @NonNull
    @Override
    public String toString() {
        return "WeatherForecastInformation{" +
                "definition='" + definition + '\'' +
                '}';
    }

}
