public class RoutingMapTree{
    Exchange root=null;
    MobilePhone my_mobilephone=null;
    Exchange my_nExchange=null;
    Exchange idexchange=null;
    Exchange Lowest_Exchange=null;
    int lambda=0;
    int ideex=0;
    int router=0;
	int sss=0;
	int nyeee=0;
    public RoutingMapTree(){
        Exchange firstnode=new Exchange(0);
        root=firstnode;
    }
    public Exchange findPhone(MobilePhone m){
        try {
            if (m.status() == true) {
                return m.location();
            }
            else {
                throw new Exception();
            }
        }
        catch(Exception e){
			sss=1;
			nyeee=m.number();
			}
        return null;
    }
    /*  public void findlowestrouter(Exchange a,Exchange b,Exchange c){
          if(router==1|| c.numChildren()==0){
              return;
          }
          else{
              int numchild=c.numChildren();
              for(int z=0;z<numchild;z++){
                  Exchange child=c.child(z);
                  Myset c_int_a=(child.residentSet().mobilephoneset).Intersection(a.residentSet().mobilephoneset);
                  Myset c_int_b=(child.residentSet().mobilephoneset).Intersection(b.residentSet().mobilephoneset);
                  if(!c_int_a.IsEmpty() || !c_int_b.IsEmpty()){
                      if(c_int_a.IsEmpty() || c_int_b.IsEmpty()){
                          Lowest_Exchange=c;
                          router=1;
                      }
                      else{
                          findlowestrouter(a,b,child);
                      }
                  }
  
              }
          }
      }
      public Exchange lowestRouter(Exchange a, Exchange b){
          if(a==b){
              return a;
          }
          else if(!(((a.residentSet().mobilephoneset).Intersection(b.residentSet().mobilephoneset)).IsEmpty())){
              Exchange nyae=a;
              int fla=0;
              while(nyae!=null){
                  if(nyae==b){
                      fla=1;
                      break;
                  }
                  nyae=nyae.papa;
              }
              if(fla==1){
                  return b;
              }
              else{
                  return a;
              }
          }
          else{
              Exchange rootnya=root;
              Lowest_Exchange=null;
              router=0;
              findlowestrouter(a,b,rootnya);
              return Lowest_Exchange;
          }
  
      }*/
    public Exchange lowestRouter(Exchange a,Exchange b){
        ExchangeList a_=new ExchangeList();
        ExchangeList b_=new ExchangeList();
        if(a==b){
            return a;
        }
        while(!a.isRoot()){
            a_.add(a);
            a=a.papa;

        }
        a_.add(a);
        while(b!=null){
            b_.add(b);
            if(!((a_.exchangelist).Intersection(b_.exchangelist)).IsEmpty()){
                break;
            }
            b=b.papa;
        }
        return b;
    }
    public ExchangeList routeCall(MobilePhone a, MobilePhone b){
        ExchangeList routecall=new ExchangeList();
        Exchange a_ex=findPhone(a);
        Exchange b_ex=findPhone(b);
        try {
            if (a_ex != null && b_ex != null) {
                Exchange lowrouter = lowestRouter(a_ex, b_ex);
                while (a_ex != lowrouter) {
                    routecall.add(a_ex);
                    a_ex = a_ex.papa;
                }
                routecall.add(lowrouter);
                ExchangeList temp = new ExchangeList();
                while (b_ex != lowrouter) {
                    temp.addulta(b_ex);
                    b_ex = b_ex.papa;
                }
                Node ny = temp.exchangelist.FindHead();
                while (ny != null) {
                    routecall.add((Exchange) ny.data);
                    ny = ny.next;
                }
                return routecall;
            }
            else{
                throw new Exception();
            }
        }
        catch(Exception e){

        }
        return null;
    }
    public void movePhone(MobilePhone a, Exchange b){
        if(a.status()==true){
            switchOffhelper(a,a.location());
            a.fieldl_location=b;
            Exchange nya=b;
            while(!(nya.isRoot())){
                (nya.residentSet()).add(a);
                nya=nya.papa;
            }
            (nya.residentSet()).add(a);
        }
    }
    public void findbaseExchange(Exchange my_exchange,int id){
        if(lambda==1 || my_exchange.numChildren()==0){
            return;
        }
        else{
            int nchildren=my_exchange.numChildren();
            for(int x=0;x<nchildren;x++){
                Exchange curr_exchange=my_exchange.child(x);
                Node new_node=curr_exchange.residentSetmobile.mobilephoneset.FindHead();
                while(new_node!=null){
                    if(id==((MobilePhone)new_node.data).number()){
                        if(curr_exchange.numChildren()==0){
                            my_nExchange=curr_exchange;
                            lambda=1;
                        }
                        else{
                            findbaseExchange(curr_exchange,id);
                        }
                    }
                    new_node=new_node.next;
                }
            }
        }

    }
    public void idexchangefinder(Exchange a,int identity){
        if(a.numbe()==identity){
            idexchange=a;
            ideex=1;
        }
        else if(ideex==1 || a.numChildren()==0){
            return;
        }
        else{
            int numberof=a.numChildren();
            for(int y=0;y<numberof;y++){
                idexchangefinder(a.child(y),identity);
            }

        }

    }
    public boolean Exctractor(Exchange e,int id){
        Node n=new Node();
        n=e.residentSetmobile.mobilephoneset.FindHead();
        int flag=0;
        while(n!=null){
            if(((MobilePhone)n.data).number()==id){
                my_mobilephone=((MobilePhone)n.data);
                flag=1;
                break;
            }
            n=n.next;
        }
        if(flag==1){
            return true;
        }
        else{
            return false;
        }
    }
    public void switchOn(MobilePhone a, Exchange b){
        if(a.status()==true){
            return;
        }
        else{
            a.switchOn();
            Exchange nya=b.papa;
            while(!(nya.isRoot())){
                (nya.residentSet()).add(a);
                nya=nya.papa;
            }
            (nya.residentSet()).add(a);
        }
    }
    public void switchOff(MobilePhone a){
        if(a.status()==false){
            return;
        }
        else{
            a.switchOff();
        }
    }
    public void switchOffhelper(MobilePhone a,Exchange bb){
        while(!(bb.isRoot())){
            (bb.residentSet()).delete(a);
            bb=bb.papa;
        }
        (bb.residentSet()).delete(a);

    }
    public String performAction(String actionMessage) {
        String [] currencies=actionMessage.split(" ");
        try {
            if (currencies[0].equals("addExchange")) {
                int i = Integer.parseInt(currencies[1]);
                int j = Integer.parseInt(currencies[2]);
                try {
                    Exchange rexchange = root;
                    ideex = 0;
                    idexchange = null;
                    idexchangefinder(rexchange, i);
                    if (idexchange.equals(null)) {
                        throw new Exception();
                    }
                    Exchange ee = idexchange;
                    Exchange nyaa = root;
                    ideex = 0;
                    idexchange = null;
                    idexchangefinder(nyaa, j);
                    if (idexchange == null) {
                        Exchange b = new Exchange(j);
                        b.papa = ee;
                        ee.exchangelinkedlist.add(b);
                        while (!(ee.isRoot())) {
                            if (!ee.residentSet().mobilephoneset.IsEmpty() && !b.residentSet().mobilephoneset.IsEmpty()) {
                                ee.residentSet().mobilephoneset = (ee.residentSet().mobilephoneset).Union(b.residentSet().mobilephoneset);
                            } else if (ee.residentSet().mobilephoneset.IsEmpty() && !b.residentSet().mobilephoneset.IsEmpty()) {
                                ee.residentSet().mobilephoneset = b.residentSet().mobilephoneset;
                            }
                            ee = ee.papa;
                            b = b.papa;
                        }
                        if (!ee.residentSet().mobilephoneset.IsEmpty() && !b.residentSet().mobilephoneset.IsEmpty()) {
                            ee.residentSet().mobilephoneset = (ee.residentSet().mobilephoneset).Union(b.residentSet().mobilephoneset);
                        } else if (ee.residentSet().mobilephoneset.IsEmpty() && !b.residentSet().mobilephoneset.IsEmpty()) {
                            ee.residentSet().mobilephoneset = b.residentSet().mobilephoneset;
                        }
                    }
                    return "";
                } catch (Exception e) {
                    System.out.println("No exchange with identifier of " + i + " is found");
                }
            } else if (currencies[0].equals("switchOnMobile")) {
                int i = Integer.parseInt(currencies[1]);
                int j = Integer.parseInt(currencies[2]);
                try {
                    Exchange z = root;
                    Exchange rexchange = root;
                    ideex = 0;
                    idexchange = null;
                    idexchangefinder(rexchange, j);
                    if (idexchange.equals(null)) {
                        throw new Exception();
                    }
                    my_mobilephone = null;
                    boolean k = Exctractor(z, i);
                    if (k) {
                        my_nExchange = null;
                        lambda = 0;
                        Exchange nexch = root;
                        findbaseExchange(nexch, i);
                    }
                    if (k && my_nExchange == idexchange) {
                        my_mobilephone.switchOn();
                    } else if (!k) {
                        MobilePhone m = new MobilePhone(i);
                        idexchange.residentSetmobile.add(m);
                        m.fieldl_location = idexchange;
                        switchOn(m, idexchange);
                    } else if (k && my_nExchange != idexchange) {
                        switchOffhelper(my_mobilephone, my_nExchange);
                        idexchange.residentSetmobile.add(my_mobilephone);
                        my_mobilephone.fieldl_location = idexchange;
                        switchOn(my_mobilephone, idexchange);
                    }
                    return "";
                } catch (Exception e) {
                    System.out.println("Error- No exchange with identifier " + j);
                }

            } else if (currencies[0].equals("switchOffMobile")) {
                int i = Integer.parseInt(currencies[1]);
                try {
                    Exchange es = root;
                    my_mobilephone = null;
                    boolean k = Exctractor(es, i);
                    if (k == false) {
                        throw new Exception();
                    }
                    switchOff(my_mobilephone);
                    return "";
                } catch (Exception e) {
                    System.out.println("Error- No mobile phone with identifier " + i);
                }
            } else if (currencies[0].equals("queryNthChild")) {
                int i = Integer.parseInt(currencies[1]);
                int j = Integer.parseInt(currencies[2]);
                try {
                    Exchange rexchange = root;
                    ideex = 0;
                    idexchange = null;
                    idexchangefinder(rexchange, i);
                    if (idexchange == null) {
                        throw new Exception();
                    }

                    String s = Integer.toString(idexchange.child(j).numbe());
                    System.out.println(actionMessage + ": " + s);
                    return (actionMessage + ": " + s);
                } catch (Exception e) {
                    System.out.println("Error - No " + j + " child of Exchange " + i);
                }

            } else if (currencies[0].equals("queryMobilePhoneSet")) {
                int i = Integer.parseInt(currencies[1]);
                try {
                    Exchange rexchange = root;
                    ideex = 0;
                    idexchange = null;
                    idexchangefinder(rexchange, i);
                    if (idexchange == null) {
                        throw new Exception();
                    }
                    Node k = idexchange.residentSetmobile.mobilephoneset.FindHead();
                    String s = "";
                    while (k.next != null) {
                        if (((MobilePhone) k.data).status()) {
                            s = s + Integer.toString(((MobilePhone) k.data).number()) + ", ";
                        }
                        k = k.next;
                    }
                    s = s + Integer.toString(((MobilePhone) k.data).number());
                    System.out.println(actionMessage + ": " + s);
                    return (actionMessage + ": " + s);


                } catch (Exception e) {
                    System.out.println("Mobile phone with given identifier does not exist");
                }
            } else if (currencies[0].equals("findPhone")) {
                int i = Integer.parseInt(currencies[1]);
                try {
                    Exchange es = root;
                    my_mobilephone = null;
                    boolean k = Exctractor(es, i);
                   /* if (k == false) {
                        throw new Exception();
                    }*/
                    String s = "";
					if(k==false){
						s="Error - No mobile phone with identifier " +i+ " found in the network";
					}
					if(k==true){
                    s = Integer.toString((findPhone(my_mobilephone)).numbe());}
                    System.out.println("queryFindPhone "+currencies[1] + ": " + s);
                    return ("queryFindPhone "+currencies[1] + ": " + s);
                } catch (Exception e) {
                    System.out.println("Mobile phone with given identifier does not exist");
                }
            } else if (currencies[0].equals("lowestRouter")) {
                int i = Integer.parseInt(currencies[1]);
                int j = Integer.parseInt(currencies[2]);
                try {
                    Exchange rexchange = root;
                    ideex = 0;
                    idexchange = null;
                    idexchangefinder(rexchange, i);
                    if (idexchange == null) {
                        throw new Exception();
                    }
                    Exchange a = idexchange;
                    Exchange rxchange = root;
                    ideex = 0;
                    idexchange = null;
                    idexchangefinder(rxchange, j);
                    if (idexchange == null) {
                        throw new Exception();
                    }
                    Exchange b = idexchange;
                    Exchange lowest_exchange = lowestRouter(a, b);
                    String s = "";
                    if (lowest_exchange == null) {
                        System.out.println("nulla");
                    }
                    if (lowest_exchange != null) {
                        s = Integer.toString(lowest_exchange.numbe());
                    }
                    System.out.println("queryLowestRouter "+currencies[1]+" "+currencies[2] + ": " + s);
                    return ("queryLowestRouter "+currencies[1]+" "+currencies[2] + ": " + s);
                } catch (Exception e) {
                    System.out.println("Exchange with given id does not exist");
                }
            } else if (currencies[0].equals("findCallPath")) {
                int i = Integer.parseInt(currencies[1]);
                int j = Integer.parseInt(currencies[2]);
                try {
                    Exchange es = root;
                    my_mobilephone = null;
                    boolean k = Exctractor(es, i);
                    if (!k) {
                        throw new Exception();
                    }
                    MobilePhone a = my_mobilephone;
                    my_mobilephone = null;
                    boolean t = Exctractor(es, j);
                    if (!t) {
                        throw new Exception();
                    }
                    MobilePhone b = my_mobilephone;
					sss=0;
					nyeee=0;
                    ExchangeList callpath = routeCall(a, b);
                    String s = "";
					if(sss==1){
						s="Error - Mobile phone with identifier " +nyeee+ " is currently switched off";
					}
                    if (callpath != null) {
                        Node routecall_node = callpath.exchangelist.FindHead();
                        while (routecall_node.next != null) {

                            s = s + ((Exchange) routecall_node.data).numbe() + ", ";
                            routecall_node = routecall_node.next;
                        }
                        s = s + ((Exchange) routecall_node.data).numbe();
                    }
                    System.out.println("queryFindCallPath "+currencies[1]+" "+currencies[2]  + ": " + s);
                    return ("queryFindCallPath "+currencies[1]+" "+currencies[2]  + ": " + s);
                } catch (Exception e) {
                    System.out.println("Mobile phone with given identifier does not exist");
                }
            } 
			else if (currencies[0].equals("movePhone")) {
                int i = Integer.parseInt(currencies[1]);
                int j = Integer.parseInt(currencies[2]);
                try {
                    Exchange es = root;
                    my_mobilephone = null;
                    boolean t = Exctractor(es, i);
                    if (!t) {
                        throw new Exception();
                    }
                    Exchange rexchange = root;
                    ideex = 0;
                    idexchange = null;
                    idexchangefinder(rexchange, j);
                    if (idexchange == null) {
                        throw new Exception();
                    }
                    movePhone(my_mobilephone, idexchange);
                    return "";
                } catch (Exception e) {
					
                }
            }
            else{
                throw new Exception();
            }
        }
        catch(Exception e){
            System.out.println("Please enter input in correct format");
        }
        
        return "";
    }
}