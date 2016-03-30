import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		
		
	}

	private static List<Particle> ParticleGenerator(int N){
		double radius = 1.0;
		double size = 100;
		double speed = 0.3;
		List<Particle> list = new ArrayList<Particle>();
		for(int i=1 ; i<=N;i++){
			list.add(new Particle(radius, 0.0, Math.random()*size, Math.random()*size, new Velocity(speed, Math.random()*(2*Math.PI))));
		}
		return list;
	}
}
