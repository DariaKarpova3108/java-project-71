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
public class ContentFile1 {
    private String setting1;
    private int setting2;
    private boolean setting3;
    private String key1;
    private Integer[] numbers1;
    private Integer[] numbers2;
    private int id;
    private Object defaultValue;
    private boolean checked;
    private Integer[] numbers3;
    private String[] chars1;
    private String[] chars2;

}
