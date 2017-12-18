import java.util.ArrayList;

public class Table {

	static final int MAXPLAYER = 4;//最多一張牌桌能坐幾個人
	private Deck Deck;//存放所有的牌
	private Player[] Player;//存放所有的玩家
	private Dealer Dealer;//存放一個莊家
	private int[] pos_betArray;//存放每個玩家在一局下的注
	
	public Table(int nDeck)//Table的 Constructor
	{
		Deck=new Deck(nDeck);
		Player=new Player[MAXPLAYER];
	}
	
	public void set_player(int pos, Player p)//將Player放到牌桌上
	{
		Player[pos]=p;
	}
	
	public Player[] get_player()//回傳所有在牌桌上的player
	{
		return Player;
	}
	
	public void set_dealer(Dealer d)//將Dealer放到牌桌上
	{
		Dealer=d;
	}
	
	public Card get_face_up_card_of_dealer()//回傳dealer打開的那張牌(第二張牌)
	{
		return Dealer.getOneRoundCard().get(1);
	}
	
	private void ask_each_player_about_bets()
	{
		pos_betArray=new int[MAXPLAYER];
		for(int i=0;i<=3;i++)
		{
			Player[i].sayHello();//請每個玩家打招呼
			Player[i].makeBet();//請每個玩家下注
			pos_betArray[i]=Player[i].makeBet();//每個玩家下的注存在pos_betArray
		}
	}
	
	private void distribute_cards_to_dealer_and_players()//發牌給玩家跟莊家
	{
		for(int r=0;r<=3;r++)
		{
			ArrayList<Card> temp1=new ArrayList<Card>();
			temp1.add(Deck.getOneCard(true));
			temp1.add(Deck.getOneCard(true));
			Player[r].setOneRoundCard(temp1);//發兩張打開的牌給玩家
		}
		ArrayList<Card> temp2=new ArrayList<Card>();
		temp2.add(Deck.getOneCard(false));
		temp2.add(Deck.getOneCard(true));
		Dealer.setOneRoundCard(temp2);//發一張蓋著的牌以及一張打開的牌給莊家
		System.out.print("Dealer's face up card is ");
		get_face_up_card_of_dealer().printCard();
	}
	
	private void ask_each_player_about_hits()//問每個玩家要不要牌
	{
		for(int n=0;n<=3;n++)
		{
			System.out.print(Player[n].getName()+"'s Cards now:");
			for(Card c : Player[n].getOneRoundCard()){
				c.printCard();
			} 
			boolean hit=false;
			do{
				hit=Player[n].hit_me(this);
				if(hit){
					Player[n].getOneRoundCard().add(Deck.getOneCard(true));
					Player[n].setOneRoundCard(Player[n].getOneRoundCard());
					System.out.print("Hit! ");
					System.out.print(Player[n].getName()+"'s Cards now:");
					for(Card c : Player[n].getOneRoundCard()){
						c.printCard();
					}
				}
				else if(Player[n].getTotalValue()>21)
				{
					System.out.println(Player[n].getName()+"'s hit is over!");
				}
				else{
					System.out.println("Pass hit!");
					System.out.println(Player[n].getName()+"'s hit is over!");
				}
			}while(hit);	
		}
	}
	
	private void ask_dealer_about_hits()//詢問莊家是否要牌
	{
		/*System.out.println("Dealer's Cards now:");
		for(Card c : Dealer.getOneRoundCard()){
			c.printCard();
		}*/
		boolean hit=false;
		do{
			hit=Dealer.hit_me(this);
			if(hit){
				Dealer.getOneRoundCard().add(Deck.getOneCard(true));
				Dealer.setOneRoundCard(Dealer.getOneRoundCard());
				//System.out.print("Hit! ");
				//System.out.println("Dealer's Cards now:");
				/*for(Card c : Dealer.getOneRoundCard()){
					c.printCard();
				}*/
			}
			else if(Dealer.getTotalValue()>21)
			{
				//System.out.println("Dealer's hit is over!");
			}
			else{
				//System.out.println("Pass hit!");
			}
		}while(hit);
		
		System.out.println("Dealer's hit is over!");

	}
	
	private void calculate_chips()//莊家與玩家比較，看誰贏，要是莊家贏，把玩家籌碼沒收，要是莊家輸，則賠玩家與下注籌碼相符之籌碼，如平手則籌碼不變
	{
		System.out.print("Dealer's card value is "+Dealer.getTotalValue()+" ,Cards:");
		Dealer.printAllCard();
		for(int t=0;t<=3;t++)
		{
			System.out.println(Player[t].getName()+"'s Cards:");
			for(Card c : Player[t].getOneRoundCard()){
				c.printCard();
			}
			System.out.print(Player[t].getName()+" card value is "+Player[t].getTotalValue());
			if(Dealer.getTotalValue()>21 && Player[t].getTotalValue()>21){
				System.out.println(", chips have no change! The Chips now is: "+Player[t].getCurrentChips());
			}else if(Dealer.getTotalValue()<=21&&Player[t].getTotalValue()>21){
				Player[t].increaseChips(-pos_betArray[t]);
				System.out.println(", Loss "+pos_betArray[t]+" Chips, the Chips now is: "+Player[t].getCurrentChips());
			}else if(Dealer.getTotalValue()>21&&Player[t].getTotalValue()<=21){
				Player[t].increaseChips(pos_betArray[t]);
				System.out.println(", Get "+pos_betArray[t]+" Chips, the Chips now is: "+Player[t].getCurrentChips());
			}else if(Dealer.getTotalValue()>Player[t].getTotalValue()&&Dealer.getTotalValue()<=21){
				Player[t].increaseChips(-pos_betArray[t]);
				System.out.println(", Loss "+pos_betArray[t]+" Chips, the Chips now is: "+Player[t].getCurrentChips());
			}else if(Dealer.getTotalValue()<Player[t].getTotalValue()&&Player[t].getTotalValue()<=21){
				Player[t].increaseChips(pos_betArray[t]);
				System.out.println(", Get "+pos_betArray[t]+" Chips, the Chips now is: "+Player[t].getCurrentChips());
			}else{
				System.out.println(", chips have no change! The Chips now is: "+Player[t].getCurrentChips());
			}

		}
	}
	
	public int[] get_players_bet()//回傳玩家在一局下的注
	{
		return pos_betArray;
	}
	
	public void play(){//進行比賽
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	
	
}
