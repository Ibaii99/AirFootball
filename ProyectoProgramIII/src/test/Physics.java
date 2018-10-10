package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import Objetos.Pelota;
import physics.PhysicObject;

class Physics {
	
	private PhysicObject object1;
	private PhysicObject object2;

	private void crearPelotas() {
		char r = 'a';
		object1 = new Pelota(10, 10,r , true, false);
		object2 = new Pelota(10, 10,r , true, false);
	}
	
	public void colocarPelotas() {
		object1.setX(10);		object1.setY(10);
		object2.setX(10);		object2.setY(50);
	}
	
	@Test
	void testChocaConBorde() {
		crearPelotas();
		colocarPelotas();
		object1.addVelocidad(0,5);
		while(object1.chocaConObjeto(object2) != null) {
			System.out.println(object1.getX()+ "/n"+ "Y:" + object1.getY());
		}
		fail("Not yet implemented");
	}

	@Test
	void testChocaConObjeto() {
		fail("Not yet implemented");
	}

	@Test
	void testContieneA() {
		fail("Not yet implemented");
	}

	@Test
	void testCalcVelocidadDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testCalcAceleracion() {
		fail("Not yet implemented");
	}

	@Test
	void testCalcEspacioDoubleLongDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testCalcEspacioDoubleLongDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testCalcTiempoHastaEspacioDoubleDoubleDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testCalcVelocidadDoubleLongDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testCalcTiempoHastaEspacioDoubleDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testCalcChoque() {
		fail("Not yet implemented");
	}

	@Test
	void testIgualACero() {
		fail("Not yet implemented");
	}

}
