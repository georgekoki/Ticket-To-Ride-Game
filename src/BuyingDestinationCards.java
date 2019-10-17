import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.classes.GameUtilities;
import model.classes.DestinationCard;
import model.classes.OnTheTrack;
import model.classes.Player;

class BuyingDestinationCards {

	@Test
	void test() {
		GameUtilities.setUpUtilities();
		ArrayList<String> tempColor = new ArrayList<String>();
		tempColor.add("red");
		tempColor.add("red");
		tempColor.add("green");
		DestinationCard card = new DestinationCard(null, "image", 10, 12,  "Miami", "Las Vegas", tempColor);
		int[] t = new int[8];
		for(int i = 0; i < 8; i++)
			t[i] = i + 2;
		Player p = new Player(null, 0, "player", null, new OnTheTrack(t, null), null);
		System.out.println("Poop");
		if(!card.canPlayerGetCard(p)) fail("Shit");
	}

}
