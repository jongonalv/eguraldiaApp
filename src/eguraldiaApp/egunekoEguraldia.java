package eguraldiaApp;

import java.time.LocalDate;

public class egunekoEguraldia {
	private LocalDate[] time;
	private long[] weatherCode;
	private double[] temperature2MMax;
	private double[] temperature2MMin;
	private String[] sunrise;
	private String[] sunset;
	private double[] snowfallSum;
	private double[] windSpeed10MMax;
	private long[] windDirection10MDominant;

	public LocalDate[] getTime() {
		return time;
	}

	public void setTime(LocalDate[] value) {
		this.time = value;
	}

	public long[] getWeatherCode() {
		return weatherCode;
	}

	public void setWeatherCode(long[] value) {
		this.weatherCode = value;
	}

	public double[] getTemperature2MMax() {
		return temperature2MMax;
	}

	public void setTemperature2MMax(double[] value) {
		this.temperature2MMax = value;
	}

	public double[] getTemperature2MMin() {
		return temperature2MMin;
	}

	public void setTemperature2MMin(double[] value) {
		this.temperature2MMin = value;
	}

	public String[] getSunrise() {
		return sunrise;
	}

	public void setSunrise(String[] value) {
		this.sunrise = value;
	}

	public String[] getSunset() {
		return sunset;
	}

	public void setSunset(String[] value) {
		this.sunset = value;
	}

	public double[] getSnowfallSum() {
		return snowfallSum;
	}

	public void setSnowfallSum(double[] value) {
		this.snowfallSum = value;
	}

	public double[] getWindSpeed10MMax() {
		return windSpeed10MMax;
	}

	public void setWindSpeed10MMax(double[] value) {
		this.windSpeed10MMax = value;
	}

	public long[] getWindDirection10MDominant() {
		return windDirection10MDominant;
	}

	public void setWindDirection10MDominant(long[] value) {
		this.windDirection10MDominant = value;
	}
}
