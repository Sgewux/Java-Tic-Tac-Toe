import java.util.Scanner;
import gamemethods.Checks;
import gamemethods.PlayMethods;

public class TicTacToe{
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char[][] board = {
            {'*', '*', '*'},
            {'*', '*', '*'},
            {'*', '*', '*'}
        };


        while(true){
            
            
            int userRow;
            int userColumn;
            System.out.println("Current board's state: ");
            PlayMethods.printBoard(board);

            try {
                System.out.print("Choose a row >> ");
                userRow = sc.nextInt() - 1;
                System.out.print("Choose a column >>");
                userColumn = sc.nextInt() - 1;

            } catch (java.util.InputMismatchException e) {

                System.out.println("Please use integers.");
                sc.next();
                continue;
                
            }

            if(userRow < 3 && userColumn < 3 && userColumn >= 0 && userRow >= 0){
                if(board[userRow][userColumn] == '*'){
                    board[userRow][userColumn] = 'X';
                }
                else{
                    System.out.println("\033[H\033[2J");
                    System.out.println("You choosed a busy possition.");
                    continue;
                }
            }else{
                System.out.println("\033[H\033[2J");
                System.out.println("Unvalid range.");
                continue;
            }

            if(Checks.isThereAWinner(board) != 0){
                System.out.println("\033[H\033[2J");
                switch(Checks.isThereAWinner(board)){
                    case 1:
                        System.out.println("User Won!");
                        break;
                    case 2:
                        System.out.println("Pc Won!");
                        break;

                }

                break;
            }

            if(Checks.isFullBoard(board) == true){
                System.out.println("\033[H\033[2J");
                System.out.println("Tie");
                break;
            }

            
            PlayMethods.selectPosition(board);


            if(Checks.isThereAWinner(board) != 0){
                System.out.println("\033[H\033[2J");
                switch(Checks.isThereAWinner(board)){
                    case 1:
                        System.out.println("User Won!");
                        break;
                    case 2:
                        System.out.println("Pc Won!");
                        break;

                }

                break;
            }

            if(Checks.isFullBoard(board) == true){
                System.out.println("\033[H\033[2J");
                System.out.println("Tie");
                break;
            }    
            
            System.out.println("\033[H\033[2J");

        }

        System.out.println("Final board's state: ");
        PlayMethods.printBoard(board);

        sc.close();
    }
}