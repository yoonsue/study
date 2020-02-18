package builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BuilderTest {

    // Director
    @Test
    public void builder() {
        String name = "yoonsue";
        int age = 25;

        LombokBuilder lombok = LombokBuilder.builder()
                .name(name)
                .age(age)
                .build();

        // Product
        MyBuilder mine = MyBuilder.builder()
                .name(name)
                .age(age)
                .build();

        Assertions.assertEquals(lombok.getName(), mine.getName());
        Assertions.assertEquals(lombok.getAge(), mine.getAge());
    }

}