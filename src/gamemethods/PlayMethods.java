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

    private static boolean isCorner(Integer[] a){
        if((a[0] + a[1] == 0 || a[0] + a[1] == 2 || a[0] + a[1] == 4) && a[0] != 1) {
            return true;
        }else{
            return false;
        }
    }
    //All these methods from here to the end are for the pc's playing engine

    private static List<Integer[]> getPositions(char[][] board, char who){
        /**
        Given a board and a character to look for this method will return an ArrayList with Integer arrays inside it filled
        with the coordinates where are the given character 
        **/
        List<Integer[]> positions = new ArrayList<Integer[]>();

        for(int row = 0; row < 3; row++){
			for(int column = 0; column < 3; column++ ){
				if(board[row][column] == who){
					Integer[] newPos = {row, column};
					positions.add(newPos);
				}
			}
		}

        return positions;

    }


    private static int[] detectThreat(char[][] board){
        /**
          Using the board this method will find threats and give us the coordinates where whe have to put our O to dont let the user win
          if there are no threats the method will return an array filled with -1
        **/
        int[] threat = {-1, -1};
        List<Integer[]> positions = getPositions(board, 'X');

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

                        if(board[a][b] == '*' && threat[0] == -1 && threat[1] == -1){
                            threat[0] = a;
                            threat[1] = b;
                        }
                    
                    }else if(firstPos[1] == secondPos[1]){
                        int b = firstPos[1];
                        int a;

                        if(firstPos[0] + secondPos[0] == 1){
                            a = 2;
                        }else{
                            a = Math.abs(firstPos[0] - secondPos[0]) - 1;
                        }

                        if(board[a][b] == '*' && threat[0] == -1 && threat[1] == -1){
                            threat[0] = a;
                            threat[1] = b;
                        }

                    }else if(isCorner(secondPos) && isCorner(firstPos)){
                        

                        if(board[1][1] == '*' && (threat[0] == -1 && threat[1] == -1)){
                            threat[0] = 1;
                            threat[1] = 1;
                        }
                    }else if(isCorner(firstPos)){
                        int a = Math.abs(firstPos[0] - 2);
                        int b = Math.abs(firstPos[1] - 2);
            
                        if(board[a][b] == '*' && threat[0] == -1 && threat[1] == -1){
                            threat[0] = a;
                            threat[1] = b;
                        }
                    
                    
                    }else if(isCorner(secondPos)){
                        int a = Math.abs(secondPos[0] - 2);
                        int b = Math.abs(secondPos[1] - 2);
                 
                        if(board[a][b] == '*' && threat[0] == -1 && threat[1] == -1){
                            threat[0] = a;
                            threat[1] = b;
                        }
                    }
                    
                }
            }
        
            /** 
            Threats algorithm will work even if there is just one position because if the user plays a corner a smart move 
            could be play the opossite corner, also if the center is free we would like to move there because it will probably
            help us to find oportunities in a future. So these user moves are considered threats.
            **/

        }else if(positions.size() == 1){
            Integer[] position = positions.get(0);

            if(isCorner(position)){
                int a = Math.abs(position[0] - 2);
                int b = Math.abs(position[1] - 2);

                if(board[a][b] == '*'){
                    threat[0] = a;
                    threat[1] = b;
                }

            }else if(board[1][1] == '*'){
                threat[0] = 1;
                threat[1] = 1;
            
            }
        
        }

        return threat;
    }


    private static int[] detectOpportunity(char[][] board){
        /**
          Using the board this method will find opportunities to win and return an array with the coordinates where we have
          to put our O to win, if there are no opportunities the method will return an array filled with -1 
        **/
        int[] opportunity = {-1, -1};
        List<Integer[]> positions = getPositions(board, 'O');

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

                        if(board[a][b] == '*' && opportunity[0] == -1 && opportunity[1] == -1){
                            opportunity[0] = a;
                            opportunity[1] = b;
                            
                        }
                        
                    
                    }else if(firstPos[1] == secondPos[1]){
                        int b = firstPos[1];
                        int a;

                        if(firstPos[0] + secondPos[0] == 1){
                            a = 2;
                        }else{
                            a = Math.abs(firstPos[0] - secondPos[0]) - 1;
                        }

                        if(board[a][b] == '*' && opportunity[0] == -1 && opportunity[1] == -1){
                            opportunity[0] = a;
                            opportunity[1] = b;
                           
                        }

                        

                    }else if(isCorner(secondPos) && isCorner(firstPos)){
                        

                        if(board[1][1] == '*' && (opportunity[0] == -1 && opportunity[1] == -1)){
                            opportunity[0] = 1;
                            opportunity[1] = 1;
                        }
                    }
                    else if(isCorner(firstPos)){
                        int a = Math.abs(firstPos[0] - 2);
                        int b = Math.abs(firstPos[1] - 2);
        
                        if((board[a][b] == '*' && opportunity[0] == -1 && opportunity[1] == -1) && board[1][1] == 'O'){
                            opportunity[0] = a;
                            opportunity[1] = b;
                            
                        }

                        
                    
                    }else if(isCorner(secondPos)){
                        int a = Math.abs(secondPos[0] - 2);
                        int b = Math.abs(secondPos[1] - 2);
        
                        if((board[a][b] == '*' && opportunity[0] == -1 && opportunity[1] == -1) && board[1][1] == 'O'){
                            opportunity[0] = a;
                            opportunity[1] = b;
                           
                        }
                    }
                }
            }
        }

        /**
        opportunity algorithm will only work if there are two or more pc positions
        **/

        return opportunity;
    }


    public static void selectPosition(char[][] board){
        //This func uses the board state to decide how to play

        int[] threat = detectThreat(board);
        int[] opportunity = detectOpportunity(board);

        if(opportunity[0] != -1 && opportunity[1] != -1){ //If we have an opportunity to win
            board[opportunity[0]][opportunity[1]] = 'O';
           
        
        }else if(threat[0] != -1 && threat[1] != -1){ //If we dont have an opportunity to win but we do have a threat
            board[threat[0]][threat[1]] = 'O';
           
        }else{ //If we dont have an opportunity to win neither a threat
            int myRow;
            int myColumn;
            myRow = (int)Math.floor(Math.random()*(2-0+1)+0);
            myColumn = (int)Math.floor(Math.random()*(2-0+1)+0);

            while(board[myRow][myColumn] != '*'){
                myRow = (int)Math.floor(Math.random()*(2-0+1)+0);
                myColumn = (int)Math.floor(Math.random()*(2-0+1)+0);
            }

            board[myRow][myColumn] = 'O';
        }

    }

}
