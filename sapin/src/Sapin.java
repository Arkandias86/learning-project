import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sapin {

    //calculate the size of the last line of a given tree size and return it. 0 is treated separatly
    public int calculate_last_line (int input){
        int size_previous_line = 7; //we start using it only when there are 2 stages for the tree
        int result = 0;
        if ( input == 1)
        {
            return 7;
        }
        else {
            for (int i = 2; i <= input; i++) {
                result = (((4 + (i - 1) - 2)) * 2) + size_previous_line;
                size_previous_line = result;
            }
        }
        return result;
    }

    //give the middle of the last line which will be the start for the first star
    public int calculate_middle (int size){
        int result = 0;
        result = (size / 2) + 1;
        return result;
    }

    //display stars with middle coordinates and sides stars around
    public void write_stars(int middle, int sides){
        int first = middle - sides;
        int last = middle + sides;
        StringBuilder stars_line = new StringBuilder();

        for (int i = 0; i <first; i++){ //empty spaces until first star
            stars_line.append(" ");
        }

        for (int i = first; i <= last; i++){ //stars are added
            stars_line.append("*");
        }
        System.out.println(stars_line);
    }
    //display the trunk of the tree
    public void trunk(int size, int middle){
        StringBuilder trunk_line = new StringBuilder();
        int sides = 0;
        int first = 0;
        int last = 0;

        if (size % 2 != 0){ //when odd size there are equals amount of pipes around the middle one
            sides = size / 2;
            first = middle - sides;
            last = middle + sides;
        }else{ //when even, choosing to have one more on the left side than the right side
            sides = size / 2;
            first = middle - sides;
            last = middle + (sides - 1);
        }

            for (int i = 0; i < first; i++){ //adding empty spaces
                trunk_line.append(" ");
            }
            for (int i = first; i <= last; i++) {
                trunk_line.append("|"); //constructing the trunk
            }
            for (int i = 0; i < size; i++) {
                System.out.println(trunk_line); //adding the layers of the trunk
            }

    }

    public void tree(int size){
        int line_step = 4; //number of lines for first tree
        boolean finish = false;
        int number_of_sides_stars = 0;

        int number_last_line = calculate_last_line(size);
        int middle = calculate_middle(number_last_line);

        if (size <= 0){
            return;
        }else{
            while (!finish){
                for (int i = 0; i < size; i++){ //looping for each step of the size
                    for (int j = 0; j < line_step; j++){ //looping for each line in the current step
                        write_stars(middle, number_of_sides_stars);
                        number_of_sides_stars++;
                    }
                    line_step++; //each new step has one more line
                    number_of_sides_stars -= 2; // first line of new step has one less star than last line
                }
                finish = true;
            }
        }
        trunk(size, middle); // adding the trunk
    }



    public static void main(String[] args) {
        int size = 0;
        System.out.println("Please, enter the size of the tree you want and press enter:");
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(System.in))) {
            size = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sapin test = new Sapin();
        test.tree(size);
    }
}
