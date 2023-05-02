import java.io.*;

public class Model {

    ToysList currentList;
    private int currentIndex;
    private String path;

    public Model(String path){
        currentList = new ToysList();
        currentIndex = 0;
        this.path = path;
    }

    public Toy currentToy(){
        if(currentIndex >= 0){
            return currentList.getToyInfo(currentIndex);
        } else {
            return null;
        }
    }

    public void load() {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String id = reader.readLine();
            while (id != null) {
                String toy_name = reader.readLine();
                String toy_weight = reader.readLine();
                this.currentList.add(new Toy(Integer.valueOf(id), toy_name, Integer.valueOf(toy_weight)));
                id = reader.readLine();
            }
            reader.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(){
        try (FileWriter writer = new FileWriter(path, false)){
            for (int i = 0; i < currentList.count(); i++){
                Toy toy = currentList.getToyInfo(i);
                writer.append(String.format("%d\n", toy.id));
                writer.append(String.format("%s\n", toy.toy_name));
                writer.append(String.format("%d\n", toy.weight));
            }
            writer.flush();
            writer.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());

        }
    }

    public ToysList currentList(){
        return this.currentList;
    }

    public int getCurrentIndex(){
        return this.currentIndex;
    }

    public void setCurrentIndex(int value){
        this.currentIndex = value;
    }

}
