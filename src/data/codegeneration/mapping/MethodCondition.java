package data.codegeneration.mapping;

public class MethodCondition {
	
	private String val;
	private String minVal;
	private String maxVal;
	private String regex;
	private String length;
	private String dataFormat;
	
	public MethodCondition() {
		// TODO Auto-generated constructor stub
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getMinVal() {
		return minVal;
	}

	public void setMinVal(String minVal) {
		this.minVal = minVal;
	}

	public String getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(String maxVal) {
		this.maxVal = maxVal;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Data Format: "+this.getDataFormat()+"\n Value:"+this.getVal()+"";
	}
}
