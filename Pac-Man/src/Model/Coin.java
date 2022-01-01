package Model;


public class Coin {
	private Circle circle;

	public Coin(Circle circle) {
		super();
		this.circle = circle;
	}

	public int getX() {
		return circle.getCenterX();
	}

	public int getY() {
		return circle.getCenterY();
	}

	public Circle getCircle() {
		return circle;
	}
	
//	@Override
//	public int hashCode() {
//		return Objects.hash(name);
//	}


}
