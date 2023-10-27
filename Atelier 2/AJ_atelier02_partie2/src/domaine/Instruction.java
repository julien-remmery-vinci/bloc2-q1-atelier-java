package domaine;

import java.time.Duration;

public class Instruction {
    private String description;
    private Duration dureeEnminutes;

    public Instruction(String description, int dureeEnminutes) {
        this.description = description;
        this.dureeEnminutes = Duration.ofMinutes(dureeEnminutes);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getDureeEnminutes() {
        return dureeEnminutes;
    }

    public void setDureeEnminutes(Duration dureeEnminutes) {
        this.dureeEnminutes = dureeEnminutes;
    }

    @Override
    public String toString() {
        return "("+dureeEnminutes.toHoursPart()+":"+dureeEnminutes.toMinutesPart()+") "+description;
    }
}
