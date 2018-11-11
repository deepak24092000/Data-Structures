class Nodex{
    private WordEntry wd;
    private Nodex next;
    WordEntry getwd(){
        return wd;
    }
    void setword_entry(WordEntry w){
        wd=w;
    }
    Nodex getlink(){
        return next;
    }
    void setlink(Nodex n){
        next=n;
    }
}
public class MyHashTable{
    Nodex[] my_hash_table=new Nodex[1021];
    public int pow(int h){
        int l=1;
        for(int s=0;s<h;s++){
            l=l*2;
        }
        return l;
    }
    private int getHashIndex(String str){
        int length=str.length();
        int h=0;
        for(int i=0;i<length;i++){
            h+=(pow(i))*((int)str.charAt(i));
        }
        h=h%1021;
        return h;
    }
    int Hash_Index(String s){
        int h=getHashIndex(s);
        return h;
    }
    void addPositionsForWord(WordEntry w){
        int HashIndex=getHashIndex(w.Word);
        Nodex new_node=my_hash_table[HashIndex];
        int flag=0;
        while(new_node!=null){
            if((new_node.getwd().Word).equals(w.Word)){
                flag=1;
                break;
            }
            new_node=new_node.getlink();
        }
        if(flag==1){
            Node<Position> new_wordentry=new Node<>();
            new_wordentry=w.wordpositions.FindHeadoflink();
            while(new_wordentry!=null){
                new_node.getwd().wordpositions.addElementinlink(new_wordentry.get());
                new_wordentry=new_wordentry.getNext();
            }
        }
        if(flag==0){
            Nodex nr=new Nodex();
            WordEntry wn=new WordEntry(w.Word);
            Node<Position> nn=new Node<>();
            nn=w.wordpositions.FindHeadoflink();
            while(nn!=null){
                wn.wordpositions.addElementinlink(nn.get());
                nn=nn.getNext();
            }
            nr.setword_entry(wn);
            nr.setlink(null);
            Nodex ny=new Nodex();
            ny=my_hash_table[HashIndex];
            if(ny==null){
                my_hash_table[HashIndex]=nr;
            }
            else{
                while(ny.getlink()!=null){
                    ny=ny.getlink();
                }
                ny.setlink(nr);
            }
        }
    }
    MySet<PageEntry> givepageswhich_contains_word(String str){
        int hashindex=getHashIndex(str);
        Nodex new_nodex=my_hash_table[hashindex];
        MySet<PageEntry> my_set=new MySet<>();
        int flag=0;
        while(new_nodex!=null){
            if((new_nodex.getwd().Word).equals(str)){
                flag=1;
                break;
            }
            new_nodex=new_nodex.getlink();
        }
        if(flag==0){
            return  my_set;
        }
        if(flag==1){
            Node<Position> pos=new Node<>();
            pos=new_nodex.getwd().wordpositions.FindHeadoflink();
            while(pos!=null){
                //System.out.println("aaya");
                Node<PageEntry> nn=new Node<>();
                nn=my_set.FindHead();
                int fleg=0;
                int i=0;
                while(nn!=null){
                    if(pos.get().getPageEntry().thispage.gettermfreq(pos.get().getPageEntry().PageName,str)>nn.get().thispage.gettermfreq(nn.get().PageName,str)) {
                        fleg=1;
                        my_set.addElementFront(pos.get().getPageEntry(),i);
                    }
                    i++;
                    nn=nn.getNext();
                }
                if(fleg==0){
                    my_set.addElementFront(pos.get().getPageEntry(),my_set.my.numberofelements());
                }
                pos=pos.getNext();
            }
        }
        return my_set;
    }
    MySet<Position> tell_positions(String str,String pg){
        int hashindex=getHashIndex(str);
        Nodex new_nodex=my_hash_table[hashindex];
        MySet<Position> my_set=new MySet<>();
        int flag=0;
        while(new_nodex!=null){
            if((new_nodex.getwd().Word).equals(str)){
                flag=1;
                break;
            }
            new_nodex=new_nodex.getlink();
        }
        if(flag==0){
            return my_set;
        }
        else{
            Node<Position> nt=new Node<>();
            nt=new_nodex.getwd().wordpositions.FindHeadoflink();
            while(nt!=null){
                if((nt.get().getPageEntry().PageName).equals(pg)){
                    my_set.addElement(nt.get());
                }
                nt=nt.getNext();
            }
        }
        return my_set;
    }


}