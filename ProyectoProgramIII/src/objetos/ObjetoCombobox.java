public class ObjetoCombobox {
	private final int value;
    private final String label;

    public ObjetoCombobox(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return this.label;
    }
}
