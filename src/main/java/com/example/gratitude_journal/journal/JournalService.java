package com.example.gratitude_journal.journal;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gratitude_journal.journal.entry.JournalEntry;
import com.example.gratitude_journal.journal.entry.JournalEntryDTO;
import com.example.gratitude_journal.journal.entry.JournalEntryRepository;
import com.example.gratitude_journal.journal.exception.EntryNotFoundException;
import com.example.gratitude_journal.journal.id_date_pair.IdDatePairDTO;
import com.example.gratitude_journal.user.User;
import com.example.gratitude_journal.user.UserService;

/**
 * Service providing operations on Journal entities and enforcing business
 * logic. Part of the service layer of the Journal-API.
 * 
 * @author Afeef Neiroukh
 */
@Service
public class JournalService {
    /**
     * {@link com.example.gratitude_journal.journal.entry.JournalEntryRepository}
     * object injected by Spring.
     */
    private final JournalEntryRepository entryRepository;

    /**
     * {@link com.example.gratitude_journal.user.UserService} object injected by
     * Spring.
     */
    private final UserService userService;

    /**
     * JournalService constructor.
     * 
     * @param entryRepository {@link com.example.gratitude_journal.journal.entry.JournalEntryRepository}
     *                        object injected by Spring.
     * @param userService     {@link com.example.gratitude_journal.user.UserService}
     */
    public JournalService(JournalEntryRepository entryRepository, UserService userService) {
        this.entryRepository = entryRepository;
        this.userService = userService;
    }

    /**
     * Get a Collection containing a
     * {@link com.example.gratitude_journal.journal.id_date_pair.IdDatePairDTO}
     * object for every
     * {@link com.example.gratitude_journal.journal.entry.JournalEntry} object in
     * the journal.
     * 
     * @param userName The user name of the user to perform the request on.
     * @return A {@code Collection<IdDatePairDTO>} object containing a
     *         {@link com.example.gratitude_journal.journal.id_date_pair.IdDatePairDTO}
     *         object for every
     *         {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     *         object in the journal.
     */
    public Collection<IdDatePairDTO> getEntries(String userName) {
        User user = userService.getUserByUserName(userName);

        return entryRepository.getEntriesByJournalId(user.getJournal().getJournalId());
    }

    /**
     * Add a new entry to the journal of the user matching the provided user name.
     * 
     * @param userName The user name of the user to add an entry to its journal.
     * @param newEntry The new entry to add.
     * @return The {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     *         object added.
     */
    public JournalEntry addEntry(String userName, JournalEntry newEntry) {
        User user = userService.getUserByUserName(userName);

        user.getJournal().addEntry(newEntry);
        userService.saveUser(userName);

        return newEntry;
    }

    /**
     * Retrieve a {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     * object by its id.
     * 
     * @param journalEntryId The id of the
     *                       {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     *                       object to retrieve.
     * @return The {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     *         object matching the provided id.
     * @throws EntryNotFoundException No journal entry matching the provided id was
     *                                found.
     */
    public JournalEntry getEntry(Long journalEntryId) {
        Optional<JournalEntry> entry = entryRepository.findById(journalEntryId);

        if (!entry.isPresent())
            throw new EntryNotFoundException(journalEntryId);

        return entry.get();
    }

    /**
     * Update a {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     * object. Object is identified by the provided id and updated using the fields
     * of the provided
     * {@link com.example.gratitude_journal.journal.entry.JournalEntryDTO} object.
     * 
     * @param journalEntryId The id of the
     *                       {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     *                       object to update.
     * @param updatedEntry   The DTO containing the new values.
     * @return The updated
     *         {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     *         object matching the provided id.
     * @throws EntryNotFoundException No entry matching the provided id was found.
     */
    public JournalEntry updateEntry(Long journalEntryId, JournalEntryDTO updatedEntry) {
        return entryRepository.findById(journalEntryId)
                .map(foundEntry -> {
                    foundEntry.setWellBeing(updatedEntry.wellBeing());
                    foundEntry.setGratefulForToday(updatedEntry.gratefulForToday());
                    foundEntry.setGratefulForTodayDescription(updatedEntry.gratefulForTodayDescription());
                    foundEntry.setGratefulForInLife(updatedEntry.gratefulForInLife());
                    foundEntry.setGratefulForInLifeDescription(updatedEntry.gratefulForInLifeDescription());
                    return entryRepository.save(foundEntry);
                })
                .orElseThrow(() -> new EntryNotFoundException(journalEntryId));
    }

    /**
     * Delete a {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     * object. Object is identified by the provided id.
     * 
     * @param journalEntryId The id of the
     *                       {@link com.example.gratitude_journal.journal.entry.JournalEntry}
     *                       object to delete.
     * @throws EntryNotFoundException No entry matching the provided id was found.
     */
    public void deleteEntry(Long journalEntryId) {
        entryRepository.findById(journalEntryId)
                .ifPresentOrElse(entry -> entryRepository.delete(entry),
                        () -> {
                            throw new EntryNotFoundException(journalEntryId);
                        });
    }
}