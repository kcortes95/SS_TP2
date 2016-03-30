import java.util.*;

public class Particle implements Comparable<Particle>{
	
	
	private static int counter = 1;
	private int ID;
	
	private Position pos; //me dice la posicion en este momento
	private Velocity v;
	//private Position posNext; //me dice cual es la posicion en el siguiente momento!! 
	
	private double radio;
	private double color;
	
	public Particle(double radio, double color, double x, double y, Velocity v) {
		this.pos = new Position(x,y);
		this.v = v;
		this.radio = radio;
		this.color = color;
		this.ID = counter++;
	}
	
	/*public Particle(double radio, double color, double x, double y){
		this(radio,color,x,y,0,0);
	}*/
	
	public Velocity getV() {
		return v;
	}
	
	
	public Position getPosition(){
		return pos;
	}
	
	
	public double getRadio() {
		return radio;
	}
	

	public void setColor(double color) {
		this.color = color;
	}
	
	public int getID(){
		return this.ID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Particle)){
			return false;
		}else{
			Particle particleObj = (Particle) obj;
			return this.ID == particleObj.ID;
		}
	}
	
	public String toString(){
		return "" + ID;
	}

	@Override
	public int compareTo(Particle arg0) {
		return this.ID-arg0.getID();
	}
	
	public void updatePos(double time){
		this.pos = new Position(pos.getX()+v.getXVelocity()*time,pos.getY()+v.getYVelocity()*time);
	}
	
	public void setAngle(double ang){
		this.v.setAngle(ang);
	}
}
