package com.goit.pshcherba;


import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;


/**
 * Runner class for demonstrating operations with {@link NoteService}.
 * This class implements {@link ApplicationRunner} and executes CRUD operations on startup.
 * <p>
 * It creates sample notes, performs add, delete, update, and retrieval operations,
 * and prints results to the console.
 * </p>
 */
@Controller
@AllArgsConstructor
public class Runner implements ApplicationRunner {
    private NoteService noteService;


    /**
     * Executes a series of CRUD operations using {@link NoteService}.
     * <p>
     * Operations performed:
     * <ul>
     *     <li>Create and add notes</li>
     *     <li>Delete a note by ID</li>
     *     <li>List all notes</li>
     *     <li>Update a note</li>
     *     <li>Retrieve a note by ID</li>
     * </ul>
     * </p>
     *
     * @param args the application arguments (not used in this implementation)
     */
    @Override
    public void run(ApplicationArguments args) {
        // Notes
        Note firstNote = new Note("Task 14", "Spring Boot - Core.");
        Note secondNote = new Note("Task 15", "Spring Boot - MVC.");
        Note thirdNote = new Note("Task 16", "Spring Boot - Data.");

        // Add note
        System.out.println();
        System.out.println("Added note: " + noteService.add(firstNote));
        System.out.println("Added note: " + noteService.add(secondNote));
        System.out.println("Added note: " + noteService.add(thirdNote));
        System.out.println();

        // Delete note
        noteService.deleteById(3);

        // List all
        System.out.println("All notes after deleting the note by id = " + thirdNote.getId() + ":");
        noteService.listAll().forEach(System.out::println);
        System.out.println();

        // Update note
        secondNote.setTitle("Task 12");
        secondNote.setContent("ORM. Hibernate.");
        noteService.update(secondNote);

        // Get note by id
        System.out.println("Note by id = " + secondNote.getId() + " after updating: " + noteService.getById(2));

    }
}
