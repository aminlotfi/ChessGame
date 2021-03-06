package board;

import com.google.common.collect.ImmutableMap;
import pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        if (piece != null) {
            return new OccupiedTile(tileCoordinate, piece);
        } else {
            return EMPTY_TILE.get(tileCoordinate);
        }
    }

    private Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPeace();

    public static final class EmptyTile extends Tile {

        public EmptyTile(int tileCoordinate) {
            super(tileCoordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPeace() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {

        Piece pieceOnTile;

        public OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPeace() {
            return this.pieceOnTile;
        }
    }
}
