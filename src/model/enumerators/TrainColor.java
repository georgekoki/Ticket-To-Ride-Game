package model.enumerators;

public enum TrainColor{
	red, blue, yellow, green, purple, white, orange, black, locomotive;
	
	/**
	 * Returns a TrainColor that matches the string
	 * <br><b>Pre Conditions : </b> String s is a valid String that corresponds to a colour
	 * <br><b>Post Conditions : </b> Returns the colour that matches the String
	 * @param s String to be parsed
	 * @return Appropriate TrainColor
	 */
	public static TrainColor parseString(String s) {
		switch(s) {
		case "red": return TrainColor.red;
		case "blue": return TrainColor.blue;
		case "yellow": return TrainColor.yellow;
		case "green": return TrainColor.green;
		case "purple": return TrainColor.purple;
		case "white": return TrainColor.white;
		case "orange": return TrainColor.orange;
		case "black": return TrainColor.black;
		case "locomotive" : return TrainColor.locomotive;
		default: return null;
		}
	}
}
