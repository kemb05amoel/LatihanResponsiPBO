/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import interfaces.ApplicantAction;
/**
 *
 * @author USER
 */
public class InternApplicant extends Participant implements ApplicantAction {

    private double writingScore;
    private double codingScore;
    private double interviewScore;

    public InternApplicant() {
    }

    public InternApplicant(int id, String name, String division,
            double writingScore,
            double codingScore,
            double interviewScore) {

        super(id, name, division);
        this.writingScore = writingScore;
        this.codingScore = codingScore;
        this.interviewScore = interviewScore;
    }

    public double getWritingScore() {
        return writingScore;
    }

    public void setWritingScore(double writingScore) {
        this.writingScore = writingScore;
    }

    public double getCodingScore() {
        return codingScore;
    }

    public void setCodingScore(double codingScore) {
        this.codingScore = codingScore;
    }

    public double getInterviewScore() {
        return interviewScore;
    }

    public void setInterviewScore(double interviewScore) {
        this.interviewScore = interviewScore;
    }

    @Override
    public double calculateFinalScore() {

        double average = (writingScore + codingScore + interviewScore) / 3;

        return average;
    }

    @Override
    public String getStatus() {
        return calculateFinalScore() >= 85
                ? "DITERIMA"
                : "TIDAK DITERIMA";
    }
}
