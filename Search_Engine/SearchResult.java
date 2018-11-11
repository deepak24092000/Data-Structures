public class SearchResult implements Comparable<SearchResult>{
    private PageEntry pe;
    private float f;
    public SearchResult(PageEntry p,float r){
        pe=p;
        f=r;
    }
    public PageEntry getPageEntry(){
        return pe;
    }
    public float getRelevance(){
        return f;
    }
    public int compareTo(SearchResult otherObject){
        if(f<otherObject.getRelevance()){
            return 1;
        }
        else if(f==otherObject.getRelevance()){
            return 0;
        }
        else{
            return -1;
        }
    }
}
