package ro.pub.cs.systems.eim.practicaltest02.network;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.practicaltest02.general.Constants;
import ro.pub.cs.systems.eim.practicaltest02.general.Utilities;

public class ClientThread extends Thread {

    private final String address;
    private final int port;
    private final String city;
    private final TextView weatherForecastTextView;

    private Socket socket;

    public ClientThread(String address, int port, String city, TextView weatherForecastTextView) {
        this.address = address;
        this.port = port;
        this.city = city;
        this.weatherForecastTextView = weatherForecastTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            printWriter.println(city);
            printWriter.flush();
            printWriter.flush();
            String weatherInformation;
            while ((weatherInformation = bufferedReader.readLine()) != null) {
                final String finalizedWeatherInformation = weatherInformation;
                weatherForecastTextView.post(() -> weatherForecastTextView.setText(finalizedWeatherInformation));
            }
        } catch (IOException ioException) {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                }
            }
        }
    }

}
