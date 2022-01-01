package Model.Cohen;

public enum BitEnum {

	zero(0), one(1);

	private int value;

	private BitEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public boolean and(BitEnum bitEnum) {
		return this.value == bitEnum.value;
	}
}
