package pairmatching;

import java.util.ArrayList;
import java.util.List;

public class PairMemory {

    private List<PairInfo> pairInfos;

    public PairMemory() {
        this.pairInfos = new ArrayList<>();
    }

    public void addMemory(Pair pair) {
        List<PairInfo> pairInfos = pair.convertPairInfos();
        this.pairInfos.addAll(pairInfos);
    }

    public boolean validatePair(List<Pair> pairs, Level level) {
        for (Pair pair : pairs) {
            if (!validateCrew(pair, level)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateCrew(Pair pair, Level level) {
        for (PairInfo pairInfo : pairInfos) {
            if (!pairInfo.validatePair(pair, level)) {
                return false;
            }
        }
        return true;
    }

    public void removeAll() {
        pairInfos = new ArrayList<>();
    }
}
