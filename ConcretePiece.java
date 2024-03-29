import java.util.ArrayList;
import java.util.HashSet;

public abstract class ConcretePiece implements Piece {

    //  private Piece[][] board;
    private Player owner;
    private String type;
    private int number;
    private ArrayList<Position> moves;
    private int distance;
    private int eatAmount = 0;

    public ConcretePiece(Player player, int index){
        this.owner = player;
        this.number = index;
        this.distance = 0;
        moves = new ArrayList<>();
    }

    @Override
    public Player getOwner() {
        return owner;
    }
    public void setDistance(Position a, Position b) {
        distance += a.distance(b);
    }

    public void removeLastMove() {

        if(moves.size() >= 2)
            distance -= moves.get(moves.size()-1).distance(moves.get(moves.size()-2)) ;

        if(moves.size() > 1)
            moves.remove(moves.size() -1);
    }


    @Override
    public String getType() {
        return type;
    }

    public ArrayList<Position> getMoves(){
        return moves;
    }

    public void addMove(Position a){
        if (!moves.isEmpty()) {
            this.distance += moves.get(getMoves().size()-1).distance(a);
        }
        moves.add(a);
    }


    @Override
    public String toString() {
        return "ConcretePiece{" +
                "owner=" + owner +
                ", type='" + type + '\'' +
                ", number=" + number +
                '}';
    }


    public int getEatAmount() {
        return eatAmount;
    }
    public void addEat(){ eatAmount++; }
    public void puke() {eatAmount--; }

    public abstract String getName();
    public abstract int getEatnums();
    public int getNumber() {
        return number;
    }

    public ArrayList<Position> getDistinctMoves() {
        moves.remove(0);
        if(moves.isEmpty()){
            return null;
        }
        HashSet<Position> uniqueMovesSet = new HashSet<>(moves);
        return new ArrayList<>(uniqueMovesSet);
    }


    public int getDistance() {
        return distance;
    }
}