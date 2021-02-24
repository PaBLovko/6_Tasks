package by.epam.tasks.fourth;

import java.util.Random;

public class Ship {
	Random random = new Random();
	public int capacity;
	public boolean bool;
	int piers;

	public int getPiers() {
		return piers;
	}

	public void setPiers(int piers) {
		this.piers = piers;
	}

	public Ship() {
		this.bool = random.nextBoolean();
		if (this.bool) capacity = 10;
		else capacity = 0;
	}
}
