public class PageIndex {
    WordEntry allword;
    MyLinkedList<WordEntry> my_hash=new MyLinkedList<WordEntry>();
    Boolean containsString(String s){
        int flag=1;
        Node<WordEntry> newnode=my_hash.FindHeadoflink();
        while(newnode!=null){
            if((newnode.get().Word).equals(s)){
                flag=0;
                allword=newnode.get();
                break;
            }
            newnode=newnode.getNext();
        }
        if(flag==0){
            return true;
        }
        return false;
    }
    WordEntry givewordentry(String str){
        boolean k=containsString(str);
        if(k){
            return allword;
        }
        else{
            return null;
        }
    }
    void addPositionForWord(String str, Position p){
        if(containsString(str)){
            allword.addPosition(p);
        }
        else{
            WordEntry wordentry=new WordEntry(str);
            wordentry.addPosition(p);
            my_hash.addElementinlink(wordentry);
        }
    }
    MyLinkedList<WordEntry> getWordEntries(){
        return my_hash;
    }
    float getfrequency(String g,String str){
        boolean k=containsString(str);
        if(k){
            return allword.getFrequency(g);
        }
        return 0;
    }
    float gettermfreq(String g,String str){
        boolean k=containsString(str);
        if(k){
            return allword.getTermFrequency(g);
        }
        return 0;
    }

}
