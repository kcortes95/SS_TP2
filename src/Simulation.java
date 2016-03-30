import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Simulation {
	
	private Grid grid;
	private double Rc;
	private double totalTime;
	private double intervals;
	private double noiseAmp;
	private Set<Particle> particles;
	private Map<Particle,Set<Particle>> condition = new TreeMap<>();
	
	/**
	 * 
	 * @param grid
	 * @param totalTime - Total simulation runtime in seconds
	 * @param intervals - Time in between calculation periods in seconds
	 */
	public Simulation(Grid grid, double totalTime, double intervals, double Rc, double noiseAmp, Set<Particle> set){
		if(totalTime<0 || intervals<0 || intervals>totalTime)
			throw new IllegalArgumentException("Invalid time parameters");
		this.grid = grid;
		this.totalTime = totalTime;
		this.intervals = intervals;
		this.Rc = Rc;
		this.noiseAmp = noiseAmp;
		this.particles = set;
	}
	
	public void run(){
		double time = 0;
		while(time<=totalTime){
			simulate();
			Output.getInstace().write(particles,time);
			time += intervals;
		}
	}
	
	private void simulate(){
		calculateNeighbours();
		updateParticles();
	}
	
	private void updateParticles(){
		for(Particle p: particles){
			p.updatePos(intervals);
			double avAngle = getAverageAngle(p);
			p.setAngle(avAngle + (Math.random()*2-1)*noiseAmp);
		}
	}
	
	private double getAverageAngle(Particle p){
		double totalSin = Math.sin(p.getV().getAngle());
		double totalCos = Math.cos(p.getV().getAngle());
		if(condition.containsKey(p)){
			for(Particle n: condition.get(p)){
				totalSin += Math.sin(n.getV().getAngle());
				totalCos += Math.cos(n.getV().getAngle());
			}
			totalSin /= condition.get(p).size()+1;
			totalCos /= condition.get(p).size()+1;
		}
		return Math.atan(totalSin/totalCos);
	}
	
	private void calculateNeighbours(){
		int M = grid.getM();
		for(int i=0; i<M; i++){
			for(int j=0; j<M; j++){
				for(Particle p1: grid.getCell(i, j).getParticles()){
					// Checks first for other particles in its same cell
					for(Particle paux: grid.getCell(i, j).getParticles()){
						if(!p1.equals(paux)){
							if(getDistance(p1,paux)<=Rc){
								addToCondition(p1,paux);
							}
						}
					}
					// Then checks for particles in adjacent cells
					for(Cell neighbourCell : grid.getCell(i, j).getNeighbours()){
						for(Particle p2: neighbourCell.getParticles()){
							if(getDistance(p1,p2)<=Rc){
								addToCondition(p1,p2);
								addToCondition(p2,p1);
							}	
						}
					}
				}
			}
		}
	}
	
	private void addToCondition(Particle p1, Particle p2){
		if(!condition.containsKey(p1))
			condition.put(p1, new HashSet<Particle>());
		condition.get(p1).add(p2);
	}
	
	private double getDistance(Particle p1, Particle p2){
		return Math.sqrt(Math.pow(p1.getPosition().getX()-p2.getPosition().getX(), 2) + Math.pow(p1.getPosition().getY()-p2.getPosition().getY(), 2))-p1.getRadio()-p2.getRadio();
	}

}
