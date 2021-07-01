package gamemethods;

import java.util.List;
import java.lang.Math;
import java.util.ArrayList;

public final class PlayMethods {

    public static void printBoard(char[][] board){
        for(char[] row : board){
            for(char column : row){
                System.out.print(column + " ");
            }
            System.out.println(' ');
        }
    }

    public static void clearConsole(){
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            
            if(os.contains("mac") || os.contains("linux")){
                Runtime.getRuntime().exec("clear");
            }

        } catch (Exception e) {
            
            System.out.println("AAAAAAAAAAAAAA");
        }    
    }

    public static void selectPosition(char[][] board){
        //This func uses the board state to decide how to play
        int myRow = 5;
        int myColumn = 5;
        List<Integer[]> positions = new ArrayList<Integer[]>();

        for(int row = 0; row < 3; row++){
			for(int column = 0; column < 3; column++ ){
				if(board[row][column] == 'X'){
					Integer[] newPos = {row, column};
					positions.add(newPos);
				}
			}
		}

        if(positions.size() >= 2){
            for(int i = 0; i < positions.size() - 1; i++){
                Integer[] firstPos = positions.get(i); 
                for(int j = i + 1; j < positions.size(); j++){
                    Integer[] secondPos = positions.get(j);

                    if(firstPos[0] == secondPos[0]){
                        int a = firstPos[0];
                        int b;

                        if(firstPos[1] + secondPos[1] == 1){
                            b = 2;
                        }else{
                            b = Math.abs(firstPos[1] - secondPos[1]) - 1;
                        }

                        if(board[a][b] == '*' && myRow == 5 && myColumn == 5){
                            myRow = a;
                            myColumn = b;
                        }
                    
                    }else if(firstPos[1] == secondPos[1]){
                        int b = firstPos[1];
                        int a;

                        if(firstPos[0] + secondPos[0] == 1){
                            a = 2;
                        }else{
                            a = Math.abs(firstPos[0] - secondPos[0]) - 1;
                        }

                        if(board[a][b] == '*' && myRow == 5 && myColumn == 5){
                            myRow = a;
                            myColumn = b;
                        }

                    }else if((firstPos[0] + firstPos[1] == 0 || firstPos[0] + firstPos[1] == 2 || firstPos[0] + firstPos[1] == 4) && firstPos[0] != 1){
                        int a = Math.abs(firstPos[0] - 2);
                        int b = Math.abs(firstPos[1] - 2);
        
                        if(board[a][b] == '*' && myRow == 5 && myColumn == 5){
                            myRow = a;
                            myColumn = b;
                        }
                    
                    }else if((secondPos[0] + secondPos[1] == 0 || secondPos[0] + secondPos[1] == 2 || secondPos[0] + secondPos[1] == 4) && secondPos[0] != 1){
                        int a = Math.abs(secondPos[0] - 2);
                        int b = Math.abs(secondPos[1] - 2);
        
                        if(board[a][b] == '*' && myRow == 5 && myColumn == 5){
                            myRow = a;
                            myColumn = b;
                        }
                    }
                }
            }
        
        }else if(positions.size() == 1){
            Integer[] position = positions.get(0);

            if((position[0] + position[1] == 0 || position[0] + position[1] == 2 || position[0] + position[1] == 4) && position[0] != 1){
                int a = Math.abs(position[0] - 2);
                int b = Math.abs(position[1] - 2);

                if(board[a][b] == '*'){
                    myRow = a;
                    myColumn = b;
                }

            }else if(board[1][1] == '*'){
                myRow = 1;
                myColumn = 1;
            
            }else{
                myRow = (int)Math.floor(Math.random()*(2-0+1)+0);
                myColumn = (int)Math.floor(Math.random()*(2-0+1)+0);

                while(board[myRow][myColumn] != '*'){
                    myRow = (int)Math.floor(Math.random()*(2-0+1)+0);
                    myColumn = (int)Math.floor(Math.random()*(2-0+1)+0);
                }
            }
        }

        if(myRow == 5 && myColumn == 5){
            myRow = (int)Math.floor(Math.random()*(2-0+1)+0);
            myColumn = (int)Math.floor(Math.random()*(2-0+1)+0);

                while(board[myRow][myColumn] != '*'){
                    myRow = (int)Math.floor(Math.random()*(2-0+1)+0);
                    myColumn = (int)Math.floor(Math.random()*(2-0+1)+0);
                }
        }

        board[myRow][myColumn] = 'O';
    }

}
