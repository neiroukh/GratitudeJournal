package com.example.gratidude_journal.journal.entry;

public record JournalEntryDTO(JournalEntry.WellBeing wellBeing,
                String gratefullForToday,
                String gratefullForTodayDescription,
                String gratefullForInLife,
                String gratefullForInLifeDescription) {

        public static boolean compareToEntry(JournalEntryDTO entryDTO, JournalEntry entry) {
                if (entryDTO == null && entry == null)
                        return true;
                if (entryDTO == null || entry == null)
                        return false;
                return entryDTO.wellBeing.equals(entry.getWellBeing())
                                && entryDTO.gratefullForToday().equals(entry.getGratefullForToday())
                                && entryDTO.gratefullForTodayDescription()
                                                .equals(entry.getGratefullForTodayDescription())
                                && entryDTO.gratefullForInLife().equals(entry.getGratefullForInLife())
                                && entryDTO.gratefullForInLifeDescription()
                                                .equals(entry.getGratefullForInLifeDescription());
        }
}