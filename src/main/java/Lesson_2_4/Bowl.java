package Lesson_2_4;

public class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = Math.max(0, food);
    }

    public void add(int grams) {
        if (grams > 0) {
            food += grams;
        }
    }

    public boolean take(int grams) {
        if (grams <= 0) return false;
        if (food >= grams) {
            food -= grams;
            return true;
        }
        return false;
    }

    public int getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Bowl{" + "food=" + food + "г}";
    }
}
