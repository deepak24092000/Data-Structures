public class WordEntry {
    String Word;
    MyLinkedList<Position> wordpositions=new MyLinkedList<Position>();
    AvlTree avl_tree=new AvlTree();
    WordEntry(String word){
        Word=word;
    }
    void addPosition(Position position){
        //System.out.println("aayega");
        wordpositions.addElementinlink(position);
        add_in_avl(position);
    }
    void addPositions(MyLinkedList<Position> positions){
        Node<Position> mynew=positions.FindHeadoflink();
        while(mynew!=null){
            wordpositions.addElementinlink(mynew.get());
            add_in_avl(mynew.get());
            mynew=mynew.getNext();
        }
    }
    void add_in_avl(Position p){
        avl_tree.insert(p);
    }
    Position findnextposition(int g){
        if(avl_tree.findnext(g)==null){
            return null;
        }
        else {
            return avl_tree.findnext(g).data;
        }
    }
    Position beforeposition(int s){
        return avl_tree.findbefore(s).data;
    }
    Position minmin_index(){
        return avl_tree.findmin().data;
    }
    MyLinkedList<Position> getAllPositionsForThisWord(){
        return wordpositions;
    }
    float getTermFrequency(String g)
    {
        float freq=getFrequency(g);
        if(freq!=0){
            float total=(float)wordpositions.FindHeadoflink().get().getPageEntry().max_index;
            return (freq)/(total);
        }
        return 0;

    }
    float getFrequency(String g)
    {
        return ((float)wordpositions.numberofelements());

    }
}
