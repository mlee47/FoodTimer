package projects.example.newfoodtimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


        List<String> noodle = new ArrayList<String>();
        noodle.add("라면\nRamen");
        noodle.add("스파게티면\nSpaghetti");
        noodle.add("우동\nUdon");
        noodle.add("소면\nPlain Noodle");
        noodle.add("당면\nGlass Noodle");
        noodle.add("칼국수면\nNoodle Soup");

        List<String> egg = new ArrayList<String>();
        egg.add("완숙\nHard Boiled");
        egg.add("반숙\nSoft Boiled");


        List<String> vegetable = new ArrayList<String>();
        vegetable.add("콩나물\nBean Sprouts");
        vegetable.add("숙주\nMung Bean Sprouts");
        vegetable.add("시금치\nSpinach");

        expandableListDetail.put("면 Noodles", noodle);
        expandableListDetail.put("댤걀 Egg", egg);
        expandableListDetail.put("야채 Vegetables", vegetable);
        return expandableListDetail;
    }
}