package eguraldiaApp;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.io.Serializable;
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

// TODO: Auto-generated Javadoc
/**
 * The Class unekoEguraldia.
 */
public class unekoEguraldia implements Serializable{

	/** The temperature 2 M. */
	private double temperature2M;
	
	/** The weather code. */
	private long weatherCode;
	
	/** The wind speed 10 M. */
	private double windSpeed10M;
	
	/** The wind direction 10 M. */
	private long windDirection10M;
	
	/** The elementuak. */
	private HashMap<Integer, JLabel> elementuak = new HashMap<>();

	/**
	 * Instantiates a new uneko eguraldia.
	 *
	 * @param temperature the temperature
	 * @param weatherCode the weather code
	 * @param windSpeed the wind speed
	 * @param windDirection the wind direction
	 */
	public unekoEguraldia(double temperature, long weatherCode, double windSpeed, long windDirection) {
		setTemperature2M(temperature);
		setWeatherCode(weatherCode);
		setWindDirection10M(windDirection);
		setWindSpeed10M(windSpeed);
	}

	/**
	 * Instantiates a new uneko eguraldia.
	 */
	public unekoEguraldia() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the temperature 2 M.
	 *
	 * @return the temperature 2 M
	 */
	public double getTemperature2M() {
		return temperature2M;
	}

	/**
	 * Sets the temperature 2 M.
	 *
	 * @param value the new temperature 2 M
	 */
	public void setTemperature2M(double value) {
		this.temperature2M = value;
	}

	/**
	 * Gets the weather code.
	 *
	 * @return the weather code
	 */
	public long getWeatherCode() {
		return weatherCode;
	}

	/**
	 * Sets the weather code.
	 *
	 * @param value the new weather code
	 */
	public void setWeatherCode(long value) {
		this.weatherCode = value;
	}

	/**
	 * Gets the wind speed 10 M.
	 *
	 * @return the wind speed 10 M
	 */
	public double getWindSpeed10M() {
		return windSpeed10M;
	}

	/**
	 * Sets the wind speed 10 M.
	 *
	 * @param value the new wind speed 10 M
	 */
	public void setWindSpeed10M(double value) {
		this.windSpeed10M = value;
	}

	/**
	 * Gets the wind direction 10 M.
	 *
	 * @return the wind direction 10 M
	 */
	public long getWindDirection10M() {
		return windDirection10M;
	}

	/**
	 * Sets the wind direction 10 M.
	 *
	 * @param value the new wind direction 10 M
	 */
	public void setWindDirection10M(long value) {
		this.windDirection10M = value;
	}

	/**
	 * Lortu uneko eguraldia.
	 *
	 * @param URLApi the URL api
	 * @return the uneko eguraldia
	 */
	public unekoEguraldia lortuUnekoEguraldia(String URLApi) {

		// API konexioa egin
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