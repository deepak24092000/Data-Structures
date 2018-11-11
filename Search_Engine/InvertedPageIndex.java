import java.util.ArrayList;
import java.util.*;
public class InvertedPageIndex {
    MyHashTable inverted=new MyHashTable();
    private MySet<PageEntry> all_pages=new MySet<>();
    HashMap<PageEntry,Integer> hm=new HashMap<>();
    void addPage(PageEntry p){
        all_pages.addElement(p);
        MyLinkedList<WordEntry> New_link=p.getPageIndex().my_hash;
        Node<WordEntry> nye=new  Node<WordEntry>();
        nye=New_link.FindHeadoflink();
        while(nye!=null){
            inverted.addPositionsForWord(nye.get());
            nye=nye.getNext();
        }
    }
    MySet<PageEntry> give_all_pages(){
        return all_pages;
    }
    HashMap<PageEntry,Integer> return_hashmap(){
        return  hm;
    }
    MySet<PageEntry> getPagesWhichContainWord(String str){
        return inverted.givepageswhich_contains_word(str);
    }
    boolean containspage(String str){
        Node<PageEntry> page_node=all_pages.FindHead();
        boolean flag=false;
        while(page_node!=null){
            if((page_node.get().PageName).equals(str)){
                flag=true;
                break;
            }
            page_node=page_node.getNext();
        }
        return flag;
    }
    MySet<Position> getpositions_inword(String str,String pg)
    {
        return inverted.tell_positions(str,pg);
    }
    MySet<PageEntry> thatcontainsallword(String str[]){
        MySet<PageEntry> me=new MySet<>();
        me=getPagesWhichContainWord(str[0]);
        for(int i=1;i<str.length;i++){
            me=me.intersection(getPagesWhichContainWord(str[i]));
        }
        return me;
    }
    MySet<PageEntry> thatcontainsanyoftheword(String str[]){
        MySet<PageEntry> ms=new MySet<>();
        for(int j=0;j<str.length;j++){
            ms=ms.union(getPagesWhichContainWord(str[j]));
        }
        return ms;
    }
    ArrayList<SearchResult> givesearchqueryforphrase(String str[]){
        MySet<PageEntry> mpe=new MySet<>();
        hm.clear();
        mpe=getPagesWhichContainPhrase(str);
        Node<PageEntry> n=new Node<>();
        n=mpe.my.FindHeadoflink();
        MySet<SearchResult> msr=new MySet<>();
        while(n!=null){
            float relevance=0;
                double s=(double)hm.get(n.get());
                double j=(s/(n.get().max_index-((str.length-1)*s)));
                double g=Math.log(((double)all_pages.my.numberofelements())/mpe.my.numberofelements());
                relevance+=j*g;
            SearchResult sr=new SearchResult(n.get(),relevance);
            msr.addElement(sr);
            n=n.getNext();
        }
        MySort ms=new MySort();
        return ms.sortThisList(msr);
    }
    ArrayList<SearchResult> givesearchfororquery(String str[]){
        MySet<PageEntry> mpe=new MySet<>();
        mpe=thatcontainsanyoftheword(str);
        Node<PageEntry> n=new Node<>();
        n=mpe.my.FindHeadoflink();
        MySet<SearchResult> msr=new MySet<>();
        while(n!=null){
            float relevance=0;
            n.get().ipi=this;
            relevance=n.get().getRelevanceOfPage(str,false);
            SearchResult sr=new SearchResult(n.get(),relevance);
            msr.addElement(sr);
            n=n.getNext();
        }
        MySort ms=new MySort();
        return ms.sortThisList(msr);
    }
    ArrayList<SearchResult> givesearchforandquery(String str[]){
        MySet<PageEntry> mpe=new MySet<>();
        mpe=thatcontainsallword(str);
        Node<PageEntry> n=new Node<>();
        n=mpe.my.FindHeadoflink();
        MySet<SearchResult> msr=new MySet<>();
        while(n!=null){
            n.get().ipi=this;
            float relevance=n.get().getRelevanceOfPage(str,false);
            SearchResult sr=new SearchResult(n.get(),relevance);
            msr.addElement(sr);
            n=n.getNext();
        }
        MySort ms=new MySort();
        return ms.sortThisList(msr);
    }
    MySet<PageEntry>getPagesWhichContainPhrase(String str[]){
        MySet<PageEntry> pe=new MySet<>();
        MySet<PageEntry> ret=new MySet<>();
        int length=str.length;
        pe=thatcontainsallword(str);
        Node<PageEntry> npe=new Node<>();
        npe=pe.my.FindHeadoflink();
        int l=0;
        int d=0;
        while(npe!=null){
            int flag=0;
            WordEntry s = npe.get().thispage.givewordentry(str[0]);
            Position p = s.minmin_index();
            d=0;
            while(p!=null) {
                l=p.getWordIndex();
                for (int j = 1; j < length; j++) {
                    WordEntry w = npe.get().thispage.givewordentry(str[j]);
                    Position m = w.findnextposition(l);
                    Position n = npe.get().findnextposition(l);
                    if (m == null || n == null) {
                        flag = 1;
                        break;
                    }
                    else {
                        l=m.getWordIndex();
                        if (m.getWordIndex() == n.getWordIndex()) {
                            flag = 2;
                        } else {
                            flag = 1;
                            break;
                        }
                    }
                }
                if (flag == 2) {
                    ret.addElement(npe.get());
                    d++;
                }
                p=s.findnextposition(p.getWordIndex());
            }
            if(ret.my.IsMember(npe.get())){
                hm.put(npe.get(),d);
            }
            npe=npe.getNext();
        }
        return ret;
    }
}
