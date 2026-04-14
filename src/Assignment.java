public class Assignment {
    // Purpose: To hold data for a single piece of work
    private String name;
    private double score;
    private double maxScore;

    public Assignment(String name, double score, double maxScore) {
        this.name = name;
        this.score = score;
        this.maxScore = (maxScore == 0) ? 1 : maxScore;
    }

    // Get Methods
    public double getPercentage() {
        return (score / maxScore) * 100;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public double getMaxScore() {
        return maxScore;
    }
}

