package enums;

/**
 *
 * @author Như Tâm
 */
public enum DiscountType {
	PERCENT(0), PRICE(1);

	private final int value;

	private DiscountType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public boolean compare(int value) {
		return this.value == value;
	}

	public static DiscountType fromInt(int value) {
		for (DiscountType type : values()) {
			if (type.compare(value)) {
				return type;
			}
		}
		return null;
	}
}
