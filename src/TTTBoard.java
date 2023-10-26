import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TTTBoard extends JFrame {

    private TicTacToeTile[][] tiles;
    private String player;
    private int COL = 3;
    private int ROW = 3;
    JPanel boardFrame;

    JButton quitBtn;
    JPanel mainPnl;

    public TTTBoard(){

            boardFrame = new JPanel();
            boardFrame.setLayout(new GridBagLayout());
            GridBagConstraints cons = new GridBagConstraints();
            quitBtn = new JButton("Quit");
            mainPnl = new JPanel();

            tiles = new TicTacToeTile[3][3];
            tiles[0][0] = (new TicTacToeTile(0,0));
            tiles[0][1] = (new TicTacToeTile(0,1));
            tiles[0][2] = (new TicTacToeTile(0,2));

            tiles[1][0] = (new TicTacToeTile(1,0));
            tiles[1][1] = (new TicTacToeTile(1,1));
            tiles[1][2] = (new TicTacToeTile(1,2));

            tiles[2][0] = (new TicTacToeTile(2,0));
            tiles[2][1] = (new TicTacToeTile(2,1));
            tiles[2][2] = (new TicTacToeTile(2,2));


            cons.ipadx = 150;
            cons.ipady = 150;

            cons.gridx= 0;
            cons.gridy = 0;
            boardFrame.add(tiles[0][0],cons);
            cons.gridx= 1;

            boardFrame.add(tiles[0][1],cons);
            cons.gridx = 2;
            boardFrame.add(tiles[0][2],cons);

            cons.gridx=0;
            cons.gridy = 1;
            boardFrame.add(tiles[1][0],cons);
            cons.gridx=1;
            boardFrame.add(tiles[1][1],cons);
            cons.gridx=2;
            boardFrame.add(tiles[1][2],cons);

            cons.gridx=0;
            cons.gridy = 2;
            boardFrame.add(tiles[2][0],cons);
            cons.gridx=1;
            boardFrame.add(tiles[2][1],cons);
            cons.gridx=2;
            boardFrame.add(tiles[2][2],cons);
            cons.gridx=1;
            cons.gridy=3;
            cons.ipadx=100;
            cons.ipady=100;
            boardFrame.add(quitBtn,cons);

            mainPnl.add(boardFrame);
            boardFrame.setVisible(true);
            this.add(mainPnl);

            player = "X";
            addListeners();

    }

    public void swapPlayer(){
            if(player.equals("X")){
                    player = "O";
            }
            else {
                    player= "X";
            }
    }

    public void showWinDialog(){
            int choice = JOptionPane.showConfirmDialog(null,player + " Won! \n Do you want to play again?");
            if (choice == 0){
                    tiles[0][0].setState("empty");
                    tiles[0][1].setState("empty");
                    tiles[0][2].setState("empty");

                    tiles[1][0].setState("empty");
                    tiles[1][1].setState("empty");
                    tiles[1][2].setState("empty");

                    tiles[2][0].setState("empty");
                    tiles[2][1].setState("empty");
                    tiles[2][2].setState("empty");

            }
            else{System.exit(0);}
    }

    public void showTieDialog(){
            int choice = JOptionPane.showConfirmDialog(null,"It's a Tie! \n Do you want to play again?");
            if (choice == 0){
                    tiles[0][0].setState("empty");
                    tiles[0][1].setState("empty");
                    tiles[0][2].setState("empty");

                    tiles[1][0].setState("empty");
                    tiles[1][1].setState("empty");
                    tiles[1][2].setState("empty");

                    tiles[2][0].setState("empty");
                    tiles[2][1].setState("empty");
                    tiles[2][2].setState("empty");

            }
            else{System.exit(0);}
    }

    public boolean checkWin(){
        if (checkVert() || checkDiag() || checkHoriz()){
                return true;
        }
        return false;

    }
    public boolean checkTie(){
            boolean xFlag = false;
            boolean oFlag = false;
            // Check all 8 win vectors for an X and O so
            // no win is possible
            // Check for row ties
            for(int row=0; row < ROW; row++)
            {
                    if(tiles[row][0].getState().equals("X") ||
                            tiles[row][1].getState().equals("X") ||
                            tiles[row][2].getState().equals("X"))
                    {
                            xFlag = true; // there is an X in this row
                    }
                    if(tiles[row][0].getState().equals("O") ||
                            tiles[row][1].getState().equals("O") ||
                            tiles[row][2].getState().equals("O"))
                    {
                            oFlag = true; // there is an O in this row
                    }

                    if(! (xFlag && oFlag) )
                    {
                            return false; // No tie can still have a row win
                    }

                    xFlag = oFlag = false;

            }
            // Now scan the columns
            for(int col=0; col < COL; col++)
            {
                    if(tiles[0][col].getState().equals("X") ||
                            tiles[1][col].getState().equals("X") ||
                            tiles[2][col].getState().equals("X"))
                    {
                            xFlag = true; // there is an X in this col
                    }
                    if(tiles[0][col].getState().equals("O") ||
                            tiles[1][col].getState().equals("O") ||
                            tiles[2][col].getState().equals("O"))
                    {
                            oFlag = true; // there is an O in this col
                    }

                    if(! (xFlag && oFlag) )
                    {
                            return false; // No tie can still have a col win
                    }
            }
            // Now check for the diagonals
            xFlag = oFlag = false;

            if(tiles[0][0].getState().equals("X") ||
                    tiles[1][1].getState().equals("X") ||
                    tiles[2][2].getState().equals("X") )
            {
                    xFlag = true;
            }
            if(tiles[0][0].getState().equals("O") ||
                    tiles[1][1].getState().equals("O") ||
                    tiles[2][2].getState().equals("O") )
            {
                    oFlag = true;
            }
            if(! (xFlag && oFlag) )
            {
                    return false; // No tie can still have a diag win
            }
            xFlag = oFlag = false;

            if(tiles[0][2].getState().equals("X") ||
                    tiles[1][1].getState().equals("X") ||
                    tiles[2][0].getState().equals("X") )
            {
                    xFlag =  true;
            }
            if(tiles[0][2].getState().equals("O") ||
                    tiles[1][1].getState().equals("O") ||
                    tiles[2][0].getState().equals("O") )
            {
                    oFlag =  true;
            }
            if(! (xFlag && oFlag) )
            {
                    return false; // No tie can still have a diag win
            }

            // Checked every vector so I know I have a tie
            return true;
    }


    public boolean checkHoriz(){
            for(int row=0; row < ROW; row++)
            {
                    if(tiles[row][0].getState().equals(player) &&
                            tiles[row][1].getState().equals(player) &&
                            tiles[row][2].getState().equals(player))
                    {
                            return true;
                    }
            }
            return false; // no row win
    }
    public boolean checkVert(){
            for(int col=0; col < COL; col++)
            {
                    if(tiles[0][col].getState().equals(player) &&
                            tiles[1][col].getState().equals(player) &&
                            tiles[2][col].getState().equals(player))
                    {
                            return true;
                    }
            }
            return false; // no col win
    }
    public boolean checkDiag(){
            if(tiles[0][0].getState().equals(player) &&
                    tiles[1][1].getState().equals(player) &&
                    tiles[2][2].getState().equals(player) )
            {
                    return true;
            }
            if(tiles[0][2].getState().equals(player) &&
                    tiles[1][1].getState().equals(player) &&
                    tiles[2][0].getState().equals(player) )
            {
                    return true;
            }
            return false;
    }



        private void addListeners(){

                tiles[0][0].addActionListener(new tileListener(0,0));
                tiles[0][1].addActionListener(new tileListener(0,1));
                tiles[0][2].addActionListener(new tileListener(0,2));

                tiles[1][0].addActionListener(new tileListener(1,0));
                tiles[1][1].addActionListener(new tileListener(1,1));
                tiles[1][2].addActionListener(new tileListener(1,2));

                tiles[2][0].addActionListener(new tileListener(2,0));
                tiles[2][1].addActionListener(new tileListener(2,1));
                tiles[2][2].addActionListener(new tileListener(2,2));

                quitBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int choice =JOptionPane.showConfirmDialog(null,"Do you want to quit?");
                                if (choice == 0){
                                        System.exit(0);
                                }
                        }
                });

        }

        class tileListener implements ActionListener{
                int row;
                int col;
                public tileListener(int Row, int Col){
                        row = Row;
                        col = Col;
                }

                public void actionPerformed(ActionEvent e) {

                        if (e.getSource() instanceof TicTacToeTile) {
                                if (((TicTacToeTile) e.getSource()).getState().equals("empty")) {
                                        ((TicTacToeTile) e.getSource()).setState(player);
                                        if(checkWin()){
                                                showWinDialog();
                                        }
                                        if(checkTie()){
                                                showTieDialog();
                                        }
                                        swapPlayer();
                                }

                        }
                }

        }

}
