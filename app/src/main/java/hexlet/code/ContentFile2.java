package hexlet.code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentFile2 {
    private String setting1;
    private int setting2;
    private String setting3;
    private String key2;
    private Integer[] numbers1;
    private Integer[] numbers2;
    private Object id;
    private String[] defaultValue;
    private boolean checked;
    private Integer[] numbers4;
    private String[] chars1;
    private boolean chars2;
    private NestedClass obj1;
}


