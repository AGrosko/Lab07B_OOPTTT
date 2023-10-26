import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wulft
 */
public class TicTacToeTile extends JButton
{
    private int row;
    private int col;
    private String state;

    public TicTacToeTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        state = "empty";
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public String getState(){return state;}

    public void setState(String toSet){
        state = toSet;
        if (state.equals("empty")){ this.setText("");}
        else{
        this.setText(state);}
    }

}