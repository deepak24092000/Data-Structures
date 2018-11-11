public class MobilePhone {
   private int id;
   private boolean status = false;
   Exchange fieldl_location;

    MobilePhone(int number) {
        id = number;
    }

    public int number() {
        return id;
    }

    public boolean status() {
        return status;
    }

    public void switchOn() {
        status = true;
    }

    public void switchOff() {
        status = false;
    }

    public Exchange location() {
        try {
            if (status == true) {
                return fieldl_location;
            } else {
                throw new Exception();
            }
        }
        catch(Exception ee){
            System.out.println("trying to get location of a switched off phone");
        }
        return null;
    }
}