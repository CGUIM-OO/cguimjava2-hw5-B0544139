
public class Card {
	public enum Suit {Club,Diamond,Heart,Spade};//花色
	private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	/**
	 * @param s suit
	 * @param r rank
	 */
	public Card(Suit s,int value){
		suit=s;
		rank=value;
	}	
	//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
	public void printCard(){
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
		//String[] type1={"Clubs","Diamonds","Hearts","Spades"};//所有的花色
        String[] number1={"Ace","2","3","4","5","6","7","8","9","10",
        		       "Jack","Queen","King"};//所有的數字
        //String type=Integer.toString(suit);
        String number=Integer.toString(rank);
        //type=type1[suit-1];//將輸入轉換為對應到的花色
        number=number1[rank-1];//將輸入轉換為對應到的數字
		System.out.println(suit+" "+number);//將花色及數字顯示出來
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}
