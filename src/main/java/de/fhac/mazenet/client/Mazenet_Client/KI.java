package de.fhac.mazenet.client.Mazenet_Client;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import de.fhac.mazenet.server.game.Board;
import de.fhac.mazenet.server.game.Position;
import de.fhac.mazenet.server.generated.MazeCom;
import de.fhac.mazenet.server.generated.MazeComMessagetype;

public class KI extends Client{

	public KI(Socket s) throws IOException, JAXBException {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
public static void AI(MazeCom mazeCom) throws UnmarshalException, IOException {
		
		
		
		
	/*  restent inchangeable
		   	||
		    ||
		 	\/  */
		possibePositions = de.fhac.mazenet.server.game.Position.getPossiblePositionsForShiftcard();//les pos possibles pour shiftCard
		
		shiftCard.setRow(shiftPosition.getRow()); //shift pos hya la pos lighatkun fiha la carte 
		shiftCard.setCol(shiftPosition.getCol());
		possibePositions.remove(shiftCard.getOpposite());//hydna la position opposée dial spieler
		System.out.println("ùùùùùù"+shiftCard.getOpposite());
		

		etq: for (Position possiblePosition : possibePositions) {
			rand = true;
			
			shiftPosition.setRow(possiblePosition.getRow());
			shiftPosition.setCol(possiblePosition.getCol());

			moveMsg.setShiftPosition(shiftPosition);
			moveMsg.setShiftCard(card);
			Board fakeboard = board.fakeShift(moveMsg);
			schatzPositionData = fakeboard.findTreasure(treasure);
			spielerPositionData = fakeboard.findPlayer(id);
			
			if (schatzPositionData != null) { 

				reachablePositions = fakeboard.getAllReachablePositions(spielerPositionData);
				System.out.println(reachablePositions);
				for (Position position : reachablePositions) {

					if (schatzPositionData.getRow() == position.getRow() && schatzPositionData.getCol() == position.getCol()) {
						pinPosition.setRow(schatzPositionData.getRow());
						pinPosition.setCol(schatzPositionData.getCol());
						moveMsg.setNewPinPos(pinPosition);
						rand = false;
						break etq;
						
					}
				}
				
			}
			else {System.out.println("null");}
		}
		System.out.println(rand);
	if (rand){
		System.out.println("***");
		rndpushCard(possibePositions);
	}
	//Vorbereitung, die das Ergebnis an den Server sendet
		mazeCom.setMessagetype(MazeComMessagetype.MOVE);
		mazeCom.setMoveMessage(moveMsg);
		mazeCom.setId(id);
		//das Ergebnis senden
		outputStream.write(mazeCom);

	}
	
	

	public static void rndpushCard(List<Position> possibePositions) throws UnmarshalException, IOException {
		System.out.println(possibePositions.size());
		index = rnd.nextInt(possibePositions.size());
		System.out.println("index"+index);
		randCardPosition = possibePositions.get(index);


		shiftPosition.setRow(randCardPosition.getRow());
		shiftPosition.setCol(randCardPosition.getCol());
		moveMsg.setShiftPosition(shiftPosition);
		moveMsg.setShiftCard(card);
		
		Board fakeboard = board.fakeShift(moveMsg);
		spielerPositionData = fakeboard.findPlayer(id);
		moveMsg.setNewPinPos(spielerPositionData);

	}

	

}
