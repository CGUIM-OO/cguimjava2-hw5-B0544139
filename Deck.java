import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	ArrayList<Card> openCard=new ArrayList<Card>();//存放此副牌中所有打開的牌
	ArrayList<Card> usedCard=new ArrayList<Card>();//已經發出去的牌
	public int nUsed;//已經發了幾張
	//TODO: Please implement the constructor (30 points)
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		for(int num1=1;num1<=nDeck;num1++)//幾副牌
        {
        	for(Card.Suit s : Card.Suit.values())//花色
        	{
        		 for(int y=1;y<=13;y++)//數字
        		 {
        				Card card=new Card(s,y);//將card實體化
        				cards.add(card);//將card放入ArrayList<Card>裡
        		 }
        	}
        }
		shuffle();//組好牌後先洗牌
		
	}	
	//TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		for(int index=0;index<cards.size();index++)
		{
			Card card = new Card(cards.get(index).getSuit(),cards.get(index).getRank());//將卡片及花色實體化
			card.printCard();//將所有卡顯示出來
		}

	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	
	public void shuffle()
	{
		
		for(int num=0;num<52;num++)//隨機選牌,交換位置
		{
			Random rnd = new Random();
			int j = rnd.nextInt(52);
			Card temp=cards.get(num);//將第num個card先移到temp
			cards.set(num,cards.get(j));//用隨機選到第j個牌擺到第num個位置
			cards.set(j,temp);//將原本的第num個card移到第j個位置
		}
		usedCard.clear();//重設已經發出去的牌
		nUsed=0;//重設發了幾張牌的數目
		openCard.clear();//重設打開的牌
	}
	
	public Card getOneCard(boolean isOpened)
	{
		if(usedCard.size()==52)//如果發出去的牌已經有52張則洗牌
		{
			shuffle();
		}
        if(isOpened)
        {
        	openCard.add(cards.get(nUsed));//發出去的牌若是開著的，加入openCard
        }
		usedCard.add(cards.get(nUsed));//將發出去的牌記錄在usedCard
		nUsed=nUsed+1;//將發牌的數目記錄在nUsed
		return cards.get(nUsed-1);//回傳發出去的牌
	}
	
	public ArrayList getOpenedCard(){
		return openCard;//回傳此副牌中所有打開過的牌
	}
		
	
	
	
		
	
}
