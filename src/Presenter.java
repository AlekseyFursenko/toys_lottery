import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Presenter {

    private Model model;
    private View view;

    public Presenter(View view, String pathDB){
        this.view = view;
        model = new Model(pathDB);
    }

    public void LoadFromFile() {
        model.load();

        if (model.currentList().count() > 0) {
            model.setCurrentIndex(0);
            var toy = model.currentToy();

            System.out.println(model.currentList().count() + " records has been downloaded from the disc.");
        }
    }

    public void add(){
        model.currentList().add(
                new Toy(view.getId(),
                        view.getToyName(),
                        view.getWeight())
        );
    }

    public void edit(){
        if (model.currentList().count() < 1) {
            System.out.println("No any records in the toy list");
        } else {
            System.out.print("Input toy's ID you want to edit - ");
            int id = view.getId();

            if(index_Id(id) < 0){
                System.out.println("No such ID was found. Try another one.");
            }else {
                model.setCurrentIndex(index_Id(id));
                Toy temp = model.currentToy();
                System.out.println("Toy record " + temp.toString() + ". Input new data.");
                model.currentList().editToy(new Toy(view.getId(),
                                                    view.getToyName(),
                                                    view.getWeight()));
                System.out.println("Data changed");
            }
        }
    }

    public int getIndex(int id){
        return 0;
    }
    public void remove() {
        if (model.currentList().count() < 1) {
            System.out.println("No any records in the toy list");
        } else {
            System.out.print("Input toy's ID you want to delete - ");
            int id = view.getId();

            if(index_Id(id) < 0){
                System.out.println("No such ID was found. Try another one.");
            }else {
                model.setCurrentIndex(index_Id(id));
                Toy temp = model.currentToy();
                model.currentList().remove(model.currentToy());
                System.out.println("Toy record " + temp.toString() + " removed from list.");
            }
        }
    }

    public void saveToFile(){
        model.save();
    }

    public void list_of_toys(){
        if(model.currentList().count() > 0){
            for (int i = 0; i < model.currentList().count(); i++) {
                model.setCurrentIndex(i);
                Toy toy = model.currentToy();
                System.out.println(toy.toString());
            }
            }
    }

    public void list_clear(){
        model.currentList.listClear();
        System.out.println("All data in toy list has been deleted.");
    }


    public void next(){
        if(model.currentList().count() > 0){
            if(model.getCurrentIndex() +1 < model.currentList().count()){
                model.setCurrentIndex(model.getCurrentIndex() + 1);
                Toy toy = model.currentToy();
                view.setId(toy.id);
                view.setToyName(toy.toy_name);
                view.setWeight(toy.weight);
            }
        }
    }

    public void prev(){
        if(model.currentList().count() > 0){
            if(model.getCurrentIndex() - 1 > -1){
                model.setCurrentIndex(model.getCurrentIndex() - 1);
                Toy toy = model.currentToy();
                view.setId(toy.id);
                view.setToyName(toy.toy_name);
                view.setWeight(toy.weight);
            }
        }
    }

    public int index_Id(int id){
        for (int i = 0; i < model.currentList().count(); i++) {
            model.setCurrentIndex(i);
            if (model.currentToy().id == id ){
                return i;
            }
        }
        return -1;
    }

    // get data to lottery queue
    public void lottery() {
        if (model.currentList().count() < 1) {
            System.out.println("No any records in the toy list");
        } else {
            Queue<Toy> lotList = new PriorityQueue<>(new Comparator<Toy>() {
                @Override
                public int compare(Toy o1, Toy o2) {
                    return o1.weight < o2.weight ? 1 : -1;
                }
            });
            int numToys = view.getNumber();
            System.out.println("List of toys for a lottery:");
            for (int i = 0; i < numToys; i++) {
                lotList.add(randomToy());
            }
            saveLotteryResult(lotList);
            System.out.println("Compare by weight lottery list of toys has been saved on disc to " + Config.pathLotList);
        }
    }
    // get random toy
    public Toy randomToy(){
        int totalWeight = 0;
        int toyWeight = 0;
        for(Toy toy : model.currentList().getToys()) {
            totalWeight += toy.weight;
        }
        double rand = Math.random() * totalWeight;

        for (Toy toy : model.currentList().getToys()) {
            toyWeight += toy.weight;
            if (toyWeight >= rand) {
                System.out.println(toy.toString());
                return toy;
            }
        }
        throw new RuntimeException("Should never be shown.");
    }
    private void saveLotteryResult(Queue lotlist){
        try (FileWriter writer = new FileWriter(Config.pathLotList, false)){
            while (!lotlist.isEmpty()){
                writer.append(lotlist.poll().toString()).append("\n");
            }
            writer.flush();
            writer.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());

        }
    }
}
