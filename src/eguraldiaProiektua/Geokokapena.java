package eguraldiaProiektua;

import java.util.ArrayList;

public class Geokokapena {

	private float id;
	private String name;
	private float latitude;
	private float longitude;
	private float elevation;
	private String country_code;
	private String timezone;
	private float population;
	ArrayList<Object> postcodes = new ArrayList<Object>();
	private float country_id;
	private String country;
	private String admin1;
	private String admin2;

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

	public float getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public float getElevation() {
		return elevation;
	}

	public String getCountry_code() {
		return country_code;
	}

	public String getTimezone() {
		return timezone;
	}

	public float getPopulation() {
		return population;
	}

	public float getCountry_id() {
		return country_id;
	}

	public String getCountry() {
		return country;
	}

	public ArrayList<Object> getPostcodes() {
		return postcodes;
	}
	
	public String getAdmin1() {
		return admin1;
	}
	
	public String getAdmin2() {
		return admin2;
	}

	// Setter Methods

	public void setId(float id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setElevation(float elevation) {
		this.elevation = elevation;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public void setPopulation(float population) {
		this.population = population;
	}

	public void setCountry_id(float country_id) {
		this.country_id = country_id;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostcodes(ArrayList<Object> postcodes) {
		this.postcodes = postcodes;
	}@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	public void setAdmin1(String admin1) {
		this.admin1 = admin1;
	}
	
	public void setAdmin2(String admin2) {
		this.admin2 = admin2;
	}
}