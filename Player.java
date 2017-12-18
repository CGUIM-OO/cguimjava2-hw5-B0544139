import java.util.ArrayList;

public class Player extends Person{

	private String name;//玩家姓名
	private int chips;//玩家有的籌碼
	private int bet;//玩家此局下注的籌碼
	//ArrayList<Card> oneRoundCard=new ArrayList<Card>();//此牌局的卡
	
	
	
	public Player(String name, int chips){
		this.name=name;//建立玩家的姓名
		this.chips=chips;//建立玩家的籌碼
	}
	
	public String getName(){
		return name;//取得玩家姓名
	}
	
	public int makeBet(){
		if(chips==0)//檢查是否還有錢，沒錢了就不能再繼續下注
		{
			bet=0;
		}
		else bet=1;
		return bet;
	}
	
	/*public void setOneRoundCard(ArrayList cards){
		oneRoundCard=cards;//設定此牌局所得到的卡
	}*/
	
	public boolean hit_me(Table table){
		if(getTotalValue()<=16)//16點以下要牌，17點以上不要牌
		{
			return true;
		}
		
		/*else if(getTotalValue()>21)
		{
			
		}*/
		else 
		{
			return false;
		}
	}
	
	/*public int getTotalValue(){
		int sum=0;
		for(int i=0;i<oneRoundCard.size();i++)//此牌局所得的卡點數加總
		{
		    oneRoundCard.get(i).getRank();
		    if(oneRoundCard.get(i).getRank()==11)
		    {
		    	sum=sum+10;
		    }
		    else if(oneRoundCard.get(i).getRank()==12)
		    {
		    	sum=sum+10;
		    }
		    else if(oneRoundCard.get(i).getRank()==13)
		    {
		    	sum=sum+10;
		    }
		    else sum=sum+oneRoundCard.get(i).getRank();
		}
		return sum;//回傳此牌局所得的卡點數加總
	}*/
	
	public int getCurrentChips(){
		return chips;//回傳玩家現有籌碼
	}
	
	public void increaseChips (int diff){
		chips=chips+diff;//進行遊戲後玩家籌碼的變動
	}
	
	public void sayHello(){
		System.out.println("Hello, I am " + name + ".");//玩家Say Hello
		System.out.println("I have " + chips + " chips.");//玩家有多少籌碼
	}


}
