class Node{
    Object data;
    Node next;
}
public class Myset{
    Node head=null;

    public void Insert(Object i){
        if(!IsMember(i)){
            Node node=new Node();
            node.data=i;
            node.next=null;
            if(head==null){
                head=node;
            }
            else{
                Node g=head;
                while(g.next!=null){
                    g=g.next;
                }
                g.next=node;
				
            }
        }
    }
    public void ins(Object i){
        if(!IsMember(i)){
            Node node=new Node();
            node.data=i;

            if(head==null){
                head=node;
            }
            else{
               node.next=head;
               head=node;
            }
        }
    }
    public int numberofelements(){
        Node new_node=head;
        int i=0;
        while(new_node!=null){
            new_node=new_node.next;
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
                Node ek_nya_node=head;
                while(u!=s ){
                    ek_nya_node=ek_nya_node.next;
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
    public void show(){
        Node p=new Node();
        p=head;
        while( p!=null){


            System.out.println((p).data);
            p=p.next;
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
    public boolean IsMember(Object i){
        Node node=new Node();
        node=head;
        int flag=0;
        while(node!=null){
            if(node.data==i){
                flag=1;
                break;
            }
            node=node.next;
        }
        if(flag==1){return true;}
        else {return false;}
    }
    public void delete(Object i){
        Node node1=new Node();
        Node node2=new Node();
        node1=head;
        node2=head.next;
        int j=0;
        int flag=0;
        try {
            if (node2 == null && node1.data == i) {
                head = null;
                flag=1;
            }
            while (node2 != null && flag==0) {
                if (j == 0 && node1.data == i) {
                    head = node2;
                    flag=1;
                    break;
                }
                if (node2.data == i && node2.next != null) {
                    node2 = node2.next;
                    node1.next = node2;
                    flag=1;
                    break;
                }
                if (node2.data == i && node2.next == null) {
                    node1.next = null;
                    flag=1;
                    break;
                }
                node1 = node1.next;
                node2 = node2.next;
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
    public Node FindHead(){
        return head;
    }
    public Myset Union(Myset a){
        Myset b=new Myset();
        Node node1=new Node();
        node1=head;
        Node node2=new Node();
        node2=a.FindHead();

        while(node1!=null){
            b.Insert(node1.data);
            node1=node1.next;
        }
        while(node2!=null){
            node1=head;
            int flag=0;
            while(node1!=null){
                if(node1.data==node2.data){
                    flag=1;
                    break;
                }
                node1=node1.next;
            }
            if(flag==0){
                b.Insert(node2.data);
            }
            node2=node2.next;
        }
        return b;
    }
    public Myset Intersection(Myset a){
        Myset b=new Myset();
        Myset c=new Myset();
        Node node1=new Node();
        node1=head;
        Node node2=new Node();
        node2=a.FindHead();

        while(node1!=null){
            b.Insert(node1.data);
            node1=node1.next;
        }
        while(node2!=null){
            node1=head;
            int flag=0;
            while(node1!=null){
                if(node1.data==node2.data){
                    flag=1;
                    break;
                }
                node1=node1.next;
            }
            if(flag==1){
                c.Insert(node2.data);
            }
            node2=node2.next;
        }
        return c;
    }
}