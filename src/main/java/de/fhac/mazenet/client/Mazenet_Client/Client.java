package de.fhac.mazenet.client.Mazenet_Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.fhac.mazenet.server.game.Board;
import de.fhac.mazenet.server.game.Position;

import de.fhac.mazenet.server.generated.CardData;
import de.fhac.mazenet.server.generated.DisconnectMessageData;
import de.fhac.mazenet.server.generated.Errortype;
import de.fhac.mazenet.server.generated.LoginReplyMessageData;
import de.fhac.mazenet.server.generated.MazeCom;
import de.fhac.mazenet.server.generated.MazeComMessagetype;
import de.fhac.mazenet.server.generated.MoveMessageData;
import de.fhac.mazenet.server.generated.ObjectFactory;
import de.fhac.mazenet.server.generated.PositionData;
import de.fhac.mazenet.server.generated.Treasure;
import de.fhac.mazenet.server.generated.WinMessageData.Winner;
import de.fhac.mazenet.server.networking.MazeComMessageFactory;

import de.fhac.mazenet.server.networking.XmlInputStream;
import de.fhac.mazenet.server.networking.XmlOutputStream;

 

public class Client {
	

	 Socket socket;
	 DataOutputStream outstream;
	 DataInputStream instream;
	 XmlInputStream inputStream;	static XmlOutputStream outputStream;
	 ObjectFactory objectFactory;
	 JAXBContext context;
	 Marshaller marshaller;
	 Unmarshaller unmarshaller;
	 static int id;
	 static int index;
	 static Board board;
	 static CardData card;
	 static Treasure treasure;
	 static Position shiftCard = new Position();
	 static List<Position> possibePositions;
	 static List<Position> reachablePositions;
	 static MoveMessageData moveMsg = new MoveMessageData();
	 static boolean rand;
	 static Position randCardPosition = new Position();
	 static Random rnd = new Random();
	 static PositionData spielerPositionData = new PositionData();
	 static PositionData schatzPositionData = new PositionData();
	 static PositionData pinPosition = new PositionData();
	 static PositionData shiftPosition = new PositionData();
	 
	 MazeComMessagetype msgType;
	 boolean stop = false;
	 MazeCom mazeCom = new MazeCom();
	 MazeCom maze = new MazeCom();
	 


	public Client(Socket s) throws IOException, JAXBException
	{
		socket = s;
		outstream = new DataOutputStream(socket.getOutputStream());
		instream = new DataInputStream(socket.getInputStream());
		outputStream = new XmlOutputStream(outstream);
		inputStream = new XmlInputStream(instream);
		
		objectFactory = new ObjectFactory();
		context = JAXBContext.newInstance(MazeCom.class);
		
		marshaller = context.createMarshaller();
		unmarshaller = context.createUnmarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	}
	
public void login(String name) throws IOException, JAXBException {
		Client clt = new Client(socket);

		mazeCom = MazeComMessageFactory.createLoginMessage(name);//create mazeCom
		System.out.println("Player :  "+mazeCom.getLoginMessage().getName());
		socket = clt.socket;
		
		Client.outputStream.write(mazeCom);
		
		mazeCom = clt.inputStream.readMazeCom();
		
		id = mazeCom.getId();
				
		//-------------------------
		while (!stop) {
			communicate();
			}
	}
	public void communicate() throws IOException, JAXBException {

		mazeCom = inputStream.readMazeCom(); 
		System.out.println("message:  "+mazeCom.getMessagetype());
		msgType= mazeCom.getMessagetype();
		
		
		
		switch (msgType) {
		case LOGIN:
			System.out.println("login tdar ");
			break;
		case LOGINREPLY:
			LoginReplyMessageData loginReplyMsg = mazeCom.getLoginReplyMessage();
			Client.id = loginReplyMsg.getNewID();
			
			System.out.println("Login Reply Message received.");
			System.out.println("ID: " + Client.id);
			
			break;
			
		case AWAITMOVE:
			//Aktuelle Daten abrufen (Board, Karte ,Treasure und Spieler Statuen)
			
			board = new Board(mazeCom.getAwaitMoveMessage().getBoard());//shrita li fash katbdl shiftCard changes too .
			card = mazeCom.getAwaitMoveMessage().getBoard().getShiftCard(); // get the shiftCard
			treasure = mazeCom.getAwaitMoveMessage().getTreasureToFindNext();// the treasure to find next;
			
			//KI hna !
			KI.AI(maze);
			break;
			
		
			
		case ACCEPT:
			if(!mazeCom.getAcceptMessage().isAccept()) {
				System.out.println("illegal");
			}
			break;

		case WIN:
			onWin(mazeCom.getWinMessage().getWinner());
			stop = true;
			socket.close();
			break;

		case DISCONNECT:
			onDisconnect(mazeCom.getDisconnectMessage());
			stop = true;
			socket.close();
			break;

		default:
			break;
		}
	

	}
	
	public void onWin(Winner winner) {
		System.out.println("Spiel vorbei! Gewinner ist: " + winner.getValue());
	}
	
	public void onDisconnect(DisconnectMessageData message) {
		Errortype error = message.getErrortypeCode();
		System.out.println("Der Fehler ist: " + error.value());
		System.out.println("Verbindung getrennt!");
	}

}

