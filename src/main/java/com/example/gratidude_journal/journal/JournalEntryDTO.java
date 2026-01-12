package com.example.gratidude_journal.journal;

public record JournalEntryDTO(JournalEntry.WellBeing wellBeing,
        String gratefullForToday,
        String gratefullForTodayDescription,
        String gratefullForInLife,
        String gratefullForInLifeDescription) {
}
