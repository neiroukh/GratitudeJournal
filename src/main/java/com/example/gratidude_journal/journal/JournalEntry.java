package com.example.gratidude_journal.journal;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
class JournalEntry {
    @Id
    @GeneratedValue
    Long journalEntryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_id", nullable = false)
    private Journal journal;

    private enum WellBeing {
        AWFUL,
        BAD,
        MILDLY_BAD,
        NEUTRAL,
        MILDLY_GOOD,
        GOOD,
        FANTASTIC
    }

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    WellBeing wellBeing;

    String gratefullForToday;
    String gratefullForTodayDescription;
    String gratefullForInLife;
    String gratefullForInLifeDescription;

    public JournalEntry() {
        this.date = LocalDate.now();
    }

    public JournalEntry(Journal journal) {
        this.journal = journal;
        this.date = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }

    public WellBeing getWellBeing() {
        return this.wellBeing;
    }

    public void setWellBeing(WellBeing wellBeing) {
        this.wellBeing = wellBeing;
    }

    public String getGratefullForToday() {
        return this.gratefullForToday;
    }

    public void setGratefullForToday(String gratefullForToday) {
        this.gratefullForToday = gratefullForToday;
    }

    public String getGratefullForTodayDescription() {
        return this.gratefullForTodayDescription;
    }

    public void setGratefullForTodayDescription(String gratefullForTodayDescription) {
        this.gratefullForTodayDescription = gratefullForTodayDescription;
    }

    public String getGratefullForInLife() {
        return this.gratefullForInLife;
    }

    public void setGratefullForInLife(String gratefullForInLife) {
        this.gratefullForInLife = gratefullForInLife;
    }

    public String getGratefullForInLifeDescription() {
        return this.gratefullForInLifeDescription;
    }

    public void setGratefullForInLifeDescription(String gratefullForInLifeDescription) {
        this.gratefullForInLifeDescription = gratefullForInLifeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof JournalEntry))
            return false;
        JournalEntry journalEntry = (JournalEntry) o;
        return Objects.equals(this.journalEntryId, journalEntry.journalEntryId)
                && Objects.equals(this.journal, journalEntry.journal)
                && Objects.equals(this.wellBeing, journalEntry.wellBeing)
                && Objects.equals(this.gratefullForToday, journalEntry.gratefullForToday)
                && Objects.equals(this.gratefullForTodayDescription, journalEntry.gratefullForTodayDescription)
                && Objects.equals(this.gratefullForInLife, journalEntry.gratefullForInLife)
                && Objects.equals(this.gratefullForInLifeDescription, journalEntry.gratefullForInLifeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.journalEntryId);
    }

    @Override
    public String toString() {
        return "JournalEntry, " + date.toString();
    }
}