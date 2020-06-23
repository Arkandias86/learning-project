import java.util.ArrayList;

public class Sapin {
    public ArrayList<Integer> line_number = new ArrayList<>();
    public int size_previous_line = 7; //we start using it only when there are 2 stages for the tree

    //calculate the size of the last line of a given tree size and return it. 0 is treated separatly
    public int calculate_last_line (int input){
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

        if (size % 2 != 0){ //when odd size there are equals amount of pipes around the middle one
            int sides = size / 2;
            int first = middle - sides;
            int last = middle + sides;

            for (int i = 0; i < first; i++){
                trunk_line.append(" ");
            }
            for (int i = first; i <= last; i++) {
                trunk_line.append("|");
            }
            for (int i = 0; i < size; i++) {
                System.out.println(trunk_line);
            }
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
        trunk(size, middle);
    }



    public static void main(String[] args) {
        int size = 3;

        Sapin test = new Sapin();
        test.tree(size);
    }
}
