package com.example.gratidude_journal.journal.entry;

public record JournalEntryDTO(JournalEntry.WellBeing wellBeing,
        String gratefullForToday,
        String gratefullForTodayDescription,
        String gratefullForInLife,
        String gratefullForInLifeDescription) {
}
