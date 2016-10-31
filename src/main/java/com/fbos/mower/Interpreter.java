package com.fbos.mower;

import com.fbos.mower.enums.Movement;
import com.fbos.mower.enums.Orientation;
import com.fbos.mower.geom.*;

import java.util.*;

/**
 * Interpreter of literal instructions. <br/>
 *
 * Created by fb on 30/10/2016.
 */
public class Interpreter {

    private static final int
            AREA_BOUNDS_LINE_INDEX = 0,
            FIRST_POSITION_LINE_INDEX = 1,
            X_CHAR_INDEX = 0,
            Y_CHAR_INDEX = 1,
            ORIENTATION_CHAR_INDEX = 2;

    private static final String SPACE_REGEX = "\\s+";

    /**
     * Converts a list of literal instructions to a list of {@link Moveable}. <br/>
     *
     * @param lines literal instructions
     * @return list of {@link Moveable}
     */
    public List<Moveable> interpret(List<String> lines) {
        // preconditions
        if (null == lines || lines.isEmpty()) {
            return Collections.emptyList();
        }

        List<Moveable> moveables = new ArrayList<>(lines.size() / 2);

        final FilledArea area;
        String line = lines.get(AREA_BOUNDS_LINE_INDEX);
        try {
            area = buildArea(line);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Invalid instruction for area bounds, expected 2 digits: " + line, e);
        }

        ListIterator<String> iterator = lines.listIterator(FIRST_POSITION_LINE_INDEX);
        while (iterator.hasNext()) {
            Moveable moveable;
            List<Movement> movements;

            line = iterator.next();
            try {
                moveable = buildMoveable(line, area);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Invalid instruction for coordinates, expected 2 digits and an orientation: " + line, e);
            }

            line = iterator.next();
            try {
                movements = buildMovements(line);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Invalid instruction for movements, expected direction chars: " + line, e);
            }
            moveable.setMovements(movements);
            moveables.add(moveable);
        }
        return moveables;

    }

    /**
     * Build an {@link Area}.
     *
     * @param bounds that must be not null and match format "X Y", throws exception otherwise
     * @return an {@link Area}
     */
    protected FilledArea buildArea(String bounds) {
        String[] digits = bounds.split(SPACE_REGEX);
        final double x = Double.valueOf(digits[X_CHAR_INDEX]);
        final double y = Double.valueOf(digits[Y_CHAR_INDEX]);
        return new FilledArea(x, y);
    }

    /**
     * Build a {@link Moveable}
     *
     * @param coordinates that must be not null and match format "X Y O",
     *                    with D the {@link Orientation} char code, throws exception otherwise
     * @param area {@link Area} to move on
     * @return a {@link Moveable}
     */
    protected Moveable buildMoveable(final String coordinates, final FilledArea area) {
        String[] chars = coordinates.split(SPACE_REGEX);
        final double x = Double.valueOf(chars[X_CHAR_INDEX]);
        final double y = Double.valueOf(chars[Y_CHAR_INDEX]);
        final Orientation orientation = Orientation.getFromCode(chars[ORIENTATION_CHAR_INDEX].charAt(0));
        Position position = new Position(x, y);

        // build the only concrete moveable known: Mower.
        // other object could be easily specified by introduce an other letter in the instruction,
        // e.g. "M X Y O" for Mower
        Moveable moveable = new Mower(position, orientation);
        moveable.setArea(area);
        return moveable;
    }

    /**
     * Build a list of {@link Movement}
     * @param movements at format "DDDDDDDD", with D a {@link Movement} char code, can be empty
     * @return a list of {@link Movement}
     */
    protected List<Movement> buildMovements(String movements) {
        if (movements.isEmpty()) {
            return Collections.emptyList();
        }
        List<Movement> result = new ArrayList<>(movements.length());
        for (int i = 0; i < movements.length(); i++) {
            result.add(Movement.getFromCode(movements.charAt(i)));
        }
        return result;
    }
}
