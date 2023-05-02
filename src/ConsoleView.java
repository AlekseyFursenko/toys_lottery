import java.util.Scanner;

public class ConsoleView implements View{
    Scanner in;
    public ConsoleView(){
        in = new Scanner(System.in);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public Integer getId(){
        String value = null;
        while (true){
            System.out.printf("ID: ");
            value = in.nextLine();
            if (isNumeric(value)) {
                break;
            }
            System.out.println("Wrong! Please input integer value!" );
        }

        return Integer.valueOf(value);
    }

    @Override
    public void setId(int value){
        System.out.printf("ID: %d\n", value);
    }

    @Override
    public String getToyName(){
        System.out.printf("Toy name: ");
        return in.nextLine();
    }

    @Override
    public void setToyName(String value){
        System.out.printf("Toy name: %s\n", value);
    }

    @Override
    public Integer getWeight() {
        String value = null;
        while (true){
            System.out.printf("Weight: ");
            value = in.nextLine();
            if (isNumeric(value)) {
                break;
            }
            System.out.println("Wrong! Please input integer value!" );
        }

        return Integer.valueOf(value);
    }

    @Override
    public void setWeight(int value) {
        System.out.printf("Weight: %d\n", value);
    }

    @Override
    public Integer getNumber(){
        String value = null;
        while (true){
            System.out.printf("Please input a quantity of toys for lottery: ");
            value = in.nextLine();
            if (isNumeric(value) && Integer.parseInt(value) > 0) {
                break;
            }
            System.out.println("Wrong! Please input an integer positive value!" );
        }

        return Integer.valueOf(value);
    }


}
