package application;

import java.util.LinkedList;

public class vertex  {
	Country country;
	vertex previous;
	int num;
	double distance=Integer.MAX_VALUE;
	boolean visited;
	LinkedList<edges> e = new LinkedList<edges>();

	public vertex(Country country, int number) {
		super();
		this.country = country;
		this.num = number;
	}

	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public LinkedList<edges> getE() {
		return e;
	}

	public void setE(LinkedList<edges> e) {
		this.e = e;
	}

	public boolean FindEdge(String ss) {

		for (int i = 0; i < e.size(); i++) {
			if (e.get(i).getD().getCountry().getName().compareToIgnoreCase(ss) == 0)
				return true;
		}
		return false;
	}

	public String ttoString() {
		String r = country.getName()+":";
		for (int i = 0; i < e.size(); i++) {
			r = r + e.get(i).desination.country.getName() + ",";
		}
		return r;
	}

	
}
