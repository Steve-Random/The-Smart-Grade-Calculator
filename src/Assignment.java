public class Assignment {
    // Purpose: To hold data for a single piece of work
    private String name;
    public double score;
    public double maxScore;

    public Assignment( String name, double score, double maxScore){
        this.name = name;
        this.score = score;
        this.maxScore = maxScore;
    }

    // Get Methods

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

