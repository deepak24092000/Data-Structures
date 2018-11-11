import java.util.ArrayList;
public class Exchange{
    private int unique_identifier;
    Exchange papa;
    ExchangeList exchangelinkedlist=new ExchangeList();
    MobilePhoneSet residentSetmobile=new MobilePhoneSet();
    public Exchange(int number){
        unique_identifier=number;
    }
    public Exchange parent(){
        return papa;
    }
    public int numbe(){
        return unique_identifier;
    }
    public int numChildren(){
        return exchangelinkedlist.size();
    }
    public Exchange child(int i){
        return exchangelinkedlist.get(i);
    }
    public boolean isRoot(){
        if(papa==null){
            return true;
        }
        else{
            return false;
        }
    }
    public RoutingMapTree subtree(int i){
        RoutingMapTree r=new RoutingMapTree();
        r.root=child(i);
        return r;
    }
    public MobilePhoneSet residentSet(){

        return residentSetmobile;
    }
}