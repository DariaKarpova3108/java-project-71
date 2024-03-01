package formatters.stylish;

import hexlet.code.StatusValue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StylishFormatter {
    public static String formatterStylish(List<Map<String, StatusValue>> list) throws Exception {
        List<Map<String, String>> resultList = new ArrayList<>();

        for (var entry : list) {
            String key = entry.entrySet().iterator().next().getKey();
            StatusValue valueStatus = entry.entrySet().iterator().next().getValue();
            String status = valueStatus.getStatus();
            Object valueOld = valueStatus.getOldValue();
            Object valueNew = valueStatus.getNewValue();

            switch (status) {
                case ("withoutChanges") -> resultList.add(Map.of("  " + key, valueOld.toString()));
                case ("update") -> {
                    resultList.add(Map.of("- " + key, valueOld.toString()));
                    resultList.add(Map.of("+ " + key, valueNew.toString()));
                }
                case ("deleted") -> resultList.add(Map.of("- " + key, valueOld.toString()));
                case ("added") -> resultList.add(Map.of("+ " + key, valueNew.toString()));
            }
        }

        String result = resultList.stream()
                .sorted(Comparator.comparing(map -> map.entrySet().iterator().next().getKey().substring(2)))
                .map(map -> map.entrySet().iterator().next())
                .map(map -> "  " + map.getKey() + ": " + map.getValue())
                .collect(Collectors.joining("\n", "{\n", "\n}"));

        return result;
    }
}
