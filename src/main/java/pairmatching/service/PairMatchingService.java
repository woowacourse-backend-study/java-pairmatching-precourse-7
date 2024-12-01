package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.PairCrews;
import pairmatching.domain.vo.Crew;
import pairmatching.infrastructure.CustomException;
import pairmatching.infrastructure.ExceptionMessage;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.PairRepository;
import pairmatching.service.dto.PairMatchingRequest;

import java.util.ArrayList;
import java.util.List;

public class PairMatchingService {
    public List<PairCrews> getPairs(PairMatchingRequest request) {
        List<PairCrews> pairCrews = PairRepository.findPairCrews(request.course(), request.mission());
        if (pairCrews == null) {
            throw new CustomException(ExceptionMessage.NO_EXIST_MATCHING.getMessage());
        }
        return pairCrews;
    }

    public void reset() {
        PairRepository.reset();
    }

    public boolean isAlreadyExist(PairMatchingRequest request) {
        List<PairCrews> pairCrews = PairRepository.findPairCrews(request.course(), request.mission());
        return pairCrews != null;
    }

    public List<PairCrews> generate(PairMatchingRequest request) {
        List<Crew> crews = CrewRepository.findAllByCourse(request.course());
        validateCrewCount(crews);

        List<PairCrews> result = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            List<PairCrews> generatedCrews = generatePairs(crews);
            List<PairCrews> savedPairCrews = PairRepository.findPairCrewsByLevel(request.mission().getLevel());
            if (!isExistDuplicate(savedPairCrews, generatedCrews)) {
                result = generatedCrews;
                break;
            }
        }

        if (result.isEmpty()) {
            throw new CustomException(ExceptionMessage.FAILED_TO_MATCHING.getMessage());
        }
        PairRepository.save(request.course(), request.mission(), result);
        return result;
    }

    private boolean isExistDuplicate(List<PairCrews> saved, List<PairCrews> generated) {
        for (PairCrews pairCrews : generated) {
            if (saved.stream().anyMatch(p -> p.isDuplicate(pairCrews))) {
                return true;
            }
        }
        return false;
    }

    private List<PairCrews> generatePairs(List<Crew> crews) {
        validateCrewCount(crews);

        List<String> shuffledNames = Randoms.shuffle(crews.stream().map(Crew::getName).toList());
        if (crews.size() % 2 == 0) {
            return getEvenPairCrews(shuffledNames, crews);
        }
        return getOddPairCrews(shuffledNames, crews);
    }

    private List<PairCrews> getEvenPairCrews(List<String> shuffledNames, List<Crew> crews) {
        List<PairCrews> pairCrews = new ArrayList<>();
        for (int i = 0; i < shuffledNames.size() - 1; i += 2) {
            List<String> targetNames = shuffledNames.subList(i, i + 2);
            List<Crew> newPair = targetNames.stream()
                    .map(name -> {
                        return crews.stream().filter(crew -> crew.getName().equals(name))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("존재하지 않는 크루입니다."));
                    })
                    .toList();
            pairCrews.add(new PairCrews(newPair));
        }
        return pairCrews;
    }

    private List<PairCrews> getOddPairCrews(List<String> shuffledNames, List<Crew> crews) {
        List<PairCrews> pairCrews = new ArrayList<>();
        for (int i = 0; i < shuffledNames.size() - 2; i += 2) {
            int endIndex = i + 2;
            if (i == shuffledNames.size() - 3) {
                endIndex = shuffledNames.size();
            }
            List<String> targetNames = shuffledNames.subList(i, endIndex);
            List<Crew> newPair = targetNames.stream()
                    .map(name -> {
                        return crews.stream().filter(crew -> crew.getName().equals(name))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("존재하지 않는 크루입니다."));
                    })
                    .toList();
            pairCrews.add(new PairCrews(newPair));
        }
        return pairCrews;
    }

    private void validateCrewCount(List<Crew> crews) {
        if (crews.size() < 2) {
            throw new CustomException(ExceptionMessage.DISABLE_PAIR_MATCHING.getMessage());
        }
    }
}
