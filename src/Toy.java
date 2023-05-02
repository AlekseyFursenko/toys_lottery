public class Toy implements Comparable<Toy>{
    public int id;
    public String toy_name;
    public int weight;

    public Toy(int id, String name, int weight){
        this.id = id;
        this.toy_name = name;
        this.weight = weight;
    }

    @Override
    public int compareTo(Toy o) {
        return o.weight > this.weight ? 1 : -1;
    }

    @Override
    public boolean equals(Object obj){
        Toy t = (Toy)obj;
        return this.id == t.id;
    }

    @Override
    public String toString(){
        return "ID: " + id +
                "  Toy name: " + toy_name +
                "  Weight: " + weight;
    }

}
