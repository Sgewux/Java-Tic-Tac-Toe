package gamemethods;

public final class Checks {

    private static int checkRows(char[][] board){
        //Will return 1 if player wins, 2 if pc wins 0 if no one won
        int playerCounter;
        int pcCounter;

        for(char[] row : board){
            playerCounter = 0;
            pcCounter = 0;
            for(char item : row){
                switch(item){
                    case 'X':
                        playerCounter += 1;
                        break;
                    case 'O':
                        pcCounter += 1;
                        break;
                    
                }
            }

            if(playerCounter == 3){
                return 1;
            
            }else if(pcCounter == 3){
                return 2;
            }
            

        }

        return 0;
    }

    private static int checkColumns(char[][] board){
        
        int playerCounter;
        int pcCounter;

        for(int i = 0; i < 3; i++){
            playerCounter = 0;
            pcCounter = 0;
            for(int j = 0; j < 3; j++){

                switch(board[j][i]){
                    case 'X':
                        playerCounter += 1;
                        break;
                    case 'O':
                        pcCounter += 1;
                        break;
                }
            }

            if(playerCounter == 3){
                return 1;
            
            }else if(pcCounter == 3){
                return 2;
            }
        }

        return 0;
    }

    private static int checkFirstDiagonal(char[][] board){
        int playerCounter = 0;
        int pcCounter = 0;

        for(int i = 0; i < 3; i++){
            switch(board[i][i]){
                case 'X':
                    playerCounter += 1;
                    break;
                case 'O':
                    pcCounter += 1;
                    break;
            }
        }

        if(playerCounter == 3){
            return 1;
        
        }else if(pcCounter == 3){
            return 2;
        }else{
            return 0;
        }
    }

    private static int checkSecondDiagonal(char[][] board){
        int playerCounter = 0;
        int pcCounter = 0;
        for(int i = 0; i < 3; i++){
            switch(board[i][Math.abs(i - 2)]){
                case 'X':
                    playerCounter += 1;
                    break;
                case 'O':
                    pcCounter += 1;
                    break;
            }
            
        }

        if(playerCounter == 3){
            return 1;
        
        }else if(pcCounter == 3){
            return 2;
        }else{
            return 0;
        }
    }

    public static int isThereAWinner(char[][] board){
        int rows = checkRows(board);
        int columns = checkColumns(board);
        int firstDiagonal = checkFirstDiagonal(board);
        int secondDiagonal = checkSecondDiagonal(board);

        if(columns != 0 || rows != 0 || firstDiagonal != 0 || secondDiagonal != 0){
            if(rows != 0){
                return rows;
            
            }else if(columns != 0){
                return columns;
            
            }else if(firstDiagonal != 0){
                return firstDiagonal;
            
            }else if(secondDiagonal != 0){
                return secondDiagonal;
            }
        }
        
        return 0;
    }

    public static boolean isFullBoard(char[][] board){
        int blankCounter = 0;
        for(char[] row : board){
            for(char column : row){
                if(column == '*'){
                    blankCounter += 1;
                }
            }
        }

        if(blankCounter > 0){
            return false;
        }

        return true;
    }

}