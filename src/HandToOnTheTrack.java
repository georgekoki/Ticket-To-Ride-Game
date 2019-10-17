import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.classes.GameUtilities;
import model.classes.Hand;
import model.classes.Player;
import model.classes.RailYard;
import model.classes.TrainCard;
import model.enumerators.TrainColor;
import model.exceptions.InvalidColorException;
import model.exceptions.NotEnoughCardsException;

class HandToOnTheTrack {

	@Test
	void test() {
		GameUtilities.setUpUtilities();
		ArrayList<TrainCard> t = new ArrayList<TrainCard>();
		t.add(new TrainCard(null, "image", TrainColor.red));
		t.add(new TrainCard(null, "image", TrainColor.red));
		t.add(new TrainCard(null, "image", TrainColor.red));
		t.add(new TrainCard(null, "image", TrainColor.red));
		Hand hand = new Hand(t, null, null, null);
		Player p = new Player(null, 0, "", null, null, hand);
		RailYard yard = new RailYard(new int[] {0,0,0,0,0,0,0,0}, p);
		p.setRailYardVar(yard);
		Player op;
		op =  new Player(null, 0, "name", new RailYard(new int[] {2,0,0,0,0,0,0,0} , null), null, null);
		try {
			TrainCard[] tc = new TrainCard[hand.getTrainCards().size()];
			for(int i = 0; i < hand.getTrainCards().size(); i++)
				tc[i] = hand.getTrainCards().get(i);
			yard.placeInRailYard(tc,op , 0);
		} catch (InvalidColorException e) {
			fail("Invalid exception thrown");
		} catch (NotEnoughCardsException e) {
			fail("Invalid exception thrown");
		}
		if(op.getRailYard().getColors()[0] != 0) fail("Train Robbery did not work");
	}

}
