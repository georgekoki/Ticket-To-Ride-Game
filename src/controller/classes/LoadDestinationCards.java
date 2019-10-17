package controller.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import model.classes.DestinationCard;
/**
 * CS252 Object-Oriented Programming
 *
 * LoadDestinationCards Class. Loads up Destination Cards from the CSV into an ArrayList.
 *
 * @author George Kokiadis <i>csd3962@csd.uoc.gr</i>
 *
 */
public class LoadDestinationCards {
    public static ArrayList<DestinationCard> readCards() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("destinationCards.csv")));
        String sCurrentLine = "";
        ArrayList<DestinationCard> array = new ArrayList<DestinationCard>(46);
        boolean firstLine = true;
        while ((sCurrentLine = br.readLine()) != null) {
        	if(firstLine == true)
        		firstLine = false;
        	else {
	            String[] splitLine = sCurrentLine.split(",");
	            String id = splitLine[0];
	            String from = splitLine[1];
	            String to = splitLine[2];
	            int score = Integer.parseInt(splitLine[3]);
	            String colorsList = splitLine[4];
	            String[] splitColors = colorsList.split("-");
	            ArrayList<String> colors = new ArrayList<String>();
	            colors.addAll(Arrays.asList(splitColors));
	            String imagePath = splitLine[5];
	            array.add(new DestinationCard(null,"/destination_Tickets/" + imagePath, score, Integer.parseInt(id), from, to, colors));
        	}
        }
        br.close();
        return array;
    }
}
