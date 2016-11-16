import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

/**
 * Created by zyongliu on 15/11/16.
 */
public class GameMap {
    private List<Place> places = new ArrayList<>();
    private Dice dice = () -> (int) ((random() * 6) + 1);


    public GameMap(List<Place> places) {
        this.places = places;
    }

    public GameMap() {
    }

    public int move(Player player) {
        int target = 0;
        int step = dice.roll();
        for (int i = 1; i < step + 1; i++) {
            Tool tool = getPlace((player.getPosition() + i) % getSize()).getTool();
            if (tool != null) {
                target = tool.work(player, i, getHospitalPosition());
            }
        }
        return target;
    }

    public int getSize() {
        return places.size();
    }

    public Place getPlace(int positon) {
        return places.get(positon % getSize());
    }

    public int getHospitalPosition() {
        for (int i = 0; i < getSize(); i++) {
            if (getPlace(i) instanceof Hospital) {
                return i;
            }
        }
        return 0;
    }
}
