package eguraldiaApp;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// TODO: Auto-generated Javadoc
/**
 * The Class orduEguraldia.
 */
public class orduEguraldia implements Serializable{
	
	/** The time. */
	public String[] time;
	
	/** The temperature 2 M. */
	private double[] temperature2M;
	
	/** The precipitation probability. */
	private long[] precipitationProbability;
	
	/** The rain. */
	private double[] rain;
	
	/** The weather code. */
	private long[] weatherCode;
	
	/** The wind speed 10 M. */
	private double[] windSpeed10M;
	
	/** The wind direction 10 M. */
	private long[] windDirection10M;
	
	/**
	 * Instantiates a new ordu eguraldia.
	 *
	 * @param time the time
	 * @param temperature the temperature
	 * @param precipitation the precipitation
	 * @param rain the rain
	 * @param weatherCode the weather code
	 * @param windspeed the windspeed
	 * @param windDirection the wind direction
	 */
	public orduEguraldia(String[] time, double[] temperature, long precipitation[], double[] rain, long[] weatherCode, double[] windspeed, long[] windDirection) {
		setTime(time);
		setTemperature2M(temperature);
		setPrecipitationProbability(precipitation);
		setRain(rain);
		setWeatherCode(weatherCode);
		setWindSpeed10M(windspeed);
		setWindDirection10M(windDirection);
	}
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(String[] time) {
		this.time = time;
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String[] getTime() {
		return time;
	}
	
	/**
	 * Instantiates a new ordu eguraldia.
	 */
	public orduEguraldia() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the temperature 2 M.
	 *
	 * @return the temperature 2 M
	 */
	public double[] getTemperature2M() {
		return temperature2M;
	}

	/**
	 * Sets the temperature 2 M.
	 *
	 * @param value the new temperature 2 M
	 */
	public void setTemperature2M(double[] value) {
		this.temperature2M = value;
	}

	/**
	 * Gets the precipitation probability.
	 *
	 * @return the precipitation probability
	 */
	public long[] getPrecipitationProbability() {
		return precipitationProbability;
	}

	/**
	 * Sets the precipitation probability.
	 *
	 * @param value the new precipitation probability
	 */
	public void setPrecipitationProbability(long[] value) {
		this.precipitationProbability = value;
	}

	/**
	 * Gets the rain.
	 *
	 * @return the rain
	 */
	public double[] getRain() {
		return rain;
	}

	/**
	 * Sets the rain.
	 *
	 * @param value the new rain
	 */
	public void setRain(double[] value) {
		this.rain = value;
	}

	/**
	 * Gets the weather code.
	 *
	 * @return the weather code
	 */
	public long[] getWeatherCode() {
		return weatherCode;
	}

	/**
	 * Sets the weather code.
	 *
	 * @param value the new weather code
	 */
	public void setWeatherCode(long[] value) {
		this.weatherCode = value;
	}

	/**
	 * Gets the wind speed 10 M.
	 *
	 * @return the wind speed 10 M
	 */
	public double[] getWindSpeed10M() {
		return windSpeed10M;
	}

	/**
	 * Sets the wind speed 10 M.
	 *
	 * @param value the new wind speed 10 M
	 */
	public void setWindSpeed10M(double[] value) {
		this.windSpeed10M = value;
	}

	/**
	 * Gets the wind direction 10 M.
	 *
	 * @return the wind direction 10 M
	 */
	public long[] getWindDirection10M() {
		return windDirection10M;
	}

	/**
	 * Sets the wind direction 10 M.
	 *
	 * @param value the new wind direction 10 M
	 */
	public void setWindDirection10M(long[] value) {
		this.windDirection10M = value;
	}

	/**
	 * Lortu ordu eguraldia.
	 *
	 * @param URLApi the URL api
	 * @return the ordu eguraldia
	 */
	public orduEguraldia lortuOrduEguraldia(String URLApi) {

		// 	API-arekin konexioa egin
		try {
			HttpURLConnection conn = httpAPIKonexioa.fetchConnection(URLApi);

			if (conn.getResponseCode() != 200) {
				JOptionPane.showMessageDialog(null, "Ezin izan da API-ra konektatu", "Error",
						JOptionPane.ERROR_MESSAGE);
				return null;
			}
			
			// Emaitzak lortu api-aren arabera, konexioa erabiliz json-a lortzen da
			StringBuilder resultJson = httpAPIKonexioa.jsonLortu(conn);
			
			// Informazioa jsonobject bihurtu
			JSONParser parser = new JSONParser();
			JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

			// Obektua sortu bakarrik orduko informazioa hartzeko
			JSONObject ordukoa = (JSONObject) resultJsonObj.get("hourly");

			// Array ezberdinak sortu orduko informazio guztia gordetzeko
			JSONArray timeArray = (JSONArray) ordukoa.get("time");
			JSONArray weatherCodeArray = (JSONArray) ordukoa.get("weather_code");
			JSONArray temperature2MMaxArray = (JSONArray) ordukoa.get("temperature_2m");
			JSONArray precipitationProbabilityArray = (JSONArray) ordukoa.get("precipitation_probability");
			JSONArray rainArray = (JSONArray) ordukoa.get("rain");
			JSONArray windArray = (JSONArray) ordukoa.get("wind_speed_10m");
			JSONArray windDirectionJsonArray = (JSONArray) ordukoa.get("wind_direction_10m");

			// Uneko ordukoaren indizea lortu
			LocalDateTime now = LocalDateTime.now();
			int hasiera = 0;
			for (int i = 0; i < timeArray.size(); i++) {
			    String timeString = (String) timeArray.get(i);
			    LocalDateTime time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			    if (time.isAfter(now) || time.isEqual(now)) {
			        hasiera = i;
			        break;
			    }
			}

			// redimensionatu array-ak tamaina berrira
			int tamainaArray 			= timeArray.size() - hasiera;
			time 						= new String[tamainaArray];
			weatherCode 				= new long[tamainaArray];
			temperature2M 				= new double[tamainaArray];
			precipitationProbability 	= new long[tamainaArray];
			rain 						= new double[tamainaArray];
			windSpeed10M 				= new double[tamainaArray];
			windDirection10M 			= new long[tamainaArray];

			// Datuak gehitu lista ezberdineta kontuan hartuz tamaina berria
			for (int i = hasiera; i < timeArray.size(); i++) {
			    time[i - hasiera] 						= (String) timeArray.get(i);
			    weatherCode[i - hasiera] 				= (long) weatherCodeArray.get(i);
			    temperature2M[i - hasiera] 				= (double) temperature2MMaxArray.get(i);
			    precipitationProbability[i - hasiera] 	= (long) precipitationProbabilityArray.get(i);
			    rain[i - hasiera] 						= (double) rainArray.get(i);
			    windSpeed10M[i - hasiera] 				= (double) windArray.get(i);
			    windDirection10M[i - hasiera] 			= (long) windDirectionJsonArray.get(i);
			}

			// Ordu eguraldi berria sortu
			return new orduEguraldia(time, temperature2M, precipitationProbability, rain, weatherCode, windSpeed10M, windDirection10M);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}