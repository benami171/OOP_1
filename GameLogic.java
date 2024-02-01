import java.lang.reflect.Array;
import java.util.*;

public class GameLogic implements PlayableLogic {
    private ConcretePiece[][] board;
    private int player2Counter = 24;
    private final int BOARD_SIZE = 11;
    private ConcretePlayer player1;
    private ConcretePlayer player2;
    private ConcretePlayer currPlayer;
    private boolean isPlayar1turn;
    private ConcretePiece[] ConcretePieceArr;
    // saves curr position and original position, used in undo.
    private Stack<Back> backs = new Stack<>();


    public GameLogic() {
        isPlayar1turn = false;
        board = new ConcretePiece[BOARD_SIZE][BOARD_SIZE];
        ConcretePieceArr = new ConcretePiece[37];
        player1 = new ConcretePlayer(true);
        player2 = new ConcretePlayer(false);
        player1Pieces();
        player2Pieces();
    }

// create the pieces for player 1
    private void player1Pieces() {
        for (int i = 0; i < 13; i++) {
            if (i == 6) {
                ConcretePieceArr[i] = new King(player1, 7);
                i++;
                //  continue;
            }
            ConcretePieceArr[i] = new Pawn(player1, i + 1);
        }
        board[3][5] = ConcretePieceArr[0];
        ConcretePieceArr[0].addMove(new Position(5, 3));
        board[4][4] = ConcretePieceArr[1];
        ConcretePieceArr[1].addMove(new Position(4, 4));
        board[4][5] = ConcretePieceArr[2];
        ConcretePieceArr[2].addMove(new Position(5, 4));
        board[4][6] = ConcretePieceArr[3];
        ConcretePieceArr[3].addMove(new Position(6, 4));
        board[5][3] = ConcretePieceArr[4];
        ConcretePieceArr[4].addMove(new Position(3, 5));
        board[5][4] = ConcretePieceArr[5];
        ConcretePieceArr[5].addMove(new Position(4, 5));
        board[5][5] = ConcretePieceArr[6];                               //king
        ConcretePieceArr[6].addMove(new Position(5, 5));        //king
        board[5][6] = ConcretePieceArr[7];
        ConcretePieceArr[7].addMove(new Position(6, 5));
        board[5][7] = ConcretePieceArr[8];
        ConcretePieceArr[8].addMove(new Position(7, 5));
        board[6][4] = ConcretePieceArr[9];
        ConcretePieceArr[9].addMove(new Position(4, 6));
        board[6][5] = ConcretePieceArr[10];
        ConcretePieceArr[10].addMove(new Position(5, 6));
        board[6][6] = ConcretePieceArr[11];
        ConcretePieceArr[11].addMove(new Position(6, 6));
        board[7][5] = ConcretePieceArr[12];
        ConcretePieceArr[12].addMove(new Position(5, 7));
    }
// create the pieces for player 2
    private void player2Pieces() {
        for (int i = 13; i < 37; i++) {
            ConcretePieceArr[i] = new Pawn(player2, i - 12);
        }
        board[0][3] = ConcretePieceArr[13];
        ConcretePieceArr[13].addMove(new Position(3, 0));
        board[0][4] = ConcretePieceArr[14];
        ConcretePieceArr[14].addMove(new Position(4, 0));
        board[0][5] = ConcretePieceArr[15];
        ConcretePieceArr[15].addMove(new Position(5, 0));
        board[0][6] = ConcretePieceArr[16];
        ConcretePieceArr[16].addMove(new Position(6, 0));
        board[0][7] = ConcretePieceArr[17];
        ConcretePieceArr[17].addMove(new Position(7, 0));
        board[1][5] = ConcretePieceArr[18];
        ConcretePieceArr[18].addMove(new Position(5, 1));
        board[3][0] = ConcretePieceArr[19];
        ConcretePieceArr[19].addMove(new Position(0, 3));
        board[3][10] = ConcretePieceArr[20];
        ConcretePieceArr[20].addMove(new Position(10, 3));
        board[4][0] = ConcretePieceArr[21];
        ConcretePieceArr[21].addMove(new Position(0, 4));
        board[4][10] = ConcretePieceArr[22];
        ConcretePieceArr[22].addMove(new Position(10, 4));
        board[5][0] = ConcretePieceArr[23];
        ConcretePieceArr[23].addMove(new Position(0, 5));
        board[5][1] = ConcretePieceArr[24];
        ConcretePieceArr[24].addMove(new Position(1, 5));
        board[5][9] = ConcretePieceArr[25];
        ConcretePieceArr[25].addMove(new Position(9, 5));
        board[5][10] = ConcretePieceArr[26];
        ConcretePieceArr[26].addMove(new Position(10, 5));
        board[6][0] = ConcretePieceArr[27];
        ConcretePieceArr[27].addMove(new Position(0, 6));
        board[6][10] = ConcretePieceArr[28];
        ConcretePieceArr[28].addMove(new Position(10, 6));
        board[7][0] = ConcretePieceArr[29];
        ConcretePieceArr[29].addMove(new Position(0, 7));
        board[7][10] = ConcretePieceArr[30];
        ConcretePieceArr[30].addMove(new Position(10, 7));
        board[9][5] = ConcretePieceArr[31];
        ConcretePieceArr[31].addMove(new Position(5, 9));
        board[10][3] = ConcretePieceArr[32];
        ConcretePieceArr[32].addMove(new Position(3, 10));
        board[10][4] = ConcretePieceArr[33];
        ConcretePieceArr[33].addMove(new Position(4, 10));
        board[10][5] = ConcretePieceArr[34];
        ConcretePieceArr[34].addMove(new Position(5, 10));
        board[10][6] = ConcretePieceArr[35];
        ConcretePieceArr[35].addMove(new Position(6, 10));
        board[10][7] = ConcretePieceArr[36];
        ConcretePieceArr[36].addMove(new Position(7, 10));
    }

    @Override
    public boolean move(Position a, Position b) {
        Position corner1 = new Position(0, 0);
        Position corner2 = new Position(0, 10);
        Position corner3 = new Position(10, 0);
        Position corner4 = new Position(10, 10);

        if (b.getY() != a.getY() && b.getX() != a.getX()) return false;
        Piece aPiece = getPieceAtPosition(a);
        Piece bPiece = getPieceAtPosition(b);
        if (aPiece == null || bPiece != null) return false;
        if (isPlayar1turn) {
            currPlayer = this.player1;
        } else {
            currPlayer = this.player2;
        }
        if (currPlayer != aPiece.getOwner()) return false;
        if (a.getY() != b.getY()) {
            for (int i = a.getY(); i < b.getY(); i++) {
                if (board[i + 1][a.getX()] != null) return false;
            }
            for (int i = a.getY(); i > b.getY(); i--) {
                if (board[i - 1][a.getX()] != null) return false;
            }

        }
        if (a.getX() != b.getX()) {
            for (int i = a.getX(); i < b.getX(); i++) {
                if (board[a.getY()][i + 1] != null) return false;
            }
            for (int i = a.getX(); i > b.getX(); i--) {
                if (board[a.getY()][i - 1] != null) return false;
            }
        }
        if (!Objects.equals(aPiece.getType(), "♔"))
            if (b.equals(corner1) || b.equals(corner2) || b.equals(corner3) || b.equals(corner4))
                return false;


        //moving
        board[b.getY()][b.getX()] = board[a.getY()][a.getX()];
        board[a.getY()][a.getX()] = null;


        //eating
        int y = b.getY();
        int x = b.getX();

        ArrayList<Pawn> tmpEaten = new ArrayList<Pawn>();
        ArrayList<Position> tmpPos = new ArrayList<Position>();

        Back back = new Back(a.getX(), a.getY(), b.getX(), b.getY());

        if (!Objects.equals(aPiece.getType(), "♔")) {
            if (isPlayar1turn) {
                if (y != 0 && board[y - 1][x] != null && board[y - 1][x].getOwner() == player2) { //checks if hes not on the sides and that he has an enemy next to him
                    if ((y - 2 < 0) || (board[y - 2][x] != null && board[y - 2][x].getOwner() == player1 && !Objects.equals(board[y - 2][x].getType(), "♔"))) {   //checks if he׳s pinning him to a wall or if he has an ally pinning him
                        tmpEaten.add(new Pawn(player2, board[y - 1][x].getNumber())); //add eaten to list
                        tmpPos.add(new Position(x, y - 1)); // adding dead position
                        board[y - 1][x] = null; //remove eaten
                        ConcretePieceArr[board[y][x].getNumber() - 1].addEat(); //add eat to killer
                        player2Counter--;
                    }
                }
                if (y != 10 && board[y + 1][x] != null && board[y + 1][x].getOwner() == player2) { //down
                    if ((y + 2 > 10) || (board[y + 2][x] != null && board[y + 2][x].getOwner() == player1 && !Objects.equals(board[y + 2][x].getType(), "♔"))) {
                        tmpEaten.add(new Pawn(player2, board[y + 1][x].getNumber()));
                        tmpPos.add(new Position(x, y + 1));
                        board[y + 1][x] = null;
                        ConcretePieceArr[board[y][x].getNumber() - 1].addEat();
                        player2Counter--;
                    }
                }
                if (x != 0 && board[y][x - 1] != null && board[y][x - 1].getOwner() == player2) { //left
                    if ((x - 2 < 0) || (board[y][x - 2] != null && board[y][x - 2].getOwner() == player1 && !Objects.equals(board[y][x - 2].getType(), "♔"))) {
                        tmpEaten.add(new Pawn(player2, board[y][x - 1].getNumber()));
                        tmpPos.add(new Position(x - 1, y));
                        board[y][x - 1] = null;
                        ConcretePieceArr[board[y][x].getNumber() - 1].addEat();
                        player2Counter--;
                    }
                }
                if (x != 10 && board[y][x + 1] != null && board[y][x + 1].getOwner() == player2) { //right
                    if ((x + 2 > 10) || (board[y][x + 2] != null && board[y][x + 2].getOwner() == player1 && !Objects.equals(board[y][x + 2].getType(), "♔"))) {
                        tmpEaten.add(new Pawn(player2, board[y][x + 1].getNumber()));
                        tmpPos.add(new Position(x + 1, y));
                        board[y][x + 1] = null;
                        ConcretePieceArr[board[y][x].getNumber() - 1].addEat();
                        player2Counter--;
                    }
                }

            } else {
                if (y != 0 && board[y - 1][x] != null && board[y - 1][x].getOwner() == player1 && !Objects.equals(board[y - 1][x].getType(), "♔")) { //up
                    if ((y - 2 < 0) || (board[y - 2][x] != null && board[y - 2][x].getOwner() == player2)) {
                        tmpEaten.add(new Pawn(player1, board[y - 1][x].getNumber()));
                        tmpPos.add(new Position(x, y - 1));
                        board[y - 1][x] = null;
                        ConcretePieceArr[board[y][x].getNumber() - 1].addEat();
                    }
                }
                if (y != 10 && board[y + 1][x] != null && board[y + 1][x].getOwner() == player1 && !Objects.equals(board[y + 1][x].getType(), "♔")) { //down
                    if ((y + 2 > 10) || (board[y + 2][x] != null && board[y + 2][x].getOwner() == player2)) {
                        tmpEaten.add(new Pawn(player1, board[y + 1][x].getNumber()));
                        tmpPos.add(new Position(x, y + 1));
                        board[y + 1][x] = null;
                        ConcretePieceArr[board[y][x].getNumber() - 1].addEat();
                    }
                }
                if (x != 0 && board[y][x - 1] != null && board[y][x - 1].getOwner() == player1 && !Objects.equals(board[y][x - 1].getType(), "♔")) { //left
                    if ((x - 2 < 0) || (board[y][x - 2] != null && board[y][x - 2].getOwner() == player2)) {
                        tmpEaten.add(new Pawn(player1, board[y][x - 1].getNumber()));
                        tmpPos.add(new Position(x - 1, y));
                        board[y][x - 1] = null;
                        ConcretePieceArr[board[y][x].getNumber() - 1].addEat();
                    }
                }
                if (x != 10 && board[y][x + 1] != null && board[y][x + 1].getOwner() == player1 && !Objects.equals(board[y][x + 1].getType(), "♔")) { //right
                    if ((x + 2 > 10) || (board[y][x + 2] != null && board[y][x + 2].getOwner() == player2)) {
                        tmpEaten.add(new Pawn(player1, board[y][x + 1].getNumber()));
                        tmpPos.add(new Position(x + 1, y));
                        board[y][x + 1] = null;
                        ConcretePieceArr[board[y][x].getNumber() - 1].addEat();
                    }
                }
            }
        }

        // helps us in undo
        back.setEaten(tmpEaten);
        back.setEatenPos(tmpPos);
        backs.add(back);
        isPlayar1turn = !isPlayar1turn;
        ((ConcretePiece) aPiece).addMove(b);

        if(isGameFinished()){
            if(!isPlayar1turn) {
                printFinishedGame(true);
            } else {
                printFinishedGame(false);
            }
        }
        return true;
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        return board[position.getY()][position.getX()];
    }

    @Override
    public Player getFirstPlayer() {
        return player1;
    }

    @Override
    public Player getSecondPlayer() {
        return player2;
    }

    @Override
    public boolean isGameFinished() {
        if (isDefendersWon() || isAttackersWon()) {
            return true;
        }
        return false;
    }

    public Position getKingPosition() {
        ArrayList<Position> kingMoves = ConcretePieceArr[6].getMoves();
        return kingMoves.get(kingMoves.size() - 1);
    }

    public boolean isDefendersWon() {
        // check if king is in one of the corners
        ConcretePiece corner1 = board[0][0];
        ConcretePiece corner2 = board[0][10];
        ConcretePiece corner3 = board[10][0];
        ConcretePiece corner4 = board[10][10];
        if (corner1 != null && Objects.equals(corner1.getType(), "♔")) {
            player1.addWin();
            return true;
        }
        if (corner2 != null && Objects.equals(corner2.getType(), "♔")) {
            player1.addWin();
            return true;
        }
        if (corner3 != null && Objects.equals(corner3.getType(), "♔")) {
            player1.addWin();
            return true;
        }
        if (corner4 != null && Objects.equals(corner4.getType(), "♔")) {
            player1.addWin();
            return true;
        }

        // check if all player2 pieces are eaten
        if (player2Counter == 0) {
            player1.addWin();
            return true;
        }
        return false;
    }

    public boolean isAttackersWon() {

        int temp = 4;
        int count = 0;
        int x = getKingPosition().getX();
        int y = getKingPosition().getY();
        if (x == 0 || y == 0 || x == 10 || y == 10)
            temp = 3;

        if (y != 0 && board[y - 1][x] != null) { //up
            if (board[y - 1][x].getOwner() == player2)
                count++;
        }
        if (y != 10 && board[y + 1][x] != null) { //down
            if (board[y + 1][x].getOwner() == player2)
                count++;
        }
        if (x != 0 && board[y][x - 1] != null) { //left
            if (board[y][x - 1].getOwner() == player2)
                count++;
        }
        if (x != 10 && board[y][x + 1] != null) { //right
            if (board[y][x + 1].getOwner() == player2)
                count++;
        }
        if (count == temp) {
            player2.addWin();
            return true;
        }

        return false;
    }


    @Override
    public boolean isSecondPlayerTurn() {
        return !isPlayar1turn;
    }

    @Override
    public void reset() {
        board = new ConcretePiece[BOARD_SIZE][BOARD_SIZE];
        player1Pieces();
        player2Pieces();
        isPlayar1turn = false;
        player1.resetWins();
        player2.resetWins();
    }

    //    ==================================== UNDO ======================================
    //    un
    @Override
    public void undoLastMove() {
        if (!backs.isEmpty()) {
            Back tmp = backs.pop();
            isPlayar1turn = !isPlayar1turn;

            ArrayList<Pawn> eatenPawns = tmp.getEaten();
            ArrayList<Position> eatenPos = tmp.getEatenPositions();

            int eatNum = eatenPawns.size();
            while (eatNum != 0) { //removing eating amount
                ConcretePieceArr[board[tmp.getCurrY()][tmp.getCurrX()].getNumber() - 1].puke();
                eatNum--;
            }
            board[tmp.getOrgY()][tmp.getOrgX()] = board[tmp.getCurrY()][tmp.getCurrX()]; //move back the piece we moved
            board[tmp.getCurrY()][tmp.getCurrX()] = null; //removing from last positions

            if(board[tmp.getOrgY()][tmp.getOrgX()].getOwner() == player1) {
                ConcretePieceArr[board[tmp.getOrgY()][tmp.getOrgX()].getNumber() - 1].removeLastMove(); //remove distance and last move
            }else{
                ConcretePieceArr[board[tmp.getOrgY()][tmp.getOrgX()].getNumber() - 1 + 13].removeLastMove(); //remove distance and last move
            }

            //restore eaten pieces
            while (!eatenPawns.isEmpty() && !eatenPos.isEmpty()) {
                if (eatenPawns.get(0).getOwner() == player1){
                    board[eatenPos.get(0).getY()][eatenPos.get(0).getX()] = ConcretePieceArr[eatenPawns.get(0).getNumber() - 1];
                }else{
                    board[eatenPos.get(0).getY()][eatenPos.get(0).getX()] = ConcretePieceArr[eatenPawns.get(0).getNumber() - 1 + 13];
                }
                ConcretePieceArr[eatenPawns.get(0).getNumber() - 1].addMove(new Position(eatenPos.get(0).getX(), eatenPos.get(0).getY()));
                eatenPos.remove(0);
                eatenPawns.remove(0);
            }
        }
    }

    @Override
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    //    ==================================== COMPARATORS ======================================
    public void printFinishedGame(boolean isDefendersWon) {
        ArrayList<ConcretePiece> win = new ArrayList<>();
        ArrayList<ConcretePiece> lose = new ArrayList<>();
    //    Iterate through ConcretePieceArr and add the first 12 pieces to 'win' and the rest to 'lose'
        for (int i = 0; i < ConcretePieceArr.length; i++) {
            if (i < 13) {
                win.add(ConcretePieceArr[i]);
            } else {
                lose.add(ConcretePieceArr[i]);
            }
        }
        printByMoves(lose, win);
        System.out.println("***************************************************************************");
        printByKills(!isDefendersWon);
        System.out.println("***************************************************************************");
        printByDistance(win, lose);
        System.out.println("***************************************************************************");
        printPositionByMoves();
        System.out.println("***************************************************************************");


    }

    private void printByMoves(ArrayList<ConcretePiece> lose, ArrayList<ConcretePiece> win) {
        win.sort((o1, o2) -> {
//            The sort will be by the number of sizes + if D1 and D3 do the same moves, than D1 will be first
            if (o1.getMoves().size() == o2.getMoves().size()) {
                if (o1.getNumber() < o2.getNumber()) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return o1.getMoves().size() - o2.getMoves().size();
        });
        lose.sort((o1, o2) -> {
            if (o1.getMoves().size() == o2.getMoves().size()) {
                if (o1.getNumber() < o2.getNumber()) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return o1.getMoves().size() - o2.getMoves().size();
        });
        for (ConcretePiece piece : lose) {
            if (piece.getMoves().size() <= 1)
                continue;
            System.out.println(piece.getName() + ": " + piece.getMoves());
        }
        for (ConcretePiece piece : win) {
            if (piece.getMoves().size() <= 1)
                continue;
            System.out.println(piece.getName() + ": " + piece.getMoves());
        }
    }

    private void printByKills(boolean isDefendersWon) {
        // add all pieces that killed to relevantPieces
        ArrayList<ConcretePiece> relevantPieces = new ArrayList<>();
        for (int i = 0; i < ConcretePieceArr.length; i++) {
            ConcretePiece piece = ConcretePieceArr[i];
            if (piece.getEatAmount() > 0) {
                relevantPieces.add(piece);
            }
        }

        Comparator<ConcretePiece> killCompare = ((o1, o2) -> {
            // Sort by highest kill count first then by lowest number
            if (o1.getEatAmount() > o2.getEatAmount()) {
                return-1;
            }else if (o1.getEatAmount() < o2.getEatAmount()) {
                return 1;
            } else if (o1.getEatAmount() == o2.getEatAmount()) {
                return 0;
            }
            else if(o1.getNumber() != o2.getNumber()){
                return o1.getNumber() - o2.getNumber();
            }
            // If counts are equal, sort by winner first
            if (isDefendersWon) {
                return o1.getOwner().isPlayerOne() ? -1 : 1;
            } else {
                return o1.getOwner().isPlayerOne() ? 1 : -1;
            }
        });

        relevantPieces.sort(killCompare);
        for (ConcretePiece piece : relevantPieces) {
            System.out.println(piece.getName() + ": " + piece.getEatAmount() + " kills");
        }
    }

    public void printByDistance(ArrayList<ConcretePiece> win, ArrayList<ConcretePiece> lose) {
        ArrayList<ConcretePiece> all = new ArrayList<>();
        all.addAll(win);
        all.addAll(lose);
        // by number of distance than by number in ascending order (d1 prior to d7 if they have the same distance)
        // if same distance from different teams, the winner will be first.
        Player winner = all.get(0).getOwner();
        all.sort((o1, o2) -> {
            if (o1.getDistance() == o2.getDistance()) {
                if (o1.getNumber() < o2.getNumber()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return o1.getDistance() - o2.getDistance();
        });
        all.sort((o1, o2) -> {
            if ((o1.getDistance() == o2.getDistance()) && (o1.getOwner() == winner && o2.getOwner() != winner)) {
                return 1;
            } else if ((o1.getDistance() == o2.getDistance()) && o1.getOwner() != winner && o2.getOwner() == winner) {
                return -1;
            }
            return 0;
        });
        for (int i = all.size() - 1; i > 0; i--) {
            ConcretePiece piece = all.get(i);
            if (piece.getDistance() <= 0)
                continue;
            System.out.println(piece.getName() + ": " + piece.getDistance() + " squares");
        }
    }


    //    This method analyzes the movement of ConcretePiece objects and prints the count of distinct pieces
    //    that have visited each unique position. Positions are sorted by the frequency of visits in descending order,
    //    with ties broken by the lowest X and Y values. The results are printed,
    //    ensuring each position is only considered once, even if multiple pieces have visited it.
    public void printPositionByMoves() {
        HashMap<Position, Integer> map = new HashMap<>();
        HashSet<String> printedPositions = new HashSet<>();
        for (ConcretePiece piece : ConcretePieceArr) {
            if (piece.getMoves().size() <= 1)
                continue;
            HashSet<Position> set = new HashSet<>(piece.getMoves());
            for (Position position : set) {
                for (Map.Entry<Position, Integer> entry : map.entrySet()) {
                    if (entry.getKey().equals(position)) {
                        entry.setValue(entry.getValue() + 1);
                    }
                }
                if (!map.containsKey(position)) {
                    map.put(position, 1);
                }
            }
        }
        List<Map.Entry<Position, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((entry1, entry2) -> {
            // Sort by highest count first
            int countComparison = Integer.compare(entry2.getValue(), entry1.getValue());
            if (countComparison != 0) {
                return countComparison;
            }
            // If counts are equal, sort by lowest X
            int xComparison = Integer.compare(entry1.getKey().getX(), entry2.getKey().getX());
            if (xComparison != 0) {
                return xComparison;
            }
            // If X values are equal, sort by lowest Y
            return Integer.compare(entry1.getKey().getY(), entry2.getKey().getY());
        });
        for (Map.Entry<Position, Integer> entry : sortedEntries) {
            String positionString = entry.getKey().getX() + "," + entry.getKey().getY();
            if (entry.getValue() > 1 && printedPositions.add(positionString)) {
                System.out.println(entry.getKey() + "" + entry.getValue() + " pieces");
            }
        }
    }
}

