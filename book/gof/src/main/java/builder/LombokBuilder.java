package builder;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
class LombokBuilder {
    private String name;
    private int age;
}
