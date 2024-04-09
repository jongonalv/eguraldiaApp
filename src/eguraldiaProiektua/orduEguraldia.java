package eguraldiaProiektua;

public class orduEguraldia {
	
	private String[] time;
	private double[] temperature2M;
	private long[] precipitationProbability;
	private double[] rain;
	private double[] snowfall;
	private long[] weatherCode;
	private double[] windSpeed10M;
	private long[] windDirection10M;

	public String[] getTime() {
		return time;
	}

	public void setTime(String[] value) {
		this.time = value;
	}

	public double[] getTemperature2M() {
		return temperature2M;
	}

	public void setTemperature2M(double[] value) {
		this.temperature2M = value;
	}

	public long[] getPrecipitationProbability() {
		return precipitationProbability;
	}

	public void setPrecipitationProbability(long[] value) {
		this.precipitationProbability = value;
	}

	public double[] getRain() {
		return rain;
	}

	public void setRain(double[] value) {
		this.rain = value;
	}

	public double[] getSnowfall() {
		return snowfall;
	}

	public void setSnowfall(double[] value) {
		this.snowfall = value;
	}

	public long[] getWeatherCode() {
		return weatherCode;
	}

	public void setWeatherCode(long[] value) {
		this.weatherCode = value;
	}

	public double[] getWindSpeed10M() {
		return windSpeed10M;
	}

	public void setWindSpeed10M(double[] value) {
		this.windSpeed10M = value;
	}

	public long[] getWindDirection10M() {
		return windDirection10M;
	}

	public void setWindDirection10M(long[] value) {
		this.windDirection10M = value;
	}
}