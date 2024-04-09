package eguraldiaProiektua;

public class eguraldia {

	orduEguraldia orduEguraldia;
	egunekoEguraldia egunekoEguraldia;
	unekoEguraldia unekoEguraldia;
	
	public eguraldia(unekoEguraldia unekoEguraldia) {
		setUnekoEguraldia(unekoEguraldia);
	}
	
	public eguraldia() {
	}
	
	public void setEgunekoEguraldia(egunekoEguraldia egunekoEguraldia) {
		this.egunekoEguraldia = egunekoEguraldia;
	}
	
	public void setOrduEguraldia(orduEguraldia orduEguraldia) {
		this.orduEguraldia = orduEguraldia;
	}
	
	public void setUnekoEguraldia(unekoEguraldia unekoEguraldia) {
		this.unekoEguraldia = unekoEguraldia;
	}
	
	public unekoEguraldia getUnekoEguraldia() {
		return unekoEguraldia;
	}
	
	public egunekoEguraldia getEgunekoEguraldia() {
		return egunekoEguraldia;
	}
	
	public orduEguraldia getOrduEguraldia() {
		return orduEguraldia;
	}

	public eguraldia getEguraldiDatuak(Geokokapena geokokapena) {
		
		double latitudea = geokokapena.getLatitude();
		double longitudea = geokokapena.getLongitude();
		
		String urlString = "https://api.open-meteo.com/v1/forecast?"
						 + "latitude=" + latitudea + "&longitude=" + longitudea 
						 + "&current=temperature_2m,weather_code,wind_speed_10m,"
						 + "wind_direction_10m&hourly=temperature_2m,precipitation_probability,"
						 + "rain,snowfall,weather_code,wind_speed_10m,wind_direction_10m&daily=weather_code,"
						 + "temperature_2m_max,temperature_2m_min,sunrise,sunset,snowfall_sum,"
						 + "wind_speed_10m_max,wind_direction_10m_dominant";
		
		unekoEguraldia = new unekoEguraldia();
		unekoEguraldia = unekoEguraldia.lortuUnekoEguraldia(urlString);
		
		return new eguraldia(unekoEguraldia);
		
	}		
}