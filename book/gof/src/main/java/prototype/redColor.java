package prototype;

// Concrete Prototype
class redColor extends Color {
    public redColor() {
        this.colorName = "red";
    }

    @Override
    void addColor() {
        System.out.println("Blue color added");
    }

}