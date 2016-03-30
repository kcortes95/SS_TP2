import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		//Rmax 1
		int L = 5;
		int N = 10;
		int Rc = 1;
		Set<Particle> set = ParticleGenerator(N,L);
		Grid grid = new LinearGrid(L, (int)(L/(Rc+2*1)), set);
		Simulation s = new Simulation(grid, 10,1,Rc,0.1,set);
		s.run();
	}

	private static Set<Particle> ParticleGenerator(int N, int L){
		double radius = 1.0;
		double speed = 0.3;
		Set<Particle> list = new HashSet<Particle>();
		for(int i=1 ; i<=N;i++){
			list.add(new Particle(radius, 0.0, Math.random()*L, Math.random()*L, new Velocity(speed, Math.random()*(2*Math.PI))));
		}
		return list;
	}
}
