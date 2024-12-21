package utilites;

import java.io.Serializable;
import java.util.Objects;

public class Mark implements Serializable {
    private static final long serialVersionUID = 5063732366564975348L;
    private double att1;
    private double att2;
    private double finalExamScore;
    private double totalScore;

    public Mark() {
    }

    public Mark(double att1, double att2, double finalExamScore) {
        setAtt1(att1);
        setAtt2(att2);
        setFinalExamScore(finalExamScore);
    }

    public double getTotalScore() {
        totalScore = att1 + att2 + finalExamScore;
        return totalScore;
    }

    public String getLetterGrade() {
        double score = getTotalScore();
        return calculateLetterGrade(score);
    }

    public double getAtt1() {
        return att1;
    }

    public void setAtt1(double att1) {
        this.att1 = validateScore(att1);
    }

    public double getAtt2() {
        return att2;
    }

    public void setAtt2(double att2) {
        this.att2 = validateScore(att2);
    }

    public double getFinalExamScore() {
        return finalExamScore;
    }

    public void setFinalExamScore(double finalExamScore) {
        this.finalExamScore = validateScore(finalExamScore);
    }

    @Override
    public String toString() {
        return "Mark { First Attestation: " + att1 +
               ", Second Attestation: " + att2 +
               ", Final Exam: " + finalExamScore +
               ", Total Score: " + getTotalScore() +
               ", Letter Grade: " + getLetterGrade() + " }";
    }

    @Override
    public int hashCode() {
        return Objects.hash(att1, att2, finalExamScore);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mark other = (Mark) obj;
        return Double.compare(other.att1, att1) == 0 &&
               Double.compare(other.att2, att2) == 0 &&
               Double.compare(other.finalExamScore, finalExamScore) == 0;
    }

    private String calculateLetterGrade(double score) {
        if (score < 50) return "F";
        else if (score < 55) return "D";
        else if (score < 60) return "D+";
        else if (score < 65) return "C-";
        else if (score < 70) return "C";
        else if (score < 75) return "C+";
        else if (score < 80) return "B-";
        else if (score < 85) return "B";
        else if (score < 90) return "B+";
        else if (score < 95) return "A-";
        else return "A";
    }

    private double validateScore(double score) {
        return (score < 0) ? 0 : score;
    }
}

