public class Position {
    private PageEntry pe;
    private int index;
    Position(PageEntry p,int wordIndex){
        pe=p;
        index=wordIndex;
    }
    PageEntry getPageEntry(){
        return pe;
    }
    int getWordIndex(){
        return index;
    }
}
