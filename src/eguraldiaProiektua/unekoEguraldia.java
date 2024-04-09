package eguraldiaProiektua;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class unekoEguraldia {

	private double temperature2M;
	private long weatherCode;
	private double windSpeed10M;
	private long windDirection10M;
	private HashMap<Integer, JLabel> elementuak = new HashMap<>();

	public unekoEguraldia(double temperature, long weatherCode, double windSpeed, long windDirection) {
		setTemperature2M(temperature);
		setWeatherCode(weatherCode);
		setWindDirection10M(windDirection);
		setWindSpeed10M(windSpeed);
	}

	public unekoEguraldia() {
		// TODO Auto-generated constructor stub
	}

	public double getTemperature2M() {
		return temperature2M;
	}

	public void setTemperature2M(double value) {
		this.temperature2M = value;
	}

	public long getWeatherCode() {
		return weatherCode;
	}

	public void setWeatherCode(long value) {
		this.weatherCode = value;
	}

	public double getWindSpeed10M() {
		return windSpeed10M;
	}

	public void setWindSpeed10M(double value) {
		this.windSpeed10M = value;
	}

	public long getWindDirection10M() {
		return windDirection10M;
	}

	public void setWindDirection10M(long value) {
		this.windDirection10M = value;
	}

	public unekoEguraldia lortuUnekoEguraldia(String URLApi) {

		try {
			HttpURLConnection conn = httpAPIKonexioa.fetchConnection(URLApi);

			if (conn.getResponseCode() != 200) {
				JOptionPane.showMessageDialog(null, "Ezin izan da API-ra konektatu", "Error",
						JOptionPane.ERROR_MESSAGE);
				return null;
			}

			// Emaitzak lortu
			StringBuilder resultJson = httpAPIKonexioa.jsonLortu(conn);

			JSONParser parser = new JSONParser();
			JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

			JSONObject unekoa = (JSONObject) resultJsonObj.get("current");

			temperature2M = (double) unekoa.get("temperature_2m");
			windSpeed10M = (double) unekoa.get("wind_speed_10m");
			windDirection10M = (long) unekoa.get("wind_direction_10m");
			weatherCode = (long) unekoa.get("weather_code");

			return new unekoEguraldia(temperature2M, weatherCode, windSpeed10M, windDirection10M);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}