package by.epam.tasks.second.DAO;

import by.epam.tasks.second.entity.Note;

import java.util.List;

public interface NoteDAO {

	boolean add(Note note);

	boolean remove(Note note);

	List<Note> getAll();

	Note toNote(String note);

	void saveAll(List<Note> noteList);
}