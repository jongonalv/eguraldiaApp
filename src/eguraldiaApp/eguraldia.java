package eguraldiaApp;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class eguraldia.
 */
public class eguraldia implements Serializable{

	/** The ordu eguraldia. */
	orduEguraldia orduEguraldia;
	
	/** The eguneko eguraldia. */
	egunekoEguraldia egunekoEguraldia;
	
	/** The uneko eguraldia. */
	unekoEguraldia unekoEguraldia;
	
	/**
	 * Eguraldi bat inizializatzeko eraikitzailea.
	 *
	 * @param unekoEguraldia the uneko eguraldia
	 * @param egunekoEguraldia the eguneko eguraldia
	 * @param orduEguraldia the ordu eguraldia
	 */
	public eguraldia(unekoEguraldia unekoEguraldia, egunekoEguraldia egunekoEguraldia, orduEguraldia orduEguraldia) {
		setUnekoEguraldia(unekoEguraldia);
		setEgunekoEguraldia(egunekoEguraldia);
		setOrduEguraldia(orduEguraldia);
	}
	
	/**
	 * Instantiates a new eguraldia.
	 */
	public eguraldia() {
	}
	
	/**
	 * Sets the eguneko eguraldia.
	 *
	 * @param egunekoEguraldia the new eguneko eguraldia
	 */
	public void setEgunekoEguraldia(egunekoEguraldia egunekoEguraldia) {
		this.egunekoEguraldia = egunekoEguraldia;
	}
	
	/**
	 * Sets the ordu eguraldia.
	 *
	 * @param orduEguraldia the new ordu eguraldia
	 */
	public void setOrduEguraldia(orduEguraldia orduEguraldia) {
		this.orduEguraldia = orduEguraldia;
	}
	
	/**
	 * Sets the uneko eguraldia.
	 *
	 * @param unekoEguraldia the new uneko eguraldia
	 */
	public void setUnekoEguraldia(unekoEguraldia unekoEguraldia) {
		this.unekoEguraldia = unekoEguraldia;
	}
	
	/**
	 * Gets the uneko eguraldia.
	 *
	 * @return the uneko eguraldia
	 */
	public unekoEguraldia getUnekoEguraldia() {
		return unekoEguraldia;
	}
	
	/**
	 * Gets the eguneko eguraldia.
	 *
	 * @return the eguneko eguraldia
	 */
	public egunekoEguraldia getEgunekoEguraldia() {
		return egunekoEguraldia;
	}
	
	/**
	 * Gets the ordu eguraldia.
	 *
	 * @return the ordu eguraldia
	 */
	public orduEguraldia getOrduEguraldia() {
		return orduEguraldia;
	}

	/**
	 * Eguraldiaren datuak lortu. Hemen, API-aren helbidea jartzen da
	 * eguraldiaren datuak lortzeko eta 3 klase ezberdinei deitzen zaie
	 * eguraldi objektu ezberdinak sortzeko.
	 *
	 * @param geokokapena --> kokapena, eguraldiaren datuak lortzeko
	 * @return the eguraldi --> eguraldia
	 */
	public eguraldia getEguraldiDatuak(Geokokapena geokokapena) {
		
		// Kokapenaren latitudea eta longitudea lortu
		double latitudea = geokokapena.getLatitude();
		double longitudea = geokokapena.getLongitude();
		
		// API-aren URL-a
		String urlString = "https://api.open-meteo.com/v1/forecast?"
                + "latitude=" + latitudea + "&longitude=" + longitudea 
                + "&timezone=Europe/Madrid"
                + "&current=temperature_2m,weather_code,wind_speed_10m,"
                + "wind_direction_10m&hourly=temperature_2m,precipitation_probability,"
                + "rain,snowfall,weather_code,wind_speed_10m,wind_direction_10m&daily=weather_code,"
                + "temperature_2m_max,temperature_2m_min,sunrise,sunset,precipitation_sum,snowfall_sum,"
                + "wind_speed_10m_max,wind_direction_10m_dominant";

		// Eguraldi ezberdinak inizializatu eta bueltatu
		unekoEguraldia = new unekoEguraldia();
		unekoEguraldia = unekoEguraldia.lortuUnekoEguraldia(urlString);
		
		egunekoEguraldia = new egunekoEguraldia();
		egunekoEguraldia = egunekoEguraldia.lortuEgunekoEguraldia(urlString);
		
		orduEguraldia = new orduEguraldia();
		orduEguraldia = orduEguraldia.lortuOrduEguraldia(urlString);
		
		return new eguraldia(unekoEguraldia, egunekoEguraldia, orduEguraldia);
		
	}		
}