public class ExchangeList {
    Myset exchangelist=new Myset();
    public void add(Exchange e){
        exchangelist.Insert(e);
    }
    public void delete(Exchange e){
        exchangelist.delete(e);
    }
    public int size(){
        return exchangelist.numberofelements();
    }
    public Exchange get(int i){
        return (Exchange)(exchangelist.ithchildren(i).data);
    }
    public void addulta(Exchange e){
        exchangelist.ins(e);
    }
	public Node ihchildren(int i){
		return exchangelist.ithchildren(i);
	}

}