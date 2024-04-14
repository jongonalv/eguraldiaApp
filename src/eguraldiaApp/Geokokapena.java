package eguraldiaApp;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Geokokapena.
 */
public class Geokokapena implements Serializable{

	/** The id. */
	private float id;
	
	/** The name. */
	private String name;
	
	/** The latitude. */
	private float latitude;
	
	/** The longitude. */
	private float longitude;
	
	/** The elevation. */
	private float elevation;
	
	/** The country code. */
	private String country_code;
	
	/** The timezone. */
	private String timezone;
	
	/** The population. */
	private float population;
	
	/** The postcodes. */
	ArrayList<Object> postcodes = new ArrayList<Object>();
	
	/** The country id. */
	private float country_id;
	
	/** The country. */
	private String country;
	
	/** The admin 1. */
	private String admin1;
	
	/** The admin 2. */
	private String admin2;

	/**
	 * Geokokapen berri bat iniziazatu.
	 *
	 * @param id the id
	 * @param name the name
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param elevation the elevation
	 * @param country_code the country code
	 * @param timezone the timezone
	 * @param population the population
	 * @param postcodes the postcodes
	 * @param country_id the country id
	 * @param country the country
	 * @param admin1 the admin 1
	 * @param admin2 the admin 2
	 */
	public Geokokapena(float id, String name, float latitude, float longitude, float elevation
			  , String country_code, String timezone, float population, ArrayList<Object> postcodes
			  , float country_id, String country, String admin1, String admin2) {
		setId(id);
		setName(name);
		setLatitude(latitude);
		setLongitude(longitude);
		setElevation(elevation);
		setCountry_code(country_code);
		setTimezone(timezone);
		setPopulation(population);
		setPostcodes(postcodes);
		setCountry_id(country_id);
		setCountry(country);
		setAdmin1(admin1);
		setAdmin2(admin2);
	  }

	// Getter Methods

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public float getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * Gets the elevation.
	 *
	 * @return the elevation
	 */
	public float getElevation() {
		return elevation;
	}

	/**
	 * Gets the country code.
	 *
	 * @return the country code
	 */
	public String getCountry_code() {
		return country_code;
	}

	/**
	 * Gets the timezone.
	 *
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * Gets the population.
	 *
	 * @return the population
	 */
	public float getPopulation() {
		return population;
	}

	/**
	 * Gets the country id.
	 *
	 * @return the country id
	 */
	public float getCountry_id() {
		return country_id;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Gets the postcodes.
	 *
	 * @return the postcodes
	 */
	public ArrayList<Object> getPostcodes() {
		return postcodes;
	}
	
	/**
	 * Gets the admin 1.
	 *
	 * @return the admin 1
	 */
	public String getAdmin1() {
		return admin1;
	}
	
	/**
	 * Gets the admin 2.
	 *
	 * @return the admin 2
	 */
	public String getAdmin2() {
		return admin2;
	}

	// Setter Methods

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(float id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * Sets the elevation.
	 *
	 * @param elevation the new elevation
	 */
	public void setElevation(float elevation) {
		this.elevation = elevation;
	}

	/**
	 * Sets the country code.
	 *
	 * @param country_code the new country code
	 */
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	/**
	 * Sets the timezone.
	 *
	 * @param timezone the new timezone
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	/**
	 * Sets the population.
	 *
	 * @param population the new population
	 */
	public void setPopulation(float population) {
		this.population = population;
	}

	/**
	 * Sets the country id.
	 *
	 * @param country_id the new country id
	 */
	public void setCountry_id(float country_id) {
		this.country_id = country_id;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Sets the postcodes.
	 *
	 * @param postcodes the new postcodes
	 */
	public void setPostcodes(ArrayList<Object> postcodes) {
		this.postcodes = postcodes;
	}
/**
 * Finalize.
 *
 * @throws Throwable the throwable
 */
@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	/**
	 * Sets the admin 1.
	 *
	 * @param admin1 the new admin 1
	 */
	public void setAdmin1(String admin1) {
		this.admin1 = admin1;
	}
	
	/**
	 * Sets the admin 2.
	 *
	 * @param admin2 the new admin 2
	 */
	public void setAdmin2(String admin2) {
		this.admin2 = admin2;
	}
}
