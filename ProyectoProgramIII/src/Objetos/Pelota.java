package Objetos;

import java.awt.Point;

import physics.PhysicObject;

public class Pelota extends PhysicObject{

	public Pelota(double x, double y, char color, boolean rebota, boolean elementoEstatico) {
		super(x, y, color, rebota, elementoEstatico);
		
	}

	@Override
	public double getVolumen() {
		return 0;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int chocaConBorde(PhysicObject v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point chocaConObjeto(PhysicObject objeto2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contieneA(Point punto) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
