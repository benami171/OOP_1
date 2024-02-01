import java.lang.reflect.Array;
import java.util.ArrayList;

public class Back {

    private int orgX, orgY, currX, currY;
    private ArrayList<Pawn> eaten = new ArrayList<Pawn>();
    private ArrayList<Position> eatenPos = new ArrayList<Position>();

    public Back(){}
    public Back(int orgX, int orgY, int currX, int currY){
        this.orgX = orgX;
        this.orgY = orgY;
        this.currX = currX;
        this.currY = currY;
    }

    public int getCurrY() {return currY;}
    public int getCurrX() {return currX;}
    public int getOrgX() { return this.orgX; }
    public int getOrgY() { return orgY; }

    //set and get eaten positions
    public void setEatenPos(ArrayList<Position> eatenPositions) { this.eatenPos = eatenPositions ;};
    public ArrayList<Pawn> getEaten() {return eaten;}
    public ArrayList<Position> getEatenPositions() { return eatenPos;};
    public void setEaten(ArrayList<Pawn> eat){
        this.eaten = eat;
    }

}