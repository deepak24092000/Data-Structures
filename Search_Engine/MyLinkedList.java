class Node<X>{
    private X obj;
    private Node<X> link;
    public void add(X obj){
        this.obj=obj;
    }
    public X get(){
        return obj;
    }

    public void setLink(Node<X> link) {
        this.link=link;
    }

    public Node<X> getNext() {
        return link;
    }
}
public class MyLinkedList<X>{
    Node<X> head=null;
    void addElementinlink(X element){
        Node<X> nya=new Node<X>();
        nya.add(element);
        if(head==null){
            head=nya;
            head.setLink(null);
        }
        else{
            Node<X> n=new Node<X>();
            n=head;
            while(n.getNext()!=null){
                n=n.getNext();
            }
            nya.setLink(null);
            n.setLink(nya);
        }
    }
    void addElementinlinkFront(X element,int i){
        Node<X> nya=new Node<X>();
        nya.add(element);
        int j=0;
        Node<X> ek_aur_nya=new Node<X>();
        Node<X> dusra_aur_nya=new Node<X>();
        ek_aur_nya=head;
        if(i==0){
            nya.setLink(head);
            head=nya;
        }
        else{
            while(ek_aur_nya!=null){
                if(j==i-1){
                    dusra_aur_nya=ek_aur_nya.getNext();
                    ek_aur_nya.setLink(nya);
                    nya.setLink(dusra_aur_nya);
                    break;
                }
                j++;
                ek_aur_nya=ek_aur_nya.getNext();
            }
        }

    }
    Node<X> FindHeadoflink(){
        return head;
    }
    void printlink(){
        Node<X> nya=head;
        while(nya!=null){
            System.out.println(nya.get());
            nya=nya.getNext();
        }
    }
    public boolean IsEmpty(){
        if(head==null){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean IsMember(X element){
        Node<X> node=new Node<X>();
        if(head==null){
            return false;
        }
        node=head;
        int flag=0;
        while(node!=null){
            if(node.get().equals(element)){
                flag=1;
                break;
            }
            node=node.getNext();
        }
        if(flag==1){return true;}
        else {return false;}
    }
    public void delete(X i){
        Node<X> node1=new Node<X>();
        Node<X> node2=new Node<X>();
        node1=head;
        node2=head.getNext();
        int j=0;
        int flag=0;
        try {
            if (node2 == null && node1.get() == i) {
                head = null;
                flag=1;
            }
            while (node2 != null && flag==0) {
                if (j == 0 && node1.get() == i) {
                    head = node2;
                    flag=1;
                    break;
                }
                if (node2.get() == i && node2.getNext() != null) {
                    node2 = node2.getNext();
                    node1.setLink(node2);
                    flag=1;
                    break;
                }
                if (node2.get() == i && node2.getNext() == null) {
                    node1.setLink(null);
                    flag=1;
                    break;
                }
                node1 = node1.getNext();
                node2 = node2.getNext();
                j++;
            }
            if(flag==0){
                throw new Exception();
            }
        }
        catch(Exception e){
            System.out.println("Element to be removed not found");
        }
    }
    public int numberofelements(){
        Node<X> new_node=head;
        int i=0;
        while(new_node!=null){
            new_node=new_node.getNext();
            i++;
        }
        return i;
    }
    public Node ithchildren(int s){
        try{
            if(numberofelements()<s){
                throw new Exception();
            }
            else{
                int u=0;
                Node<X> ek_nya_node=head;
                while(u!=s ){
                    ek_nya_node=ek_nya_node.getNext();
                    u++;
                }
                return ek_nya_node;
            }

        }
        catch(Exception e){
            System.out.println("The element at given index does not exist");
        }
        return null;
    }
}