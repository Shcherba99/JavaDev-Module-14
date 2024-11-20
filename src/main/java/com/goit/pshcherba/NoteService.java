package com.goit.pshcherba;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class for managing notes.
 * Provides basic CRUD operations for working with notes.
 */
@Service
public class NoteService {
    private final Map<Long, Note> noteMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);


    /**
     * Retrieves a list of all notes.
     *
     * @return a list containing all notes in the service.
     */
    public List<Note> listAll() {
        return new ArrayList<>(noteMap.values());
    }


    /**
     * Adds a new note to the service.
     * Generates a unique ID for the note and stores it in the map.
     *
     * @param note the note to add.
     * @return the added note with its generated ID.
     */
    public Note add(Note note) {
        long id = idGenerator.getAndIncrement();
        note.setId(id);
        noteMap.put(id, note);
        return note;
    }


    /**
     * Deletes a note by its ID.
     * If no note with the given ID exists, a {@link NoSuchElementException} is thrown.
     *
     * @param id the ID of the note to delete.
     * @throws NoSuchElementException if no note with the given ID is found.
     */
    public void deleteById(long id) {
        if (noteMap.remove(id) == null) {
            throw new NoSuchElementException("Delete note error. Note by id = " + id + " not found");
        }
    }


    /**
     * Updates an existing note.
     * If the note with the given ID does not exist, a {@link NoSuchElementException} is thrown.
     *
     * @param note the note containing updated data.
     * @throws NoSuchElementException if no note with the given ID is found.
     */
    public void update(Note note) {
        if (!noteMap.containsKey(note.getId())) {
            throw new NoSuchElementException("Update note error. Note by id = " + note.getId() + " not found");
        }
        noteMap.put(note.getId(), note);
    }


    /**
     * Retrieves a note by its ID.
     * If no note with the given ID exists, a {@link NoSuchElementException} is thrown.
     *
     * @param id the ID of the note to retrieve.
     * @return the note with the specified ID.
     * @throws NoSuchElementException if no note with the given ID is found.
     */
    public Note getById(long id) {
        Note note = noteMap.get(id);
        if (note == null) {
            throw new NoSuchElementException("Get note error. Note by id = " + id + " not found");
        }
        return note;
    }
}
