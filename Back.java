import java.lang.reflect.Array;
import java.util.ArrayList;

public class Back {

    private int eatNum, orgX, orgY, dist, currX, currY;
    private Pawn moved;

    ArrayList<Pawn> eaten = new ArrayList<Pawn>();

    ArrayList<Position> eatenPos = new ArrayList<Position>();

    ArrayList<Pawn> allies = new ArrayList<Pawn>();
    public Back(){}
    public Back(int eatNum, int orgX, int orgY, int currX, int currY, int dist, Pawn moved, ArrayList<Pawn> eaten){
        this.eatNum = eatNum;
        this.orgX = orgX;
        this.orgY = orgY;
        this.currX = currX;
        this.currY = currY;
        this.dist = dist;
        this.eaten = eaten;

    }
    public Back(int orgX, int orgY, int currX, int currY, int dist, Pawn moved){
        this.orgX = orgX;
        this.orgY = orgY;
        this.currX = currX;
        this.currY = currY;
        this.dist = dist;
    }
    public void setEaten(ArrayList<Pawn> eat){
        this.eaten = eat;
    }
    public void setEatNum(int x){
        this.eatNum = x;
    }


    public int getDist() {return dist;}

    public int getCurrY() {return currY;}

    public int getCurrX() {return currX;}

    public int getEatNum() {return this.eatNum; }
    public int getOrgX() { return this.orgX; }

    public int getOrgY() { return orgY; }

    public Pawn getMoved() {return moved;}

    public ArrayList<Pawn> getEaten() {return eaten;}


    //set and get eaten positions
    public void setEatenPos(ArrayList<Position> eatenPositions) { this.eatenPos = eatenPositions ;};

    public ArrayList<Position> getEatenPositions() { return eatenPos;};


    //get and set allies


    //set and get the positions of allies that helped with eating
    public void addAlly(Pawn ally) { this.allies.add(ally) ;};
    public ArrayList<Pawn> getAllies() { return allies ;};




}

