public class Step {

    Position pos;
    int amount = 0;

    public Step(Position position){

        this.pos = position;
        this.amount++;
    }

    public boolean equals(Position b){
        return (this.pos.getX() == b.getX() && this.pos.getY() == b.getY() );
    }
    public void addStep(){
        this.amount++;
    }
    public boolean removeStep(){
        if (amount != 0)
        {
            this.amount--;
        }
        return amount == 0; //returns true if empty to remove this position

    }

}
