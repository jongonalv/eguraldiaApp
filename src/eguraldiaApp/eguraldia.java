package eguraldiaApp;

public class eguraldia{
	private double latitude;
	private double longitude;
	private double generationtimeMS;
	private long utcOffsetSeconds;
	private String timezone;
	private String timezoneAbbreviation;
	private double elevation;
	private unekoEguraldia current;
	private orduEguraldia hourly;
	private egunekoEguraldia daily;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double value) {
		this.latitude = value;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double value) {
		this.longitude = value;
	}

	public double getGenerationtimeMS() {
		return generationtimeMS;
	}

	public void setGenerationtimeMS(double value) {
		this.generationtimeMS = value;
	}

	public long getUTCOffsetSeconds() {
		return utcOffsetSeconds;
	}

	public void setUTCOffsetSeconds(long value) {
		this.utcOffsetSeconds = value;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String value) {
		this.timezone = value;
	}

	public String getTimezoneAbbreviation() {
		return timezoneAbbreviation;
	}

	public void setTimezoneAbbreviation(String value) {
		this.timezoneAbbreviation = value;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double value) {
		this.elevation = value;
	}

	public void getEguraldiDatuak(Geokokapena geokokapena) {
		
		double latitudea = geokokapena.getLatitude();
		double longitudea = geokokapena.getLongitude();
		
		String urlString = "https://api.open-meteo.com/v1/forecast?"
						 + "latitude=" + latitudea + "&longitude=" + longitudea 
						 + "&current=temperature_2m,weather_code,wind_speed_10m,"
						 + "wind_direction_10m&hourly=temperature_2m,precipitation_probability,"
						 + "rain,snowfall,weather_code,wind_speed_10m,wind_direction_10m&daily=weather_code,"
						 + "temperature_2m_max,temperature_2m_min,sunrise,sunset,snowfall_sum,"
						 + "wind_speed_10m_max,wind_direction_10m_dominant";
						 
		unekoEguraldia unekoEguraldia = new unekoEguraldia();		
		unekoEguraldia = unekoEguraldia.lortuUnekoEguraldia(urlString);
		
	
	}		
}