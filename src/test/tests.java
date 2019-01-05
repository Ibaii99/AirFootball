package test;

import java.util.Random;

public class tests {

	public static void main(String[] args) {
		Random r = new Random();
		int low = 3;
		int high = 5;
		int result = r.nextInt(high-low) + low;
		System.out.println(result);
	}

}
