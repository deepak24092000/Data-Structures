public class MobilePhoneSet{
    Myset mobilephoneset=new Myset();
    public void add(MobilePhone a){
        mobilephoneset.Insert(a);
    }
    public void delete(MobilePhone a){
        mobilephoneset.delete(a);
    }
}