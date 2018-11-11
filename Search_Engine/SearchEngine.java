import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchEngine {
    private InvertedPageIndex Search_inv_page_index=new InvertedPageIndex();
    SearchEngine(){
        InvertedPageIndex v=new InvertedPageIndex();
        Search_inv_page_index=v;
    }
    void performAction(String actionMessage){
        String[] currencies =actionMessage.split(" ");
        if(currencies[0].equals("addPage")){
            PageEntry iski=new PageEntry(currencies[1]);
            //System.out.println(iski.max_index);
            Search_inv_page_index.addPage(iski);
        }
        else if(currencies[0].equals("queryFindPagesWhichContainWord")){
            String l=currencies[1].toLowerCase();
            MySet<PageEntry> new_Myset=new MySet<>();
            if(l.equals("stack")||l.equals("stacks")){
                new_Myset=Search_inv_page_index.getPagesWhichContainWord("stack");
            }
            else if(l.equals("structure")||l.equals("structures")){
                new_Myset=Search_inv_page_index.getPagesWhichContainWord("structure");
            }
            else if(l.equals("application")||l.equals("applications")){
                new_Myset=Search_inv_page_index.getPagesWhichContainWord("application");
            }
            else{
                new_Myset=Search_inv_page_index.getPagesWhichContainWord(l);
            }
            String s="";
            if(new_Myset.my.IsEmpty()){
                System.out.println("No webpage contains word "+ currencies[1]);
            }
            else{
                Node<PageEntry> n=new Node<PageEntry>();
                n=new_Myset.FindHead();
                while(n.getNext()!=null){
                    s+=n.get().PageName+", ";
                    n=n.getNext();
                }
                s=s+=n.get().PageName;
                System.out.println(s);
            }

        }
        else if(currencies[0].equals("queryFindPositionsOfWordInAPage")){
            String l=currencies[1].toLowerCase();
            boolean contains_page=Search_inv_page_index.containspage(currencies[2]);
            if(!contains_page){
                System.out.println("No webapge "+currencies[2]+" found");
            }
            else{
                MySet<Position> my_pos=new MySet<>();
                if(l.equals("stack")||l.equals("stacks")){
                    my_pos=Search_inv_page_index.getpositions_inword("stack",currencies[2]);
                }
                else if(l.equals("structure")||l.equals("structures")){
                    my_pos=Search_inv_page_index.getpositions_inword("structure",currencies[2]);
                }
                else if(l.equals("application")||l.equals("applications")){
                    my_pos=Search_inv_page_index.getpositions_inword("application",currencies[2]);
                }
                else{
                    my_pos=Search_inv_page_index.getpositions_inword(l,currencies[2]);
                }
                if(my_pos.my.IsEmpty()){
                    System.out.println("Webpage "+currencies[2]+"does not contain word "+currencies[1]);
                }
                else{
                    Node<Position> pos=new Node<>();
                    pos=my_pos.FindHead();
                    String s="";
                    while(pos.getNext()!=null){
                        s+=Integer.toString(pos.get().getWordIndex())+", ";
                        pos=pos.getNext();
                    }
                    s=s+=Integer.toString(pos.get().getWordIndex());
                    System.out.println(s);
                }
            }
        }
        else if(currencies[0].equals("queryFindPagesWhichContainAllWords")){
            ArrayList<String> al=new ArrayList<>();
            for(String s:currencies){
                if(!s.equals(("queryFindPagesWhichContainAllWords"))) {
                    if(s.equals("stacks")|| s.equals("Stacks")){
                        al.add("stack");
                    }
                    else if(s.equals("structures")|| s.equals("Structures")){
                        al.add("structure");
                    }
                    else if(s.equals("applications")||s.equals(("Applications"))){
                        al.add("application");
                    }
                    else {
                        al.add(s);
                    }
                }
            }
            String[] str=new String[al.size()];
            int i=0;
            for(String d: al){
                d=d.toLowerCase();
                str[i]=d;
                i++;
            }
            ArrayList<SearchResult> asr=Search_inv_page_index.givesearchforandquery(str);
            if(asr.isEmpty()){
                System.out.println("null");
            }
            if(asr.isEmpty()){
                String g="";
                for(String h:al){
                    g+=h+" ";
                }
                System.out.println("No webpage contains all of these words "+g);
            }
            else {
                String f="";
                int k=0;
                for ( k=0;k<asr.size()-1;k++) {
                    f+=asr.get(k).getPageEntry().PageName+", ";
                }
                f+=asr.get(k).getPageEntry().PageName;
                System.out.println(f);
            }
        }
        else if(currencies[0].equals("queryFindPagesWhichContainAnyOfTheseWords")){
            ArrayList<String> al=new ArrayList<>();
            for(String s:currencies){
                if(!s.equals(("queryFindPagesWhichContainAnyOfTheseWords"))) {
                    if(s.equals("stacks")|| s.equals("Stacks")){
                        al.add("stack");
                    }
                    else if(s.equals("structures")|| s.equals("Structures")){
                        al.add("structure");
                    }
                    else if(s.equals("applications")||s.equals(("Applications"))){
                        al.add("application");
                    }
                    else {
                        al.add(s);
                    }
                }
            }
            String[] str=new String[al.size()];
            int i=0;
            for(String d: al){
                d=d.toLowerCase();
                    str[i] = d;
                    i++;
            }
            ArrayList<SearchResult> asr=Search_inv_page_index.givesearchfororquery(str);
            if(asr.isEmpty()){
                String g="";
                for(String h:al){
                    g+=h+" ";
                }
                System.out.println("No webpage contains any of the words "+g);
            }
            else {
                String f="";
                int k=0;
                for ( k=0;k<asr.size()-1;k++) {
                    f+=asr.get(k).getPageEntry().PageName+", ";
                }
                f+=asr.get(k).getPageEntry().PageName;
                System.out.println(f);
            }
        }
        else if(currencies[0].equals("queryFindPagesWhichContainPhrase")){
            ArrayList<String> al=new ArrayList<>();
            for(String s:currencies){
                if(!s.equals(("queryFindPagesWhichContainPhrase"))) {
                    if(s.equals("stacks")|| s.equals("Stacks")){
                        al.add("stack");
                    }
                    else if(s.equals("structures")|| s.equals("Structures")){
                        al.add("structure");
                    }
                    else if(s.equals("applications")||s.equals(("Applications"))){
                        al.add("application");
                    }
                    else {
                        al.add(s);
                    }
                }
            }
            String[] str=new String[al.size()];
            int i=0;
            for(String d: al){
                d=d.toLowerCase();
                str[i] = d;
                i++;
            }
            ArrayList<SearchResult> asr=Search_inv_page_index.givesearchqueryforphrase(str);
            if(asr.isEmpty()){
                String g="";
                for(String h:al){
                    g+=h+" ";
                }
                System.out.println("No webpage contains given phrase "+g);
            }
            else {
                String f="";
                int k=0;
                for ( k=0;k<asr.size()-1;k++) {
                   f+=asr.get(k).getPageEntry().PageName+", ";
                }
                f+=asr.get(k).getPageEntry().PageName;
                System.out.println(f);
            }
        }
        else{
            System.out.println("Please give Query in correct format");
        }
        //System.out.println("------------------------------------------------------------------------------------------------------------");
    }
}
