package data.dat.entities;

public enum QualityMetrics {
	
	
	Completeness(0),
	Consistency(1),
	Validity(2),
	Accuracy(3),
	Uniqueness(4),
	Timeliness(5);
	
	private int index;
	QualityMetrics(int index){this.index=index;}
	
	public int getIndex() {
		return this.index;
	}
}
