package com.example.gratitude_journal.journal.entry;

import com.example.gratitude_journal.journal.JournalController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class JournalEntryModelAssembler
        implements RepresentationModelAssembler<JournalEntry, EntityModel<JournalEntry>> {

    @Override
    public EntityModel<JournalEntry> toModel(JournalEntry journalEntry) {
        return EntityModel.of(journalEntry,
                linkTo(methodOn(JournalController.class).getEntry(journalEntry.getJournalEntryId())).withSelfRel(),
                linkTo(methodOn(JournalController.class).deleteEntry(journalEntry.getJournalEntryId()))
                        .withRel("delete"),
                linkTo(methodOn(JournalController.class).updateEntry(journalEntry.getJournalEntryId(), null))
                        .withRel("update"));
    }
}