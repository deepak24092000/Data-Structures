import java.util.*;
import java.io.*;
public class PageEntry {
    InvertedPageIndex ipi=new InvertedPageIndex();
    String PageName;
    String alluse;
    int max_index=0;
    int jayega=0;
    PageIndex thispage;
    MyLinkedList<String> connectors=new MyLinkedList<String>();
    MyLinkedList<Integer> Ascii=new MyLinkedList<Integer>();
    AvlTree pageentry =new AvlTree();
    PageEntry(String pageName){
        thispage=new PageIndex();
        PageName=pageName;
        createpageIndex();
    }
    void addAscii(){
        Ascii.addElementinlink(123);
        Ascii.addElementinlink(125);
        Ascii.addElementinlink(91);
        Ascii.addElementinlink(93);
        Ascii.addElementinlink(60);
        Ascii.addElementinlink(62);
        Ascii.addElementinlink(61);
        Ascii.addElementinlink(40);
        Ascii.addElementinlink(41);
        Ascii.addElementinlink(46);
        Ascii.addElementinlink(44);
        Ascii.addElementinlink(59);
        Ascii.addElementinlink(58);
        Ascii.addElementinlink(39);
        Ascii.addElementinlink(34);
        Ascii.addElementinlink(35);
        Ascii.addElementinlink(63);
        Ascii.addElementinlink(33);
        Ascii.addElementinlink(45);
    }
    void Addconectors(){
        connectors.addElementinlink("a");
        connectors.addElementinlink("an");
        connectors.addElementinlink("the");
        connectors.addElementinlink("they");
        connectors.addElementinlink("these");
        connectors.addElementinlink("this");
        connectors.addElementinlink("for");
        connectors.addElementinlink("is");
        connectors.addElementinlink("are");
        connectors.addElementinlink("was");
        connectors.addElementinlink("of");
        connectors.addElementinlink("or");
        connectors.addElementinlink("and");
        connectors.addElementinlink("does");
        connectors.addElementinlink("will");
        connectors.addElementinlink("whose");
    }
    void createpageIndex(){
        store_in_alluse();
        if(jayega==0) {
            removepunchuation();
            converttolowercase();
            Addconectors();
            String new_str = alluse;
            String Partial_use = "";
            int g=0;
            int p = 1;
            new_str=new_str.replaceAll("\\s+"," ");
            String curr[]=new_str.split(" ");
            for(String h: curr){
                if(!connectors.IsMember(h)){
                    if (h.equals("stack") || h.equals("stacks")) {
                        Position new_pos = new Position(this, p);
                        pageentry.insert(new_pos);
                        thispage.addPositionForWord("stack", new_pos);

                    } else if (h.equals("structure") || h.equals("structures")) {
                        Position new_pos = new Position(this, p);
                        pageentry.insert(new_pos);
                        thispage.addPositionForWord("structure", new_pos);
                    } else if (h.equals("application") || h.equals("applications")) {
                        Position new_pos = new Position(this, p);
                        pageentry.insert(new_pos);
                        thispage.addPositionForWord("application", new_pos);
                    } else {
                        Position new_pos = new Position(this, p);
                        pageentry.insert(new_pos);
                        thispage.addPositionForWord(h, new_pos);
                    }
                    g++;
                }
                p++;
            }
            max_index=g;
        }
    }
    void store_in_alluse(){
        try{
            FileInputStream fs = new FileInputStream("webpages"+"/"+PageName);
            Scanner sc = new Scanner(fs);
            while(sc.hasNext()){
                alluse+=sc.nextLine();
                alluse+=" ";
            }
        }
        catch(FileNotFoundException e){
            jayega=1;
            System.out.println("Page with given name does not exist");
        }
    }
    Position findnextposition(int g){
        if(pageentry.findnext(g)==null){
            return null;
        }
        else {
            return pageentry.findnext(g).data;
        }
    }
    Position beforeposition(int s){
        return pageentry.findbefore(s).data;
    }
    Position minmin_index(){
        return pageentry.findmin().data;
    }
    void converttolowercase(){
        alluse=alluse.toLowerCase();
    }
    void removepunchuation(){
        String new_string="";
        addAscii();
        for(int i=0;i<alluse.length();i++){
            if(Ascii.IsMember(((int)alluse.charAt(i)))){
                if(!(alluse.charAt(i-1)==' ')&&!(alluse.charAt(i+1)==' ') ){
                    new_string+=" ";
                }
            }
            else{
                new_string+=alluse.charAt(i);
            }
        }
        alluse=new_string.substring(4,new_string.length());
    }
    PageIndex getPageIndex(){
        return  thispage;
    }
    float getRelevanceOfPage(String str[],boolean doTheseWordsRepresentAPhrase){
        float rel=0;
        if(!doTheseWordsRepresentAPhrase){
            for(int p=0;p<str.length;p++){
                double f=(ipi.getPagesWhichContainWord(str[p]).my.numberofelements());
                double j=Math.log((ipi.give_all_pages().my.numberofelements())/f);
                //System.out.println(j);
                double h=thispage.gettermfreq(this.PageName,str[p]);
                rel += h*j;
            }
        }
        return rel;
    }
}

