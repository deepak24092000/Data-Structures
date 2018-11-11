class MySet<X>{
    MyLinkedList<X> my=new MyLinkedList<>();
    void addElement(X element){
        if(!my.IsMember(element)){
            my.addElementinlink(element);}
    }
    void addElementFront(X element,int i){
        if(!my.IsMember(element)) {
            if (my.numberofelements() < i) {

            } else {
                my.addElementinlinkFront(element, i);
            }
        }
    }
    Node<X> FindHead(){
        return my.FindHeadoflink();
    }
    void print(){
        my.printlink();
    }
    MySet<X> union(MySet<X> otherSet){
        MySet<X> b=new MySet<X>();
        Node<X> node1;
        node1=my.FindHeadoflink();
        Node<X> node2;
        node2=otherSet.FindHead();
        while(node1!=null){
            b.addElement(node1.get());
            node1=node1.getNext();
        }
        while(node2!=null){
            node1=my.FindHeadoflink();
            int flag=0;
            while(node1!=null){
                if(node1.get().equals(node2.get())){
                    flag=1;
                    break;
                }
                node1=node1.getNext();
            }
            if(flag==0){
                b.addElement(node2.get());
            }
            node2=node2.getNext();
        }
        return b;
    }
    MySet<X> intersection(MySet<X> otherSet){
        MySet<X> b=new MySet<X>();
        MySet<X> c=new MySet<X>();
        Node<X> node1=new Node<X>();
        node1=my.FindHeadoflink();
        Node<X> node2=new Node<X>();
        node2=otherSet.FindHead();

        while(node1!=null){
            b.addElement(node1.get());
            node1=node1.getNext();
        }
        while(node2!=null){
            node1=my.FindHeadoflink();
            int flag=0;
            while(node1!=null){
                if(node1.get()==node2.get()){
                    flag=1;
                    break;
                }
                node1=node1.getNext();
            }
            if(flag==1){
                c.addElement(node2.get());
            }
            node2=node2.getNext();
        }
        return c;
    }
}
