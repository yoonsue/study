package builder;

class MyBuilder {
    private String name;
    private int age;

    public MyBuilder(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static MyBuilder.Builder builder() {
        return new MyBuilder.Builder();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Concrete Builder
    public static class Builder {
        private String name;
        private int age;

        Builder() {
        }

        // Part
        public MyBuilder.Builder name(String name) {
            this.name = name;
            return this;
        }

        // Part
        public MyBuilder.Builder age(int age) {
            this.age = age;
            return this;
        }

        public MyBuilder build() {
            return new MyBuilder(this.name, this.age);
        }

        public String toString() {
            return "MyBuilder.Builder(name=" + this.name + ", age=" + this.age + ")";
        }

    }
}
