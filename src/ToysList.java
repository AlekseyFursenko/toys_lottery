import java.util.ArrayList;
import java.util.List;

public class ToysList {

    private List<Toy> toys;

    public ToysList(){
        toys = new ArrayList<Toy>();
    }
    // create toy
    public boolean add(Toy toy){
        boolean flag = false;
        if (!toys.contains(toy)) {
            toys.add(toy);
            flag = true;
        }
        return flag;
    }

    // read
    public Toy getToyInfo(int index){
        return contains(index) ? toys.get(index) : null;
    }
    // edit
    public boolean editToy(Toy toy){
        boolean flag = false;
        if(toys.indexOf(toy) != -1){
            toys.set(toys.indexOf(toy),toy);
            flag = true;
        }
        return flag;
    }

    //delete
    public boolean remove(Toy toy){
        boolean flag = false;
        if(toys.indexOf(toy) != -1){
            toys.remove(toy);
            flag = true;
        }
        return flag;
    }

    private boolean contains(int index){
        return toys != null && toys.size() > index;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public int count(){
        return toys.size();
    }

    public void listClear () { this.toys.clear();}
}

