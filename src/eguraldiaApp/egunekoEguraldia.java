package eguraldiaApp;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// TODO: Auto-generated Javadoc
/**
 * The Class egunekoEguraldia.
 */
public class egunekoEguraldia implements Serializable{
	
	/** The weather code. */
	private long[] weatherCode;
	
	/** The temperature 2 M max. */
	private double[] temperature2MMax;
	
	/** The temperature 2 M min. */
	private double[] temperature2MMin;
	
	/** The sunrise. */
	private String[] sunrise;
	
	/** The sunset. */
	private String[] sunset;
	
	/** The precipitation sum. */
	private double[] precipitationSum;
	
	/** The snowfall sum. */
	private double[] snowfallSum;
	
	/** The wind speed 10 M max. */
	private double[] windSpeed10MMax;
	
	/** The wind direction 10 M dominant. */
	private long[] windDirection10MDominant;

	/**
	 * Instantiates a new eguneko eguraldia.
	 *
	 * @param weatherCode the weather code
	 * @param temperature2MMax the temperature 2 M max
	 * @param temperature2MMin the temperature 2 M min
	 * @param sunrise the sunrise
	 * @param sunset the sunset
	 * @param precipitationSum the precipitation sum
	 * @param snowfallSum the snowfall sum
	 * @param windSpeed10MMax the wind speed 10 M max
	 * @param windDirection10MDominant the wind direction 10 M dominant
	 */
	public egunekoEguraldia(long[] weatherCode, double[] temperature2MMax,double[] temperature2MMin,String[] sunrise, String[] sunset,
							double[] precipitationSum, double[] snowfallSum, double[] windSpeed10MMax, long[] windDirection10MDominant) {
		setWeatherCode(weatherCode);
		setTemperature2MMax(temperature2MMax);
		setTemperature2MMin(temperature2MMin);
		setSunrise(sunrise);
		setSunset(sunset);
		setPrecipitationSum(precipitationSum);
		setSnowfallSum(snowfallSum);
		setWindSpeed10MMax(windSpeed10MMax);
		setWindDirection10MDominant(windDirection10MDominant);
	}
	
	/**
	 * Instantiates a new eguneko eguraldia.
	 */
	public egunekoEguraldia() {
		// TODO Auto-generated constructor stub
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
	 * Gets the temperature 2 M max.
	 *
	 * @return the temperature 2 M max
	 */
	public double[] getTemperature2MMax() {
		return temperature2MMax;
	}

	/**
	 * Sets the temperature 2 M max.
	 *
	 * @param value the new temperature 2 M max
	 */
	public void setTemperature2MMax(double[] value) {
		this.temperature2MMax = value;
	}

	/**
	 * Gets the temperature 2 M min.
	 *
	 * @return the temperature 2 M min
	 */
	public double[] getTemperature2MMin() {
		return temperature2MMin;
	}

	/**
	 * Sets the temperature 2 M min.
	 *
	 * @param value the new temperature 2 M min
	 */
	public void setTemperature2MMin(double[] value) {
		this.temperature2MMin = value;
	}

	/**
	 * Gets the sunrise.
	 *
	 * @return the sunrise
	 */
	public String[] getSunrise() {
		return sunrise;
	}

	/**
	 * Sets the sunrise.
	 *
	 * @param value the new sunrise
	 */
	public void setSunrise(String[] value) {
		this.sunrise = value;
	}

	/**
	 * Gets the sunset.
	 *
	 * @return the sunset
	 */
	public String[] getSunset() {
		return sunset;
	}

	/**
	 * Sets the sunset.
	 *
	 * @param value the new sunset
	 */
	public void setSunset(String[] value) {
		this.sunset = value;
	}
	
	/**
	 * Sets the precipitation sum.
	 *
	 * @param precipitationSum the new precipitation sum
	 */
	public void setPrecipitationSum(double[] precipitationSum) {
		this.precipitationSum = precipitationSum;
	}
	
	/**
	 * Gets the precipitation sum.
	 *
	 * @return the precipitation sum
	 */
	public double[] getPrecipitationSum() {
		return precipitationSum;
	}

	/**
	 * Gets the snowfall sum.
	 *
	 * @return the snowfall sum
	 */
	public double[] getSnowfallSum() {
		return snowfallSum;
	}

	/**
	 * Sets the snowfall sum.
	 *
	 * @param value the new snowfall sum
	 */
	public void setSnowfallSum(double[] value) {
		this.snowfallSum = value;
	}

	/**
	 * Gets the wind speed 10 M max.
	 *
	 * @return the wind speed 10 M max
	 */
	public double[] getWindSpeed10MMax() {
		return windSpeed10MMax;
	}

	/**
	 * Sets the wind speed 10 M max.
	 *
	 * @param value the new wind speed 10 M max
	 */
	public void setWindSpeed10MMax(double[] value) {
		this.windSpeed10MMax = value;
	}

	/**
	 * Gets the wind direction 10 M dominant.
	 *
	 * @return the wind direction 10 M dominant
	 */
	public long[] getWindDirection10MDominant() {
		return windDirection10MDominant;
	}

	/**
	 * Sets the wind direction 10 M dominant.
	 *
	 * @param value the new wind direction 10 M dominant
	 */
	public void setWindDirection10MDominant(long[] value) {
		this.windDirection10MDominant = value;
	}

	/**
	 * Lortu eguneko eguraldia API-arem arabera.
	 *
	 * @param URLApi the URL api
	 * @return the eguneko eguraldia
	 */
	public egunekoEguraldia lortuEgunekoEguraldia(String URLApi) {

		try {
			HttpURLConnection conn = httpAPIKonexioa.fetchConnection(URLApi);

			if (conn.getResponseCode() != 200) {
				JOptionPane.showMessageDialog(null, "Ezin izan da API-ra konektatu", "Error",
						JOptionPane.ERROR_MESSAGE);
				return null;
			}

			// Emaitzak lortu
			StringBuilder resultJson = httpAPIKonexioa.jsonLortu(conn);

			// Emaitzak json object bihurtu
			JSONParser parser = new JSONParser();
			JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

			// Eguneko infromazioa lortu json object-aren arabera
			JSONObject egunekoa = (JSONObject) resultJsonObj.get("daily");

			// Datu guztiak lortu, Json array erabiliz, datu guztiak atributuei pasa
			JSONArray weatherCodeArray = (JSONArray) egunekoa.get("weather_code");
			weatherCode = new long[weatherCodeArray.size()];
			for (int i = 0; i < weatherCodeArray.size(); i++) {
			    weatherCode[i] = (long) weatherCodeArray.get(i);
			}

			JSONArray temperature2MMaxArray = (JSONArray) egunekoa.get("temperature_2m_max");
			temperature2MMax = new double[temperature2MMaxArray.size()];
			for (int i = 0; i < temperature2MMaxArray.size(); i++) {
			    temperature2MMax[i] = (double) temperature2MMaxArray.get(i);
			}

			JSONArray temperature2MMinArray = (JSONArray) egunekoa.get("temperature_2m_min");
			temperature2MMin = new double[temperature2MMinArray.size()];
			for (int i = 0; i < temperature2MMinArray.size(); i++) {
			    temperature2MMin[i] = (double) temperature2MMinArray.get(i);
			}

			JSONArray sunriseArray = (JSONArray) egunekoa.get("sunrise");
			sunrise = new String[sunriseArray.size()];
			for (int i = 0; i < sunriseArray.size(); i++) {
			    sunrise[i] = (String) sunriseArray.get(i);
			}

			JSONArray sunsetArray = (JSONArray) egunekoa.get("sunset");
			sunset = new String[sunsetArray.size()];
			for (int i = 0; i < sunsetArray.size(); i++) {
			    sunset[i] = (String) sunsetArray.get(i);
			}

			JSONArray precipitationSumArray = (JSONArray) egunekoa.get("precipitation_sum");
			precipitationSum = new double[precipitationSumArray.size()];
			for (int i = 0; i < precipitationSumArray.size(); i++) {
			    precipitationSum[i] = (double) precipitationSumArray.get(i);
			}

			JSONArray snowfallSumArray = (JSONArray) egunekoa.get("snowfall_sum");
			snowfallSum = new double[snowfallSumArray.size()];
			for (int i = 0; i < snowfallSumArray.size(); i++) {
			    snowfallSum[i] = (double) snowfallSumArray.get(i);
			}

			JSONArray windSpeed10MMaxArray = (JSONArray) egunekoa.get("wind_speed_10m_max");
			windSpeed10MMax = new double[windSpeed10MMaxArray.size()];
			for (int i = 0; i < windSpeed10MMaxArray.size(); i++) {
			    windSpeed10MMax[i] = (double) windSpeed10MMaxArray.get(i);
			}

			JSONArray windDirection10MDominantArray = (JSONArray) egunekoa.get("wind_direction_10m_dominant");
			windDirection10MDominant = new long[windDirection10MDominantArray.size()];
			for (int i = 0; i < windDirection10MDominantArray.size(); i++) {
			    windDirection10MDominant[i] = (long) windDirection10MDominantArray.get(i);
			}
		
			return new egunekoEguraldia(weatherCode, temperature2MMax, temperature2MMin, sunrise, sunset, precipitationSum, snowfallSum, windSpeed10MMax, windDirection10MDominant);
							
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
}

