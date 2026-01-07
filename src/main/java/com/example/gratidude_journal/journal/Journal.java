package com.example.gratidude_journal.journal;

import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Journal {
    private @Id @GeneratedValue Long journalId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "journal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JournalEntry> journalEntries = new HashSet<JournalEntry>();

    public Journal() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Journal))
            return false;
        Journal journal = (Journal) o;
        return Objects.equals(this.journalId, journal.journalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.journalId);
    }

    @Override
    public String toString() {
        return "Journal";
    }
}