package dal.gravity;

public class GravityConstant implements GravityModel{
	public static final double EARTH_GRAVITY = 9.80665;
	public double g;

	public GravityConstant(double g){
		this.g = g;
	}
	public GravityConstant(){
		this.g = EARTH_GRAVITY;
	}
	@Override
	public double getGravitationalField() {
		return g;
	}
}