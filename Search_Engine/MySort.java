import java.util.ArrayList;
import java.util.Collections;
public class MySort{
    ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries){
        ArrayList<SearchResult> al=new ArrayList<SearchResult>();
        Node<SearchResult> n=new Node<>();
        n=listOfSortableEntries.my.FindHeadoflink();
        while(n!=null){
            al.add(n.get());
            n=n.getNext();
        }
        Collections.sort(al);
        return al;
    }
}
