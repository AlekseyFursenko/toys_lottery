import java.util.Scanner;

public class App {
    public static void ButtonClick(){
        System.out.print("\033[H\033[J"); //очистить-консоль-в-java-во-время-действия-программы
        View view = new ConsoleView();
        Presenter presenter = new Presenter(view, Config.pathToyList);

        try (Scanner in = new Scanner(System.in)){
            while (true){
                System.out.print("----------------------------\n" +
                                "1 - Create a toy record\n" +
                                "2 - List of toys\n" +
                                "3 - Edit toy record\n" +
                                "4 - Remove toy record\n" +
                                "5 - Save toy list on disc\n" +
                                "6 - Load toy list from disc\n" +
                                "7 - New toy list\n" +
                                "8 - Toy lottery\n" +
                                "0 - Exit\n" +
                                "------------------------------\n" +
                                "Choose a command from above\n");
                String key = in.next();
                System.out.print("\033[H\033[J");

                switch (key){
                    case "1":
                        presenter.add();
                        break;
                    case "2":
                        presenter.list_of_toys();
                        break;
                    case "3":
                        presenter.edit();
                        break;
                    case "4":
                        presenter.remove();
                        break;
                    case "5":
                        presenter.saveToFile();
                        break;
                    case "6":
                        presenter.LoadFromFile();
                        break;
                    case "7":
                        presenter.list_clear();
                        break;
                    case "8":
                        presenter.lottery();
                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Input other command!");
                        break;

                }
            }
        }
    }
}
